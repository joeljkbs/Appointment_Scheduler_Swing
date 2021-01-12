
package cst8284.asgmt4.employee;

import java.util.ArrayList;
import java.util.Scanner;


public abstract class Employee {
	/**
	 * String object used to store fullName
	 */
	private String fullName;

	/**
	 * no arg constructor that chains to another one arg constructor to set the
	 * fullname field
	 */
	protected Employee() {
		this("unknown");
	}

	/**
	 * 
	 * @param fullName String object is taken to set the fullName field of Employee
	 *                 class.
	 */
	protected Employee(String fullName) {
		setName(fullName);
	}

	protected static Scanner scan = new Scanner(System.in);

	/**
	 * This method is a setter used for inputing String value into the fullName
	 * field of the Employee class.
	 * 
	 * @param fullName String object is taken to set the fullName field of Employee
	 *                 class.
	 */
	public void setName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Returns the value of fullName field of the Employee class.
	 * 
	 * @return value of fullName field of the Employee class.
	 */
	public String getName() {
		return fullName;
	}

	/**
	 * Abstract method used by the subclasses of Employee to be overridden to get
	 * the activity type.
	 * 
	 * @return A String ActivityType that can then be used for inputing value into
	 *         the category field of activity object.
	 */
	public abstract String[] getActivityType();

	/**
	 * Used to return the same result as getEmployee();
	 * 
	 * @return value of name field of an Employee.
	 */
	@Override
	public String toString() {
		return getName();
	}

}