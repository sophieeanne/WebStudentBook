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
import java.io.IOException;

@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
    private StudentDBUtil studentDBUtil;
    private DataSource dataSource;

    public void init() throws ServletException {
        super.init();
        try {
            // Initialiser la DataSource (comme dans StudentControllerServlet)
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/studentdb");
            studentDBUtil = new StudentDBUtil(dataSource);
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        // TODO Auto-generated method stub
        request.getRequestDispatcher("/add-student.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 1. Lire les paramètres du formulaire
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");

            // 2. Créer un nouvel objet Student
            Student newStudent = new Student(firstName, lastName, email);

            // 3. Ajouter l'étudiant à la base de données
            studentDBUtil.addStudent(newStudent);

            // 4. Rediriger vers la liste des étudiants (GET request)
            response.sendRedirect("StudentControllerServlet");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
