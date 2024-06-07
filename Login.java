import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Project.ConnectionProvider;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	JLabel l0;
	String captcha ;
	JLabel ll;
	private JLabel ll_1;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setUndecorated(true);
					frame.setVisible(true);
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static String create() {
        Random rand = new Random();
        int length = 7 + (Math.abs(rand.nextInt()) % 3); // Fixed typo random -> rand, added closing parenthesis for Math.abs
        StringBuffer captcha = new StringBuffer(); // Fixed typo capt -> captcha
        for (int i = 0; i < length; i++) {
            int base = Math.abs(rand.nextInt()) % 62; // Fixed typo random -> rand
            int charNumber = 0; // Fixed typo charn -> charNumber
            char charN; // Added missing variable declaration char charN
            if (base < 26) {
                charN = (char) (65 + base); // Fixed typo char -> charN
            } else if (base < 52) {
                charN = (char) (97 + (base - 26)); // Fixed typo char -> charN, added missing parenthesis
            } else {
                charN = (char) (48 + (base - 52)); // Fixed typo char -> charN, changed -- to -
            }
            captcha.append(charN); // Fixed typo capatacha -> captcha, char -> charN
        }
        return captcha.toString(); // Fixed typo capatacha -> captcha
    }
	public Login() {
		ll = new JLabel("New label");
		l0 = new JLabel(); // Initialize l0 before setting its text
		l0.setFont(new Font("Tahoma", Font.BOLD, 13));
	    captcha = create();
	    l0.setText(captcha);
		
		
		l0.setText(captcha);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0,1920,1080
				);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("USERNAME");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(587, 140, 138, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PASSWORD");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(587, 255, 105, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(935, 152, 200, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(935, 255, 200, 19);
		contentPane.add(passwordField);
		
		JCheckBox checkBox = new JCheckBox("SHOWPASSWORD");
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox.isSelected())
				{
					passwordField.setEchoChar((char)0);
				}
				else
				{
					passwordField.setEchoChar('*');
				}
			}
		});
		checkBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		checkBox.setBounds(937, 327, 181, 21);
		contentPane.add(checkBox);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setBackground(new Color(248, 248, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*	if(textField.getText().equals("admin")&& passwordField.getText().equals("admin"))
				{
					setVisible(false);
							
				 new Home().setVisible(true);				}
				
			
		  else
			{
				JOptionPane.showMessageDialog(null, "incorrect");
			}*/String c=textField_1.getText();
				String u=textField.getText();
				String newpass=passwordField.getText();
				try {
					
					 if (!c.equals(captcha)) { // Compare entered captcha with generated one
					        textField_1.setText(""); 
					        ll_1.setText("invalid captcha");
					        //JOptionPane.showMessageDialog(null, "Invalid captcha. Please try again."); 
					       // ll.setText("");
					    } else {
				    Connection con = ConnectionProvider.getCon();
				    String query = "SELECT * FROM secu WHERE user = ? AND password = ?";
				    PreparedStatement pst = con.prepareStatement(query);
				    pst.setString(1, u); // Set the value for the first parameter (user)
				    pst.setString(2, newpass); // Set the value for the second parameter (password)
				    
				    ResultSet rs = pst.executeQuery();
				    
				    if (rs.next()) {
				        setVisible(false);
				        

				        Home h = new Home(); // Create a new instance of the Login frame
				        h.setUndecorated(true); // Set the new Login frame to be undecorated
				        h.setVisible(true); //
				    } else {
				        JOptionPane.showMessageDialog(null, "INVALID USERNAME OR PASSWORD");
				    }
				    
				    pst.close(); // Close the PreparedStatement
				    con.close(); // Close the connection
				} }catch (Exception e1) {
				    JOptionPane.showMessageDialog(null, e1);
				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(587, 603, 114, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("CLOSE");
		btnNewButton_1.setBackground(new Color(240, 248, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a=JOptionPane.showConfirmDialog(null, "do u want to ext","EXIT",JOptionPane.YES_NO_OPTION);
				if(a==0)
				{
					System.exit(0);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(997, 679, 105, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("RESET");
		btnNewButton_2.setBackground(new Color(240, 248, 255));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Reset frame = new Reset();
				frame.setUndecorated(true);
				frame.setVisible(true);
				
			}
		});
		btnNewButton_2.setBounds(587, 679, 114, 29);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("SIGNUP");
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Signin frame = new Signin();
				frame.setUndecorated(true);
				frame.setVisible(true);
				
			}
		});
		btnNewButton_3.setBounds(997, 603, 99, 29);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_3 = new JLabel("LOGIN");
		lblNewLabel_3.setFont(new Font("Yu Gothic Medium", Font.BOLD, 25));
		lblNewLabel_3.setBounds(742, 51, 133, 49);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("CAPATCHA");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(587, 418, 104, 19);
		contentPane.add(lblNewLabel_4);
		
		textField_1 = new JTextField();
		textField_1.setBounds(935, 420, 200, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		 //l0 = new JLabel("New label");
		l0.setBounds(997, 492, 181, 13);
		contentPane.add(l0);
		
		ll_1 = new JLabel("");
		ll_1.setFont(new Font("Tahoma", Font.ITALIC, 12));
		ll_1.setForeground(new Color(255, 0, 0));
		ll_1.setBounds(978, 451, 200, 13);
		contentPane.add(ll_1);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				captcha = create();
			    l0.setText(captcha);
			}
		});
		btnNewButton_4.setIcon(new ImageIcon("C:\\Users\\yadha\\Downloads\\icons8-refresh-16.png"));
		btnNewButton_4.setBounds(935, 481, 30, 29);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\yadha\\Downloads\\Untitled design.png"));
		lblNewLabel_2.setBounds(-768, 0, 2308, 888);
		contentPane.add(lblNewLabel_2);
	}
}
