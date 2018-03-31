package contact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name"); 
		String email=request.getParameter("email"); 
		String mobilenumber=request.getParameter("mobilenumber");
		String address=request.getParameter("address"); 
		String password=request.getParameter("password");
		
		HttpSession session=request.getSession();
		if(email.equals((String)session.getAttribute("email")))
		{
			request.setAttribute("ErrorMessage", "You are already registred");
          RequestDispatcher dispatcher =  request.getRequestDispatcher("index.jsp");
          dispatcher.forward(request, response);
		}
		else
		{
			session.setAttribute("name", name); 
			session.setAttribute("email", email); 
			session.setAttribute("mobilenumber", mobilenumber); 
			session.setAttribute("address", address); 
			session.setAttribute("password", password);	
			response.sendRedirect("dashboard.jsp");
			
		}
		
		
	}

}
