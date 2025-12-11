<%--
  Created by IntelliJ IDEA.
  User: itoda
  Date: 27/11/2025
  Time: 15:55
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<html>
<head>
    <meta charset="UTF-8">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <title>Add a Student</title>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>ESILV Engineer School</h2>
    </div>
</div>

<div id="container">
    <h3>Add New Student</h3>
    <form action="AddStudentServlet" method="post">
        <table>
            <tbody>
            <tr>
                <td><label>First Name: </label></td>
                <td><input type="text" name="firstName" required/></td>
            </tr>
            <tr>
                <td><label>Last Name: </label></td>
                <td><input type="text" name="lastName" required/></td>
            </tr>
            <tr>
                <td><label>Email: </label></td>
                <td><input type="email" name="email" required/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Save" class="button"/></td>
            </tr>
            </tbody>
        </table>
    </form>

    <div style="clear:both;"></div>
    <a href="StudentControllerServlet">Back to List</a>
</div>
</body>
</html>