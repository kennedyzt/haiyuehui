<@css href="css/common.css" />
<script>
    var rootPath = '<@url value="/"/>',
        staticPath = '<@static />',
        uploadPath = '<@fstatic />';

    function createUrl(path) {
        return encodeURI(rootPath + path);
    }
    function uploadUrl(path){
        return encodeURI(uploadPath + path);
    }
    function createMenuUrl(icon){
        return encodeURI(staticPath + "/icons/"+icon+".png");
    }
</script>
<#--js引入-->
<@js src="js/jquery/jquery-1.10.2.js,
    js/layer/layer.js,
    js/laydate/laydate.js,
    js/siping/siping.js,
    js/jquery/jquery.PrintArea.js,
    js/siping/print/LodopFuncs.js"/>
