package contact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username"); 
		String password=request.getParameter("password"); 
	 
		HttpSession session=request.getSession();
		String uname=(String)session.getAttribute("email");
		String pwd=(String)session.getAttribute("password");
		try {
		if(uname.equals(username) && pwd.equals(password))  
		{
			response.sendRedirect("dashboard.jsp");
		}
		else
		 {
			 request.setAttribute("logininvalid", "Invalid credentials");
	          RequestDispatcher dispatcher =  request.getRequestDispatcher("index.jsp");
	          dispatcher.forward(request, response); 
		 }
		}
	  catch(Exception e)
		{
		  request.setAttribute("logininvalid", "Invalid credentials");
          RequestDispatcher dispatcher =  request.getRequestDispatcher("index.jsp");
          dispatcher.forward(request, response); 		}
	}

}
