<%--
  Created by IntelliJ IDEA.
  User: itoda
  Date: 27/11/2025
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,com.example.webstudentbook.*" %>
<%@ page import="com.example.webstudentbook.Student" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<html>
<head>
    <title>Web Student Tracker</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
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
            <form action="AddStudentServlet" method="get">
                <input type="submit" value="Add Student"/>
            </form>
        <table>
            <tr>
                <th>First Name </th>
                <th>Last Name</th>
                <th>Email </th>
                <th>Action</th>
            </tr>

            <c:forEach var="tempStudent" items="${STUDENT_LIST }" >
            <c:url var="EditLink" value= "EditStudentServlet">
                <c:param name="studentId" value="${tempStudent.id}"/>
            </c:url>
                <c:url var="deleteLink" value="DeleteStudentServlet">
                    <c:param name="studentId" value="${tempStudent.id}"/>
                </c:url>
            <tr>
                <td> ${tempStudent.firstName}</td>
                <td> ${tempStudent.lastName}</td>
                <td> ${tempStudent.email}</td>
                <td>
                    <!-- Edit Button -->
                    <a href="${EditLink}" class="button">Edit</a>

                    <!-- Delete Button -->
                    <a href="${deleteLink}"
                       class="button delete-button"

                       onclick="if(!confirm('Are you sure you want to delete this student?')) return false"> <!-- Get confirmation before deleting -->
                        Delete
                    </a>
                </td>
            </tr>
                </c:forEach>
        </table>
    </div>
</div>
</body>
</html>

