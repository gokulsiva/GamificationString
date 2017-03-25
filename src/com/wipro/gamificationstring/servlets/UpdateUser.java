package com.wipro.gamificationstring.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wipro.gamificationstring.bean.UserBean;
import com.wipro.gamificationstring.service.UserAdmin;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUser() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("GamificationStringUserEmail") == null){
			response.sendRedirect("index.jsp?message=Please LogIn.");
			return;
		}
		
		String userName = request.getParameter("name");
		String userEmail = request.getParameter("email");
		String userPassword = request.getParameter("password");
		String userId = request.getParameter("id");
		if( !userId.equals(request.getSession().getAttribute("GamificationStringId"))){
			response.sendRedirect("LogOut");
		}
		UserBean user = UserAdmin.getUser(new Integer(userId));
		user.setName(userName);
		user.setEmail(userEmail);
		user.setPassword(userPassword);
		String status = UserAdmin.updateUser(user);
		response.setContentType("text/html");
		response.getWriter().write(status);
	}

}
