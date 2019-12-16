<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Login form</title>

	<link href="/css/style.css" rel="stylesheet">
	<link href="/css/font-awesome.css" rel="stylesheet">
</head>
<body>

<form action="/login" method="post">
	<input type="hidden" name="_csrf" value="${_csrf.token}" />
<div class="container">

	<img src="img/men.png">
	<form>

		<div class="dws-input">
			<input type="text" name="username" placeholder="Введите логин">
		</div>
		<div class="dws-input">
			<input type="password" name="password" placeholder="Введите пароль">
		</div>
		<input class="dws-submit" type="submit" name="submit" value="ВОЙТИ"><br />
		<a href="/registration">Зарегистрироваться</a>
	</form>
</div>
</form>
</body>