package service.serviceImpl;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;
import service.EmployeeService;
import util.DataBaseConnection;

/**
 * An implementation of the EmployeeService interface for handling employee registration
 * This class provides methods to register employees, check for existing usernames and phone numbers,
 * and retrieve a list of registered employees
 * It extends HttpServlet to handle HTTP servlet requests
 * 
 * @author Naitik Bagdi
 */
public class EmployeeServiceImpl extends HttpServlet implements EmployeeService {

	private static final long serialVersionUID = 1L;
	Connection connnection = DataBaseConnection.getConnection();

	/**
	 * Registers a new employee based on the provided employee details
	 * This method checks for existing usernames and phone numbers before proceeding with registration
	 * If the registration is successful, it redirects to a JSP page displaying the registration list
  	 * 
	 * @param request The HttpServletRequest object containing the client's request information
	 * @param response The HttpServletResponse object for sending the response back to the client
	 * @param employee An Employee object representing the details of the employee to be registered
	 */	
	@Override
	public void userRegistraion(HttpServletRequest request, HttpServletResponse response, Employee employee) {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String contactNumber = request.getParameter("contactnumber");
		String address = request.getParameter("address");

		boolean isNull = (firstName == null || lastName == null || userName == null || password == null
				|| contactNumber == null || address == null);

		// Set values in the employee object
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setUserName(userName);
		employee.setPassword(password);
		employee.setContactNumber(contactNumber);
		employee.setAddress(address);
		try {
			boolean value = checkUserExist(employee) || checkPhoneNumberExist(employee);
			if (value) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.print("exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("upersaveRegistration");
		System.out.println(isNull);
		if (!isNull) {
			if (saveRegistraion(employee) == 1) {
				registrationList(request, response);
			}
		}
	}

	private boolean checkPhoneNumberExist(Employee employee) {
		boolean phoneNumberResult = false;
		selectQuery = "Select contact_number from employee_registration where contact_number = ?";
		try {
			PreparedStatement preparedStatementObject = connnection.prepareStatement(selectQuery);
			preparedStatementObject.setString(1, employee.getContactNumber());
			ResultSet resultSet = preparedStatementObject.executeQuery();
			phoneNumberResult = resultSet.next();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return phoneNumberResult;
	}

	/**
	 * Checks if a given phone number already exists in the employee registration database
	 * 
	 * @param employee An Employee object containing the phone number to be checked
	 * @return true if the phone number already exists in the database, false otherwise
	 */
	private boolean checkUserExist(Employee employee) {
		boolean userNameResult = false;
		selectQuery = "Select username from employee_registration where username = ?";
		try {
			PreparedStatement preparedStatementObject = connnection.prepareStatement(selectQuery);
			preparedStatementObject.setString(1, employee.getUserName());
			ResultSet resultSet = preparedStatementObject.executeQuery();
			userNameResult = resultSet.next();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return userNameResult;
	}

	/**
	 * Saves the registration details of an employee into the employee registration database
	 * 
	 * @param employee An Employee object containing the registration details to be saved
	 * @return An integer indicating the outcome of the save operation:
	 *         - 1 if the registration details were successfully saved
	 *         - 0 if an error occurred during the save operation
	 */
	private int saveRegistraion(Employee employee) {
		int registrationResult = 0;
		insertQuery = "Insert into employee_registration (first_name, last_name, username, password, contact_number, address)"
				+ " value(?,?,?,?,?,?)";
		try {
			PreparedStatement preparedStatementObject = connnection.prepareStatement(insertQuery);
			preparedStatementObject.setString(1, employee.getFirstName());
			preparedStatementObject.setString(2, employee.getLastName());
			preparedStatementObject.setString(3, employee.getUserName());
			preparedStatementObject.setString(4, employee.getPassword());
			preparedStatementObject.setString(5, employee.getContactNumber());
			preparedStatementObject.setString(6, employee.getAddress());
			registrationResult = preparedStatementObject.executeUpdate();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return registrationResult;
	}

	/**
	 * Retrieves a list of registered employees from the employee registration database
	 * This method fetches employee details from the database and stores them in a List<Employee>
	 * It also sets the employee list as a session attribute and forwards the request to a JSP file for display
	 * 
	 * @param request The HttpServletRequest object containing the client's request information
	 * @param response The HttpServletResponse object for sending the response back to the client
	 * @return A List<Employee> containing the registered employees' details
	 */
	private List<Employee> registrationList(HttpServletRequest request, HttpServletResponse response) {
		List<Employee> employeeList = new ArrayList<Employee>();
		selectQuery = "Select * from employee_registration";
		try {
			PrintWriter out = response.getWriter();
			PreparedStatement preparedStatementObject = connnection.prepareStatement(selectQuery);
			ResultSet resultSet = preparedStatementObject.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setFirstName(resultSet.getString("first_name"));
				employee.setLastName(resultSet.getString("last_name"));
				employee.setUserName(resultSet.getString("username"));
				employee.setPassword(resultSet.getString("password"));
				employee.setContactNumber(resultSet.getString("contact_number"));
				employee.setAddress(resultSet.getString("address"));
				employeeList.add(employee);
			}
			HttpSession session = request.getSession();
			session.setAttribute("employeeList", employeeList);

			// Forward the request to the JSP file
			RequestDispatcher dispatcher = request.getRequestDispatcher("registrationlist.jsp");
			dispatcher.forward(request, response);
			out.print("<h3 style='color:green'>Registration successfull</h3>");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return employeeList;
	}

}
