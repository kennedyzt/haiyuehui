var layerTitle;
var layerCloseBtn = true;
var layerIndex = parent.layer.getFrameIndex(window.name);
var productTypeList = null;
$(function() {
    var url = window.location.href;
    var ii = url.indexOf("pageSize");
    if (ii != -1) {
        var pp = url.substr(ii + 9, 2);
        $("#selectedPageSize_").val(pp);
    } else {
        $("#selectedPageSize_").val(10);
    }
    $("#gotoPage").click(function() {
        var pageNo = $("#gotoPageNo_").val();
        var pageSize = $("#selectedPageSize_").val();
        var url = $(this).attr("data-page-url");
        var _url = url.substr(1, url.length - 1);
        var totalCnt = $("#total_page_ct").text();
        if (pageNo == "" || isNaN(pageNo) || parseInt(totalCnt) < parseInt(pageNo) || parseInt(pageNo) < 1) {
            $("#gotoPageNo_").val("");
            $("#gotoPageNo_").focus();
        } else {
            var perfix = _url.indexOf('?') == -1 ? "?" : "&";
            window.location.href = createUrl(_url + perfix + "pageNo=" + parseInt(pageNo) + "&pageSize=" + pageSize);
        }
    });
    if ($("#selectedPageSize_").val() == null) {
        $("#selectedPageSize_").val(10);
    }
    $("#cancel_btn").click(function() {
        siping.confirm("确定取消吗？", function() {
            var obj = new Object();
            var code = parent.mainTabControl.getActiveTab();
            obj.target = parent.$("#" + code).find("a");
            parent.mainTabControl.tabClose(obj);
        });
    });
    $("#goback_btn").click(function(){
        window.history.go(-1);
    });
    String.prototype.replaceStr = function(original, begin, end, replacement) {
        var m = original.substr(0, begin);
        var n = original.substring(end, original.length);
        return m + replacement + n;
    }
    Array.prototype.contains = function(item){
        return RegExp(item).test(this);
    };
    // 为元素绑定事件
    siping.eventBinding($('*'));
    if (parent.layerCloseBtn) {
        $("#layer_close_icon").click(function() {
            parent.layer.close(layerIndex);
        });
    } else {
        $("#layer_close_icon").remove();
    }
    $("#_layer_title").text(parent.layerTitle);
    var mainHeight = parent.$("#right_main_page").height();
    $("#tab-control_iframes").css("height", Number(mainHeight) - 35);
});

var siping = {
    openWindow : function(url, width, height, title, closeBtn) {
        layerTitle = title;
        if (closeBtn == false) {
            layerCloseBtn = false;
        }
        var index = layer.open({
            type : 2,
            title : false,
            content : [ createUrl(url), 'no' ],
            area : [ width, height ],
            closeBtn : false,
            shift : 1,
            maxmin : false
        });
        return index;
    },
    loading : function() {
        var index = layer.load(2, {
            time : 10 * 1000
        });
        return index;
    },
    close : function(index) {
        layer.close(index);
    },
    ajax : function(opt) {
        index = layer.load(2, {
            time : 5 * 1000
        });
        function fn() {
        }
        var url = opt.url || "", async = opt.async !== false, method = opt.method || 'GET', datas = opt.data || null, success = opt.success || fn;
        var contentType = opt.contentType || "application/x-www-form-urlencoded";
        $.ajax({
            type : method,
            url : createUrl(url),
            async : async,
            data : datas,
            dataType : "json",
            contentType : contentType,
            success : function(data) {
                layer.close(index);
                success(data);
            }
        });
    },
    confirm : function(tip, yesFn) {
        layer.confirm(tip, {
            icon : 3,
            title : false
        }, function(index) {
            yesFn();
            layer.close(index);
        });
    },
    alert : function(code, msg, redirectUrl) {
        if (code == 0) {
            layer.alert(msg, {
                icon : 2
            });
        }
        if (code == 1) {
            layer.alert(msg, {
                icon : 1
            }, function(index) {
                layer.close(index);
                if (redirectUrl == undefined) {
                    window.location.reload(true);
                } else {
                    window.location.href = createUrl(redirectUrl);
                }
            });
        }
    },
    tips : function(msg, domId) {
        layer.tips(msg, $("#" + domId), {
            tips : [ 1, '#34A7FF' ]
        });
    },
    redirect : function(url) {
        window.location.href = createUrl(url);
    },
    reload : function() {
        window.location.reload(true);
    },
    getSelect : function(dom, code, name, url, idArray, rowid) {
        var v = dom.value;
        $.ajax({
            type : 'get',
            url : createUrl(url),
            async : true,
            dataType : "json",
            data : {
                keyword : v
            },
            success : function(data) {
                var _left = $(dom).offset().left;
                var _top = $(dom).offset().top;
                var _width = $(dom).height();
                var list = '<div class="li_div my_radius">';
                for (var i = 0; i < data.length; i++) {
                    list += '<div class="li_span"><span class="li_code">' + data[i][code] + '</span>-<span>' + data[i][name] + '</span></div>'
                }
                list += '</div>';
                $(".li_div").remove();
                $(dom).after(list);
                $(".li_div").css({
                    "left" : _left,
                    "top" : _top + _width + 3
                });
                $(".li_div").delegate(".li_span", "click", function(e) {
                    var licode = $(this).children(".li_code").text();
                    $(dom).val(licode);
                    $(".li_div").remove();
                    for (var i = 0; i < data.length; i++) {
                        if (data[i][code] == licode) {
                            for (var j = 0; j < idArray.length; j++) {
                                var thisId = idArray[j];
                                if (data[i][thisId] != null && data[i][thisId] != undefined)
                                    $("#" + thisId + rowid).text(data[i][thisId]);
                            }
                            break;
                        }
                    }
                });
            }
        });
    },
    eventBinding : function(dom) {
        $(dom).each(function() {
            if ($(this).attr("data-hover-tip") != undefined) {
                var index;
                $(this).mouseover(function() {
                    index = layer.tips($(this).attr("data-hover-tip"), $(this), {
                        tips : [ 1, '#34A7FF' ]
                    });
                });
                $(this).mouseout(function() {
                    layer.close(index);
                });
            }
            if ($(this).attr("data-click-tip") != undefined) {
                $(this).click(function() {
                    layer.tips($(this).attr("data-click-tip"), $(this), {
                        tips : [ 1, '#34A7FF' ]
                    });
                });
            }
            if ($(this).attr("data-required") == "true") {
                $(this).before('<font style="color:red;margin:0 2px 0 0;">*</font>');
            }
            if ($(this).attr("data-type") == "datetime") {
                $(this).click(function() {
                    laydate({
                        istime : true,
                        format : 'YYYY-MM-DD hh:mm:ss'
                    });
                });
            }
            if ($(this).attr("data-type") == "date") {
                $(this).click(function() {
                    laydate();
                });
            }
            if ($(this).attr("data-type") == "firstday") {
                var date=new Date();
                date.setDate(1);
                $(this).val(date.Format("yyyy-MM-dd"));
                $(this).click(function() {
                    laydate();
                });
            }
            if ($(this).attr("data-type") == "lastday") {
                var date=new Date();
                var currentMonth=date.getMonth();
                var nextMonth=++currentMonth;
                var nextMonthFirstDay=new Date(date.getFullYear(),nextMonth,1);
                var oneDay=1000*60*60*24;
                date = new Date(nextMonthFirstDay-oneDay);
                $(this).val(date.Format("yyyy-MM-dd"));
                $(this).click(function() {
                    laydate();
                });
            }
            if ($(this).attr("data-type") == "number") {
                $(this).keydown(function(event) {
                    var keyCode = event.keyCode;
                    if (!isNumber(keyCode))
                        return false;
                });
                $(this).keyup(function() {
                    if (isNaN($(this).val())) {
                        layer.tips("必须为数字类型", $(this), {
                            tips : [ 1, '#34A7FF' ]
                        });
                        $(this).val("");
                    }
                });
                $(this).blur(function() {
                    if($(this).val()!=""){
                        $(this).val(Number($(this).val()).toFixed(6));
                    }
                });
            }
            if ($(this).attr("data-type") == "materialType") {
                $(this).click(function() {
                    // 判断是否有弹出框，如果没有，new一个
                    $(this).next().css({
                        "min-height" : "200px",
                        "border" : "1px solid #e3e3e3"
                    });
                    if ($(this).next().children("ul").length == 0) {
                        if(!productTypeList){
                            siping.ajax({
                                method : 'get',
                                url : 'materialtype/getlist',
                                async : true,
                                dataType : "json",
                                success : function(data) {
                                    productTypeList = data;
                                    var materialType = new tree("materialTypeTree", productTypeList);
                                    $("#materialTypeTree").removeAttr("hidden");
                                    return;
                                }
                            });
                        }
                    }
                    // 弹出框选择
                    if (typeof ($(this).next().attr("hidden")) != "undefined")
                        $(this).next().removeAttr("hidden");
                    else
                        $(this).next().attr("hidden", "hidden");
                });
            }
            if ($(this).attr("data-type") == "purchaseMaterial") {
                $(this).keyup(function() {
                    var idArray = [ "id", "materialNo", "materialName", "specificationsModel", "barcode", "brand", "season", "unitName", "minOrder", "expirationDate" ];
                    var rowId = $(this).parent().parent().attr("id");
                    getSelectMaterial(this, 'materialNo', 'materialName', "material/getlistbykey", idArray, rowId, "isPurchase");
                });
            }
            if ($(this).attr("data-type") == "inventoryMaterial") {
                $(this).keyup(function() {
                    var idArray = [ "id", "materialNo", "materialName", "specificationsModel", "barcode", "brand", "season", "unitName", "minOrder", "expirationDate" ];
                    var rowId = $(this).parent().parent().attr("id");
                    getSelectMaterialByInventoryCheck(this, 'materialNo', 'materialName', "material/getlistbykey", idArray, rowId);
                });
            }
            if ($(this).attr("data-type") == "saleMaterial") {
                $(this).keyup(function() {
                    var idArray = [ "id", "materialNo", "materialName", "specificationsModel", "barcode", "brand", "season", "unitName", "minOrder" ];
                    var rowId = $(this).parent().parent().attr("id");
                    getSelectMaterial(this, 'materialNo', 'materialName', "material/getlistbykey", idArray, rowId, "isSell");
                });
            }
            if ($(this).attr("data-type") == "inventoryMaterial") {
                $(this).keyup(function() {
                    var idArray = [ "id", "materialNo", "materialName", "specificationsModel", "barcode", "brand", "season", "unitName", "minOrder" ];
                    var rowId = $(this).parent().parent().attr("id");
                    getSelectMaterial(this, 'materialNo', 'materialName', "material/getlistbykey", idArray, rowId, "isInventory");
                });
            }
        });
    },
    fillGrid : function(opt){
        var loadIndex = layer.load(2, { time : 30 * 1000 });
        var content = opt.content;
        var width = opt.width || '100%';
        var height = opt.height || '420px';
        var ajax = opt.ajax;
        var header = opt.header;
        var dataColumn = opt.column;
        var page = opt.page;
        var pageFn = page.fn;
        var method = ajax.type || 'get';
        var async = ajax.async !== false;
        var isOrder = opt.rowNum !== false;
        var contentType = ajax.contentType || 'application/x-www-form-urlencoded';
        var data = ajax.data;
        var totalRow = opt.totalRow;
        if(contentType == "application/json"){
            data = JSON.stringify(ajax.data);
        }
        var gridHtml = '<div style="overflow:auto;height:'+height+';"><table style="width: '+width+';"><thead><tr>';
        if(isOrder){
            gridHtml += '<td style="width: 3%;"></td>';
        }
        for(var i=0;i<header.length;i++){
            gridHtml += '<td width="'+header[i].width+'">'+header[i].name+'</td>';
        }
        gridHtml += '</tr></thead><tbody></tbody></table></div>';
        $("#"+content).html(gridHtml);
        $.ajax({
            type : method,
            url : createUrl(ajax.url),
            async : async,
            data : data,
            dataType : "json",
            contentType : contentType,
            success : function(data) {
                setTimeout(function() {
                    layer.close(loadIndex);
                    var rowData = data.records;
                    var rowHtml = "";
                    for(var i=0;i<rowData.length;i++){
                        if(isOrder){
                            rowHtml += '<tr><td style="text-align: center;color: rgb(94, 94, 247);">'+(i+1)+'</td>';
                        }else{
                            rowHtml += '<tr>';
                        }
                        for(var j=0;j<dataColumn.length;j++){
                            var col = dataColumn[j];
                            if(typeof col == "string"){
                                var celldata = rowData[i][col];
                                if(celldata == null || celldata == undefined){ celldata = ""; }
                                if(typeof celldata == "number"){
                                    celldata = Number(celldata);
                                    celldata = Math.round(celldata*100)/100;
                                }
                                rowHtml += '<td>'+celldata+'</td>';
                            }
                            if(typeof col == "object"){
                                var param = col.param;
                                var newUrl = "";
                                if(param){
                                    newUrl = jointURL(col.url,param,rowData[i]);
                                }
                                var cellValue = rowData[i][col.name];
                                if(col.openType == "window"){
                                    rowHtml += '<td><a title="'+col.title+'" onclick="gridRowClick(\''+newUrl+'\',\''+col.width+'\',\''+col.height+'\',\''+col.title+'\');">'+cellValue+'</a></td>';
                                }
                                if(col.openType == "tab"){
                                    rowHtml += '<td><a title="'+col.title+'" href="'+createUrl(newUrl)+'">'+cellValue+'</a></td>';
                                }
                            }
                        }
                        rowHtml += '</tr>';
                    }
                    $("#"+content).find("tbody").html(rowHtml);
                    if(totalRow){
                        var totalRowHtml = '<div class="grid_total_row">';
                        for(var i=0;i<totalRow.length;i++){
                            var numb = data.stats[totalRow[i].id];
                            totalRowHtml += '<span><font>'+totalRow[i].text+'：</font>'+Math.round(numb*100)/100+'</span>';
                        }
                        totalRowHtml += '</div>';
                        $("#"+content).append(totalRowHtml);
                    }
                    if(page){
                        var pageNo = data.pageNo;
                        var pageSize = data.pageSize;
                        var totalRecords = data.totalRecords;
                        var totalPages = parseInt((totalRecords + pageSize -1)/pageSize);
                        var pageHtml = '<div class="page_nav"><div class="page_sizes"><span>显示条数</span>'+
                            '<select class="my_radius" id="selected_page_size" onchange="'+pageFn+'('+pageNo+',this.value)">';
                        if(page.options){
                            for(var i=0;i<page.options.length;i++){
                                pageHtml += '<option value="'+page.options[i]+'">'+page.options[i]+'</option>';
                            }
                        }else{
                            pageHtml += '<option selected="selected" value="10">10</option><option value="20">20</option><option value="30">30</option>'+
                                '<option value="50">50</option><option value="80">80</option>';
                        }
                        pageHtml += '</select></div><div class="page_ctrol">';
                        if (pageNo == 1) {
                            pageHtml += '<span><img src="'+staticPath+'/icons/first0.png"></span>';
                            pageHtml += '<span><img src="'+staticPath+'/icons/previous0.png"></span>';
                        }else{
                            pageHtml += '<span><a onclick="'+pageFn+'(1,'+pageSize+')"><img src="'+staticPath+'/icons/first.png"></a></span>';
                            pageHtml += '<span><a onclick="'+pageFn+'('+(pageNo-1)+','+pageSize+')"><img src="'+staticPath+'/icons/previous1.png"></a></span>';
                        }
                        pageHtml += '<span>第&nbsp;<font id="current_page_num">'+pageNo+'</font>&nbsp;页</span>';
                        if (pageNo >= totalPages) {
                            pageHtml += '<span><img src="'+staticPath+'/icons/next0.png"></span>';
                            pageHtml += '<span><img src="'+staticPath+'/icons/last0.png"></span>';
                        }else{
                            pageHtml += '<span><a onclick="'+pageFn+'('+(pageNo+1)+','+pageSize+')"><img src="'+staticPath+'/icons/next1.png"></a></span>';
                            pageHtml += '<span><a onclick="'+pageFn+'('+totalPages+','+pageSize+')"><img src="'+staticPath+'/icons/last.png"></a></span>';
                        }
                        pageHtml += '<span>共&nbsp;<font id="total_pages_cnts">'+totalPages+'</font>&nbsp;页</span></div>';
                        pageHtml += '<div class="page_to"><span>共&nbsp;'+totalRecords+'&nbsp;条</span></div></div>';
                        $("#"+content).append(pageHtml);
                        $("#selected_page_size").val(pageSize);
                    }
                }, 500);
            }
        });
    },
    treetable : function(opt){
        var loadIndex = layer.load(2, { time : 30 * 1000 });
        var content = opt.content;
        var width = opt.width || '100%';
        var height = opt.height || '420px';
        var ajax = opt.ajax;
        var header = opt.header;
        var dataColumn = opt.column;
        var page = opt.page;
        var pageFn = page.fn;
        var method = ajax.type || 'get';
        var async = ajax.async !== false;
        var isOrder = opt.rowNum !== false;
        var contentType = ajax.contentType || 'application/x-www-form-urlencoded';
        var data = ajax.data;
        var totalRow = opt.totalRow;
        var checkbox = opt.checkbox;
        var handler = opt.handler;
        if(contentType == "application/json"){
            data = JSON.stringify(ajax.data);
        }
        var gridHtml = '<div style="overflow:auto;height:'+height+';"><table style="width: '+width+';"><thead><tr>';
        if(isOrder){
            gridHtml += '<td style="width: 3%;"></td>';
        }
        if(handler && handler.del){
            gridHtml += '<td width="'+handler.del.width+'"><a class="td_head_a" onclick="'+handler.del.fn+'()" data-hover-tip="'+handler.del.tips+'"><img src="'+staticPath+'/icons/delete.png"></a></td>';
        }
        if(handler && handler.edit){
            gridHtml += '<td style="'+handler.edit.width+'"></td>';
        }
        for(var i=0;i<header.length;i++){
            gridHtml += '<td width="'+header[i].width+'">'+header[i].name+'</td>';
        }
        gridHtml += '</tr></thead><tbody></tbody></table></div>';
        $("#"+content).html(gridHtml);
        $.ajax({
            type : method,
            url : createUrl(ajax.url),
            async : async,
            data : data,
            dataType : "json",
            contentType : contentType,
            success : function(data) {
                setTimeout(function() {
                    layer.close(loadIndex);
                    var rowData = data.records;
                    var rowHtml = "";
                    for(var i=0;i<rowData.length;i++){
                        if(isOrder){
                            rowHtml += '<tr><td style="text-align: center;color: rgb(94, 94, 247);">'+(i+1)+'</td>';
                        }else{
                            rowHtml += '<tr>';
                        }
                        if(handler && handler.del){
                            if(checkbox){
                                rowHtml += '<td><input id="'+rowData[i][checkbox.id]+'" type="checkbox" name="material_type_check_box"></td>';
                            }else{
                                rowHtml += '<td></td>';
                            }
                        }
                        if(handler && handler.edit){
                            rowHtml += '<td><a href="'+createUrl(handler.edit.href)+'?'+handler.edit.id+'='+rowData[i][handler.edit.id]+'" data-hover-tip="'+handler.edit.tips+'"><img src="'+staticPath+'/icons/edit.png"></a></td>';
                        }
                        for(var j=0;j<dataColumn.length;j++){
                            var col = dataColumn[j];
                            if(typeof col == "string"){
                                var celldata = rowData[i][col];
                                if(celldata == null || celldata == undefined){ celldata = ""; }
                                if(typeof celldata == "number"){
                                    celldata = Number(celldata);
                                    celldata = Math.round(celldata*100)/100;
                                }
                                rowHtml += '<td>'+celldata+'</td>';
                            }
                            if(typeof col == "object"){
                                var param = col.param;
                                var newUrl = "";
                                if(param){
                                    newUrl = jointURL(col.url,param,rowData[i]);
                                }
                                var cellValue = rowData[i][col.name];
                                if(col.openType == "window"){
                                    rowHtml += '<td><a title="'+col.title+'" onclick="gridRowClick(\''+newUrl+'\',\''+col.width+'\',\''+col.height+'\',\''+col.title+'\');">'+cellValue+'</a></td>';
                                }
                                if(col.openType == "tab"){
                                    rowHtml += '<td><a title="'+col.title+'" href="'+createUrl(newUrl)+'">'+cellValue+'</a></td>';
                                }
                            }
                        }
                        rowHtml += '</tr>';
                    }
                    $("#"+content).find("tbody").html(rowHtml);
                    if(totalRow){
                        var totalRowHtml = '<div class="grid_total_row">';
                        for(var i=0;i<totalRow.length;i++){
                            var numb = data.stats[totalRow[i].id];
                            totalRowHtml += '<span><font>'+totalRow[i].text+'：</font>'+Math.round(numb*100)/100+'</span>';
                        }
                        totalRowHtml += '</div>';
                        $("#"+content).append(totalRowHtml);
                    }
                    if(page){
                        var pageNo = data.pageNo;
                        var pageSize = data.pageSize;
                        var totalRecords = data.totalRecords;
                        var totalPages = parseInt((totalRecords + pageSize -1)/pageSize);
                        var pageHtml = '<div class="page_nav"><div class="page_sizes"><span>显示条数</span>'+
                            '<select class="my_radius" id="selected_page_size" onchange="'+pageFn+'('+pageNo+',this.value)">';
                        if(page.options){
                            for(var i=0;i<page.options.length;i++){
                                pageHtml += '<option value="'+page.options[i]+'">'+page.options[i]+'</option>';
                            }
                        }else{
                            pageHtml += '<option selected="selected" value="10">10</option><option value="20">20</option><option value="30">30</option>'+
                                '<option value="50">50</option><option value="80">80</option>';
                        }
                        pageHtml += '</select></div><div class="page_ctrol">';
                        if (pageNo == 1) {
                            pageHtml += '<span><img src="'+staticPath+'/icons/first0.png"></span>';
                            pageHtml += '<span><img src="'+staticPath+'/icons/previous0.png"></span>';
                        }else{
                            pageHtml += '<span><a onclick="'+pageFn+'(1,'+pageSize+')"><img src="'+staticPath+'/icons/first.png"></a></span>';
                            pageHtml += '<span><a onclick="'+pageFn+'('+(pageNo-1)+','+pageSize+')"><img src="'+staticPath+'/icons/previous1.png"></a></span>';
                        }
                        pageHtml += '<span>第&nbsp;<font id="current_page_num">'+pageNo+'</font>&nbsp;页</span>';
                        if (pageNo >= totalPages) {
                            pageHtml += '<span><img src="'+staticPath+'/icons/next0.png"></span>';
                            pageHtml += '<span><img src="'+staticPath+'/icons/last0.png"></span>';
                        }else{
                            pageHtml += '<span><a onclick="'+pageFn+'('+(pageNo+1)+','+pageSize+')"><img src="'+staticPath+'/icons/next1.png"></a></span>';
                            pageHtml += '<span><a onclick="'+pageFn+'('+totalPages+','+pageSize+')"><img src="'+staticPath+'/icons/last.png"></a></span>';
                        }
                        pageHtml += '<span>共&nbsp;<font id="total_pages_cnts">'+totalPages+'</font>&nbsp;页</span></div>';
                        pageHtml += '<div class="page_to"><span>共&nbsp;'+totalRecords+'&nbsp;条</span></div></div>';
                        $("#"+content).append(pageHtml);
                        $("#selected_page_size").val(pageSize);
                    }
                }, 500);
            }
        });
    },
    fillSelect : function(opt){
        var id = opt.id;
        var type = opt.type;
        var url = opt.url;
        var param = opt.param;
        if(!param){
            param = null;
        }
        var optionValue = opt.optionValue;
        var optionText = opt.optionText;
        $.ajax({
            type : type,
            url : createUrl(url),
            async : true,
            data : param,
            dataType : "json",
            success : function(data) {
                var options = '<option value="">--请选择--</option>';
                for(var i=0;i<data.length;i++){
                    options += '<option value="'+data[i][optionValue]+'">'+data[i][optionText]+'</option>';
                }
                $("#"+id).append(options);
            }
        });
    }
}
function pageSizeChange(s) {
    var url = window.location.href;
    var ii = url.indexOf("pageSize");
    if (ii != -1) {
        var pp = url.substr(ii + 9, 2);
        var _url = url.replaceStr(url, ii + 9, ii + 11, s.value);
        window.location.href = _url;
    } else {
        var perfix = url.indexOf('?') == -1 ? "?" : "&";
        window.location.href = url + perfix + "pageNo=1&pageSize=" + s.value;
    }
}
function removeGridRowData(s) {
    $(s).parent().parent().remove();
}
function removeGridRow(s) {
    $(s).parent().parent().remove();
    var rowId = parseInt($(s).parent().parent().attr("id"));
    $("#dataSource tr").each(function() {
        var columns = $(this).children("td").length;
        if (parseInt(this.id) > rowId) {
            $(this).attr("id", this.id - 1); // tr的id减1
            $(this).children("td:eq(0)").text(this.id);
            // 特殊处理带有批次需要日期反算的单据
            if ($(this).find("input[name = 'dueDate']").exist()) {
                $(this).find("input[name = 'productDate']").attr("onclick", "laydate({choose: function(date){getDueDate(date," + this.id + ")}})");
                $(this).find("input[name = 'dueDate']").attr("onclick", "laydate({choose: function(date){getProductDate(date," + this.id + ")}})");
            }
        }
        getTotalAmount(s);
    });
}
function btnPrintClick() {
    window.print();
}
function preview() {
    bdhtml = window.document.body.innerHTML;
    sprnstr = "<!--startprint-->";
    eprnstr = "<!--endprint-->";
    prnhtml = bdhtml.substring(bdhtml.indexOf(sprnstr) + 17);
    prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));
    window.document.body.innerHTML = prnhtml;
    window.print();
}
function gotoPrintPage(url, id) {
    url = "/hyh_intranet/" + url + "?" + id + "=" + $("#" + id).text();
    window.location.href = url;
}

function getMaterialType() {
    // 判断是否有弹出框，如果没有，new一个
    $(this).next().css({
        "min-height" : "200px",
        "border" : "1px solid #e3e3e3"
    });
    // if ($(this).next().children("ul").length == 0) {
    if ($("#materialTypeFlag").val() == 0) {
        siping.ajax({
            method : 'get',
            url : '/materialtype/getlist',
            async : false,
            success : function(data) {
                var materialType = new tree("materialTypeTree", data);
                $("#materialTypeTree").removeAttr("hidden");
                $("#materialTypeFlag").val(1);
                $("#materialTypeShowFlag").val(1);
                return;
            }
        });
    }
    // 弹出框选择
    if ($("#materialTypeShowFlag").val() == 0) {
        $(this).next().removeAttr("hidden");
        $("#materialTypeShowFlag").val(1);
    } else {
        $(this).next().attr("hidden", "hidden");
        $("#materialTypeShowFlag").val(0);
    }
}
// 仅能输入数字
function isNumber(keyCode) {
    // 数字
    if (keyCode >= 46 && keyCode <= 57)
        return true;
    // 小数字键盘
    if (keyCode >= 96 && keyCode <= 105)
        return true;
    // Backspace键
    if (keyCode == 8 || keyCode == 110 || keyCode == 190)
        return true;
    return false;
}
function doTest() {
    var data = {};
    data.receiptNumber =  'MI1';
    data.id = '43';
    data.batchNumber = 'p01002';
    data.productionDate = '2015-09-10 00:00:00';
    $.ajax({
        type : 'post',
        url : createUrl('/pda/inventoryreceiptitem/edit'),
        async : true,
        data : JSON.stringify(data),
        success : function(data) {
            alert("跳转成功");
        }
    });
}
function gridRowClick(url, width, height, title){
    layer.open({
        type : 2,
        title : title,
        content : [ createUrl(url), 'no' ],
        area : [ width, height ],
        closeBtn : 1,
        shift : 1,
        maxmin : false
    });
}
function jointURL(url,param,rowData){
    var myLink = "";
    if(param.fromPage){
        $.each(param.fromPage,function(key,item){
            var link = '&' + key + "=" + item;
            myLink += link;
        })
    }
    if(param.fromRow){
        $.each(param.fromRow,function(key,item){
            var link = '&' + key + "=" + rowData[item];
            myLink += link;
        })
    }
    myLink = url + "?" + myLink.substr(1);
    return myLink.replace(' ','');
}

Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}