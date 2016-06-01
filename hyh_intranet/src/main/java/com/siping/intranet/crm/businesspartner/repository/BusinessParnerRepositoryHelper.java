package com.siping.intranet.crm.businesspartner.repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.stroma.framework.core.exception.BusinessProcessException;

import com.siping.smartone.businesspartner.request.BusinessPartnerBaseAttributeTile;
import com.siping.smartone.businesspartner.request.BusinessPartnerBatchAddErrorRequest;
import com.siping.smartone.businesspartner.request.BusinessPartnerBatchAddSuccessRequest;
import com.siping.smartone.businesspartner.response.ValidateMessage;

public class BusinessParnerRepositoryHelper {
    private static final int MAX_ERROR_COUNT = 20;

    public static void load(InputStream inputStream, List<BusinessPartnerBatchAddSuccessRequest> successBusinessPartners, List<BusinessPartnerBatchAddErrorRequest> errorBusinessPartners) {
        Workbook workbook;
        Sheet businessPartnerInfoSheet;
        int rows;
        try {
            workbook = new XSSFWorkbook(inputStream);
            businessPartnerInfoSheet = workbook.getSheetAt(0);
            rows = businessPartnerInfoSheet.getLastRowNum() + 1;
        } catch (Exception e) {
            throw new BusinessProcessException("上传文件解析失败!");
        }
        validateTemplateStructure(businessPartnerInfoSheet, rows); // 验证是否为模板
        Map<Integer, Map<String, String>> records = new HashMap<Integer, Map<String, String>>(); // 存放所有表格信息
        Map<String, Integer> cellIndexes = new HashMap<String, Integer>(); // 存放格子下标
        Row title = businessPartnerInfoSheet.getRow(2);
        Row recordRow;
        for (int rowIndex = 3; rowIndex < rows; rowIndex++) {
            recordRow = businessPartnerInfoSheet.getRow(rowIndex);
            if (isBlankRow(recordRow, 0))
                continue;

            Map<String, String> record = new HashMap<String, String>();
            for (int i = 0; i < title.getLastCellNum(); i++) {
                record.put(getStringCellValue(title, i), getStringCellValue(recordRow, i));
            }
            records.put(rowIndex, record); // 将单条记录放入records
        }
        for (int i = 0; i < title.getLastCellNum(); i++) {
            cellIndexes.put(getStringCellValue(title, i), i);
        }
        validateRecord(records, successBusinessPartners, errorBusinessPartners, cellIndexes); // 验证记录信息合法性
    }

    private static void validateTemplateStructure(Sheet bulkSetupSheet, int rows) {
        if (rows <= 2)
            throw new BusinessProcessException("上传文件中至少包含一条业务伙伴信息");
        validateTemplateTitle(bulkSetupSheet);
    }

    private static void validateTemplateTitle(Sheet bulkSetupSheet) {
        Row title = bulkSetupSheet.getRow(2);
        for (BusinessPartnerBaseAttributeTile fixedTitle : BusinessPartnerBaseAttributeTile.values()) {
            if (!fixedTitle.getName().equals(getStringCellValue(title, fixedTitle.getIndex())))
                throw new BusinessProcessException("请使用指定的数据模板进行上传！");
        }
    }

    protected static boolean isBlankRow(Row basicInfoRow, int startIndex) {
        if (null == basicInfoRow)
            return true;
        for (int index = startIndex; index < basicInfoRow.getPhysicalNumberOfCells() - 1; index++) {
            if (!org.apache.commons.lang.StringUtils.isBlank(getStringCellValue(basicInfoRow, index)))
                return false;
        }
        return true;
    }

    protected static String getStringCellValue(Row row, int cellnum) {
        Cell cell = row.getCell(cellnum, Row.CREATE_NULL_AS_BLANK);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        return cell.getStringCellValue();
    }

    private static void validateRecord(Map<Integer, Map<String, String>> records, List<BusinessPartnerBatchAddSuccessRequest> successBusinessPartners,
                                       List<BusinessPartnerBatchAddErrorRequest> errorBusinessPartners, Map<String, Integer> cellIndexes) {
        ValidateMessage message = new ValidateMessage();
        for (Integer rowIndex : records.keySet()) {
            Map<String, String> record = records.get(rowIndex);
            BusinessPartnerBatchAddSuccessRequest businessPartnertBulkSetupRequest = new BusinessPartnerBatchAddSuccessRequest();
            businessPartnertBulkSetupRequest.setRowIndex(rowIndex);
            message = buildProduct(rowIndex, record, businessPartnertBulkSetupRequest, cellIndexes);
            if (!message.isPass)
                errorBusinessPartners.add(setErrorInfo(rowIndex, record, message, false));
            else
                addProduct(successBusinessPartners, businessPartnertBulkSetupRequest, rowIndex);
            if (errorBusinessPartners.size() > MAX_ERROR_COUNT)
                throw new BusinessProcessException("产品错误数量超过{" + MAX_ERROR_COUNT + "}条");
        }
        successBusinessPartners.removeAll(getErrorList(errorBusinessPartners));
    }

    private static ValidateMessage buildProduct(Integer rowIndex, Map<String, String> record, BusinessPartnerBatchAddSuccessRequest successRequest, Map<String, Integer> cellIndexes) {
        ValidateMessage message = new ValidateMessage();
        for (BusinessPartnerBaseAttributeTile title : BusinessPartnerBaseAttributeTile.values()) {
            message = BusinessPartnerBatchAddValidateHelper.basicValidate(rowIndex, title.getIndex(), record.get(title.getName()).trim());
            if (!message.isPass)
                return message;
        } // 验证数据合法性
        successRequest.setRowIndex(rowIndex);
        successRequest.setPartnerCode(record.get(BusinessPartnerBaseAttributeTile.PARTNER_CODE.getName()).trim());
        successRequest.setPartnerType(Integer.parseInt(record.get(BusinessPartnerBaseAttributeTile.PARTNER_TYPE.getName()).trim()));
        successRequest.setPartnerName(record.get(BusinessPartnerBaseAttributeTile.PARTNER_NAME.getName()).trim());
        successRequest.setForeignName(record.get(BusinessPartnerBaseAttributeTile.FOREIGN_NAME.getName()).trim());
        successRequest.setPartnerGroup(Integer.parseInt(record.get(BusinessPartnerBaseAttributeTile.PARTNER_GROUP.getName()).trim()));
        successRequest.setPhone(record.get(BusinessPartnerBaseAttributeTile.PHONE.getName()).trim());
        successRequest.setMobilephone(record.get(BusinessPartnerBaseAttributeTile.MOBILEPHONE.getName()).trim());
        successRequest.setFax(record.get(BusinessPartnerBaseAttributeTile.FAX.getName()).trim());
        successRequest.setEmail(record.get(BusinessPartnerBaseAttributeTile.EMAIL.getName()).trim());
        successRequest.setWebsite(record.get(BusinessPartnerBaseAttributeTile.WEBSITE.getName()).trim());
        successRequest.setBankAccount(record.get(BusinessPartnerBaseAttributeTile.BANK_ACCOUNT.getName()).trim());
        successRequest.setContactsFirstName(record.get(BusinessPartnerBaseAttributeTile.CONTACTS_FIRST_NAME.getName()).trim());
        successRequest.setContactsLastName(record.get(BusinessPartnerBaseAttributeTile.CONTACTS_LAST_NAME.getName()).trim());
        successRequest.setContactsGender(record.get(BusinessPartnerBaseAttributeTile.CONTACTS_GENDER.getName()).trim());
        successRequest.setContactsPhone(record.get(BusinessPartnerBaseAttributeTile.CONTACTS_PHONE.getName()).trim());
        successRequest.setContactsDesription(record.get(BusinessPartnerBaseAttributeTile.CONTACTS_DESCRIPTION.getName()).trim());
        successRequest.setAddress(record.get(BusinessPartnerBaseAttributeTile.ADDRESS.getName()).trim());
        return message;
    }

    public static BusinessPartnerBatchAddErrorRequest setErrorInfo(Integer rowIndex, Map<String, String> row, ValidateMessage message, boolean isAttributeError) {
        BusinessPartnerBatchAddErrorRequest errorRequest = new BusinessPartnerBatchAddErrorRequest();
        errorRequest.setErrorRow(message.rowIndex);
        errorRequest.setErrorCell(message.cellIndex);
        errorRequest.setErrorMessage(message.message);
        errorRequest.setPartnerCode(row.get(BusinessPartnerBaseAttributeTile.PARTNER_CODE.getName()).trim());
        errorRequest.setPartnerType(Integer.parseInt(row.get(BusinessPartnerBaseAttributeTile.PARTNER_TYPE.getName()).trim()));
        errorRequest.setPartnerName(row.get(BusinessPartnerBaseAttributeTile.PARTNER_NAME.getName()).trim());
        errorRequest.setForeignName(row.get(BusinessPartnerBaseAttributeTile.FOREIGN_NAME.getName()).trim());
        errorRequest.setPartnerGroup(Integer.parseInt(row.get(BusinessPartnerBaseAttributeTile.PARTNER_GROUP.getName()).trim()));
        errorRequest.setPhone(row.get(BusinessPartnerBaseAttributeTile.PHONE.getName()).trim());
        errorRequest.setMobilephone(row.get(BusinessPartnerBaseAttributeTile.MOBILEPHONE.getName()).trim());
        errorRequest.setFax(row.get(BusinessPartnerBaseAttributeTile.FAX.getName()).trim());
        errorRequest.setEmail(row.get(BusinessPartnerBaseAttributeTile.EMAIL.getName()).trim());
        errorRequest.setWebsite(row.get(BusinessPartnerBaseAttributeTile.WEBSITE.getName()).trim());
        errorRequest.setBankAccount(row.get(BusinessPartnerBaseAttributeTile.BANK_ACCOUNT.getName()).trim());
        errorRequest.setContactsFirstName(row.get(BusinessPartnerBaseAttributeTile.CONTACTS_FIRST_NAME.getName()).trim());
        errorRequest.setContactsLastName(row.get(BusinessPartnerBaseAttributeTile.CONTACTS_LAST_NAME.getName()).trim());
        errorRequest.setContactsGender(row.get(BusinessPartnerBaseAttributeTile.CONTACTS_GENDER.getName()).trim());
        errorRequest.setContactsPhone(row.get(BusinessPartnerBaseAttributeTile.CONTACTS_PHONE.getName()).trim());
        errorRequest.setContactsDesription(row.get(BusinessPartnerBaseAttributeTile.CONTACTS_DESCRIPTION.getName()).trim());
        errorRequest.setAddress(row.get(BusinessPartnerBaseAttributeTile.ADDRESS.getName()).trim());
        return errorRequest;
    }

    private static void addProduct(List<BusinessPartnerBatchAddSuccessRequest> successProducts, BusinessPartnerBatchAddSuccessRequest productRequest, Integer rowIndex) {
        successProducts.add(productRequest);
    }

    protected static List<BusinessPartnerBatchAddSuccessRequest> getErrorList(List<BusinessPartnerBatchAddErrorRequest> errorProducts) {
        List<BusinessPartnerBatchAddSuccessRequest> list = new ArrayList<BusinessPartnerBatchAddSuccessRequest>();
        for (BusinessPartnerBatchAddErrorRequest error : errorProducts) {
            // TODO 错误验证
            // list.add(error.toProductBulkSetupRequest());
            // 把error对象转换成bulk对象,但只有货号
        }
        return list;
    }

}
