<%--
  Created by IntelliJ IDEA.
  User: andri
  Date: 11/12/2025
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%--
  Vue étudiante - Lecture seule de la liste des étudiants
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,com.example.webstudentbook.*" %>
<%@ page import="com.example.webstudentbook.Student" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<html>
<head>
    <title>Web Student Book - Student View</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <div id="user-info">
        Welcome, ${sessionScope.username} |
        <a href="LogoutServlet">Déconnexion</a>
    </div>
</head>

<% List<Student> theStudents = (List<Student>)request.getAttribute("STUDENT_LIST"); %>

<body>
<!-- ${STUDENT_LIST}-->
<div id="wrapper">
    <div id="header">
        <h2>ESILV Engineer School</h2>
    </div>
</div>
<div id="container">
    <div id="content">
        <table>

            <tr>
                <th>First Name </th>
                <th>Last Name</th>
                <th>Email </th>
                <th>Action</th>
            </tr>
            <c:forEach var="tempStudent" items="${STUDENT_LIST }" >
                <tr>
                    <td> ${tempStudent.firstName}</td>
                    <td> ${tempStudent.lastName}</td>
                    <td> ${tempStudent.email}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>

</html>