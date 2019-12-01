<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML>

<head>
    <meta charset="utf-8">
    <title>Вход в личный кабинет</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/script.js"></script>
</head>

<body>
    <div class="login">
        <form action="login" method="post">
            <input type="text" name="login" placeholder="Имя пользователя" required>
            <br>
            <input type="password" name="password" placeholder="Пароль" required>
            <br>
            <input type="submit" value="Войти">
            <p style="color: red;">
            ${errorLoginPassword}
            </p>
        </form>
        <a href="registration">Регистация</a>
    </div>
</body>

</html>