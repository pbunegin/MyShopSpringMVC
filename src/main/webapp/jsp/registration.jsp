<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML>

<head>
    <meta charset="utf-8">
    <title>Регистрация</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/script.js"></script>
</head>

<body>
    <div class="login">
        <form action="registration" id="registration">
            <input type="text" pattern="^[A-Za-zА-Яа-яЁё]{2,}$" name="firstName" autocomplete="off" placeholder="Имя..." required>
            <br>
            <input type="text" pattern="^[A-Za-zА-Яа-яЁё]{2,}$" name="lastName" autocomplete="off" placeholder="Фамилия..." required>
            <br>
            <input type="text" pattern="^[-_#0-9a-zA-Z]{3,}$" name="login" autocomplete="off" placeholder="Логин...">
            <br>
            <input type="password" pattern=".{3,}" name="password" placeholder="Пароль..." required id="password">
            <br>
            <input type="password" pattern=".{3,}" name="secondPassword" placeholder="Повторите пароль..." required id="secondPassword">
            <br>
            <input type="submit" value="Зарегистрироваться">
            <br>
        </form>
        <a href="login">Авторизация</a>
    </div>
</body>

</html>