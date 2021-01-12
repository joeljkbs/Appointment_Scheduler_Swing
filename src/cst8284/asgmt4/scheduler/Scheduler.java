
package cst8284.asgmt4.scheduler;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

import cst8284.asgmt4.employee.*;


public class Scheduler {
	
	//private static Scanner scan = new Scanner(System.in);
	
	private static ArrayList<Appointment> appointments = new ArrayList<>();
	
	private static Employee employee;
	


	
	public Scheduler(Employee emp) {
		setEmployee(emp);
		loadAppointmentsFromFile("CurrentAppointments.apts", getAppointments());
	}

	
	public void launch() {
			SchedulerViewer.loadJFrame();
	}

	
	private  void setEmployee(Employee emp) {
		employee = emp;
	}

	
	public static Employee getEmployee() {
		return employee;
	}

	
	

	


	
	public static boolean saveAppointment(Appointment apt) {
		try {
		if(apt!=null) { 
		if (findAppointment(apt.getApptDate()) == null) {

			getAppointments().add(apt);
			return true;
		}}
		 }catch(NullPointerException ex) {throw new RuntimeException("Error, Data not entered correctly. Appointment cannot be saved");}
		return false;
 }

	
	public static boolean deleteAppointment(Calendar cal) {
		if (findAppointment(cal) != null) {
			displayAppointment(cal);
			int returnValue=JOptionPane.showConfirmDialog(null,"Do you want to delete appointment","Delete Appointment",JOptionPane.YES_NO_OPTION);
			if (returnValue==0) {
				appointments.remove(findAppointment(cal));
				JOptionPane.showMessageDialog(null,"Appointment deleted");
			}
			return true;
		} else {
			JOptionPane.showMessageDialog(null,"No such Appointment exists");
			return false;
		}
	}

	
	public static boolean changeAppointment(Calendar cal) {
		if (findAppointment(cal) != null) {
			displayAppointment(cal);
			int returnValue=JOptionPane.showConfirmDialog(null,"Do You want to Change Appointment","Change",JOptionPane.YES_NO_OPTION);
			if (returnValue==0) {
				AppointmentDialog.date.setText(JOptionPane.showInputDialog("Enter new date"));
				AppointmentDialog.time.setText(JOptionPane.showInputDialog("Enter new time"));
				findAppointment(cal).setApptDate(makeCalendarFromUserInput(false));
				JOptionPane.showMessageDialog(null,"Appointment Re-Booked");
				return true;
			}
		}
		JOptionPane.showMessageDialog(null,"No such Appointment exists");
		return false;
	}

	
	public static void displayAppointment(Calendar cal) {
		Appointment apt=findAppointment(cal);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if (apt != null) {
			//System.out.println((("\n" + findAppointment(cal)).toString()) + "\n");
			SchedulerViewer.scrollText.append(findAppointment(cal).toString()+"\n");	
		} else
			SchedulerViewer.scrollText.append("No appointment scheduled between " + hour + ":00 and " + (hour + 1) + ":00\n");

	}

	
	public static void displayDaySchedule(Calendar cal) {
		for (int i = 8; i <= 16; i++) {
			cal.set(Calendar.HOUR_OF_DAY, i);
			displayAppointment(cal);
		}
	}

	
	public static boolean saveAppointmentsToFile(ArrayList<Appointment> apts, String saveFile) {
		try (FileOutputStream objectFileStream = new FileOutputStream(saveFile);
				ObjectOutputStream oos = new ObjectOutputStream(objectFileStream);) {
			for (Appointment thisAppointment : apts)
				oos.writeObject(thisAppointment);
			oos.close();
			objectFileStream.close();
			return true;
		} catch (Exception ex) {
			System.out.println("Error" + ex);
			return false;
		}
	}

	
	public static boolean loadAppointmentsFromFile(String sourceFile, ArrayList<Appointment> apts) {
		apts.clear();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(sourceFile));) {
			while (true) {
				apts.add((Appointment) ois.readObject());
			}
		} catch (ClassNotFoundException e) {
		} catch (EOFException e) {
			return true;
		} catch (IOException e) {
		}
		return false;
	}

	


	
	public static Appointment makeAppointmentFromUserInput() {
		String fullName = AppointmentDialog.name.getText();
		try {
		try {
			Appointment.IsNameCorrect(fullName.split(" ")[0]);
			Appointment.IsNameCorrect(fullName.split(" ")[1]);
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new BadAppointmentDataException("Must enter a value", "Empty or null value entered");
		}
		TelephoneNumber phone = new TelephoneNumber(AppointmentDialog.phone.getText());
		Calendar cal = makeCalendarFromUserInput(false);
		String ac = AppointmentDialog.desc.getText();
		Activity act = new Activity(ac, (String)AppointmentDialog.category.getSelectedItem());
		return new Appointment(cal, fullName, phone, act);
		}
		catch (BadAppointmentDataException ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage() + "\nDescription: " + ex.getDescription() + ", Please Re-Enter\n");
		return null;}
	}

	
	public static Calendar makeCalendarFromUserInput(boolean suppressHour) {
		int dd, mm, yy, time;
		Calendar cal = Calendar.getInstance();
		String date;
		cal.clear();
		date = AppointmentDialog.date.getText();
		dd = Integer.parseInt(date.substring(0, 2));
		mm = Integer.parseInt(date.substring(2, 4));
		yy = Integer.parseInt(date.substring(4, 8));
		if (Appointment.IsCalendarFormatCorrect(date)) {
			if (suppressHour == true)
				time = 0;
			else {
				time = processTimeString(AppointmentDialog.time.getText());
			}
			if (Appointment.IsCalendarFormatCorrect(date))
				cal.set(yy, mm - 1, dd, time, 0);
		}
		return cal;
	}

	
	private static int processTimeString(String t) {
		int hour;

		if (t.contains(":")) {
			String[] arr = t.split("(?=\\:)");
			hour = Integer.parseInt(arr[0]);
		} else if (t.contains(" ")) {
			String[] arr = t.split(" ");
			hour = Integer.parseInt(arr[0]);
		} else
			hour = Integer.parseInt(t);

		if (t.contains("p.m.") || (hour >= 1 && hour <= 4)) {
			hour = 12 + hour;
		}

		return hour;
	}

	
	private static Appointment findAppointment(Calendar cal) {
		int index = Collections.binarySearch(getAppointments(), new Appointment(cal, null, null, null, null),
				new SortAppointmentByCalendar());

		return index < 0 ? null : getAppointments().get(index);
	}

	
	public static ArrayList<Appointment> getAppointments() {
		return appointments;
	}

}
