package jsp_employee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

//@WebServlet("/login")
public class LoginController extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email") ;
		String password = req.getParameter("password") ;
		
		EmployeeCRUD crud = new EmployeeCRUD() ;
		
		try {
			String dbPassword = crud.loginController(email) ;
			
			if(dbPassword != null)
			{
				if(password.equals(dbPassword))
				{
					req.getRequestDispatcher("success.jsp").forward(req, resp);
				}
				else {
					req.setAttribute("message", "Invalid Password");
					req.getRequestDispatcher("login.jsp").forward(req, resp);
				}
			}
			else {
				System.out.println("Login Failed");
			}
		}
		catch (Exception e) {

			e.printStackTrace();
		}
		
	}
	
}
