<%@ page 
   import="java.util.GregorianCalendar"
   import="java.util.Calendar" %>
<html>
<head>

</head>

<body>
<%
   GregorianCalendar calendar = new GregorianCalendar();
   int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
   int second = calendar.get(Calendar.SECOND);
   
   out.println("The tah tah hime is " + second);
   
   
%>
    
</body>
</html>