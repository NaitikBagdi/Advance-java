package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employee;
import service.EmployeeService;
import service.serviceImpl.EmployeeServiceImpl;

/**
 * Servlet implementation class for handling employee registration requests
 * This servlet redirects GET requests to the registration page and delegates POST requests to the EmployeeService
 * for handling employee registration
 * 
 * Servlet implementation class EmployeeServlet
 * @author Naitik Bagdi
 */
@WebServlet("/register")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Employee employee = new Employee();
	EmployeeService service = new EmployeeServiceImpl();

	/**
	 * Redirects GET requests to the employee registration page
  	 * 
	 * @param request The HttpServletRequest object containing the client's request information.
	 * @param response The HttpServletResponse object for sending the response back to the client.
	 * @throws ServletException If the servlet encounters difficulty.
	 * @throws IOException If an input or output exception occurs while the servlet is handling the GET request.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("registration.jsp");
	}

	/**
	 * Handles POST requests for employee registration
	 * 
	 * @param request The HttpServletRequest object containing the client's request information
	 * @param response The HttpServletResponse object for sending the response back to the client
	 * @throws ServletException If the servlet encounters difficulty
	 * @throws IOException If an input or output exception occurs while the servlet is handling the POST request
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service.userRegistraion(request, response, employee);
	}

}
