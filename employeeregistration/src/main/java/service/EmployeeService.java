package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employee;

/**
 * An interface for handling employee registration.
 * Implementations of this interface are responsible for registering employees.
 * 
 * @author Naitik Bagdi
 */
public interface EmployeeService {

	/**
 	 * Registers a new employee based on the provided employee details
	 * This method should handle the registration process, including any validation and database operations
	 * 
	 * @param request The HttpServletRequest object containing the client's request information
	 * @param response The HttpServletResponse object for sending the response back to the client
	 * @param employee An Employee object representing the details of the employee to be registered
	 */
	public void userRegistraion(HttpServletRequest request, HttpServletResponse response, Employee employee);

}
