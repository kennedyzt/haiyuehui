<@insert template="login/right">
<div id="content" class="content">
    <div class="errorMsg">
        <h1>uh oh! That link is not supported on our mobile site. We'll add this feature soon.</h1>
        <h1><a href="javascript:" id="toStandardSite404">View this link on the Standard Site</a></h1>
        <#if (runtimeSettings.environment == "DEV")!false>
            <pre>${exception.message}</pre>
            <pre>${exception.stackTrace}</pre>
        </#if>
    </div>
</div>
</@insert>