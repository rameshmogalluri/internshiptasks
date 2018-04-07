

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
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
	 
		
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		try {
		
			Extent<Contact> ex=pm.getExtent(Contact.class,true);
		    Iterator<Contact> itr=ex.iterator();
		    boolean flag=false;
		    while(itr.hasNext())
		    {
		    	Contact login=itr.next();
		    	if(username.equals(login.getEmail()) && password.equals(login.getPassword()))   
		    	{
		    		flag=true;
		    		 response.setContentType("text/html");
						PrintWriter pw =response.getWriter();
						  pw.print("<h3> Hello"+login.getName()+"</h3>"); 
			          break;
		    	}
		    }
		  
			if(!flag)
			{
				request.setAttribute("logininvalid", "Invalid credentials");
		          RequestDispatcher dispatcher =  request.getRequestDispatcher("index.jsp");
		          dispatcher.forward(request, response); 
			}
		 
		}
	  finally {
		  pm.close();
		  }
	  }

}
