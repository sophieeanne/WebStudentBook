package com.example.webstudentbook;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;

@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {
    private StudentDBUtil studentDBUtil;
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        try{
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/studentdb");
            studentDBUtil = new StudentDBUtil(dataSource);
        }catch(NamingException e){
           throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int studentId = Integer.parseInt(request.getParameter("studentId"));
            studentDBUtil.deleteStudent(studentId);
            response.sendRedirect("StudentControllerServlet");
        }catch (Exception e){
            throw new ServletException("Error deleting student", e);
        }
    }
}
