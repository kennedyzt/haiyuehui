package com.siping.service.businesspartner.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.JDBCAccessContext;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.DateUtils;
import org.stroma.framework.core.util.StringUtils;

import com.siping.smartone.businesspartner.request.BusinessPartnerBatchAddErrorRequest;
import com.siping.smartone.businesspartner.request.BusinessPartnerBatchAddRequest;
import com.siping.smartone.businesspartner.request.BusinessPartnerBatchAddSuccessRequest;
import com.siping.smartone.businesspartner.request.BusinessPartnerBatchDeleteRequest;
import com.siping.smartone.businesspartner.request.BusinessPartnerExportParamRequest;
import com.siping.smartone.businesspartner.request.BusinessPartnerRequest;
import com.siping.smartone.businesspartner.response.BusinessPartnerQueryParam;
import com.siping.smartone.businesspartner.response.BusinessPartnerResponse;
import com.siping.smartone.businesspartner.response.UploadBusinessPartnerResponse;

@Repository
public class BusinessPartnerRestRepository {
    @Autowired
    private JDBCAccessContext jdbcAccessContext;

    private Integer getBusinessPartnerByPartnerCode(String partnerCode, Integer partnerType) {
        int select = jdbcAccessContext.findInteger("BUSINESSPARTNER.SQL_SELECT_BYPARTNERCODE", new Object[] { partnerCode, partnerType });
        return select;
    }

    public Boolean addBusinessPartner(BusinessPartnerRequest request) {
        if (getBusinessPartnerByPartnerCode(request.getPartnerCode(), request.getPartnerType()) > 0) {
            throw new BusinessProcessException("业务伙伴编码重复");
        }
        try {
            Object[] addParams = new Object[] { request.getPartnerCode(), request.getPartnerType(), request.getPartnerName(), request.getForeignName(), request.getPartnerGroup(), request.getPhone(),
                    request.getMobilephone(), request.getFax(), request.getEmail(), request.getWebsite(), request.getDescription(), request.getIsEnable(), request.getAddressEn(),
                    request.getBusinessType(), request.getCountryRegionId(), request.getPostalCode(), request.getContactsLastName(), request.getContactsFirstName(), request.getContactsGender(),
                    request.getContactsPhone(), request.getContactsMobilephone(), request.getContactsFax(), request.getContactsEmail(), request.getContactsDescription(), request.getAddress(),
                    request.getCreateBy() };
            int add = jdbcAccessContext.execute("BUSINESSPARTNER.SQL_INSERT_BUSINESSPARTNER", addParams);
            if (-1 == add) {
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessProcessException("数据库异常");
        }
        return Boolean.TRUE;
    }

    public Boolean editBusinessPartner(BusinessPartnerRequest request) {
        if (getBusinessPartnerByPartnerCode(request.getPartnerCode(), request.getPartnerType()) > 0) {
            throw new BusinessProcessException("业务伙伴编码重复");
        }
        Object[] editParams = new Object[] { request.getPartnerType(), request.getPartnerName(), request.getForeignName(), request.getPartnerGroup(), request.getPhone(), request.getMobilephone(),
                request.getFax(), request.getEmail(), request.getWebsite(), request.getDescription(), request.getIsEnable(), request.getAddressEn(), request.getBusinessType(),
                request.getCountryRegionId(), request.getPostalCode(), request.getContactsLastName(), request.getContactsFirstName(), request.getContactsGender(), request.getContactsPhone(),
                request.getContactsMobilephone(), request.getContactsFax(), request.getContactsEmail(), request.getContactsDescription(), request.getAddress(), request.getUpdateBy(),
                request.getId() };
        int edit = jdbcAccessContext.execute("BUSINESSPARTNER.SQL_UPDATE_BUSINESSPARTNER", editParams);
        if (-1 == edit) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean deleteBusinessPartnerById(BusinessPartnerBatchDeleteRequest request) {
        List<Integer> ids = request.getIds();
        List<Object[]> deleteParams = new LinkedList<Object[]>();
        for (Integer i : ids) {
            // 判断是否被使用
            checkIsBeingUsed(i);
            Object[] obj = new Object[] { i };
            deleteParams.add(obj);
        }
        int[] delete = jdbcAccessContext.batchExecute("BUSINESSPARTNER.SQL_DELETE_BUSINESSPARTNER", deleteParams);
        if (Arrays.asList(delete).contains(-1)) {
            throw new BusinessProcessException("删除失败！");
        }
        return Boolean.TRUE;
    }

    private void checkIsBeingUsed(Integer id) {
        Integer count = jdbcAccessContext.findInteger("BUSINESSPARTNER.SQL_CHECK_PARTNERID_ISUSED", new Object[] { id });
        if (count == 1) {
            throw new BusinessProcessException("在单据中被使用不能删除！");
        }
    }

    public PagingResponse<BusinessPartnerResponse> getAllBusinessPartner(BusinessPartnerQueryParam request) {
        PagingResponse<BusinessPartnerResponse> pagingResponse = new PagingResponse<BusinessPartnerResponse>();
        long begin = (request.getPageNo() - 1) * request.getPageSize();
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParams = new ArrayList<Object>();
        StringBuilder totalRecordsSql = new StringBuilder();
        List<BusinessPartnerResponse> response = jdbcAccessContext.findWithoutSqlManager(buildGetListSql(request, begin, queryParam, totalRecordsSql, totalParams),
            new RowMapper<BusinessPartnerResponse>() {
                @Override
                public BusinessPartnerResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                    return buildBusinessPartnerResponse(rs);
                }
            }, queryParam.toArray());
        int totalRecords = jdbcAccessContext.findIntegerWithoutSqlManager(totalRecordsSql.toString(), totalParams.toArray());
        pagingResponse.setRecords(response);
        pagingResponse.setTotalRecords(totalRecords);
        return pagingResponse;
    }

    private String buildGetListSql(BusinessPartnerQueryParam request, long begin, List<Object> queryParam, StringBuilder total, List<Object> totalParams) {
        Integer pageSize = request.getPageSize();
        Integer partnerType = request.getPartnerType();
        String keyWords = request.getKeywords();
        Integer partnerGroup = request.getPartnerGroup();
        Boolean isEnable = request.getIsEnable();
        total.append("SELECT count(*) from business_partner b where is_delete=false and partner_type=?");
        StringBuilder sql = new StringBuilder(
            "SELECT id id,partner_code partnerCode,partner_type partnerType,partner_name partnerName,foreign_name foreignName,partner_group partnerGroup,phone phone,mobilephone mobilephone,fax fax,email email,website website,description,is_enable isEnable,contacts_last_name contactsLastName,contacts_first_name contactsFirstName,contacts_gender contactsGender,contacts_phone contactsPhone,contacts_mobilephone contactsMobilephone,contacts_description contactsDescription,address address,create_date createDate,create_by createBy,update_date updateDate,update_by updateBy from business_partner b where b.is_delete=false and b.partner_type=?");
        queryParam.add(partnerType);
        if (StringUtils.hasText(keyWords)) {
            sql.append(" and (b.partner_code like CONCAT('%',?,'%') or b.partner_name like CONCAT('%',?,'%'))");
            total.append(" and (b.partner_code like CONCAT('%',?,'%') or b.partner_name like CONCAT('%',?,'%'))");
            queryParam.add(keyWords);
            queryParam.add(keyWords);
        }
        if (null != partnerGroup) {
            sql.append(" and b.partner_group=? ");
            total.append(" and b.partner_group=? ");
            queryParam.add(partnerGroup);
        }
        if (null != isEnable) {
            sql.append(" and b.is_enable=? ");
            total.append(" and b.is_enable=? ");
            queryParam.add(isEnable);
        }
        totalParams.addAll(queryParam);
        queryParam.add(begin);
        queryParam.add(pageSize);
        sql.append(" order by b.create_by desc");
        sql.append(" limit ?,? ");
        return sql.toString();
    }

    private static BusinessPartnerResponse buildBusinessPartnerResponse(final ResultSet rs) throws SQLException {
        BusinessPartnerResponse b = new BusinessPartnerResponse();
        b.setId(rs.getInt("id"));
        b.setPartnerCode(rs.getString("partnerCode"));
        b.setPartnerType(rs.getInt("partnerType"));
        b.setPartnerName(rs.getString("partnerName"));
        b.setForeignName(rs.getString("foreignName"));
        b.setPartnerGroup(rs.getInt("partnerGroup"));
        try {
            b.setPartnerGroupName(rs.getString("groupName"));
        } catch (Exception e) {
        }
        b.setPhone(rs.getString("phone"));
        b.setMobilephone(rs.getString("mobilephone"));
        b.setFax(rs.getString("fax"));
        b.setEmail(rs.getString("email"));
        b.setWebsite(rs.getString("website"));
        b.setDescription(rs.getString("description"));
        b.setIsEnable(rs.getBoolean("isEnable"));
        try {
            b.setAddressEn(rs.getString("addressEn"));
        } catch (Exception e) {
        }
        try {
            b.setBusinessType(rs.getInt("businessType"));
        } catch (Exception e) {
        }
        try {
            b.setCountryRegionId(rs.getInt("countryRegionId"));
        } catch (Exception e) {
        }
        try {
            b.setCountryRegionName(rs.getString("countryRegionName"));
        } catch (Exception e) {
        }
        try {
            b.setPostalCode(rs.getString("postalCode"));
        } catch (Exception e) {
        }
        b.setContactsLastName(rs.getString("contactsLastName"));
        b.setContactsFirstName(rs.getString("contactsFirstName"));
        b.setContactsGender(rs.getInt("contactsGender"));
        b.setContactsPhone(rs.getString("contactsPhone"));
        b.setContactsMobilephone(rs.getString("contactsMobilephone"));
        try {
            b.setContactsFax(rs.getString("contactsFax"));
        } catch (Exception e) {
        }
        try {
            b.setContactsEmail(rs.getString("contactsEmail"));
        } catch (Exception e) {
        }
        try {
            b.setPartnerGroupName(rs.getString("groupName"));
        } catch (Exception e) {
        }
        b.setContactsDescription(rs.getString("contactsDescription"));
        b.setAddress(rs.getString("address"));
        b.setUpdateBy(rs.getInt("updateBy"));
        b.setUpdateDate(DateUtils.date(rs.getDate("updateDate"), rs.getTime("updateDate")));
        b.setCreateBy(rs.getInt("createBy"));
        b.setUpdateDate(DateUtils.date(rs.getDate("createDate"), rs.getTime("createDate")));
        return b;
    }

    public UploadBusinessPartnerResponse batchAdd(BusinessPartnerBatchAddRequest request) {
        // 准备数据
        UploadBusinessPartnerResponse response = new UploadBusinessPartnerResponse();
        long transactionId = System.currentTimeMillis(); // 作为transaction_id事务id
        Integer createdBy = request.getCreatedBy();
        List<BusinessPartnerBatchAddSuccessRequest> successBusinessPartners = request.getBusinessPartners();
        List<Object[]> needAdd = new LinkedList<Object[]>(); // 保存需要添加的业务伙伴
        List<String> needAddPartnerCodes = new LinkedList<String>(); // 保存需要添加的编码
        List<BusinessPartnerBatchAddErrorRequest> errorBusinessPartners = request.getErrors(); // 数据格式有误的信息
        List<String> partnerCodeReponses = jdbcAccessContext.find("BUSINESSPARTNER.SQL_SELECT_PARTNERCODE", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int arg1) throws SQLException {
                return rs.getString("partnerCode");
            }
        }); // 数据库中已有的编码
        if (0 < successBusinessPartners.size()) {
            for (BusinessPartnerBatchAddSuccessRequest b : successBusinessPartners) {
                build(transactionId, createdBy, needAdd, needAddPartnerCodes, errorBusinessPartners, partnerCodeReponses, b);
            }
            request.setErrors(errorBusinessPartners); // 错误的信息放回去
            if (0 != needAdd.size()) {
                int[] add = jdbcAccessContext.batchExecute("BUSINESSPARTNER.SQL_BATCH_INSERT_BUSINESSPARTNER", needAdd); // 保存成功信息到business_partner
                if (Arrays.asList(add).contains(-1)) {
                    throw new BusinessProcessException("批量添加业务伙伴时失败");
                }
            }
        }
        int addError = jdbcAccessContext.execute("BULKPARTNERTRANSACTION.SQL_INSERT_BULKPARTNERTRANSACTION", new Object[] { transactionId, needAdd.size(), errorBusinessPartners.size(), createdBy });
        if (-1 == addError) {
            throw new BusinessProcessException("添加错误信息失败");
        }
        if (0 < errorBusinessPartners.size()) {
            List<Object[]> partnerParams = new ArrayList<Object[]>();
            for (BusinessPartnerBatchAddErrorRequest b : errorBusinessPartners) {
                partnerParams.add(new Object[] { b.getPartnerCode(), b.getPartnerType(), b.getPartnerName(), b.getForeignName(), b.getPartnerGroup(), b.getPhone(), b.getMobilephone(), b.getFax(),
                        b.getEmail(), b.getWebsite(), b.getBankAccount(), b.getContactsLastName(), b.getContactsFirstName(), b.getContactsGender(), b.getContactsPhone(), b.getContactsMobilephone(),
                        b.getContactsDesription(), b.getAddress(), transactionId, b.getErrorMessage(), createdBy });
            }
            int[] addPartnerErrors = jdbcAccessContext.batchExecute("BULKPARTNERERRORS.SQL_BATCH_INSERT_BULKPARTNERERRORS", partnerParams);
            if (Arrays.asList(addPartnerErrors).contains(-1)) {
                throw new BusinessProcessException("批量添加失败信息时失败");
            }
        }
        response.setTransactionId(transactionId);
        response.setFailedNum(errorBusinessPartners.size());
        response.setSuccessNum(needAdd.size());
        return response;
    }

    private void build(long transactionId, Integer createdBy, List<Object[]> needAdd, List<String> needAddPartnerCodes, List<BusinessPartnerBatchAddErrorRequest> errorBusinessPartners,
                       List<String> partnerCodeReponses, BusinessPartnerBatchAddSuccessRequest b) {
        if (partnerCodeReponses.contains(b.getPartnerCode())) { // 验证编码是否重复
            BusinessPartnerBatchAddErrorRequest errorBusinessPartner = new BusinessPartnerBatchAddErrorRequest();
            errorBusinessPartner.setPartnerCode(b.getPartnerCode());
            errorBusinessPartner.setPartnerType(b.getPartnerType());
            errorBusinessPartner.setPartnerName(b.getPartnerName());
            errorBusinessPartner.setForeignName(b.getForeignName());
            errorBusinessPartner.setPartnerGroup(b.getPartnerGroup());
            errorBusinessPartner.setPhone(b.getPhone());
            errorBusinessPartner.setMobilephone(b.getPhone());
            errorBusinessPartner.setFax(b.getFax());
            errorBusinessPartner.setEmail(b.getEmail());
            errorBusinessPartner.setWebsite(b.getWebsite());
            errorBusinessPartner.setBankAccount(b.getBankAccount());
            errorBusinessPartner.setContactsLastName(b.getContactsLastName());
            errorBusinessPartner.setContactsFirstName(b.getContactsFirstName());
            errorBusinessPartner.setContactsGender(b.getContactsGender());
            errorBusinessPartner.setContactsPhone(b.getContactsPhone());
            errorBusinessPartner.setContactsMobilephone(b.getContactsMobilephone());
            errorBusinessPartner.setContactsDesription(b.getContactsDesription());
            errorBusinessPartner.setAddress(b.getAddress());
            errorBusinessPartners.add(errorBusinessPartner);
        } else {
            needAddPartnerCodes.add(b.getPartnerCode());
            needAdd.add(new Object[] { b.getPartnerCode(), b.getPartnerType(), b.getPartnerName(), b.getForeignName(), b.getPartnerGroup(), b.getPhone(), b.getMobilephone(), b.getFax(), b.getEmail(),
                    b.getWebsite(), b.getBankAccount(), b.getContactsLastName(), b.getContactsFirstName(), b.getContactsGender(), b.getContactsPhone(), b.getContactsMobilephone(),
                    b.getContactsDesription(), b.getAddress(), transactionId, createdBy });
        }
    }

    public List<BusinessPartnerResponse> export(BusinessPartnerExportParamRequest request) {
        StringBuilder sql = new StringBuilder(
            "SELECT id id,partner_code partnerCode,partner_type partnerType,partner_name partnerName,foreign_name foreignName,partner_group partnerGroup,phone phone,mobilephone mobilephone,fax fax,email email,website website,description,contacts_last_name contactsLastName,contacts_first_name contactsFirstName,contacts_gender contactsGender,contacts_phone contactsPhone,contacts_mobilephone contactsMobilephone,contacts_description contactsDescription,address address,create_date createDate,create_by createBy,update_date updateDate,update_by updateBy from business_partner where is_enable=true");
        List<BusinessPartnerResponse> response = jdbcAccessContext.findWithoutSqlManager(sql.toString(), new RowMapper<BusinessPartnerResponse>() {
            @Override
            public BusinessPartnerResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildBusinessPartnerResponse(rs);
            }
        }, new Object[] {});
        return response;
    }

    public BusinessPartnerResponse get(Integer id) {
        BusinessPartnerResponse response = jdbcAccessContext.findUniqueResult("BUSINESSPARTNER.SQL_SELECT_BUSINESSPARTNER_BYID", new RowMapper<BusinessPartnerResponse>() {
            @Override
            public BusinessPartnerResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildBusinessPartnerResponse(rs);
            }
        }, new Object[] { id });
        return response;
    }

    public List<BusinessPartnerResponse> getList(String key, Integer type) {
        List<BusinessPartnerResponse> response = jdbcAccessContext.find("BUSINESSPARTNER.GET_BUSINESSPARTNER_BYKEY", new RowMapper<BusinessPartnerResponse>() {
            @Override
            public BusinessPartnerResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildBusinessPartnerResponse(rs);
            }
        }, new Object[] { type, key, key });
        return response;
    }
}
