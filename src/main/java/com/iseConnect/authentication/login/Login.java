package com.iseConnect.authentication.login;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iseConnect.model.User;

import java.io.IOException;
import java.io.PrintWriter;




@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String uname = request.getParameter("email");
		String pass = request.getParameter("password");
		String designation = request.getParameter("designation");
		
		LoginDAO lDao = new LoginDAO();
		
		HttpSession session = request.getSession();
		if( lDao.check(uname, pass,designation)) {
			User user = new User();

			user = lDao.getUserDetails(uname,designation);
			session.setAttribute("name", user.getName());
			session.setAttribute("user", user);
			
			response.sendRedirect("home.jsp");
		}else {
			out.print("<script>" +
		            "alert('Authentication Failed!');" +
		            "window.location.href = 'index.html';" + 
		            "</script>");
		}
	}

}