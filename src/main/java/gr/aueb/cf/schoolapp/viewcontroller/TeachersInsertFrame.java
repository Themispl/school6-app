package gr.aueb.cf.schoolapp.viewcontroller;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import gr.aueb.cf.schoolapp.Main;

import gr.aueb.cf.schoolapp.service.util.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;

public class TeachersInsertFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField firstnameTxt;
	private JTextField lastnameTxt;
	private JLabel errorFirstname;
	private JLabel errorLastname;
	

	/**
	 * Create the frame.
	 */
	public TeachersInsertFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				firstnameTxt.setText("");
				lastnameTxt.setText("");
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel firstnameLabel = new JLabel("Όνομα");
		firstnameLabel.setForeground(new Color(0, 0, 255));
		firstnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		firstnameLabel.setBounds(37, 41, 67, 19);
		contentPane.add(firstnameLabel);
		
		JLabel lastnameLabel = new JLabel("Επώνυμο");
		lastnameLabel.setForeground(new Color(0, 0, 255));
		lastnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lastnameLabel.setBounds(37, 91, 67, 14);
		contentPane.add(lastnameLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(30, 11, 378, 134);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lastnameTxt = new JTextField();
		lastnameTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String inputLastname;
				inputLastname = lastnameTxt.getText().trim();
				
				if(inputLastname.equals("")) {
					errorLastname.setText("το επώνυμο ειναι υποχρεωτικο");
				}
				if(!inputLastname.equals("")) {
					errorLastname.setText("");
				}
			}
		});
		lastnameTxt.setBounds(100, 77, 245, 20);
		panel.add(lastnameTxt);
		lastnameTxt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lastnameTxt.setColumns(10);
		
		firstnameTxt = new JTextField();
		firstnameTxt.setBounds(100, 29, 245, 20);
		panel.add(firstnameTxt);
		firstnameTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String inputFirstname;
				inputFirstname = firstnameTxt.getText().trim();
				
				if(inputFirstname.equals("")) {
					errorFirstname.setText("το ονομα ειναι υποχρεωτικο");
				}
				if(!inputFirstname.equals("")) {
					errorFirstname.setText("");
				}
			}
		});
		firstnameTxt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		firstnameTxt.setColumns(10);
		
		errorFirstname = new JLabel("");
		errorFirstname.setBounds(100, 52, 245, 14);
		panel.add(errorFirstname);
		errorFirstname.setForeground(new Color(255, 0, 0));
		
		errorLastname = new JLabel("");
		errorLastname.setBounds(100, 97, 245, 14);
		panel.add(errorLastname);
		errorLastname.setForeground(Color.RED);
		
		JButton insertBtn = new JButton("Εισαγωγή");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Data Binding
				 String inputFirstname = firstnameTxt.getText().trim();
				 String  inputLastname = lastnameTxt.getText().trim();
				
				
				
				if(inputFirstname.equals("")) {
					errorFirstname.setText("το ονομα ειναι υποχρεωτικο");
				}
				if(!inputFirstname.equals("")) {
					errorFirstname.setText("");
				}
				if(inputLastname.equals("")) {
					errorLastname.setText("το ονομα ειναι υποχρεωτικο");
				}
				if(!inputLastname.equals("")) {
					errorLastname.setText("");
				}
				if(inputFirstname.equals("") || inputLastname.equals("")) {
					return;
				}
				
				
				String sql = "INSERT INTO TEACHERS(FIRSTNAME, LASTNAME) VALUES(?, ?)";
				try (Connection conn = DBUtil.getConnection();
						PreparedStatement ps = conn.prepareStatement(sql)){
					
					ps.setString(1, inputFirstname);
					ps.setString(2, inputLastname);
				
				int n = ps.executeUpdate();
				JOptionPane.showMessageDialog(null, n + "recorde were insert","INSERT", JOptionPane.PLAIN_MESSAGE);
				
				}catch (SQLException e1){
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(null, " insert error", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		insertBtn.setForeground(new Color(0, 0, 255));
		insertBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		insertBtn.setBounds(180, 206, 109, 23);
		contentPane.add(insertBtn);
		
		JButton closeBtn = new JButton("Κλείσιμο");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersMenuFrame().setEnabled(true);
				Main.getTeachersinsertframe().setVisible(false);
			}
		});
		closeBtn.setForeground(new Color(0, 0, 255));
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		closeBtn.setBounds(299, 206, 109, 23);
		contentPane.add(closeBtn);
	}
}
