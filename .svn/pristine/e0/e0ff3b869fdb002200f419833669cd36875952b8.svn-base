$(function() {
    $("#postperiod_smt_add").click(function() {
        if ('SPAN'==$("#postPeriodStartTime").get(0).tagName){
           var postPeriodStartTime = $("#postPeriodStartTime").text();
        }
        else{
            postPeriodStartTime = $("#postPeriodStartTime").val();
        };
        siping.ajax({
            method : 'post',
            url : '/postperiod/add',
            async : true,
            data : {
                postPeriodNo:$("#postPeriodNo").val(),
                postPeriodName:$("#postPeriodName").val(),
                postPeriodStartTime:postPeriodStartTime,
                postPeriodEndTime:$("#postPeriodEndTime").val(),
                postPeriodFlag:$("#postPeriodFlag").val()
            },
            success : function(data) {
                if(data.success){
                    siping.alert(1,data.msg);
                }else{
                    siping.alert(0,data.msg);
                }
            }
        });
    });
    $("#postperiod_smt_edit").click(function() {
        var postPeriodEndTime = $("#postPeriodEndTime").val();
        if (postPeriodEndTime == undefined || postPeriodEndTime == ""){
            postPeriodEndTime = $("#postPeriodEndTime").text();
        }
        siping.ajax({
            method : 'post',
            url : '/postperiod/edit',
            async : true,
            data : {
                id:$("#id").val(),
                postPeriodNo:$("#postPeriodNo").text(),
                postPeriodName:$("#postPeriodName").text(),
                postPeriodStartTime:$("#postPeriodStartTime").text(),
                postPeriodEndTime:postPeriodEndTime,
                postPeriodFlag:$("#postPeriodFlag").val()
                //description:$("#description").val()
            },
            success : function(data) {
                if(data.success){
                    siping.alert(1,data.msg,"postperiod/getlist");
                }else{
                    siping.alert(0,data.msg);
                }
            }
        });
    });
});
function deletePostPeriod() {
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    for(var i=0;i<checkbox.length;i++){
        ids[i]=checkbox[i].id;
    }
    siping.confirm('确定删除吗？', '提示', function() {
        siping.ajax({
            method : 'get',
            url : '/postperiod/delete?id=' + ids,
            async : true,
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg);
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });
}