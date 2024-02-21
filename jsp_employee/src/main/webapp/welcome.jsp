<!DOCTYPE html>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>
    <%
        String username = "User"; // default username
        // you can retrieve username from session, request parameters, or any other source
        // for example, if username is passed as a request parameter with name "username"
        // String username = request.getParameter("username");

        // printing the welcome message
        out.println("<h1>Welcome " + username + "!</h1>");
    %>
    
    <%
    	String message = (String) request.getAttribute("message") ;
    
    	if(message != null)
    	{
    		
    	}
    %>
</body>
</html>



