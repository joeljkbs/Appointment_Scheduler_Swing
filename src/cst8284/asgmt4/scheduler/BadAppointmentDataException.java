
package cst8284.asgmt4.scheduler;

public class BadAppointmentDataException extends java.lang.RuntimeException {
	/**
	 * String object used to store description of the exception thrown
	 */
	private String description;

	/**
	 * No arg constructor used to chain to the 2 arg constructor to set the
	 * description field of the BadAppointmentDataException class, and the 1 arg
	 * constructor of runtimeexception
	 */
	BadAppointmentDataException() {
		this("Please try again", "Bad data entered");
	}

	/**
	 * 2 Arg constructor used to set the description field and the one arg
	 * constructor of RuntimeException
	 * 
	 * @param s1 A String passed to the one arg constructor of super class
	 *           RuntimeException
	 * @param s2 A string used to set description field.
	 */
	BadAppointmentDataException(String s1, String s2) {
		super(s1);
		setDescription(s2);
	}

	/**
	 * Setter for the description field used to populate the description with
	 * argument string s
	 * 
	 * @param s String s used to fill in the desription field of
	 *          BadAppointmentDataException object.
	 */
	public void setDescription(String s) {
		description = s;
	}

	/**
	 * Used to return the String description field's value
	 * 
	 * @return String stored in description field of BadAppointmentDataException
	 *         object.
	 */
	public String getDescription() {
		return description;
	}
}
