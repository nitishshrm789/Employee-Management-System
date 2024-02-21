package jsp_employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

@WebServlet("/login")
public class Login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String email = req.getParameter("email");
	    String password = req.getParameter("password");
	   
	    String dbPassword = null ;

	    try  {
	    	

	        EmployeeCRUD crud = new EmployeeCRUD() ;
	        	
	        dbPassword = crud.login(email) ;
	            
	        System.out.println("Password : "+dbPassword);
	            
	        if(dbPassword != null)
	        {
	        	if(dbPassword.equals(password))
	            {
//	        		out.print("Login Success");
	        		
	        		
	        		//To Create cookies
	        		Cookie cookie = new Cookie("session", email) ;
	        		resp.addCookie(cookie);
	        		
	        		
	        		//To Create HTTPSession
	        		HttpSession session = req.getSession() ;
	        		System.out.println(session);
	        		session.setAttribute("hs", "987654321");
	        		
	        		
	        		
	        		List<Employee> list = crud.getAllEmployee() ;
	        		req.setAttribute("list", list);
	        		req.getRequestDispatcher("success.jsp").forward(req, resp); 
//	        		resp.sendRedirect("success.jsp");
	        		
	        		
//	        		resp.sendRedirect("https://www.facebook.com/login/");
	            }
	        	else {
//	        		out.print("Invalid Password");
	        		req.getRequestDispatcher("login.html").forward(req, resp);
	        	}
	        }
	        else {
//	        	out.print("Registration failed");
	        	System.out.println("I am inside else...");
	        	req.getRequestDispatcher("home.html").forward(req, resp); 
	        }
	            
//	            RequestDispatcher ref = req.getRequestDispatcher("success.html") ;
//	    		ref.forward(req, resp);
	            
	    } catch (Exception e) {
	        resp.getWriter().println("Error occurred: " + e.getMessage());
	        e.printStackTrace();
	        System.out.println("Inside Exception");
	    }
		
	}
	
}
