
package cst8284.asgmt4.employee;

import java.util.ArrayList;

public class Dentist extends Employee {

	/**
	 * The no arg constructor uses the no arg constructor of Employee Class.
	 */
	public Dentist() {
		super();
	}

	/**
	 * one arg constructor used to set the fullname to the name field using the
	 * constructor of its superclass, the Employment class.
	 * 
	 * @param fullname Takes the parameter fullname to set the fullName field.
	 */
	public Dentist(String fullname) {
		super(fullname);
	}

	/**
	 * This method uses a switch case to return a String activitytype, that is read
	 * by input from the user.
	 * 
	 * @return A String value of activity type is returned read by the user input
	 *         when a menu is shown.
	 */
	public String[] getActivityType() {
//		ArrayList<String> list=new ArrayList<>();
//		list.add("Assessment");
//		list.add("Filling");
//		list.add("Crown");
//		list.add("Cosmetic Repairs");
		String[] list= {"Assessment","Filling","Crown","Cosmetic Repairs"};
		
		
		
		return list;
	}
}
