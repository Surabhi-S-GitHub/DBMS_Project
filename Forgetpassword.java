package weather;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Project.ConnectionProvider;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Reset extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_2;
	private JTextField textField_2;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reset frame = new Reset();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Reset() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0,1920,1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("RESET");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String u=textField.getText();
				String newpass=textField_1.getText();
				String code1=textField_2.getText();
				try
				{
					Connection con =ConnectionProvider.getCon();
					//Statement st=con.createStatement();
					String query = "SELECT * FROM secu WHERE user = ? AND code = ?";
				    PreparedStatement pst = con.prepareStatement(query);
				    pst.setString(1, u); // Set the value for the first parameter (user)
				    pst.setString(2, code1); // Set the value for the second parameter (password)
				    
					
                     ResultSet rs = pst.executeQuery();
                     
				    if (rs.next()) {
				    	
				    	PreparedStatement updateStatement = con.prepareStatement("UPDATE secu SET password=? WHERE code=? and user=?");
				        updateStatement.setString(1, newpass);
				        updateStatement.setString(2, code1);
				        updateStatement.setString(3, u);
				        
				        int rowsAffected = updateStatement.executeUpdate();
				        if (rowsAffected > 0) {
				        
								JOptionPane.showMessageDialog(null, "password successfully updated");
						        setVisible(false);
						        
						        Login n= new Login();
						        n.setUndecorated(true);
						        n.setVisible(true);
					}
				        
					else
					{
						JOptionPane.showMessageDialog(null, "contact doesn't exist");
					}
					
				    }}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
					
				}
				
				
			}
		});
		btnNewButton.setBounds(1110, 587, 85, 27);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("USERNAME");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(754, 205, 126, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NEW PASSWORD");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(754, 423, 141, 43);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(1029, 206, 126, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(1029, 427, 126, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblNewLabel_2 = new JLabel("SECURITY CODE");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(754, 316, 141, 38);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(1029, 328, 126, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		lblNewLabel_3 = new JLabel("RESET");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 25));
		lblNewLabel_3.setBounds(875, 48, 141, 43);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Login frame = new Login();
				frame.setUndecorated(true);
				frame.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(763, 594, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\yadha\\Downloads\\Untitled design.png"));
		lblNewLabel_4.setBounds(-765, -7, 2320, 907);
		contentPane.add(lblNewLabel_4);
	}
}