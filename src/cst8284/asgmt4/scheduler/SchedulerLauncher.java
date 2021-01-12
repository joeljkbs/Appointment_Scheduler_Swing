
package cst8284.asgmt4.scheduler;

import javax.swing.JOptionPane;

import cst8284.asgmt4.employee.*;


public class SchedulerLauncher {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater
		(new Runnable() {
		public void run() {
			Scheduler sch = new Scheduler(new Dentist("Dr. Andrews"));
			sch.launch();
		}
		});
	}

	/**
	 * It instantiates an object of scheduler class with an appropriate Dentist as
	 * its argument, And then calls the launch() method of scheduler to start
	 * executing the appointment schedular.
	 * 
	 * @return<code>true</code> if no exception is thrown <code>false</code> if a
	 * BadAppointmentDataException is thrown by the try block
	 */
//	private static boolean loadScheduler() {
//		
//		boolean schedulerLoaded = false;
//		try {
//			Scheduler sch = new Scheduler(new Dentist("Dr. Andrews"));
//			sch.launch();
//			schedulerLoaded = true;
//		} catch (BadAppointmentDataException ex) {
//			JOptionPane.showMessageDialog(null,  ex.getMessage() + "\nDescription: " + ex.getDescription() + ", Please Re-Enter\n");
//		}
//		return schedulerLoaded;
//	}

}


