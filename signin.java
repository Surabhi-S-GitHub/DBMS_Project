package weather;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Project.ConnectionProvider;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Signin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static JTextField textField;
    private static JPasswordField passwordField;
    private JPasswordField passwordField_1;
    private JTextField textField_3;
    static String username;
    static String pass;
    String confirmPass;
    JLabel l1;
    JLabel l2;
    JLabel l3;
    JLabel l4;
    private JButton btnNewButton_1;
    private JLabel lblNewLabel_5;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Signin frame = new Signin();
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
    public static boolean isStrongPassword(String password) {
        // Check if password length is at least 8 characters
        username = textField.getText();
        pass = new String(passwordField.getPassword());
        if (password.length() < 8) {
            return false;
        }

        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        // Iterate through each character in the password
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowerCase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else {
                // Check for special characters
                hasSpecialChar = true;
            }
        }

        // Check if password meets all criteria
        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
    }

    public Signin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0,1920,1080);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("USERNAME");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(577, 144, 119, 22);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("PASSWORD");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1.setBounds(577, 258, 123, 13);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("CONFIRM PASSWORD");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2.setBounds(577, 389, 205, 13);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("CODE");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_3.setBounds(577, 498, 68, 13);
        contentPane.add(lblNewLabel_3);

        JButton btnNewButton = new JButton("SIGNUP");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                username = textField.getText();
                pass = new String(passwordField.getPassword());
                confirmPass = new String(passwordField_1.getPassword());
                String code = textField_3.getText();

                if (username.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid username", "Input Error", JOptionPane.ERROR_MESSAGE);
                    textField.setText("");
                    return;
                }

                if (pass.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid password", "Input Error", JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("");
                    passwordField_1.setText("");
                    return;
                }

                if (!isStrongPassword(pass)) {
                    JOptionPane.showMessageDialog(null, "Weak password! Password should contain at least 8 characters, including at least one uppercase letter, one lowercase letter, one digit, and one special character.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("");
                    passwordField_1.setText("");
                    return;
                }

                if (!pass.equals(confirmPass)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match", "Input Error", JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("");
                    passwordField_1.setText("");
                    return;
                }

                if (code.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter valid code", "Input Error", JOptionPane.ERROR_MESSAGE);
                    textField_3.setText("");
                    return;
                }

                // Your database insertion code here
                String query = "INSERT INTO secu VALUES (?, ?, ?)";
                try {
                    Connection con = ConnectionProvider.getCon();
                    PreparedStatement statement = con.prepareStatement(query);
                    statement.setString(1, username);
                    statement.setString(2, pass);
                    statement.setString(3, code);

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Account successfully created");
                        setVisible(false);
                        Login frame = new Login();
                        frame.setUndecorated(true);
                        frame.setVisible(true);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnNewButton.setBounds(1079, 608, 100, 31);
        contentPane.add(btnNewButton);

        textField = new JTextField();
        textField.setBounds(1060, 148, 178, 19);
        contentPane.add(textField);
        textField.setColumns(10);
        textField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String PATTERN = "^[a-zA-Z0-9]{5,30}$";
                Pattern pat = Pattern.compile(PATTERN);
                Matcher m = pat.matcher(textField.getText());
                if (!m.matches()) {
                    l1.setText("Username should be atleast 4 letter");
                } else {
                    l1.setText(null);
                }
            }
        });

        passwordField = new JPasswordField();
        passwordField.setBounds(1060, 257, 178, 19);
        contentPane.add(passwordField);
        passwordField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String passwordPattern = "^(?=.[A-Z])(?=.\\d)(?=.[@$!%?&])[A-Za-z\\d@$!%*?&]{8,}$";
                Pattern pat = Pattern.compile(passwordPattern);
                Matcher matcher = pat.matcher(new String(passwordField.getPassword()));
                if (!matcher.matches()) {
                    l2.setText("Weak Password");
                    l2.setForeground(java.awt.Color.RED);
                } else {
                    l2.setText("Strong Password");
                    l2.setForeground(java.awt.Color.BLUE);
                }
            }
        });

        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(1059, 388, 179, 19);
        contentPane.add(passwordField_1);
       

       passwordField_1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(passwordField_1.getPassword());
                if (!password.equals(confirmPassword)) {
                    l3.setText("password not match");   
                    passwordField_1.setText("");
                    }
                else {
                    l3.setText(null);
                }
            }
        });

        textField_3 = new JTextField();
        textField_3.setBounds(1060, 496, 178, 22);
        contentPane.add(textField_3);
        textField_3.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("SIGN UP");
        lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 29));
        lblNewLabel_4.setBounds(726, 35, 249, 40);
        contentPane.add(lblNewLabel_4);

        JCheckBox chckbxNewCheckBox = new JCheckBox("SHOW PASSWORD");
        chckbxNewCheckBox.setBounds(1060, 442, 138, 21);
        contentPane.add(chckbxNewCheckBox);

        JCheckBox chckbxNewCheckBox_1 = new JCheckBox("SHOW PASSWORD");
        chckbxNewCheckBox_1.setBounds(1060, 327, 139, 21);
        contentPane.add(chckbxNewCheckBox_1);

        l1 = new JLabel("");
        l1.setFont(new Font("Tahoma", Font.ITALIC, 12));
        l1.setForeground(Color.RED);
        l1.setBounds(1060, 201, 220, 19);
        contentPane.add(l1);

        l2 = new JLabel("");
        l2.setFont(new Font("Tahoma", Font.ITALIC, 12));
        l2.setForeground(Color.RED);
        l2.setBounds(1060, 286, 220, 19);
        contentPane.add(l2);
        l3 = new JLabel(""); // Initialize l3 as an empty JLabel
        l3.setFont(new Font("Tahoma", Font.ITALIC, 12));
        l3.setForeground(Color.RED);
        l3.setBounds(1060, 417, 220, 19); // Adjust bounds as needed
        contentPane.add(l3); 
        
        btnNewButton_1 = new JButton("BACK");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
				setVisible(false);
        	}
        	
        });
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton_1.setBounds(577, 610, 85, 26);
        contentPane.add(btnNewButton_1);
        
        lblNewLabel_5 = new JLabel("");
        lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\yadha\\Downloads\\Untitled design.png"));
        lblNewLabel_5.setBounds(-801, 0, 2341, 857);
        contentPane.add(lblNewLabel_5);
        chckbxNewCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chckbxNewCheckBox.isSelected()) {
                    passwordField_1.setEchoChar((char) 0);
                } else {
                    passwordField_1.setEchoChar('\u2022');
                }
            }
        });

        chckbxNewCheckBox_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chckbxNewCheckBox_1.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('\u2022');
                }
            }
        });
    }
}