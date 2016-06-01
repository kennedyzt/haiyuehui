function printEMS() {
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        ids[i] = checkbox[i].id;
    }
    if (ids != "") {
        siping.ajax({
            method : 'get',
            url : '/readyshipments/gets?ids=' + ids,
            async : true,
            success : function(data) {
                CreateFullBill(data);
                LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT", "Auto-Width");
                // LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT","55%");
                // var resultMsg = LODOP.PREVIEW();
                // LODOP.SET_SHOW_MODE("SETUP_ENABLESS","11111111000001");
                 var resultMsg=LODOP.PRINT_SETUP();
                // var resultMsg = LODOP.PRINT_DESIGN();
                /*if (Number(resultMsg) == 1) {
                    siping.ajax({
                        method : 'get',
                        url : '/readyshipments/updatestatus?ids=' + ids,
                        async : true,
                        success : function(data) {
                        }
                    });
                }*/
                 siping.ajax({
                     method : 'get',
                     url : '/readyshipments/updatestatus?ids=' + ids,
                     async : true,
                     success : function(data) {
                     }
                 });
            }
        });
    } else {
        siping.alert(0, "请选择要打印的行");
    }
}
function CreateFullBill(data) {
    LODOP = getLodop();
    LODOP.SET_PRINT_STYLEA(0, "Horient", 2); // 设置居中
    LODOP.PRINT_INIT("套打EMS的模板");
    LODOP.SET_PRINT_PAGESIZE(0, "107mm", "151mm", "A4");
    for (var l = 0; l < data.length; l++) {
        var model = data[l];
        LODOP.NewPage();
        LODOP.ADD_PRINT_TEXT(24, 20, 75, 20, "运单编号:");
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        LODOP.ADD_PRINT_TEXT(46, 18, 76, 31, "RQ");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 20);
        LODOP.SET_PRINT_STYLEA(0, "Alignment", 2);
        LODOP.ADD_PRINT_TEXT(85, 15, 80, 16, "发件人信息");
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        LODOP.ADD_PRINT_TEXT(85, 303, 75, 16, "目的地");
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        LODOP.ADD_PRINT_TEXT(162, 9, 141, 50, "地址:重庆西永综合保税区跨境贸易电子商务西永基地");
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        LODOP.ADD_PRINT_TEXT(107, 9, 142, 55, "发件人:重庆西永综合保税区跨境电商-海悦汇4000235259");
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        LODOP.ADD_PRINT_TEXT(85, 178, 84, 16, "收件人信息");
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        LODOP.ADD_PRINT_TEXT(109, 159, 139, 16, "收件人:" + model.consigneeName);
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        var phonesArr = model.consignessPhone.split(",");
        if(phonesArr.length > 1){
            LODOP.ADD_PRINT_TEXT(127, 159, 139, 33, "电话:" +phonesArr[0]+ String.fromCharCode(13)+ phonesArr[1]);
        }else{
            LODOP.ADD_PRINT_TEXT(141, 159, 139, 16, "电话:" +model.consignessPhone);
        }
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        LODOP.ADD_PRINT_TEXT(162, 159, 139, 48, "地址:" + model.consigneeAddress);
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 9);
        var address = model.consigneeAddress;
        var provinceStr = "";
        var provinceArray = new Array("北京", "上海", "天津", "重庆", "河北", "山西", "内蒙", "辽宁", "吉林", "黑龙江", "江苏", "浙江", "安徽", "福建", "江西", "山东", "河南", "湖北", "湖南", "广东", "广西", "海南", "四川", "贵州", "云南", "西藏",
                "陕西", "甘肃", "宁夏", "青海", "新疆", "香港", "澳门", "台湾");
        for (var i = 0; i < provinceArray.length; i++) {
            var province = provinceArray[i];
            if (address.indexOf(province) == 0) {
                provinceStr = province;
                break;
            }
        }
        LODOP.ADD_PRINT_TEXT(113, 315, 54, 93, provinceStr);
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 20);
        LODOP.SET_PRINT_STYLEA(0, "Alignment", 2);
        LODOP.SET_PRINT_STYLEA(0, "Bold", 1);
        LODOP.ADD_PRINT_RECT(22, 6, 391, 336, 0, 1);
        LODOP.ADD_PRINT_LINE(81, 6, 80, 394, 0, 1);
        LODOP.ADD_PRINT_BARCODE(23, 127, 278, 53, "Code39", model.trackingNo);
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 5);
        LODOP.ADD_PRINT_LINE(107, 10, 106, 395, 0, 1);
        LODOP.ADD_PRINT_LINE(83, 156, 208, 157, 0, 1);
        LODOP.ADD_PRINT_LINE(78, 300, 215, 301, 0, 1);
        LODOP.ADD_PRINT_LINE(258, 9, 257, 395, 0, 1);
        LODOP.ADD_PRINT_LINE(285, 12, 284, 270, 0, 1);
        LODOP.ADD_PRINT_TEXT(263, 10, 253, 16, "单据编号:" + model.orderNumber);
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 8);
        LODOP.ADD_PRINT_LINE(358, 270, 258, 271, 0, 1);
        LODOP.ADD_PRINT_TEXT(263, 276, 115, 16, "(代)收件人签名:");
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        LODOP.ADD_PRINT_TEXT(338, 297, 98, 16, "年    月    日");
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 7);
        var materialNameStr = "";
        for (var j = 0; j < model.items.length && j < 2; j++) {
            materialNameStr += model.items[j].materialName + "    ";
        }
        LODOP.ADD_PRINT_TEXT(290, 10, 253, 41, "内容品名:" + materialNameStr);
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 9);
        LODOP.ADD_PRINT_TEXT(338, 9, 65, 16, "数量:" + model.counts);
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        LODOP.ADD_PRINT_LINE(335, 6, 336, 270, 0, 1);
        LODOP.ADD_PRINT_LINE(354, 152, 333, 153, 0, 1);
        LODOP.ADD_PRINT_TEXT(338, 159, 67, 16, "重量:" + model.materialsWeight);
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        LODOP.ADD_PRINT_RECT(394, 4, 397, 169, 0, 1);
        LODOP.ADD_PRINT_LINE(469, 6, 468, 396, 0, 1);
        LODOP.ADD_PRINT_TEXT(374, 7, 92, 16, "发件人信息");
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        LODOP.ADD_PRINT_TEXT(375, 202, 92, 16, "收件人信息");
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        LODOP.ADD_PRINT_LINE(397, 198, 466, 199, 0, 1);
        LODOP.ADD_PRINT_TEXT(396, 9, 187, 33, "发件人:重庆西永综合保税区跨境电商-海悦汇4000235259");
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        LODOP.ADD_PRINT_TEXT(433, 9, 186, 33, "地址:重庆西永综合保税区跨境贸易电子商务西永基地");
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        LODOP.ADD_PRINT_TEXT(397, 202, 190, 16, "收货人:" + model.consigneeName);
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        if(phonesArr.length > 1){
            LODOP.ADD_PRINT_TEXT(416, 202, 189, 16, "电话:" + model.consignessPhone);
            LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
            LODOP.SET_PRINT_STYLEA(0, "FontSize", 9);
        }else{
            LODOP.ADD_PRINT_TEXT(414, 202, 189, 16, "电话:" + model.consignessPhone);
            LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
            LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        }
        LODOP.ADD_PRINT_TEXT(434, 201, 193, 33, "地址:" + model.consigneeAddress);
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 9);
        LODOP.ADD_PRINT_TEXT(477, 10, 89, 16, "单据编号:");
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        LODOP.ADD_PRINT_BARCODE(470, 115, 293, 52, "Code39", model.orderNumber);
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        LODOP.ADD_PRINT_TEXT(523, 10, 78, 16, "运单号码:");
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        LODOP.ADD_PRINT_TEXT(542, 9, 82, 16, "通关条码:");
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        LODOP.ADD_PRINT_TEXT(523, 93, 228, 16, model.trackingNo);
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        LODOP.ADD_PRINT_TEXT(542, 89, 228, 16, model.customsCode);
        LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
        LODOP.SET_PRINT_STYLEA(0, "FontSize", 10);
        LODOP.ADD_PRINT_LINE(213, 9, 212, 392, 0, 1);
        LODOP.ADD_PRINT_BARCODE(214, 31, 368, 43, "Code39", model.customsCode);
    }
}

function design() {
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        ids[i] = checkbox[i].id;
    }
    if (ids != "") {
        siping.ajax({
            method : 'get',
            url : '/readyshipments/gets?ids=' + ids,
            async : true,
            success : function(data) {
                CreateFullBill(data);
                LODOP.PRINT_DESIGN();
            }
        });
    } else {
        siping.alert(0, "请选择要打印的行");
    }
}