package com.example.webstudentbook;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")  // Le filtre s'applique à toutes les URLs
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getServletPath();
        HttpSession session = req.getSession(false);

        // Pages accessibles sans login
        boolean publicPage =
                path.equals("/login.jsp")
                        || path.contains("LoginServlet")
                        || path.contains("css")
                        || path.contains("images");

        if (publicPage) {
            chain.doFilter(request, response);
            return;
        }

        // Vérifier si connecté
        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        // Récupérer le user
        User user = (User) session.getAttribute("user");

        // Vérifier les permissions
        boolean isInstructor = "instructor".equals(user.getRole());

        // Pages réservées aux profs :
        boolean adminPage =
                path.contains("AddStudentServlet") ||
                        path.contains("EditStudentServlet") ||
                        path.contains("DeleteStudentServlet") ||
                        path.contains("add-student.jsp") ||
                        path.contains("edit-student.jsp");

        if (adminPage && !isInstructor) {
            res.sendRedirect("access-denied.jsp");
            return;
        }

        // Sinon → autorisé
        chain.doFilter(request, response);
    }
}
