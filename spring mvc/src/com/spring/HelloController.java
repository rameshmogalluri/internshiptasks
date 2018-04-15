package com.spring;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;
@Controller
public class HelloController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@RequestMapping(value="/")
	public void index(HttpServletRequest request, HttpServletResponse response) throws IOException
	{ 
	response.sendRedirect("index.jsp");
	}

	@RequestMapping(value="/register",method=RequestMethod.POST) 
	public ModelAndView registration(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		
		String name=request.getParameter("name"); 
		String emailParam=request.getParameter("email"); 
		String mobilenumber=request.getParameter("mobilenumber");
		String address=request.getParameter("address"); 
		String password=request.getParameter("password");
		
		Contact c=new Contact(name,emailParam,mobilenumber,address,password,"true");
		PersistenceManager pm=PMF.get().getPersistenceManager(); 
		ModelAndView view=new ModelAndView("redirect:/dashboard");
		
	System.out.println(c.getstatus());
		try {
			Query q = pm.newQuery(Contact.class,
                    "email == emailStr");
					q.declareParameters("String emailStr"); //query to fetch based on email

				List<Contact> results = (List<Contact>) q.execute(emailParam); //list of results from Datastore

				if (!results.isEmpty()) {
					
					ModelAndView error=new ModelAndView("redirect:/index.jsp");
					error.addObject("alreadyregistred","You are already Registred..!"); 
					return error;
				  } 
				else {
					  pm.makePersistent(c);
						HttpSession session=request.getSession();
						session.setAttribute("user", c); 
						//view.addObject(c);
				  }  
			//System.out.println(results);  
		     
		}
		finally {
			pm.close();
		}
		
		return view;   
	}
	@RequestMapping("/dashboard")
	public ModelAndView dashboard(HttpServletRequest request)
	{
		ModelAndView view =new ModelAndView("welcome");
		
		HttpSession session=request.getSession();
		if(session.getAttribute("user") == null)
		{
			view=new ModelAndView("redirect:/index.jsp");
		}
		return view;
	}
	@RequestMapping("/dashboards")
	public ModelAndView loginbackbutton()
	{
		return new ModelAndView("welcome");
	}
	
	

	@RequestMapping("/login")
	public ModelAndView login(@RequestParam("username")String username,@RequestParam("password")String password,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		
		PersistenceManager pm=PMF.get().getPersistenceManager();
		ModelAndView view=new ModelAndView("redirect:/dashboard");
		try {
			Query q = pm.newQuery(Contact.class);
                    q.setFilter("email == '"+username+"'  && password == '"+password+"' && status =='true'");//query to fetch data based on email and password
					
					List<Contact> results = (List<Contact>) q.execute(); //list of results from Datastore
					
					System.out.println("results : "+results);
                    
					if (!results.isEmpty()) { 
						Contact c=results.get(0);  
						if(c.getPassword().equals(password))
						{
							HttpSession session=request.getSession();
							session.setAttribute("user",c);
						}  
						else {
							ModelAndView error=new ModelAndView("redirect:/index.jsp");
							error.addObject("logininvalid","Invalid Credentials..!");
							 
					          return error;
					       } 
					 }
					else {
						  
						ModelAndView error=new ModelAndView("redirect:/index.jsp");
						error.addObject("logininvalid","Invalid Credentials..!");
				          return error;
				       } 

					
		}
		finally {
			pm.close();
		}
		return view;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public  void update(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException
	{
		        
		          
		          // ModelAndView view=new ModelAndView("welcome");
		           HttpSession session=request.getSession();
		            Contact c=(Contact)session.getAttribute("user");
		            String username=c.getEmail();
		            PersistenceManager pm=PMF.get().getPersistenceManager();
		            
		           //getting data from XHR  
		            String name=request.getParameter("name");
		            String email=request.getParameter("email");
		            String phoneno=request.getParameter("mobilenumber");
		            String address=request.getParameter("address");
		             
		            PrintWriter out=response.getWriter();
		           		            
		            try {
		            	Query q=pm.newQuery(Contact.class,"email == '"+username+"'");//query to fetch data based on key 
		            	
		            	List<Contact> results = (List<Contact>) q.execute();  
		            	Contact user=results.get(0);
		                if(name.contains(user.getName()) && email.contains(user.getEmail()) && phoneno.contains(user.getMobileNumber()) && address.contains(user.getAddress())) 
		                {
		                	HashMap<String,String> noupdate=new HashMap<String,String>();
		                	noupdate.put("status","notupdated");
		                	noupdate.put("message","No changes to Modify");
		                	String notupdated = new Gson().toJson(noupdate);
		                	response.setContentType("application/json");
		                	out.print(notupdated);
		                	
		                }
		                else
		                {
		            	user.setName(name);
		            	user.setMobileNumber(phoneno);
		            	user.setAddress(address);
                        user.setEmail(email);
                    
                        session.setAttribute("user",user);
                        
		            	HashMap<String,String> updateDetails=new HashMap<String,String>();
		            	updateDetails.put("status", "updated"); 
		            	updateDetails.put("message","modified");
		            	updateDetails.put("name",name);
		            	updateDetails.put("email",email);
		            	updateDetails.put("phoneno",phoneno);
		            	updateDetails.put("address",address);	
		            	String userDataObj = new Gson().toJson(updateDetails);
		            	
		            	response.setContentType("application/json");
		            	out.write(userDataObj);
		    			
		    			
		                }
		            	
		            }
		            finally {
		            	pm.close();  
		      }    
	}
	@RequestMapping(value="/delete")
	public ModelAndView delete(HttpServletRequest request) 
	{
		 HttpSession session=request.getSession();
         Contact c=(Contact)session.getAttribute("user");
         String username=c.getEmail();
         PersistenceManager pm=PMF.get().getPersistenceManager();
         ModelAndView redirect=new ModelAndView("redirect:/index.jsp");
         try {
        	 Query q=pm.newQuery(Contact.class,"email == '"+username+"'");//query to fetch data based on key 
         	
         	List<Contact> results = (List<Contact>) q.execute();  
         	Contact user=results.get(0);
        	 //pm.deletePersistent(user);
         	user.setstatus("false");
         	 session=request.getSession(false); 
         	session.invalidate(); 
         }
         finally {
        	 pm.close();
         }
         
         
      return redirect;   
	}
}
