package com.siping.service.businesspartner.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.DateUtils;

import com.siping.service.common.BillsRepository;
import com.siping.smartone.businesspartner.request.PartnerGroupManagementRequest;
import com.siping.smartone.businesspartner.request.PartnerGroupRequest;
import com.siping.smartone.businesspartner.response.PartnerGroupResponse;
import com.siping.smartone.material.request.GetPartnerGroupRequest;

@Repository
public class PartnerGroupRestRepository extends BillsRepository {

    private Integer getPartnerGroupByGroupName(String groupName, Integer partnerType) {
        int select = jdbcAccessContext.findInteger("PARTNERGROUP.SQL_SELECT_BYGROUPNAME", new Object[] { groupName, partnerType });
        return select;
    }

    public Boolean add(PartnerGroupRequest request) {
        if (getPartnerGroupByGroupName(request.getGroupName(), request.getPartnerType()) > 0) {
            throw new BusinessProcessException("组名不能重复");
        }
        Object[] addParams = new Object[] { request.getGroupName(), request.getDescription(), request.getPartnerType(), request.getCreateBy() };
        int add = jdbcAccessContext.execute("PARTNERGROUP.SQL_INSERT_PARTNERGROUP", addParams);
        if (-1 == add) {
            return Boolean.FALSE;
        }
        addOperationLog("客户组或者供应商组", "新增客户组或者供应商组:[" + request.getGroupName() + "]", null, request.getCreateBy() + "");
        return Boolean.TRUE;
    }

    public Boolean edit(PartnerGroupRequest request) {
        if (getPartnerGroupByGroupName(request.getGroupName(), request.getPartnerType()) > 0) {
            if (getPartnerGroupByGroupName(request.getGroupName(), request.getPartnerType()) > 0) {// 判是否是当前edit对象
                PartnerGroupResponse response = jdbcAccessContext.findUniqueResult("PARTNERGROUP.SQL_SELECT_BY_PARTNER_GROUP_NAME", new RowMapper<PartnerGroupResponse>() {
                    @Override
                    public PartnerGroupResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                        return buildPartnerGroupResponse(rs);
                    }
                }, new Object[] { request.getGroupName() });
                if (response.getId() != request.getId()) {
                    throw new BusinessProcessException("组名不能重复");
                }
            } else {
                throw new BusinessProcessException("组名不能重复");
            }
        }
        Object[] editParams = new Object[] { request.getGroupName(), request.getDescription(), request.getUpdateBy(), request.getId() };
        int edit = jdbcAccessContext.execute("PARTNERGROUP.SQL_UPDATE_PARTNERGROUP", editParams);
        if (-1 == edit) {
            return Boolean.FALSE;
        }
        addOperationLog("客户组或者供应商组", "更新客户组或者供应商组:[" + request.getGroupName() + "]", null, request.getCreateBy() + "");
        return Boolean.TRUE;
    }

    public Boolean deletePartnerGroupById(Integer id, Integer loginId) {
        Object[] deleteParams = new Object[] { loginId, id };
        int delete = jdbcAccessContext.execute("PARTNERGROUP.SQL_DELETE_PARTNERGROUP", deleteParams);
        if (-1 == delete) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public List<PartnerGroupResponse> getAllPartnerGroup() {
        List<PartnerGroupResponse> response = jdbcAccessContext.find("PARTNERGROUP.SQL_SELECT_PARTNERGROUP", new RowMapper<PartnerGroupResponse>() {
            @Override
            public PartnerGroupResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildPartnerGroupResponse(rs);
            }
        });
        return response;
    }

    private PartnerGroupResponse buildPartnerGroupResponse(ResultSet rs) throws SQLException {
        PartnerGroupResponse p = new PartnerGroupResponse();
        p.setId(rs.getInt("id"));
        p.setGroupName(rs.getString("groupName"));
        p.setDescription(rs.getString("description"));
        p.setPartnerType(rs.getInt("partnerType"));
        p.setCreateDate(DateUtils.date(rs.getDate("createDate"), rs.getTime("createDate")));
        p.setCreateBy(rs.getInt("createBy"));
        p.setUpdateDate(DateUtils.date(rs.getDate("updateDate"), rs.getTime("updateDate")));
        p.setUpdateBy(rs.getInt("updateBy"));
        return p;
    }

    public PagingResponse<PartnerGroupResponse> getList(GetPartnerGroupRequest request) {
        long begin = (request.getPageNo() - 1) * request.getPageSize();
        PagingResponse<PartnerGroupResponse> pagingResponse = new PagingResponse<PartnerGroupResponse>();
        Integer totalRecords = jdbcAccessContext.findInteger("PARTNERGROUP.SQL_GET_TOTAL_RECORDS", new Object[] { request.getPartnerType(), begin, request.getPageSize() });
        List<PartnerGroupResponse> response = jdbcAccessContext.find("PARTNERGROUP.SQL_GET_PARTNER_GROUP_BYPAGE", new RowMapper<PartnerGroupResponse>() {
            @Override
            public PartnerGroupResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildPartnerGroupResponse(rs);
            }
        }, new Object[] { request.getPartnerType(), begin, request.getPageSize() });
        pagingResponse.setRecords(response);
        pagingResponse.setTotalRecords(totalRecords);
        return pagingResponse;
    }

    public List<PartnerGroupResponse> getList(Integer partnerType) {
        List<PartnerGroupResponse> response = jdbcAccessContext.find("PARTNERGROUP.SQL_SELECT_PARTNERGROUP_BYTYPE", new RowMapper<PartnerGroupResponse>() {
            @Override
            public PartnerGroupResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildPartnerGroupResponse(rs);
            }
        }, new Object[] { partnerType });
        return response;
    }

    public PartnerGroupResponse get(Integer id) {
        PartnerGroupResponse response = jdbcAccessContext.findUniqueResult("PARTNERGROUP.SQL_SELECT_PARTNERGROUP_BYID", new RowMapper<PartnerGroupResponse>() {
            @Override
            public PartnerGroupResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildPartnerGroupResponse(rs);
            }
        }, new Object[] { id });
        return response;
    }

    @SuppressWarnings("null")
    public Boolean management(PartnerGroupManagementRequest request) {
        List<Object[]> params = null;
        for (Integer partnerId : request.getPartnerIds()) {
            Object[] obj = new Object[] { request.getPartnerGroupId(), partnerId };
            params.add(obj);
        }
        int[] adds = jdbcAccessContext.batchExecute("PARTNERGROUP.SQL_MANAGEMENT_PARTNERGROUP", params);
        if (Arrays.asList(adds).contains(-1)) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }

    }

    public Boolean delete(Integer id, Integer updateBy) {
        // 判该partnergroup是否被引用
        if (checkPartnerGroupUsedInfo(id) == 1) {
            throw new BusinessProcessException("PartnerGroup‘" + get(id).getGroupName() + "’已经被引用，无法删除");
        }
        int delete = jdbcAccessContext.execute("PARTNERGROUP.SQL_DELETE_PARTNERGROUP", new Object[] { updateBy, id });
        if (-1 == delete) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private Integer checkPartnerGroupUsedInfo(Integer id) {
        Integer business_partner_tb_used = jdbcAccessContext.findInteger("PARTNERGROUP.SQL_GET_PARTNERGROUPUSED1_BY_ID", new Object[] { id });
        Integer bulk_partner_errors_tb_used = jdbcAccessContext.findInteger("PARTNERGROUP.SQL_GET_PARTNERGROUPUSED2_BY_ID", new Object[] { id });
        Integer partnerGroupUsed = business_partner_tb_used.intValue() + bulk_partner_errors_tb_used.intValue();
        return partnerGroupUsed;
    }
}
