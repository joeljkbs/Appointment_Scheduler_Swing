package cst8284.asgmt4.scheduler;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;

import cst8284.asgmt4.employee.Dentist;

public class SchedulerViewer extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final Toolkit tk = Toolkit.getDefaultToolkit();
	private static final Dimension screenSize = tk.getScreenSize();
	public static final JTextArea scrollText = new JTextArea();
	public static JFrame frame;

	public static void loadJFrame() {
		frame = new JFrame();
		frame.setTitle("Scheduling Appointments for "+Scheduler.getEmployee());
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int screenX = (int) screenSize.getWidth() / 2;
		int screenY = (int) (7 * screenSize.getHeight() / 8);

		frame.add(getWestPanel(), BorderLayout.WEST);
		frame.add(getCenterPanel(scrollText, screenY), BorderLayout.CENTER);
		frame.setPreferredSize(new Dimension(screenX, screenY));

		frame.pack();
		frame.setVisible(true);

	}

	public static JPanel getCenterPanel(JTextArea jta, int height) {
		JScrollPane centerPane = new JScrollPane(jta);
		centerPane.setPreferredSize(new Dimension(400, 7 * height / 8));
		JPanel jp = new JPanel();
		jp.add(centerPane);
		return jp;
	}

	public static JPanel getWestPanel() {
		JPanel controlPanel = new JPanel(new GridLayout(6, 1));
		JPanel westPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.weighty = 1;

		controlPanel.add(setWestPanelBtns(" Save Appointment ", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppointmentDialog.showSaveAppointmentDialog();
			}
		}));

		controlPanel.add(setWestPanelBtns("    Display Appointment    ", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppointmentDialog.showAppointmentDialog();
			}
		}));

		controlPanel.add(setWestPanelBtns("   Display Schedule  ", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppointmentDialog.showDailyDialog();
			}
		}));

		controlPanel.add(setWestPanelBtns("      Save Appoinmtents to File      ", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Scheduler.saveAppointmentsToFile(Scheduler.getAppointments(), "CurrentAppointments.apts"))
					JOptionPane.showMessageDialog(scrollText,"Appointment Data saved to CurrentAppointments.apts");
			}
		}));

		controlPanel.add(setWestPanelBtns("      Load Appoinmtents from File      ",

				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (Scheduler.loadAppointmentsFromFile("CurrentAppointments.apts", Scheduler.getAppointments()))
							JOptionPane.showMessageDialog(scrollText,"Appointmens succesfuly loaded from CurrentAppointments.apts");
						else
							JOptionPane.showMessageDialog(scrollText,"File not Found");
					}
				}));

		controlPanel.add(setWestPanelBtns("      Exit      ", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		}));

		westPanel.add(controlPanel, gbc);
		return westPanel;
	}

	private static JButton setWestPanelBtns(String btnLabel, ActionListener act) {
		final Font font = new Font("SansSerif", Font.PLAIN, 20);
		JButton btn = new JButton(btnLabel);
		btn.setFont(font);
		btn.addActionListener(act);
		return btn;
	}

	public static void reloadJTextArea() {
		scrollText.setText("");
	}
}
