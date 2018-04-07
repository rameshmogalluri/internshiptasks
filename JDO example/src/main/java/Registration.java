

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

public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("name"); 
		String email=request.getParameter("email"); 
		String mobilenumber=request.getParameter("mobilenumber");
		String address=request.getParameter("address"); 
		String password=request.getParameter("password");
		
		Contact c=new Contact(name,email,mobilenumber,address,password);
		  
			PersistenceManager pm = PMF.get().getPersistenceManager();  
		  
			

			try {	
				
					Extent<Contact> ex=pm.getExtent(Contact.class,true);
				    Iterator<Contact> itr=ex.iterator();
				    boolean flag=false;
				    while(itr.hasNext())
				    {
				    	Contact checkemail=itr.next();
				    	if(email.equals(checkemail.getEmail())) 
				    	{
				    		flag=true;
				    		 request.setAttribute("alreadyregistred", "You are already Registred..!");
					          RequestDispatcher dispatcher =  request.getRequestDispatcher("index.jsp");
					          dispatcher.forward(request, response);
					          break;
				    	}
				    }
				if(!flag) 
				{
				pm.makePersistent(c); 
				response.setContentType("text/html");
				PrintWriter pw =response.getWriter();
				  pw.print("<h3> Hello"+name+"</h3>");
				}
			
			 
			}
			finally { 
				pm.close();
				}
				
		
	}

}
