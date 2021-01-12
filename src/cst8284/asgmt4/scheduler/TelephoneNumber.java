
package cst8284.asgmt4.scheduler;

import java.io.*;


public class TelephoneNumber implements Serializable {
	/**
	 * int field areaCode used to store the value of areaCode
	 */
	private int areaCode;
	/**
	 * int field prefix used to store the value of prefix
	 */
	private int prefix;
	/**
	 * int field lineNumber used to store the value of lineNumber
	 */
	private int lineNumber;

	/**
	 * Single argument constructor that sets all the fields of telephoneNumber if
	 * there are no exceptions thrown.
	 * 
	 * @param phoneNumber String phoneNumber is used as a parameter to set all the
	 *                    fields of the class.
	 * @throws BadAppointmentDataException is thrown if the phoneNumber doesn't meet
	 *                                     the necessary conditions like if it
	 *                                     starts with a '0'or'1', or if the string
	 *                                     isnt exactly equal to 12.
	 */
	public TelephoneNumber(String phoneNumber) {
		if (phoneNumber.length() != 12) {
			throw new BadAppointmentDataException("Missing digit(s); correct format is AAA-PPP-NNNN,"
					+ " where AAA is the area code and PPP-NNNN is the local number", "Incorrect format");
		} else if (phoneNumber.charAt(0) == '0' || phoneNumber.charAt(0) == '1') {
			throw new BadAppointmentDataException("Area code can’t start with a ‘0’ or a ‘1’", "Invalid Number");
		} else if (!(IsBadCharacter(phoneNumber))) {
			throw new BadAppointmentDataException(
					"Telephone numbers can only contain " + "numbers or the character ‘-‘",
					"Bad character(s) in input string");
		} else {
			setAreaCode(Integer.parseInt(phoneNumber.substring(0, 3)));
			setPrefix(Integer.parseInt(phoneNumber.substring(4, 7)));
			setLineNumber(Integer.parseInt(phoneNumber.substring(8, 12)));
		}
	}

	/**
	 * Checks if the format of phoneNumber from the parameter is correct or not.
	 * Returns false, if it contains any value other than '-' or a number.
	 * 
	 * @param phoneNumber The phoneNumber that needs to evaluated for the format
	 *                    check
	 * @return <code>true</code> if there is no inconsistency with the phoneNumber
	 *         <code>false</code> if the format of phoneNumber is not correct.
	 * 
	 */
	private boolean IsBadCharacter(String phoneNumber) {
		for (int i = 0; i < phoneNumber.length(); i++) {
			if (!(phoneNumber.charAt(i) == '-' || Character.isDigit(phoneNumber.charAt(i))))
				return false;
		}

		return true;
	}

	/**
	 * returns the int value of the areaCode field of telephoneNumber class.
	 * 
	 * @return the int value of the areaCode field of telephoneNumber class.
	 */
	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * returns the int value of the prefix field of telephoneNumber class.
	 * 
	 * @return the int value of the prefix field of telephoneNumber class.
	 */
	public int getPrefix() {
		return prefix;
	}

	public void setPrefix(int prefix) {
		this.prefix = prefix;
	}

	/**
	 * returns the int value of the lineNumber field of telephoneNumber class.
	 * 
	 * @return the int value of the lineNumber field of telephoneNumber class.
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * Used to return a String of all the three fields concatenated to form a (AAA)
	 * PPP-NNNN format, then used whenever we output an object of telephoneNumber.
	 * 
	 * @return A String of all the three fields concatenated to form a (AAA)
	 *         PPP-NNNN format.
	 */
	public String toString() {
		return "(" + getAreaCode() + ") " + getPrefix() + "-" + getLineNumber();
	}

}
