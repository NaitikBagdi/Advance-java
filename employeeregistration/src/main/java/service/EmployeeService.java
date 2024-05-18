package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employee;

public interface EmployeeService {

	public void userRegistraion(HttpServletRequest request, HttpServletResponse response, Employee employee);
}