
import java.util.LinkedList;
import java.util.Scanner;

public class ContactDetails2 {
	
	
	 String name,email,address;
	 long mobileNumber;
	
	LinkedList<PojoContactDetails> details=new LinkedList<PojoContactDetails>();
	
	Scanner input=new Scanner(System.in);
	
	public static void main(String args[])
	{
		ContactDetails2 object=new ContactDetails2();
		
		Scanner input2=new Scanner(System.in);
		
		while(true)
		{
		  //listing the options 
		  System.out.println("1.Add Contact");
		  System.out.println("2.List Contact");
		  System.out.println("3.Update");
		  System.out.println("4.Search");
		  System.out.println("5.Delete");
		  System.out.println("6.Exit");
		  System.out.println("Enter your choice from 1 to 6");
		  
		  //Storing user choice
		  int choice=input2.nextInt();
		  
		  //switch for going to required choice function
		  switch(choice)
		   {
		   case 1:object.addContact(); 
	         break;
	       case 2:object.showContact();
	         break;
	       case 3:object.update();
	         break;
	       case 4:object.search();
	         break;
	       case 5:object.delete();
	          break;
	       case 6:System.exit(0);  
	        default:System.out.println("Invalid choice");    
		    }
		}
	}
	
	public ContactDetails2 addContact()
	{
		  try
		  {
			 System.out.println("Enter your name");
	         name=input.next();
	         System.out.println("Enter your email");
	         email=input.next();  
	         System.out.println("Enter your address");
	         address=input.next();
	         System.out.println("Enter your Mobile number");
	         mobileNumber=input.nextLong();   
	         details.add(new PojoContactDetails(name,email,address,mobileNumber));
	         System.out.println("Successfully inserted your details are:");
	         System.out.println(name+" "+email+" "+address+" "+mobileNumber); 
	         System.out.println("Would you like to add another details");
	         System.out.println("Enter yes or no");
	         String addDetailsChoice=input.next();
	         if(addDetailsChoice.equals("yes"))
	          addContact();
		  }
		  catch(Exception e)
		    {
			  e.printStackTrace(); 
		    }
		return null;   
	}
	public ContactDetails2 showContact()
	{
		try
		{
		if(!details.isEmpty())
		{
		for(PojoContactDetails show:details)
			System.out.println(show.getName()+"  "+show.getEmail()+"  "+show.getMobileNumber()+"  "+show.getAddress());
			System.out.println("would you like to go to Menu");
			System.out.println("Enter y for yes n for No"); 
			char choice=input.next().charAt(0);
			if(choice=='N' || choice=='n')
				System.exit(0);
		}
		else
		{
			System.out.println("Contact details are empty"); 
			System.out.println("Would you like to continue press any key and click enter");
			char continues=input.next().charAt(0);
		}
	  }
		  catch(Exception e)
		    {
			  e.printStackTrace(); 
		    }
		return null;
	}
	public ContactDetails2 update()
	{
		try
		{
		if(!details.isEmpty())
		{
		 System.out.println("Avaliable contact details are:"); 
		 for(PojoContactDetails show:details)
		System.out.println(show.getName()+"  "+show.getEmail()+"  "+show.getMobileNumber()+"  "+show.getAddress());
		 System.out.println("Please Enter the name for which details you want to update");
		 String update=input.next();
		 boolean found=false;
		 for(PojoContactDetails show:details)
		 {
			 if(show.getName().contains(update)) 
			 {
				  found=true;
				  System.out.println("1.Name"); 
				  System.out.println("2.Email"); 
				  System.out.println("3.Mobile number");
				  System.out.println("4.Address");
				  System.out.println("Select the which details you want to update");
				  int choice=input.nextInt();
				  if(choice==1)
				  {
					  System.out.println("Enter the name");
					  show.setName(input.next()); 
				  }
				  else if(choice==2)
				  {
					  System.out.println("Enter the email");
					  show.setEmail(input.next());
				  }
				  else if(choice==3)
				  {
					  System.out.println("Enter the mobile number");
					  show.setMobileNumber(input.nextLong());
				  }
				  else if(choice==4)
				  {
					  System.out.println("Enter the address");
					  show.setAddress(input.next());  
				  }
				  else
				   System.out.println("Invalid choice");
				 
				System.out.println("Your updated details are:"); 
				System.out.println(show.getName()+"  "+show.getEmail()+"  "+show.getMobileNumber()+"  "+show.getAddress());
			 }  
		 }
		  if(found==false)
			  System.out.println("You entered name is not there in the list");
		 System.out.println("Would you like to update another details");
         System.out.println("Enter yes or no");
         String updateDetailsChoice=input.next();
         if(updateDetailsChoice.equals("yes"))
        	 update();
		}
		else
		{
			System.out.println("Contact details are empty"); 
			System.out.println("Would you like to continue press any key and click enter");
			char continues=input.next().charAt(0);
		}	
	 }
		  catch(Exception e)
		    {
			  e.printStackTrace(); 
		    }
		return null;
	}
	public ContactDetails2 search()
	{
		try
		{
		 if(!details.isEmpty())
		 {
		 System.out.println("Enter any character to search the contact details");  
		    String searchContactDetails=input.next();
		    int length=searchContactDetails.length();
		    char[] charSearchContactDetails=searchContactDetails.toCharArray();
		    boolean found=false;
		    for(PojoContactDetails search:details)
		    {
		    	char[] name=search.name.toCharArray();
		    	for(int i=0;i<length;i++)
		    	{
		    	  if(charSearchContactDetails[i]==name[i])
		    	    found=true;
		    	  else
		    		  found=false; 	 
		    	}
		    	if(found==true)
		    	 System.out.println(search.getName()+"  "+search.getEmail()+"  "+search.getMobileNumber()+"  "+search.getAddress());

		    }
		    if(found==false)
		    	System.out.println("Not there in contact list");
		    
		    System.out.println("Would you like to search another details");
	        System.out.println("Enter yes or no");
	        String searchDetailsChoice=input.next();
	        if(searchDetailsChoice.equals("yes"))
	        	search();
		 }
		 else
			{
				System.out.println("Contact details are empty"); 
				System.out.println("Would you like to continue press any key and click enter");
				char continues=input.next().charAt(0);
			}
		 }
		  catch(Exception e)
		    {
			  e.printStackTrace(); 
		    }
		return null; 
	}
	public ContactDetails2 delete() 
	{
		try
		{
		if(!details.isEmpty())
		{
		 System.out.println("Avaliable contact details are:"); 
		 for(PojoContactDetails show:details)
			 System.out.println(show.getName()+"  "+show.getEmail()+"  "+show.getMobileNumber()+"  "+show.getAddress());
		 System.out.println("Enter the name for delete the details");
		 String deleteContactDetails=input.next();
		
		 for(PojoContactDetails delete:details)
		 {
			 if(delete.getName().contains(deleteContactDetails))
			 {  
				 int index=details.indexOf(delete);   
				 details.remove(index);
				 System.out.println("Remaining contact details are:");
				 for(PojoContactDetails show:details)
						System.out.println(show.name+"  "+show.email+"  "+show.mobileNumber+"  "+show.address);
			 }
		 }
		 System.out.println("Would you like to delete another details");
	        System.out.println("Enter yes or no"); 
	        String deleteDetailsChoice=input.next(); 
	        if(deleteDetailsChoice.equals("yes"))
	        	delete();
		}
		else
		{
			System.out.println("Contact details are empty"); 
			System.out.println("Would you like to continue press any key and click enter");
			char continues=input.next().charAt(0);
		}
	 }
		  catch(Exception e)
		    {
			  e.printStackTrace(); 
		    }
		return null;
	}
	
}
