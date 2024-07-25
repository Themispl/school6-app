package gr.aueb.cf.schoolapp.viewcontroller;

import gr.aueb.cf.schoolapp.Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeachersMenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public TeachersMenuFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
		setTitle("Μενού Εκπαιδευτών");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton teachersViewBtn = new JButton("Προβολή Εκπαιδευτών");
		teachersViewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersupdatedeleteframe().setVisible(true);
				Main.getTeachersMenuFrame().setEnabled(false);
			}
			
		});
		teachersViewBtn.setForeground(Color.BLUE);
		teachersViewBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		teachersViewBtn.setBounds(119, 45, 168, 43);
		contentPane.add(teachersViewBtn);
		
		JButton studentsViewBtn = new JButton("Εισαγωγή Εκπαιδευτή");
		studentsViewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersinsertframe().setVisible(true);
				Main.getTeachersMenuFrame().setEnabled(false);
			}
		});
		studentsViewBtn.setForeground(Color.BLUE);
		studentsViewBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		studentsViewBtn.setBounds(119, 109, 168, 43);
		contentPane.add(studentsViewBtn);
		
		JButton btnNewButton = new JButton("Κλείσιμο");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getMainmenuFrame().setEnabled(true);
				Main.getTeachersMenuFrame().setVisible(false);	
				}
		});
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(327, 227, 97, 23);
		contentPane.add(btnNewButton);
	}

}
