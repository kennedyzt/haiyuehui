<@insert template="login/right">
<div id="content" class="content">
    <div class="errorMsg">
        <h1>Server internal error!</h1>
        <#if (runtimeSettings.environment == "DEV")!false>
            <pre>${exception.message}</pre>
            <pre>${exception.stackTrace}</pre>
        </#if>
    </div>
</div>
</@insert>