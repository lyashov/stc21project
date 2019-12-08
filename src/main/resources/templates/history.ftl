<#import "parts/common.ftl" as c>

<@c.page>

<form method="post" enctype="multipart/form-data">
    <table class="table">
        <thead class="thead-dark">
        <tr>
<#--            <th scope="col">№</th>-->
            <th scope="col">Дата обращения</th>
            <th scope="col">ФИО</th>
            <th scope="col">Нейро-диагноз</th>
            <th scope="col">Процент точности</th>
            <th scope="col">Картинка</th>

        </tr>
        </thead>
        <tbody>

        <#list medHistories as history>
        <tr>
<#--            <th scope="row">1</th>-->
            <td>${history.date_visit}</td>
            <td>${history.user}</td>
            <td>${history.neiro_diagtose}</td>
            <td>${history.accuracy}%</td>
<#--            <td>${history.imgName}%</td>-->
            <td><img src="../uploads/2.png"></td>
        </tr>
        </#list>
        </tbody>
    </table>
</form>

</@c.page>