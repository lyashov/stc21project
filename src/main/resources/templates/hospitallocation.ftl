<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список ближайших больниц!</title>
</head>
<body>
<h1> "Cписок ближайших медецинских учреждений, в которых Вам обязательно помогут!" </h1>
<form action="/history" method="POST">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <input class="btn btn-primary btn-block" type="submit" value= "вернуться к истории!">

</form>

</body>
</html>