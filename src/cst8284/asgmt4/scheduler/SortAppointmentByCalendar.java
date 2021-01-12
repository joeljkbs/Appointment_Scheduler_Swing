
package cst8284.asgmt4.scheduler;

import java.util.Comparator;


public class SortAppointmentByCalendar implements Comparator<Appointment> {

	/**
	 * compare method is used to return values 0, 1 or -1 if appointment 1 is equal
	 * to, greater than or less than appointment 2.
	 * 
	 * @param a1 Appointment object used for comparison for use by collection.sort
	 * @param a2 Appointment object used for comparison for use by collections.sort
	 * @return int 0,1 or -1 is returned if appointments is equal, greater or less
	 *         than other appointment.
	 */
	public int compare(Appointment a1, Appointment a2) {
		return a1.getApptDate().compareTo(a2.getApptDate());
	}
}
