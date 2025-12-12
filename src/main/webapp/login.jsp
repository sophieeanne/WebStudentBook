<%--
  Created by IntelliJ IDEA.
  User: itoda
  Date: 10/12/2025
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login - Student Book</title>
    <link type="text/css" rel="stylesheet" href="css/login-style.css">
</head>
<body>
<div class="login-container">
    <div class="login-card">
        <div class="login-header">
            <h2>ESILV Student Book</h2>
            <p>Please login to continue</p>
        </div>

        <form action="LoginServlet" method="post" class="login-form">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username"
                       value="${cookie.username.value}"
                       required autofocus>
            </div>

            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>

            <div class="form-remember">
                <input type="checkbox" id="remember" name="remember" checked>
                <label for="remember">Remember username</label>
            </div>

            <div class="form-group">
                <input type="submit" value="Login" class="login-button">
            </div>

            <%-- Show errors --%>
            <c:if test="${not empty errorMessage}">
                <div class="error-message">
                        ${errorMessage}
                </div>
            </c:if>
        </form>

    </div>

</div>
</body>
</html>


