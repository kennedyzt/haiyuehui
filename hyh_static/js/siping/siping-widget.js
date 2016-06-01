//页签控件
var sipingTab = function(tabControlId) {
    this.tabControlId = tabControlId;
    this.tabsId = tabControlId + "_tabs";
    this.framesId = tabControlId + "_iframes";
    this.tabs = new Array();
    this.frames = new Array();

    this.placeAt = function(divId) {
        divContainer = $("#"+divId);
        divContainer.html('<div class="tab simple"><ul id="'+this.tabsId+'"></ul></div><div id="'+this.framesId+'" class="main"></div>');
    };
    
    this.existsTab = function(code) {
        for(var index = 0; index < this.tabs.length; index++) {
            if (this.tabs[index].attr('id').substr(4) == code) {
                return true;
            }
        }
        return false;
    }

    this.activeTab = function(code) {
        for(var index = 0; index < this.tabs.length; index++) {
            if (this.tabs[index].attr('id').substr(4) == code) {
                this.tabs[index].removeClass("visited");
                this.tabs[index].addClass("hover");
            } else {
                this.tabs[index].addClass("visited");
                this.tabs[index].removeClass("hover");
            }
            if (this.frames[index].attr('id').substr(6) == code) {
                this.frames[index].removeClass("visited");
            } else {
                this.frames[index].addClass("visited");
            }
        }
    }

    this.getActiveTab = function(){
        var code = "";
        for(var index = 0; index < this.tabs.length; index++) {
            var tabClass = this.tabs[index].attr('class');
            if(tabClass == "hover"){
                code = this.tabs[index].attr("id");
                break;
            }
        }
        return code;
    }

    this.tabClose = function(obj) {
        //关闭页签
        //获取id
        var code = $(obj.target).parent().attr("id");
        //如果是非active页，关闭tab
        if ($("#"+code).hasClass("visited")) {
            $("#"+code).remove();
        } else {
            //如果是active页，关闭tab，左侧tab页面变为活动页面
            this.activeTab($("#"+code).prev().attr('id').substr(4));
            $("#"+code).remove();
        }
        for(var index = 0; index < this.tabs.length; index++) {
            if (this.tabs[index].attr('id') == code) {
                this.tabs.splice(index, 1);
            }
        }
        //关闭iframe
        for(var index = 0; index < this.frames.length; index++) {
            if (this.frames[index].attr('id').substr(6) == code.substr(4)) {
                $("#"+this.frames[index].attr('id')).remove();
                this.frames.splice(index, 1);
            }
        }
    }

    this.addTab = function(tag, code, src) {
        //新增标签
        tabContainer = $("#"+this.tabsId);
        if (tag != "首页")
            tabContainer.append('<li id="tab_'+code+'" tab="'+tag+'">'+tag+'<a href="javascript:;">关闭</a></li>');
        else
            tabContainer.append('<li id="tab_'+code+'" tab="'+tag+'">'+tag+'</li>');
        frameContainer = $("#"+this.framesId);
        frameContainer.append('<iframe id="frame_'+code+'" src="'+src+' " scrolling="auto" frameborder="0"></iframe>');

        var that = this;
        var tabClick = function(obj) {
            //判断是否为关闭事件
            if (obj.target.tagName == "A") {
                that.tabClose(obj);
                return;
            }
            //判断tab是否为active
            if ($(obj.target).hasClass("visited")) {
                that.activeTab($(obj.target).attr("id").substr(4));
            }
        }

        $("#tab_"+code).click(tabClick);
        this.tabs[this.tabs.length] = $("#tab_"+code);
        this.frames[this.frames.length] = $("#frame_"+code);
        this.activeTab(code);
    };

    this.placeAt(tabControlId);
    this.addTab("首页",'hyh_intranetloginindex','/hyh_intranet/login/index');
}

//树形控件:根据dataSource创建树形列表
var tree = function(containerId, dataSource) {
    this.dataSource = dataSource;
    this.init = function() {
        if($("#"+containerId).children().length > 0) return;
        $("#"+containerId).append('<ul><li id="tree_0" class="root"><div class="tree_li_div"><span class="tree_icon icon_open"></span><span class="node_name">全部</span></div><ul></ul></li></ul>');
        //遍历dataSource，形成完整的树形菜单
        for(var index = 0; index < this.dataSource.length; index++) {
            this.addNode(this.dataSource[index].id, this.dataSource[index].parentTypeId, this.dataSource[index].isLeafNode, this.dataSource[index].typeName)
        }
        //给树形列表添加事件
        $("#"+containerId).find("li.notLeaf .expand_no").click(function(obj) {
            //判断当前状态，如果已展开，点击后切换为缩回图片，不显示所属树节点
            if ($(this).hasClass("expand")) {
                $(this).removeClass("expand");
                $(this).addClass("expand_no");
                $(this).next().next().attr("hidden","hidden");
            } else {
                $(this).addClass("expand");
                $(this).removeClass("expand_no");
                $(this).next().next().removeAttr("hidden");
            }
        });
        //单击选择
        $("#"+containerId).find(".node_name").click(function(obj) {
            var textId = $(this).parent().parent().attr("id").substr(5);
            if(textId == 0){
                //textId = "";
            }
            $("#"+containerId).prev().prev().val(textId);
            $("#"+containerId).prev().val($(this).text());
            $("#"+containerId).attr("hidden","hidden");
        });
    }
    
    //新增节点参数：1.父节点id;2.是否为叶节点;3.节点名称
    this.addNode = function(id, containerId, isLeafNode, nodeName) {
        var html ="";
        if ($("#tree_"+containerId).children("ul").length == 0) {
            html = '<ul hidden="hidden">';
        }
        if (isLeafNode) {
            html = html +'<li id="tree_'+id+'" class="Leaf" ><span class="tree_icon tree_point"></span><div class="tree_li_div"><span></span><span class="node_name">'+nodeName+'</span></div></li>';
        } else {
            html = html + '<li id="tree_'+id+'" class="notLeaf" ><span class="tree_icon expand_no"></span><div class="tree_li_div"><span class="tree_icon icon_close"></span><span class="node_name">'+nodeName+'</span></div></li>';
        }
        if ($("#tree_"+containerId).children("ul").length == 0) {
            html = html + '</ul>';
            $("#tree_"+containerId).append(html);
        } else {
            $("#tree_"+containerId).children("ul").append(html);
        }
    }
    
    this.init();
//    if (document.addEventListener) {
//        document.addEventListener("click", function() {
//            //隐藏
//            $("#materialTypeTree").attr("hidden","hidden");
//        }, false);
//        $("#fatherType").bind("click", function(e) {
//            $("#materialTypeTree").removeAttr("hidden");
//            e = e||event;  stopFunc(e); 
//        }, false);
//        $("#materialTypeTree").bind("click", function(e) {
//            e = e||event;  stopFunc(e); 
//        }, false);
//    } else {
//        document.attachEvent("click", function() {
//            //隐藏
//            $("#materialTypeTree").attr("hidden","hidden");
//        });
//        $("#fatherType").attachEvent("click", function(e) {
//            $("#materialTypeTree").removeAttr("hidden");
//            e = e||event;  stopFunc(e); 
//        });
//        $("#materialTypeTree").attachEvent("click", function(e) {
//            e = e||event;  stopFunc(e); 
//        });
//    }
}

function stopFunc(e){   
    e.stopPropagation?e.stopPropagation():e.cancelBubble = true;       
}