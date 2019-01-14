<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML>

<head>
    <meta charset="utf-8">
    <title>Вход в личный кабинет</title>
    <link rel="stylesheet" href="style.css">
    <script src="jquery-3.3.1.min.js"></script>
    <script src="script.js"></script>
</head>

<body>
    <div class="login">
        <form action="login" method="post">
            <input type="text" name="login" placeholder="Имя пользователя" required></label>
            <br>
            <input type="password" name="password" placeholder="Пароль" required>
            <br>
            <input type="submit" value="Войти">
            <p style="color: red;">
            <%
            Object param = request.getAttribute("errorLoginPass");
            if (param!=null){
            out.println("Неверный логин/пароль");
            }
            %>
            </p>
        </form>
        <a href="registration.html">Регистация</a>
    </div>
</body>

</html>