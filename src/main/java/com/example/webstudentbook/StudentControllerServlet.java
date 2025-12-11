package com.example.webstudentbook;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
    private StudentDBUtil studentDBUtil;
    private DataSource dataSource;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private DataSource getDataSource() throws NamingException {
        String jndi="java:comp/env/jdbc/studentdb" ;
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup(jndi);
        return dataSource;
    }
    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub
        super.init();
        try {
            dataSource= getDataSource();
            studentDBUtil = new StudentDBUtil(dataSource);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        try {
            listStudents(request,response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        String fn= req.getParameter("firstName");
        String ln= req.getParameter("lastName");
        String email = req.getParameter("email");
        Student student = new Student(fn,ln,email);
        studentDBUtil.updateStudent(student);

        resp.sendRedirect("StudentControllerServlet");
        try {
            listStudents(req,resp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void listStudents(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        List<Student> students = studentDBUtil.getStudents();
        request.setAttribute("STUDENT_LIST", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
        dispatcher.forward(request, response);
    }
}