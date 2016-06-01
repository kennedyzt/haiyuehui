function preViewInventoryCheck(){
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    if (checkbox.length != 0) {
        for (var i = 0; i < checkbox.length; i++) {
            ids[i] = checkbox[i].id;
        }
            siping.ajax({
                method : 'get',
                url : '/inventorycheck/responsebody/get?checkNumber=' + ids,
                async : true,
                success : function(data) {
                    $("#inventory_check_print").empty();
                    createDataSourceHtml(data);
                    LODOP = getLodop();
                    LODOP.SET_PRINT_STYLEA(0, "Horient", 2); // 设置居中
                    LODOP.SET_PRINT_PAGESIZE(0, 0, 0, "A4"); // 设置纸张
                    var linkHref = staticPath + "/css/print/common.css";
                    var strStyleCSS = "<link href='" + linkHref + "' type='text/css' rel='stylesheet'>";
                    var strFormHtml = strStyleCSS + "<body>" + document.getElementById("inventory_check_print").innerHTML + "</body>";
                    LODOP.PRINT_INIT("打印PC盘点单");
                    LODOP.SET_PRINT_STYLEA(0, "TableHeightScope", 1); // 0,1,2,3   不包含,包含头尾，头，尾
                    LODOP.ADD_PRINT_TABLE("10mm", "10mm", "90%", "90%", strFormHtml); // 四个数值分别表示Top,Left,Width,Height
                    LODOP.PREVIEW();
                }
            });
    } else {
        siping.alert(0, "请选择需要打印的盘点单");
    }
}

function createDataSourceHtml(data) {
    var dataSourceHtml = '<table class = "print_table"><caption><b><font face="黑体" size="4">PC盘点单</font></b><br><div class="common_print_div"><div class="div_4">单据编号:'+data.checkNumber+'</div><div class="div_4">单据日期:'+data.billsDate+'</div><div class="div_4">操作员:'+data.operatorName+'</div><div class="div_4">所有人:'+data.ownerName+'</div><div class="div_4">盘点仓库:'+data.storageName+'</div><div class="div_4">盘点库区:'+data.storageAreaName+'</div><div class="div_4">单据摘要:'+data.summary+'</div></div></caption><thead><tr><th>商品货号</th><th>国际编码</th><th>商品名称</th><th>商品类型</th><th>计量单位</th><th>库位编码</th><th>批次号</th><th>生产日期</th><th>到日日期</th><th>库存数量</th><th>盘点数量</th><th>差异数量</th></tr></thead><tbody>';
    for (i = 0; i < data.items.length; i++) {
        dataSourceHtml += "<tr><td>" + data.items[i].materialNo + "</td><td>" + data.items[i].barcode + "</td><td>" + data.items[i].materialName + "</td><td>" + data.items[i].materialTypeName + "</td><td>" + data.items[i].unitName + "</td><td>" + data.items[i].locationNo + "</td><td>" + data.items[i].batchNumber + "</td><td>" + data.items[i].productionDate + "</td><td>" + data.items[i].expirationDate + "</td><td>" + data.items[i].inventoryNumber + "</td><td>" + data.items[i].actualNumber + "</td><td>" + data.items[i].diffNumber + "</td></tr>";
    }
    dataSourceHtml += '</tbody><tfoot><tr class="tfoot"><td colspan="12" style="text-align:right;">第<font color="blue" tdata="PageNO">#</font>页/共<font tdata="PageCount" color="blue">#</font>页</td></tr></tfoot></table>';
    $("#inventory_check_print").append(dataSourceHtml);
}

function exportExcel(){
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    if (checkbox.length != 0) {
        for (var i = 0; i < checkbox.length; i++) {
            ids[i] = checkbox[i].id;
        }
            siping.ajax({
                method : 'get',
                url : '/inventorycheck/responsebody/get?checkNumber=' + ids,
                async : true,
                success : function(data) {
                    $("#inventory_check_print").empty();
                    createDataSourceExcel(data);
                    LODOP=getLodop();   
                    LODOP.PRINT_INIT(""); 
                    LODOP.ADD_PRINT_TABLE(150,800,"100%","100%",document.getElementById("inventory_check_print").innerHTML); 
                    LODOP.SET_SAVE_MODE("Orientation",2); //Excel文件的页面设置：横向打印   1-纵向,2-横向;
                    LODOP.SET_SAVE_MODE("PaperSize",data.items.length+5);  //Excel文件的页面设置：纸张大小   9-对应A4
                    LODOP.SET_SAVE_MODE("Zoom",100);       //Excel文件的页面设置：缩放比例
                    LODOP.SET_SAVE_MODE("CenterHorizontally",true);//Excel文件的页面设置：页面水平居中
                    LODOP.SET_SAVE_MODE("CenterVertically",true); //Excel文件的页面设置：页面垂直居中
                    LODOP.SAVE_TO_FILE("库存盘点.xls"); 
                }
            });
    } else {
        siping.alert(0, "请选择需要导出的盘点单");
    }
}
function createDataSourceExcel(data){
    var dataSourceHtml = '<table class = "print_table"><caption><b><font face="黑体" size="4">PC盘点单</font></b></caption><thead><tr><th>单据编号:'+data.checkNumber+'</th><th>单据日期:'+data.billsDate+'</th><th>操作员:'+data.operatorName+'</th><th>所有人:'+data.ownerName+'</th><th>盘点仓库:'+data.storageName+'</th><th>盘点库区:'+data.storageAreaName+'</th><th>单据摘要:'+data.summary+'</th><th></th><th></th><th></th><th></th><th></th></tr><tr><th>商品货号</th><th>国际编码</th><th>商品名称</th><th>商品类型</th><th>计量单位</th><th>库位编码</th><th>批次号</th><th>生产日期</th><th>到日日期</th><th>库存数量</th><th>盘点数量</th><th>差异数量</th></tr></thead><tbody>';
    for (i = 0; i < data.items.length; i++) {
        dataSourceHtml += "<tr><td>" + data.items[i].materialNo + "</td><td>" + data.items[i].barcode + "</td><td>" + data.items[i].materialName + "</td><td>" + data.items[i].materialTypeName + "</td><td>" + data.items[i].unitName + "</td><td>" + data.items[i].locationNo + "</td><td>" + data.items[i].batchNumber + "</td><td>" + data.items[i].productionDate + "</td><td>" + data.items[i].expirationDate + "</td><td>" + data.items[i].inventoryNumber + "</td><td>" + data.items[i].actualNumber + "</td><td>" + data.items[i].diffNumber + "</td></tr>";
    }
    dataSourceHtml += '</tbody><tfoot><tr class="tfoot"></tfoot></table>';
    $("#inventory_check_print").append(dataSourceHtml);
}