package com.example.webstudentbook;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // récupérer la session actuelle
        HttpSession session = request.getSession(false);
        if (session != null) {
            //invalider la session
            session.invalidate();
        }

        // rediriger vers la page de login
        response.sendRedirect("login.jsp");
    }
}