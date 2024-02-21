package jsp_employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/signin")
public class EmployeeController extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doPost(req, resp);
		
		int id = Integer.parseInt(req.getParameter("id")) ;
		String name = req.getParameter("name") ;
		long phone = Long.parseLong(req.getParameter("phone")) ;
		String address = req.getParameter("address") ;
		String email = req.getParameter("email") ;
		String password = req.getParameter("password") ;
		
		Employee employee = new Employee() ;
		
		employee.setId(id);
		employee.setName(name);
		employee.setPhone(phone);
		employee.setAddress(address);
		employee.setEmail(email);
		employee.setPassword(password);
		
		EmployeeCRUD crud = new EmployeeCRUD() ;
		
		try {
			int result = crud.signUpUser(employee) ;
			
			if(result != 0)
			{
//				PrintWriter writer = resp.getWriter() ;
//				writer.print("SignUp Success") ;
				req.setAttribute("message", "SignUp success, please Login!");
				req.getRequestDispatcher("login.html").forward(req, resp);
				
				
//				RequestDispatcher ref = req.getRequestDispatcher("login.html") ;
//				ref.forward(req, resp);
			}
			else {
				PrintWriter writer = resp.getWriter() ;
				writer.print("SignUp Failed") ;
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
