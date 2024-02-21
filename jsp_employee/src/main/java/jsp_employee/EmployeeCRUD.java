package jsp_employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class EmployeeCRUD {
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{	
		Class.forName("com.mysql.cj.jdbc.Driver") ;
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp", "root", "root") ;
		
		return connection ;
	}
	
	public int signUpUser(Employee employee) throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection() ;
		
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee	VALUES(?, ?, ?, ?, ?, ?)") ;
		
		preparedStatement.setInt(1, employee.getId());
		preparedStatement.setString(2, employee.getName());
		preparedStatement.setLong(3, employee.getPhone());
		preparedStatement.setString(4, employee.getAddress());
		preparedStatement.setString(5, employee.getEmail());
		preparedStatement.setString(6, employee.getPassword());
		
		
		int count = preparedStatement.executeUpdate() ;
		
		connection.close();
		
		return count ;
		
	}
	
	public String login(String email) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT * FROM employee WHERE email = ?";
		
		Connection connection = getConnection() ;
		PreparedStatement preparedStatement = connection.prepareStatement(sql) ;
		
		preparedStatement.setString(1, email);
        
        ResultSet result = preparedStatement.executeQuery() ;
        String dbPassword = null ;
        System.out.println("data contain : "+result.next());
        
        while(result.next())
        {
        	System.out.println("I am inside while loop");
        	dbPassword = result.getString("password") ;
        }
        
        return dbPassword ;
	}
	
	
	public String loginController(String email) throws Exception
	{
		String sql = "SELECT * FROM employee WHERE email = ?";
		
		Connection connection = getConnection() ;
		PreparedStatement preparedStatement = connection.prepareStatement(sql) ;

        preparedStatement.setString(1, email);
        
        ResultSet result = preparedStatement.executeQuery() ;
        String password = null ;
        
        while(result.next())
        {
        	password = result.getString("password") ;
        }
        
        return password ;
	}
	
	public List<Employee> getAllEmployee() throws Exception
	{
		Connection connection = getConnection() ;
		String sql = "SELECT * FROM employee";

		PreparedStatement preparedStatement = connection.prepareStatement(sql) ;
		 
		ResultSet result = preparedStatement.executeQuery() ;
		
		List<Employee> list = new ArrayList<>() ;
		
		while(result.next())
		{
			Employee employee = new Employee() ;
			
			employee.setId(result.getInt("id"));
			employee.setName(result.getString("name"));
			employee.setPhone(result.getLong("phone"));
			employee.setAddress(result.getString("address"));
			employee.setEmail(result.getString("email"));
			employee.setPassword(result.getString("password"));
			
			
			list.add(employee) ;
		}
		
		connection.close();
		
		return list ;
		
	}
	
	
	public int deleteEmployee(int id) throws Exception
	{
		Connection connection = getConnection() ;
		String sql = "DELETE FROM EMPLOYEE WHERE ID=?";
		
		
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql) ;
		preparedStatement.setInt(1, id);
		
		int result = preparedStatement.executeUpdate() ;
		connection.close();
		
		return result ;
	}
	
	
	public Employee update(int id) throws Exception {
		 Connection connection = getConnection();
		 PreparedStatement preparedStatement =connection.prepareStatement("Select * from employee where id=?");
		preparedStatement.setInt(1, id);
		 ResultSet set = preparedStatement.executeQuery();
		 
		Employee employee = new Employee();
		while (set.next()) {
			employee.setId(set.getInt("id"));
			employee.setName(set.getString("name"));
			employee.setPhone(set.getLong("phone"));
			employee.setEmail(set.getString("email"));
			employee.setAddress(set.getString("address"));
			employee.setPassword(set.getString("password"));
		}
		 connection.close();
		return employee;
		 
	 }
	
	public int updateEmployee(Employee employee) throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection(); 
		String sql = "update employee set id=?,name=?,phone=?,address=?,email=?,password=? where email = ? ";
		PreparedStatement preparedStatement= connection.prepareStatement(sql);
		preparedStatement.setInt(1,employee.getId());
		preparedStatement.setString(2, employee.getName());
		preparedStatement.setLong(3, employee.getPhone());
		preparedStatement.setString(4, employee.getEmail());
		preparedStatement.setString(5, employee.getAddress());
		preparedStatement.setString(6, employee.getPassword());
		
		
		preparedStatement.setString(7, employee.getEmail());
		
		int result = preparedStatement.executeUpdate();
		connection.close();
		return result;
	}
	
}
