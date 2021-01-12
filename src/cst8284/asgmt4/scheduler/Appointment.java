
package cst8284.asgmt4.scheduler;

import java.io.*;
import java.util.Calendar;

public class Appointment implements Serializable {
	/*
	 * A Calendar instance to store date that later can be used to schedule
	 * appointments.
	 */
	private Calendar apptDate;
	/**
	 * String object used to store firstName
	 */
	private String firstName;
	/**
	 * String object used to store lastName
	 */
	private String lastName;
	/**
	 * A TelephoneNumber object used to store the phone number from the user.
	 */
	private TelephoneNumber phone;
	/**
	 * An Activity object used to store the activity to populate the activity field
	 * of appointment
	 */
	private Activity activity;
	public static final long serialVersionUID = 1L;

	/**
	 * 4 arg constructor used to set all the fields by chaining to the 5 arg
	 * constructor.
	 * 
	 * @param cal      A Calendar instance used for setting the apptDate field.
	 * @param fullname A string field for storing fullname used for setting the name
	 *                 field.
	 * @param phone    A TelephonNumber instance used for setting the phone field.
	 * @param activity An Activity instance used for setting the activity field.
	 */
	public Appointment(Calendar cal, String fullname, TelephoneNumber phone, Activity activity) {
		this(cal, fullname.split(" ")[0], fullname.split(" ")[1], phone, activity);
	}

	/**
	 * 5 arg constructor used to set all the fields.
	 * 
	 * @param cal       A Calendar instance used for setting the apptDate field.
	 * @param firstname A string field for storing firstName used for setting the
	 *                  name field.
	 * @param lastname  A string field for storing lastName used for setting the
	 *                  name field.
	 * @param phone     A TelephonNumber instance used for setting the phone field.
	 * @param activity  An Activity instance used for setting the activity field.
	 */
	public Appointment(Calendar cal, String firstname, String lastname, TelephoneNumber phone, Activity activity) {
		setApptDate(cal);
		setFirstName(firstname);
		setLastName(lastname);
		setPhone(phone);
		setActivity(activity);
	}

	/**
	 * This method is used to check if the name inputed as a parameter is of the
	 * correct format or not.
	 * 
	 * @param name The String name which is used to check for format consistentency.
	 * @return <code>true</code> if name format is correct <code>false</code> if
	 *         name format is incorrect
	 * @throws BadAppointmentDataException if name length is more than 30 or name
	 *                                     includes any character other than
	 *                                     alphabet,-,. or ', then the exception is
	 *                                     thrown.
	 */
	public static boolean IsNameCorrect(String name) {
		if (name.isEmpty())
			throw new BadAppointmentDataException("Must enter a value", "Empty or null value entered");
		for (int i = 0; i < name.length(); i++) {
			if (!(Character.isLetter(name.charAt(i)) || name.charAt(i) == '.' || name.charAt(i) == '-'
					|| name.charAt(i) == '\''))
				throw new BadAppointmentDataException(
						"Name cannot include characters other than alphabetic characters,"
								+ " the dash (-), the period (.), and the apostrophe (‘)",
						"Illegal characters in name");
			else if (name.length() > 30)
				throw new BadAppointmentDataException("Name cannot exceed 30 characters",
						"Name exceeds maximum length");
		}

		return true;
	}

	/**
	 * The method returns a boolean and checks for the given input for the date
	 * format consistency. The following method has conditions to check for the date
	 * entered within calendar limit, for the leap year, and all the months.
	 * 
	 * @param cal String input for date in the format 'ddMMyyyy';
	 * @return <code>true</code> if date format is correct <code>false</code> if
	 *         date format is incorrect
	 * @throws BadAppointmentDataException if the date entered is inconsistent with
	 *                                     the normal gregorian calendar format, i.e
	 *                                     any date inconsistency or if the date
	 *                                     contains a charcter other than digits,
	 *                                     this exception is thrown
	 */
	public static boolean IsCalendarFormatCorrect(String cal) {
		boolean correct = true;
		int date = Integer.parseInt(cal.substring(0, 2));
		int month = Integer.parseInt(cal.substring(2, 4)), year = Integer.parseInt(cal.substring(4, 8));

		for (char ch : cal.toCharArray()) {
			if (!(Character.isDigit(ch)))
				return false;
		}
		if (month == 2) {
			if (year % 4 == 0) {
				if (year % 100 == 0) {
					if (year % 400 == 0) {
						if (date < 1 || date > 29)
							correct = false;
					} else {
						if (date < 1 || date > 28)
							correct = false;
					}
				} else {
					if (date < 1 || date > 29)
						correct = false;
				}
			} else {
				if (date < 1 || date > 28)
					correct = false;
			}
		}

		else if (month <= 7 && month >= 1) {
			if (month % 2 == 0) {
				if (date <= 1 || date > 30)
					correct = false;
			}

			else {
				if (date <= 1 || date > 31)
					correct = false;
			}

		} else if (month > 7 && month <= 12) {
			if (month % 2 == 0) {
				if (date <= 1 || date > 31)
					correct = false;
			}

			else {
				if (date <= 1 || date > 30)
					correct = false;
			}
		}
		if (correct == false)
			throw new BadAppointmentDataException("Bad calendar date entered; format is DDMMYYYY",
					"Bad calendar format");
		return correct;
	}

	/**
	 * returns apptDate field of the Appointment instance
	 * 
	 * @return apptDate of type Calendar.
	 */
	public Calendar getApptDate() {
		return apptDate;
	}

	/**
	 * sets the Calendar apptDate argument to the field apptDate
	 * 
	 * @param apptDate Calendar apptDate used to set the apptDate field in an
	 *                 appointment instance.
	 */
	public void setApptDate(Calendar apptDate) {
		this.apptDate = apptDate;
	}

	/**
	 * returns firstName field of the Appointment instance
	 * 
	 * @return firstName of type String.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * sets the firstName argument to the field firstName
	 * 
	 * @param firstName String firstName used to set the firstName field in an
	 *                  appointment instance.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * returns lastName field of the Appointment instance
	 * 
	 * @return lastName of type String.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * sets the lastName argument to the field lastName
	 * 
	 * @param lastName String lastnName used to set the lastName field in an
	 *                 appointment instance.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * returns phone field of the Appointment instance
	 * 
	 * @return value of phone field of type TelephoneNumber.
	 */
	public TelephoneNumber getPhone() {
		return phone;
	}

	/**
	 * sets the phone argument to the field phone
	 * 
	 * @param phone object of TelephoneNumber used to set the phone field in an
	 *              appointment instance.
	 */
	public void setPhone(TelephoneNumber phone) {
		this.phone = phone;
	}

	/**
	 * returns activity field of the Appointment instance
	 * 
	 * @return value of phone field of type TelephoneNumber.
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * sets the activity argument to the field activity
	 * 
	 * @param activity object of Activity used to set the activity field
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/**
	 * Used to return a String of all the three fields concatenated to form a (AAA)
	 * PPP-NNNN format, then used whenever we output an object of telephoneNumber.
	 * 
	 * @return A String of all the three fields concatenated to form a (AAA)
	 *         PPP-NNNN format.
	 */
	public String toString() {
		return getApptDate().getTime().toString() + "\n" + getFirstName() + " " + getLastName() + "\n"
				+ phone.toString() + "\n" + getActivity().toString();
	}

}
