package com.wipro.gamificationstring.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wipro.gamificationstring.bean.UserBean;
import com.wipro.gamificationstring.service.UserAdmin;

/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogIn() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String id = "";
		
		UserBean user = UserAdmin.getUser(email);
		if(user == null){
			System.out.println("redirecting");
			response.sendRedirect("index.jsp?email="+email+"&message=Invalid Email/Password");
		} else {
			
			if(!user.getPassword().equals(password)){
				response.sendRedirect("index.jsp?email="+email+"&message=Invalid Email/Password");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("GamificationStringUserEmail", email);
				id = new Integer(user.getUserId()).toString();
				session.setAttribute("GamificationStringId", id);
				session.setAttribute("GamificationStringAccountType", user.getAccountType());
				if(user.getAccountType().equals("admin")){
					response.sendRedirect("admin-question-index.jsp");
				} else {
					response.sendRedirect("question-index.jsp");
				}
			}
			
		}
			
		}

}
