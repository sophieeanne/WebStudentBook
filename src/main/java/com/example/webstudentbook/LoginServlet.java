package com.example.webstudentbook;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private StudentDBUtil studentDBUtil;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            studentDBUtil = new StudentDBUtil(
                    ((DataSource) new InitialContext().lookup("java:comp/env/jdbc/studentdb"))
            );
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Show the login page
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        try {
            // Authentificate user
            User user = studentDBUtil.authenticateUser(username, password);

            if (user != null) {
                // 1. Create the session
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("username", user.getUsername());
                session.setMaxInactiveInterval(30 * 60); // 30 minutes

                // 2. Create the cookie "remember username"
                if ("on".equals(remember)) {
                    Cookie usernameCookie = new Cookie("username", username);
                    usernameCookie.setMaxAge(7 * 24 * 60 * 60); // 7 jours
                    response.addCookie(usernameCookie);
                } else {
                    // Delete the cookie if it's not chosen
                    Cookie usernameCookie = new Cookie("username", "");
                    usernameCookie.setMaxAge(0);
                    response.addCookie(usernameCookie);
                }

                // 3. Redirect according to the role
                if ("instructor".equals(user.getRole())) {
                    response.sendRedirect("StudentControllerServlet");
                } else {
                    response.sendRedirect("ViewStudentsServlet");
                }

            } else {
                // If authentification failed
                request.setAttribute("errorMessage", "Invalid username or password");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}