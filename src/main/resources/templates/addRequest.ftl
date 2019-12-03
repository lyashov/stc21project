<#import "parts/common.ftl" as c>

<@c.page>
    <form method="post" enctype="multipart/form-data">
        <#if name??>
            ${name}
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="container">
            <input type="file" name="file">
            <input type="submit" name="submit" value="Проверить с помощью нейросети">
        </div>
    </form>
</@c.page>