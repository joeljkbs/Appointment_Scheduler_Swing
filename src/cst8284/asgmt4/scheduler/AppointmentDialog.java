package cst8284.asgmt4.scheduler;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/* Adapted, with considerable modification, from 
 * http://www.java2s.com/Code/Java/Swing-JFC/TextAcceleratorExample.htm,
 * which is sloppy code and should not be emulated.
 */

public class AppointmentDialog {
	
	private static final GridBagConstraints textConstants = new GridBagConstraints(
    	0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,  // gridx, gridy, gridwidth, gridheight, weightx, weighty
    	GridBagConstraints.EAST, 0, new Insets(2, 2, 2, 2), 1, 1); // anchor, fill, insets, ipadx, ipady
	
	private static final GridBagConstraints labelConstants = new GridBagConstraints(
    	1, GridBagConstraints.RELATIVE, 1, 1, 1.0, 0, 
    	GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);
	
	private static final GridBagConstraints btnConstants = new GridBagConstraints(
	    	1, GridBagConstraints.RELATIVE, 1, 1, 1.0, 0, 
	    	GridBagConstraints.EAST, 0, new Insets(2, 2, 2, 2), 0, 0);
	
	public static Container cp;
	private static final int labelWidth = 35;
	private static final Font defaultFont = new Font("SansSerif", Font.PLAIN, 16);
	public static JTextField name,phone,date,time,desc;
	public static JComboBox<String> category;
	
	public static void showAppointmentDialog(){
	    JFrame f = new JFrame("Get, change or delete an appointment");  
	    cp = f.getContentPane();
	    cp.setLayout(new GridBagLayout());
	    
	     date=setRow("Appointment Date (entered as DDMMYYYY):", 'd');
	     time=setRow("Appointment Time:", 't'); 
	     
	     setBtns("Find", e->{ SchedulerViewer.scrollText.setText("");
	    	 Scheduler.displayAppointment(Scheduler.makeCalendarFromUserInput(false));});
	     setBtns("Change", e->{
	    	 SchedulerViewer.scrollText.setText("");
	    	 Scheduler.changeAppointment(Scheduler.makeCalendarFromUserInput(false));});
	     setBtns("Delete", e-> Scheduler.deleteAppointment(Scheduler.makeCalendarFromUserInput(false)));
	     setBtns("Exit", e->f.dispose());
	    f.pack();
	    f.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent evt) {
	    	f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	      }
	    });
	    f.setVisible(true);
	  }
	
	private static JTextField setRow(String label, char keyboardShortcut) {
		JLabel l; JTextField t;
		cp.add(l = new JLabel(label, SwingConstants.RIGHT), textConstants);
		l.setFont(defaultFont);
		l.setDisplayedMnemonic(keyboardShortcut);
	    cp.add(t = new JTextField(labelWidth), labelConstants);
	    t.setFocusAccelerator(keyboardShortcut);
	    return t;
	}
	
	private static JButton setBtns(String btnLabel, ActionListener act) {
		final Font font = new Font("SansSerif", Font.PLAIN, 20);
		JButton btn = new JButton(btnLabel);
		btn.setFont(font);
		btn.addActionListener(act);
		cp.add(btn, btnConstants);
		return btn;
	}
	  
	public static void showSaveAppointmentDialog(){
	    JFrame f = new JFrame("Get, change or delete an appointment");  
	    cp = f.getContentPane();
	    cp.setLayout(new GridBagLayout());

	     name=setRow("Enter Client Name (as FirstName LastName):", 'n');
	     phone=setRow("Phone Number (e.g. 613-555-1212):", 'p');
	     date=setRow("Appointment Date (entered as DDMMYYYY):", 'd');
	     time=setRow("Appointment Time:", 't');
	     desc=setRow("Activity Description", 'a');
	     
	     
	     category = new JComboBox<>(Scheduler.getEmployee().getActivityType());
	     cp.add(new JLabel("Activity Type", SwingConstants.RIGHT),textConstants);
	     cp.add(category,labelConstants);
	     
	     setBtns("Save", new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Appointment appt = Scheduler.makeAppointmentFromUserInput();
					if (Scheduler.saveAppointment(appt) == true) {
						JOptionPane.showMessageDialog(cp, "Appointment Saved");
						Collections.sort(Scheduler.getAppointments(), new SortAppointmentByCalendar());
					} 
//					else
//						JOptionPane.showMessageDialog(cp, "Cannot save; an appointment at that time already exists");
					
					}
			});
	     setBtns("Exit", e->f.dispose());
	     
	    f.pack();
	    f.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent evt) {
	    	f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	      }
	    });
	    f.setVisible(true);
	  }
	
	public static void showDailyDialog(){
	    JFrame f = new JFrame("Get, change or delete an appointment");  
	    cp = f.getContentPane();
	    cp.setLayout(new GridBagLayout());
	    
	     date=setRow("Appointment Date (entered as DDMMYYYY):", 'd');
	     
	     setBtns("Display Schedule", e->{
	    	 SchedulerViewer.scrollText.setText("");
	    	 Scheduler.displayDaySchedule(Scheduler.makeCalendarFromUserInput(true));});
	     setBtns("Exit", e->f.dispose());
	    f.pack();
	    f.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent evt) {
	    	f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	      }
	    });
	    f.setVisible(true);
	  }
	
}
