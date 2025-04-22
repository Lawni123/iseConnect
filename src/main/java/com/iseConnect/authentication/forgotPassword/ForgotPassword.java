package com.iseConnect.authentication.forgotPassword;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String mail = request.getParameter("email");
        String pass = request.getParameter("newPassword");
        String designation = request.getParameter("designation");

        if (mail == null || pass == null || designation == null ||
            mail.trim().isEmpty() || pass.trim().isEmpty() || designation.trim().isEmpty()) {
            out.print("<script>" +
                      "alert('Missing data! Please try again.');" +
                      "window.location.href = 'verifyEmailForgotPassword.jsp';" +
                      "</script>");
            return;
        }

        ForgotPasswordDAO fPDao = new ForgotPasswordDAO();
        boolean success = fPDao.resetPassword(mail, pass, designation);

        if (success) {
            out.print("<script>" +
                      "alert('Successful Password Reset!');" +
                      "window.location.href = '" + designation + "Login.html';" +
                      "</script>");
        } else {
            out.print("<script>" +
                      "alert('Unsuccessful Password Reset! Please try again.');" +
                      "window.location.href = 'verifyEmailForgotPassword.jsp';" +
                      "</script>");
        }
    }
}
