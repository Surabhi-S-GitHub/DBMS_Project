package weather;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Project.ConnectionProvider;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Newcustomer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	JLabel l8;
	JLabel l1 ;
	String selectedItem;
	String meter;
	/**
	 * @wbp.nonvisual location=1274,-46
	 */
	private final Component glue = Box.createGlue();
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Newcustomer frame = new Newcustomer();

					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

    private boolean isValidEmail(String email) {
        // Regular expression for email validation
        String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
	/**
	 * Create the frame.
	 */
	public Newcustomer() {
		 l1 = new JLabel("");
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0,1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		 l8 = new JLabel("");
		 l8.setForeground(new Color(255, 0, 0));
		 l8.setFont(new Font("Tahoma", Font.ITALIC, 12));
		JLabel lblNewLabel = new JLabel("NAME");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(569, 95, 115, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("METER NO");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(569, 287, 132, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ADDRESS");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(570, 380, 78, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("EMAIL");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(570, 479, 61, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("PHONENO");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(570, 570, 90, 13);
		contentPane.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(978, 105, 173, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(978, 289, 173, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(978, 379, 170, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(978, 478, 173, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		textField_3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
            	String email = textField_3.getText();
                if (isValidEmail(email)) {
                     l1.setText("Valid email");
                     l1.setForeground(java.awt.Color.BLUE);
                 } else {
                     l1.setText("Invalid email");
                     l1.setForeground(java.awt.Color.RED);
                 }
            }
        });
		

		textField_4 = new JTextField();
		textField_4.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
		            // If the character is not a digit, backspace, or delete, consume the event
		            e.consume();
		        } else {
		            // If the length of text exceeds 10 characters, display "Digit exceeded" message and reset the text field
		            if (textField_4.getText().length() >= 10) {
		                l8.setText("Digit exceeded");
		                textField_4.setText("");

		                // Clear the message after a delay
		                Timer timer = new Timer(3000, new ActionListener() { // 3000 milliseconds = 3 seconds
		                    @Override
		                    public void actionPerformed(ActionEvent e) {
		                        l8.setText("");
		                    }
		                });
		                timer.setRepeats(false); // Set the timer to run only once
		                timer.start();
		            }
		        }
		    }
		});


		textField_4.setBounds(978, 570, 173, 19);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("NEXT");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setEnabled(true);
		        textField.requestFocus(); 
				String name=textField.getText();
				textField_1.setEnabled(true);
		        textField_1.requestFocus(); 
				 meter=textField_1.getText();
				textField_2.setEnabled(true);
		        textField_2.requestFocus(); 
				String address=textField_2.getText();
				String email=textField_3.getText();
				String phoneno=textField_4.getText();
				String service=textField_5.getText();
				// name = textField.getText();
	              //  pass = new String(passwordField.getPassword());
	                //confirmPass = new String(passwordField_1.getPassword());
	              //  String code = textField_3.getText();

	                if (name.trim().isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Please enter a valid username", "Input Error", JOptionPane.ERROR_MESSAGE);
	                    textField.setText("");
	                    return;
	                }

	                if (meter.trim().isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Please enter a valid password", "Input Error", JOptionPane.ERROR_MESSAGE);
	                    textField_1.setText("");
	                    
	                    return;
	                }
	                if (address.trim().isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Please enter a valid password", "Input Error", JOptionPane.ERROR_MESSAGE);
	                    textField_1.setText("");
	                    
	                    return;
	                }
	                if (email.trim().isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Please enter valid code", "Input Error", JOptionPane.ERROR_MESSAGE);
	                    textField_3.setText("");
	                    return;
	                }
	                if (phoneno.trim().isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Please enter valid code", "Input Error", JOptionPane.ERROR_MESSAGE);
	                    textField_3.setText("");
	                    return;
	                }
	               
	                if (service.trim().isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Please enter valid code", "Input Error", JOptionPane.ERROR_MESSAGE);
	                    textField_3.setText("");
	                    return;
	                }
				String query = "INSERT INTO detail VALUES (?, ?, ?,?,?,?,?)";
				try  {
					Connection con=ConnectionProvider.getCon();
					PreparedStatement statement = con.prepareStatement(query);
				    statement.setString(1, name);
				    statement.setString(2, meter);
				    statement.setString(3, address);
				   statement.setString(4, email);
				    statement.setString(5, phoneno);
				    statement.setString(6, selectedItem);
				    statement.setString(7, service);
				   // statement.setString(6, address);
				    
				    int rowsInserted = statement.executeUpdate();
				    if (rowsInserted > 0) {
				        
				        
				        Meterinfo frame = new Meterinfo(meter);
				        frame.setUndecorated(true);
						frame.setVisible(true);
						
				    }
				} catch (SQLException ex) {
				    ex.printStackTrace(); 
				}
				
			}
		});
		btnNewButton.setBounds(1149, 780, 115, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_5 = new JLabel("CUSTOMER DETAILS");
		lblNewLabel_5.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 25));
		lblNewLabel_5.setBounds(731, 10, 270, 34);
		contentPane.add(lblNewLabel_5);
		
		
		l8.setBounds(978, 623, 173, 13);
		contentPane.add(l8);
		
		//l l1 = new JLabel("");
		l1.setBounds(978, 512, 160, 13);
		contentPane.add(l1);
		
		JLabel lblNewLabel_7 = new JLabel("SUPPLY TYPE");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_7.setBounds(570, 670, 173, 13);
		contentPane.add(lblNewLabel_7);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 selectedItem = (String) comboBox.getSelectedItem();

                // Insert the selected item as a string into the JTextField
                
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"DOMESTIC", "COMMERCIAL"}));
		comboBox.setBounds(984, 668, 167, 21);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_6 = new JLabel("SERVICE NO");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_6.setBounds(569, 208, 115, 13);
		contentPane.add(lblNewLabel_6);
		
		textField_5 = new JTextField();
		textField_5.setBounds(978, 207, 173, 19);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); // Hide the current frame

		        Home h = new Home(); // Create a new instance of the Login frame
		        h.setUndecorated(true); // Set the new Login frame to be undecorated
		        h.setVisible(true); 
			}
			
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(569, 782, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		lblNewLabel_8.setBounds(361, 533, -86, 13);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setIcon(new ImageIcon("C:\\Users\\yadha\\Downloads\\Untitled design.png"));
		lblNewLabel_9.setBounds(-802, -11, 2356, 898);
		contentPane.add(lblNewLabel_9);
	}
}