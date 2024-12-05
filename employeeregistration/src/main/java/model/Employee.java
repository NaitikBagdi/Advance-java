package model;

import lombok.Getter;
import lombok.Setter;

/**
 * A model class representing an employee
 * This class encapsulates the properties of an employee, such as their name, username, password, contact number, and address
 * It utilizes Lombok annotations for getter and setter methods generation
 * 
 * @author Naitik Bagdi
 */
@Setter
@Getter
public class Employee {

	private Integer id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String contactNumber;
	private String address;

}
