package crudoparation.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.mysql.cj.xdevapi.Statement;

import crudoparation.model.employee;

public class employeedao {
    
	private String jdbcURL = "jdbc:mysql://localhost:3308/employee?autoReconnect=true&useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";
    
    private static final String INSERT_EMPLOYEETBL_SQL = "INSERT INTO employeetbl" + "(name, designation,emailId,phone) VALUES " + "(?, ?, ?, ?)";
    
    private static final String SELECT_EMPLOYEE_BY_ID = "select id,name,designation,emailId,phone from employeetbl where id=?";
    private static final String SELECT_ALL_EMPLOYEETBL = "select * from employeetbl";
    private static final String DELETE_EMPLOYEETBL_SQL = "delete from employeetbl where id = ?;";
    private static final String UPDATE_EMPLOYEETBL_SQL = "update employeetbl set name =?,designation =?,emailId =?,phone=? where id = ?;"; 

    protected Connection getConnection(){
    	Connection connection = null;
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    	}catch(SQLException e){
    		e.printStackTrace();
    	}catch(ClassNotFoundException e){
    		e.printStackTrace();
    	}
    	return connection;
    }
    
    //create or insert employee
     public void insertEmployee(employee employee)throws SQLException{
    	 try(Connection connection = getConnection();
    		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEETBL_SQL)){
    			preparedStatement.setString(1, employee.getName());
    			preparedStatement.setString(2, employee.getDesignation());
    			preparedStatement.setString(3, employee.getEmailId());
    			preparedStatement.setString(4, employee.getPhone());
    			preparedStatement.executeUpdate();
    		}catch (Exception e){
    			e.printStackTrace();
    		}
    	}
     
   //execute upadate employee
     public boolean updateEmployee(employee employee)throws SQLException{
    	 boolean rowUpdated;
    	 try(Connection connetion = getConnection();
    			 PreparedStatement statement = connetion.prepareStatement(UPDATE_EMPLOYEETBL_SQL)){
    		         statement.setString(1, employee.getName());
    		         statement.setString(2, employee.getDesignation());
    		         statement.setString(3, employee.getEmailId());
    		         statement.setString(4, employee.getPhone());
    		         statement.setInt(5, employee.getId());
            
    		         rowUpdated = statement.executeUpdate() > 0;                    
    	 }
    	 return rowUpdated;
     }
    
     //select employee by ID
     public employee selectEmployee(int id){
    	 employee employee = null;
    	 //step 1 : Establishing a Connection
    	 try(Connection connection = getConnection();
    	 //step 2: Create a statement using connection object
    		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);){
    		 preparedStatement.setInt(1, id);
    		 System.out.println(preparedStatement);
    	//step 3 : Execute the query or update query
    		 ResultSet rs = preparedStatement.executeQuery();
    	//step 4 : Process the ResultSet Object.
    		 while(rs.next()){
    			 String name = rs.getString("name");
    			 String designation = rs.getString("designation");
    			 String emailId = rs.getString("emailId");
    			 String phone = rs.getString("phone");
    			 employee = new employee(id, name, designation, emailId, phone);
    		 }
    	 }catch(SQLException e){
    		 e.printStackTrace();
    	 }
    	 return employee;
     }
     //Select All Employee
     public List<employee> selectAllEmployee(){
    	 List<employee> employee = new ArrayList<>();
    	 //step 1 : Establishing a Connection
    	 try(Connection connection = getConnection();
    	 //step 2: Create a statement using connection object
    		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEETBL);){
    		 System.out.println(preparedStatement);
    	//step 3 : Execute the query or update query
    		 ResultSet rs = preparedStatement.executeQuery();
    	//step 4 : Process the ResultSet Object.
    		 while(rs.next()){
    			 int id = rs.getInt("id");
    			 String name = rs.getString("name");
    			 String designation = rs.getString("designation");
    			 String emailId = rs.getString("emailId");
    			 String phone = rs.getString("phone");
    			 employee.add(new employee(id, name, designation, emailId, phone));
    		 }
    	 }catch(SQLException e){
    		 e.printStackTrace();
    	 }
    	 return employee;
     }
     //Delete Employee Details
     public boolean deleteEmployee(int id) throws SQLException {
    	 boolean rowDeleted;
    	  try(Connection connection = getConnection();
    			  PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEETBL_SQL);){
    		  statement.setInt(1, id);
    		  rowDeleted = statement.executeUpdate() > 0;
    	  }
    	  return rowDeleted;
     }
}
