package gr.aueb.cf.schoolapp.viewcontroller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import gr.aueb.cf.schoolapp.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import gr.aueb.cf.schoolapp.service.util.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Toolkit;

public class TeachersUpdateDeleteFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable teachersTable;
	private DefaultTableModel model = new DefaultTableModel();
	private JTextField lastnameSearchText;
	private JTextField idText;
	private JTextField firstnameText;
	private JTextField lastnameText;
	private JLabel errorFirstname;
	private JLabel errorLastname;
	/**
	 * Create the frame.
	 */
	public TeachersUpdateDeleteFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
		setTitle("Update/ Delete");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				buildTable();
			}
			@Override
			public void windowActivated(WindowEvent e) {
				buildTable();
				idText.setText("");
				firstnameText.setText("");
				lastnameText.setText("");
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 626, 555);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		teachersTable = new JTable();
		teachersTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idText.setText((String)model.getValueAt(teachersTable.getSelectedRow(), 0));
				firstnameText.setText((String)model.getValueAt(teachersTable.getSelectedRow(), 1));
				lastnameText.setText((String)model.getValueAt(teachersTable.getSelectedRow(), 2));
			}
		});
		teachersTable.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {"Κωδικός", "Όνομα", "Επώνυμο"}
		));
		
		model = (DefaultTableModel)teachersTable.getModel();
		
		teachersTable.setBounds(26, 32, 315, 414);
		contentPane.add(teachersTable);
		
		JScrollPane scrollPane = new JScrollPane(teachersTable);
		scrollPane.setBounds(26, 32, 315, 414);
		contentPane.add(scrollPane);
		
		JLabel lastnameSearchLabel = new JLabel("Επώνυμο");
		lastnameSearchLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lastnameSearchLabel.setBounds(26, 4, 71, 14);
		contentPane.add(lastnameSearchLabel);
		
		lastnameSearchText = new JTextField();
		lastnameSearchText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lastnameSearchText.setBounds(107, 1, 234, 20);
		contentPane.add(lastnameSearchText);
		lastnameSearchText.setColumns(10);
		
		JButton btnSearch = new JButton("Αναζήτηση");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildTable();
			}
		});
		btnSearch.setBounds(444, 1, 89, 23);
		contentPane.add(btnSearch);
		
		JLabel lbLabel = new JLabel("Κωδικός");
		lbLabel.setBounds(362, 62, 59, 14);
		contentPane.add(lbLabel);
		
		JLabel firstnameLabel = new JLabel("Όνομα");
		firstnameLabel.setBounds(362, 103, 59, 14);
		contentPane.add(firstnameLabel);
		
		JLabel lastnameLabel = new JLabel("Επώνυμο");
		lastnameLabel.setBounds(362, 148, 59, 14);
		contentPane.add(lastnameLabel);
		
		idText = new JTextField();
		idText.setEditable(false);
		idText.setBounds(420, 59, 153, 20);
		contentPane.add(idText);
		idText.setColumns(10);
		
		firstnameText = new JTextField();
		firstnameText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
			String inputFirstname;
			inputFirstname = firstnameText.getText().trim();
			
			if(inputFirstname.equals("")) {
				errorFirstname.setText("το ονομα ειναι υποχρεωτικο");
			}
			if(!inputFirstname.equals("")) {
				errorFirstname.setText("");
			}
			}
		});
		firstnameText.setColumns(10);
		firstnameText.setBounds(420, 100, 153, 20);
		contentPane.add(firstnameText);
		
		lastnameText = new JTextField();
		lastnameText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String inputLastname;
				inputLastname = lastnameText.getText().trim();
				
				if(inputLastname.equals("")) {
					errorLastname.setText("το επώνυμο ειναι υποχρεωτικο");
				}
				if(!inputLastname.equals("")) {
					errorLastname.setText("");
				}
			}
			});
		
		lastnameText.setColumns(10);
		lastnameText.setBounds(421, 145, 153, 20);
		contentPane.add(lastnameText);
		
		 errorFirstname = new JLabel("");
		errorFirstname.setForeground(new Color(255, 0, 0));
		errorFirstname.setBounds(420, 123, 153, 20);
		contentPane.add(errorFirstname);
		
		 errorLastname = new JLabel("");
		 errorLastname.setForeground(new Color(255, 0, 0));
		errorLastname.setBounds(420, 165, 153, 20);
		contentPane.add(errorLastname);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(351, 41, 234, 144);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton updateBtn = new JButton("Ενημέρωση");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Data bidning
				int inputId = Integer.parseInt(idText.getText().trim());
				String inputFirstname = firstnameText.getText().trim();
				String inputLastname = lastnameText.getText().trim();
				
				//Validetion
				if(inputFirstname.isEmpty()) {
					errorFirstname.setText("το ονομα ειναι υποχρεωτικο");
				}
				if(!inputFirstname.isEmpty()) {
					errorFirstname.setText("");
				}
				if(inputLastname.isEmpty()) {
					errorLastname.setText("το ονομα ειναι υποχρεωτικο");
				}
				if(!inputLastname.isEmpty()) {
					errorLastname.setText("");
				}
				if(inputFirstname.isEmpty() || inputLastname.isEmpty()) {
					return;
				}
				
				String sql = "UPDATE TEACHERS SET FIRSTNAME = ?, LASTNAME = ? WHERE ID = ?";
				
				try (Connection conn = DBUtil.getConnection();
						PreparedStatement ps = conn.prepareStatement(sql)){
					ps.setString(1, inputFirstname);
					ps.setString(2, inputLastname);
					ps.setInt(3,  inputId);
					
					int answer = JOptionPane.showConfirmDialog(null, "Ειστε σίγουρος", "Ενημέρωση", JOptionPane.YES_NO_OPTION);
					if(answer == JOptionPane.YES_OPTION) {
						int rowsAffected = ps.executeUpdate();
						JOptionPane.showMessageDialog(null, rowsAffected+"Γραμμή/ές ενημερώθηκαν", "Ενημέρωση",JOptionPane.INFORMATION_MESSAGE);
						
					}else {
						return;
					}
					
				}catch(SQLException e1) {
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(null, " Update error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		updateBtn.setBounds(362, 234, 89, 36);
		contentPane.add(updateBtn);
		
		JButton deleteBtn = new JButton("Διαγραφή");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM TEACHERS WHERE ID = ?";
				
				try (Connection conn = DBUtil.getConnection();
						PreparedStatement ps = conn.prepareStatement(sql)){
					int inputId = Integer.parseInt(idText.getText().trim());
					
					ps.setInt(1,  inputId);
					
					int answer = JOptionPane.showConfirmDialog(null, "Ειστε σίγουρος", "Διαγραγή", JOptionPane.YES_NO_OPTION);
					if(answer == JOptionPane.YES_OPTION) {
						int rowsAffected = ps.executeUpdate();
						JOptionPane.showMessageDialog(null, rowsAffected+"Γραμμή/ές ενημερώθηκαν", "Διαγραγή",JOptionPane.INFORMATION_MESSAGE);
						
					}else {
						return;
					}
					
				}catch(SQLException ex){
					//ex.printStackTrace();
					JOptionPane.showMessageDialog(null, " Delete error", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		deleteBtn.setBounds(484, 234, 89, 36);
		contentPane.add(deleteBtn);
		
		JButton closeBtn = new JButton("Κλείσιμο");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersMenuFrame().setEnabled(true);
				Main.getTeachersUpdateDeleteFrame().setVisible(false);
			}
		});
		closeBtn.setBounds(488, 426, 89, 23);
		contentPane.add(closeBtn);
	}
	
	private void buildTable() {
		
		Vector<String> vector;
		String sql = "SELECT ID, FIRSTNAME, LASTNAME FROM TEACHERS WHERE LASTNAME LIKE ?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){  
			
			ps.setString(1, lastnameSearchText.getText().trim() + "%");
			
			ResultSet rs = ps.executeQuery();  //ektelei to query kai fernei tis egrafes
			
			//clear model -> clear table MVVM model
			for(int i = model.getRowCount() -1; i>= 0; i--) {
				model.removeRow(i);
			}
			
			while(rs.next()){
				vector = new Vector<>(3);
				vector.add(rs.getString("ID"));
				vector.add(rs.getString("FIRSTNAME"));
				vector.add(rs.getString("LASTNAME"));
				model.addRow(vector);
			}
			
		}catch(SQLException e1) {
			//e1.printStackTrace();
			JOptionPane.showMessageDialog(null, " Select error", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
