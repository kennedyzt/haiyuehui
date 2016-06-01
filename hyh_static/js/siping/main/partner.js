$(function() {
    $("#customer_smt_add").click(function() {
        siping.ajax({
            method : 'post',
            url : '/businesspartner/add',
            async : true,
            data : {
                partnerCode : $("#partnerCode").val(),
                partnerType : $("#partnerType").val(),
                partnerName : $("#partnerName").val(),
                foreignName : $("#foreignName").val(),
                partnerGroup : $("#partnerGroup").val(),
                phone : $("#phone").val(),
                mobilephone : $("#mobilephone").val(),
                fax : $("#fax").val(),
                email : $("#email").val(),
                website : $("#website").val(),
                isEnable : $("input[name='activity']:checked").val(),
                address : $("#address").val(),
                description : $("#description").val(),
                contactsLastName : $("#contactsLastName").val(),
                contactsFirstName : $("#contactsFirstName").val(),
                contactsGender : $("#contactsGender").val(),
                contactsPhone : $("#contactsPhone").val(),
                contactsMobilephone : $("#contactsMobilephone").val(),
                contactsDescription : $("#contactsDescription").val()
            },
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg);
                    if ($("#partnerType").val() == 1) {
                        siping.redirect("/businesspartner/getlist?pageNo=1&pageSize=10&partnerType=1");
                    } else {
                        siping.redirect("/businesspartner/getlist?pageNo=1&pageSize=10&partnerType=2");
                    }
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });
    $("#supplier_smt_add").click(function() {
        siping.ajax({
            method : 'post',
            url : '/businesspartner/add',
            async : true,
            data : {
                partnerCode : $("#partnerCode").val(),
                partnerType : $("#partnerType").val(),
                partnerName : $("#partnerName").val(),
                foreignName : $("#foreignName").val(),
                partnerGroup : $("#partnerGroup").val(),
                phone : $("#phone").val(),
                mobilephone : $("#mobilephone").val(),
                fax : $("#fax").val(),
                email : $("#email").val(),
                website : $("#website").val(),
                isEnable : $("input[name='activity']:checked").val(),
                address : $("#address").val(),
                addressEn : $("#addressEn").val(),
                businessType : $("#businessType").val(),
                countryRegionId : $("#countryRegionId").val(),
                postalCode : $("#postalCode").val(),
                description : $("#description").val(),
                contactsLastName : $("#contactsLastName").val(),
                contactsFirstName : $("#contactsFirstName").val(),
                contactsGender : $("#contactsGender").val(),
                contactsPhone : $("#contactsPhone").val(),
                contactsMobilephone : $("#contactsMobilephone").val(),
                contactsFax : $("#contactsFax").val(),
                contactsEmail : $("#contactsEmail").val(),
                contactsDescription : $("#contactsDescription").val()
            },
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg);
                    if ($("#partnerType").val() == 1) {
                        siping.redirect("/businesspartner/getlist?pageNo=1&pageSize=10&partnerType=1");
                    } else {
                        siping.redirect("/businesspartner/getlist?pageNo=1&pageSize=10&partnerType=2");
                    }
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });
    $("#partner_smt_edit").click(function() {
        siping.ajax({
            method : 'post',
            url : '/businesspartner/edit',
            async : true,
            data : {
                id : $("#partner_smt_edit_id").val(),
                partnerType : $("#partnerType").val(),
                partnerName : $("#partnerName").val(),
                foreignName : $("#foreignName").val(),
                partnerGroup : $("#partnerGroup").val(),
                phone : $("#phone").val(),
                mobilephone : $("#mobilephone").val(),
                fax : $("#fax").val(),
                email : $("#email").val(),
                website : $("#website").val(),
                isEnable : $("input[name='activity']:checked").val(),
                address : $("#address").val(),
                description : $("#description").val(),
                contactsLastName : $("#contactsLastName").val(),
                contactsFirstName : $("#contactsFirstName").val(),
                contactsGender : $("#contactsGender").val(),
                contactsPhone : $("#contactsPhone").val(),
                contactsMobilephone : $("#contactsMobilephone").val(),
                contactsDescription : $("#contactsDescription").val()
            },
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg);
                    siping.redirect("/businesspartner/getlist?pageNo=1&pageSize=10&partnerType=1");
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });
    
    $("#supplier_smt_edit").click(function() {
        siping.ajax({
            method : 'post',
            url : '/businesspartner/edit',
            async : true,
            data : {
                id : $("#partner_smt_edit_id").val(),
                partnerType : $("#partnerType").val(),
                partnerName : $("#partnerName").val(),
                foreignName : $("#foreignName").val(),
                partnerGroup : $("#partnerGroup").val(),
                phone : $("#phone").val(),
                mobilephone : $("#mobilephone").val(),
                fax : $("#fax").val(),
                email : $("#email").val(),
                website : $("#website").val(),
                isEnable : $("input[name='activity']:checked").val(),
                address : $("#address").val(),
                addressEn : $("#addressEn").val(),
                businessType : $("#businessType").val(),
                countryRegionId : $("#countryRegionId").val(),
                postalCode : $("#postalCode").val(),
                description : $("#description").val(),
                contactsLastName : $("#contactsLastName").val(),
                contactsFirstName : $("#contactsFirstName").val(),
                contactsGender : $("#contactsGender").val(),
                contactsPhone : $("#contactsPhone").val(),
                contactsMobilephone : $("#contactsMobilephone").val(),
                contactsFax : $("#contactsFax").val(),
                contactsEmail : $("#contactsEmail").val(),
                contactsDescription : $("#contactsDescription").val()
            },
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg);
                    siping.redirect("/businesspartner/getlist?pageNo=1&pageSize=10&partnerType=2");
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });
    
    $("#bank_smt_add").click(function() {
        var partnerId=$("#partnerId").val();
        siping.ajax({
            method : 'post',
            url : '/bankaccount/add',
            async : true,
            data : {
                bankName : $("#bankName").val(),
                swiftCode : $("#swiftCode").val(),
                bankAddress : $("#bankAddress").val(),
                accountName : $("#accountName").val(),
                accountNo : $("#accountNo").val(),
                remark : $("#remark").val(),
                partnerId : $("#partnerId").val()
            },
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg);
                    siping.redirect("/businesspartner/bank/getlist?id="+partnerId);
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });
    $("#bank_smt_edit").click(function() {
        var partnerId=$("#partnerId").val();
        siping.ajax({
            method : 'post',
            url : '/bankaccount/edit',
            async : true,
            data : {
                id : $("#bankAccountId").val(),
                bankName : $("#bankName").val(),
                swiftCode : $("#swiftCode").val(),
                bankAddress : $("#bankAddress").val(),
                accountName : $("#accountName").val(),
                accountNo : $("#accountNo").val(),
                remark : $("#remark").val(),
                partnerId : $("#partnerId").val()
            },
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg);
                    siping.redirect("/businesspartner/bank/getlist?id="+partnerId);
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });
    $("#partner_title_1").click(function() {
        $("#partner_section_2").hide();
        $("#partner_section_1").show();
        $(this).css({
            "border-bottom" : "2px solid #60A7FF"
        }).siblings().css({
            "border-bottom" : "0"
        });
    });
    $("#partner_title_2").click(function() {
        $("#partner_section_1").hide();
        $("#partner_section_2").show();
        $(this).css({
            "border-bottom" : "2px solid #60A7FF"
        }).siblings().css({
            "border-bottom" : "0"
        });
    });
});
function deletePartner() {
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        ids[i] = checkbox[i].id;
    }
    if(ids!=""){
        siping.confirm('确定删除吗？', function() {
            siping.ajax({
                method : 'get',
                url : '/businesspartner/delete?id=' + ids,
                async : true,
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                        if ($("#partnerType").val() == 1) {
                            siping.redirect("/businesspartner/getlist?pageNo=1&pageSize=10&partnerType=1");
                        } else {
                            siping.redirect("/businesspartner/getlist?pageNo=1&pageSize=10&partnerType=2");
                        }
                    } else {
                        siping.alert(0, data.msg);
                    }
                }
            });
        });
    }else{
        siping.alert(0, "请选择要删除的列！");
    }
}
function deleteBank(partnerId){
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        ids[i] = checkbox[i].id;
    }
    if(ids!=""){
        siping.confirm('确定删除吗？', function() {
            siping.ajax({
                method : 'get',
                url : '/bankaccount/delete?id=' + ids,
                async : true,
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                        siping.redirect("/businesspartner/bank/getlist?id="+partnerId);
                    } else {
                        siping.alert(0, data.msg);
                    }
                }
            });
        });
    }else{
        siping.alert(0, "请选择要删除的列！");
    }
}
function openWinBank(id){
    siping.openWindow('/businesspartner/bank/getlist?id='+id,'70%','70%',"银行账户");
}
function openWinAddBank(id){
    siping.openWindow('/businesspartner/bank/add?id='+id,'70%','70%',"添加银行账户");
}
function addPartnerGroup(partnerType){
    var ids = new Array("groupName");
    var resultMsg = validateNotNull(ids);
    if(resultMsg.success != true){
        siping.tips("必填项",resultMsg.id);
        return;
    }
    siping.ajax({
        method : 'post',
        url : '/partnergroup/add',
        async : true,
        data : {
            groupName : $("#groupName").val(),
            description : $("#description").val(),
            partnerType : partnerType
        },
        success : function(data) {
            if (data.success) {
                siping.alert(1, data.msg);
            } else {
                siping.alert(0, data.msg);
            }
        }
    });
}
function deletePartnerGroup() {
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        ids[i] = checkbox[i].id;
    }
    if(ids!=""){
        siping.confirm('确定删除吗？', function() {
            siping.ajax({
                method : 'post',
                url : '/partnergorup/delete?id=' + ids,
                async : true,
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                        if ($("#partnerType").val() == 1) {
                            siping.redirect("/partnergroup/getlist?pageNo=1&pageSize=10&partnerType=1");
                        } else {
                            siping.redirect("/partnergroup/getlist?pageNo=1&pageSize=10&partnerType=2");
                        }
                        
                    } else {
                        siping.alert(0, data.msg);
                    }
                }
            });
        });
    }else{
        siping.alert(0, "请选择要删除的列！");
    }
}
function editPartnerGroup(){
    siping.confirm('确定更改吗？', function() {
        siping.ajax({
            method : 'post',
            url : '/partnergroup/edit',
            async : true,
            data : {
                id : $("#partner_group_id").val(),
                groupName : $("#groupName").val(),
                description : $("#description").val(),
                partnerType : $("#partnerType").val()
            },
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg);
                    if ($("#partnerType").val() == 1) {
                        siping.redirect("/partnergroup/getlist?pageNo=1&pageSize=10&partnerType=1");
                    } else {
                        siping.redirect("/partnergroup/getlist?pageNo=1&pageSize=10&partnerType=2");
                    }
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });
}
