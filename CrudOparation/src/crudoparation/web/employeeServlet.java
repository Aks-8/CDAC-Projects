package crudoparation.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudoparation.dao.employeedao;
import crudoparation.model.employee;

/**
 * Servlet implementation class employeeServlet
 */
@WebServlet("/")
public class employeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private employeedao employeedao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public employeeServlet() {
        this.employeedao = new employeedao();
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       this.doGet(request, response);	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String action  = request.getServletPath();
		
		switch(action){
		  case "/new":
			  showNewForm(request, response);
			   break;
		  case "/insert":
			  try {
				insertEmployee(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  break;
		  case "/delete" :
			  try {
				deleteEmployee(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  break;
		  case "/edit":
			   try {
				showEditForm(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  break;
		 case "/update":
			 try {
				updateEmployee(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  break;
		 default :
			 try {
				listEmployee(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				  break;
		}
	}
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException,IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
	  throws SQLException,IOException{
		String name = request.getParameter("name");
		String emailId = request.getParameter("emailId");
		String designation = request.getParameter("designation");
		String phone = request.getParameter("phone");
		employee newEmployee = new employee(name, designation, emailId, phone);
		employeedao.insertEmployee(newEmployee);
        response.sendRedirect("list");   	    
	}
	
	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
	  throws SQLException,IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		employeedao.deleteEmployee(id);
		response.sendRedirect("list");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	  throws SQLException,IOException,ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		employee extemployee = employeedao.selectEmployee(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("employee", extemployee);
		dispatcher.forward(request, response);
	}
	
	private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
	  throws SQLException,IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
	    String emailId = request.getParameter("emailId");
	    String designation = request.getParameter("designation");
	    String phone = request.getParameter("phone");
	    employee employee = new employee(id, name, designation, emailId, phone);
	    employeedao.updateEmployee(employee);
	    response.sendRedirect("list");
	}
	
	private void listEmployee(HttpServletRequest request, HttpServletResponse response)
	  throws SQLException,IOException,ServletException {
		List<employee> listEmployee = employeedao.selectAllEmployee();
		request.setAttribute("listEmployee", listEmployee);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}
}
