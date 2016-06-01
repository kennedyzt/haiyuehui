$(function() {
    businessSalesGrid(1, 10);
});
function businessSalesGrid(pageNo, pageSize) {
    var param = {fromPage:{startDate:$("#startDate").val(),endDate:$("#endDate").val()},fromRow:{shopNo:"shopNo",shopName:"shopName"}};
    var urlColumn = {name:"shopNo",openType:"window",url:"report/saleanalysisreport",param:param,width:"90%",height:"90%",title:"商家销售明细"};
    siping.fillGrid({
        content : "business_sales_grid",
        width : "100%",
        height : "500px",
        ajax : {
            type : "post",
            url : "report/businesssalesreport/getlist",
            async : true,
            data : {
                pageNo : pageNo,
                pageSize : pageSize,
                keywords : $("#keywords").val(),
                keyword : $("#keyword").val(),
                startDate : $("#startDate").val(),
                endDate : $("#endDate").val()
            },
            contentType : 'application/json'
        },
        header : [ {
            width : "25%",
            name : "商家编码"
        }, {
            width : "25%",
            name : "商家名称"
        }, {
            width : "25%",
            name : "金额"
        } ],
        column : [ urlColumn, "shopName", "totalPrice" ],
        page : {
            options : [ 10, 20, 30, 50, 80 ],
            fn : "businessSalesGrid"
        },
        rowNum : true
    });
}

function preViewBusinessSales() {
    var request = {};
    request.keywords = $("#keywords").val();
    request.startDate = $("#startDate").val();
    request.endDate = $("#endDate").val();
    request.isPaging = false;
    siping.ajax({
        method : "post",
        url : 'report/businesssalesreport/getlist',
        data : JSON.stringify(request),
        dataType : "json",
        contentType : "application/json",
        success : function(data) {
            $("#business_sales_grid_print").empty();
            createDataSourceHtml(data);
            LODOP = getLodop();
            LODOP.SET_PRINT_STYLEA(0, "Horient", 2); // 设置居中
            LODOP.SET_PRINT_PAGESIZE(0, 0, 0, "A4"); //设置纸张
            var linkHref = staticPath + "/css/print/common.css";
            var strStyleCSS = "<link href='" + linkHref + "' type='text/css' rel='stylesheet'>";
            var strFormHtml = strStyleCSS + "<body>" + document.getElementById("business_sales_grid_print").innerHTML + "</body>";
            LODOP.PRINT_INIT("打印商家销售统计");
            LODOP.ADD_PRINT_TABLE("10mm", "10mm", "190mm", "277mm", strFormHtml); //四个数值分别表示Top,Left,Width,Height
            LODOP.SET_PRINT_STYLEA(0,"TableHeightScope",1); // 0,1,2,3 不包含,包含头尾，头，尾
            LODOP.PREVIEW();
        }
    });
}
function createDataSourceHtml(data) {
    var dataSourceHtml = '<table class = "print_table"><caption><b><font face="黑体" size="4">商家销售统计</font></b></caption><thead><tr><th>商家编码</th><th>商家名称</th><th>金额</th></tr></thead><tbody>';
    for (i = 0; i < data.records.length; i++) {
        dataSourceHtml += "<tr><td>"+data.records[i].shopNo+"</td><td>"+data.records[i].shopName+"</td><td>"+data.records[i].totalPrice+"</td></tr>";
    }
    dataSourceHtml += '</tbody><tfoot><tr class="tfoot"><td colspan="3" style="text-align:right;">第<font color="blue" tdata="PageNO">#</font>页/共<font tdata="PageCount" color="blue">#</font>页</td></tr></tfoot></table>';
    $("#business_sales_grid_print").append(dataSourceHtml);
}