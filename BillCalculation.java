package weather;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Project.ConnectionProvider;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;


public class CalculateBill extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel_6;
    private JLabel lblNewLabel_7;
    private JTextField textField_1;
    JDateChooser dateChooser;
    JDateChooser dateChooser_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CalculateBill frame = new CalculateBill();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CalculateBill() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1920, 1080);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(43, 73, 45, 13);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("BILLING");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblNewLabel_1.setBounds(714, 69, 148, 36);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("METERNO");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2.setBounds(401, 226, 130, 13);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("NAME");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_3.setBounds(401, 324, 56, 13);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("ADDRESS");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_4.setBounds(401, 411, 82, 13);
        contentPane.add(lblNewLabel_4);

        textField = new JTextField();
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String meter = textField.getText();
                try {
                    Connection con = ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select * from detail where meterno ='" + meter + "'");
                    if (rs.next()) {

                        lblNewLabel_5.setText(rs.getString(1));
                        lblNewLabel_6.setText(rs.getString(3));
                        lblNewLabel_7.setText(rs.getString(5));

                    } else {
                        JOptionPane.showMessageDialog(null, "contact doesn't exist");
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1);

                }
            }
        });
        textField.setBounds(641, 225, 96, 19);
        contentPane.add(textField);
        textField.setColumns(10);

        lblNewLabel_5 = new JLabel("");
        lblNewLabel_5.setBounds(641, 324, 115, 13);
        contentPane.add(lblNewLabel_5);

        lblNewLabel_6 = new JLabel("");
        lblNewLabel_6.setBounds(641, 411, 148, 13);
        contentPane.add(lblNewLabel_6);

        lblNewLabel_7 = new JLabel("");
        lblNewLabel_7.setBounds(641, 510, 115, 13);
        contentPane.add(lblNewLabel_7);

        JLabel lblNewLabel_8 = new JLabel("PHONE");
        lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_8.setBounds(401, 510, 57, 13);
        contentPane.add(lblNewLabel_8);

        JLabel lblNewLabel_9 = new JLabel("UNIT");
        lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_9.setBounds(401, 602, 82, 13);
        contentPane.add(lblNewLabel_9);

        textField_1 = new JTextField();
        textField_1.setBounds(641, 601, 96, 19);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JButton btnNewButton = new JButton("SUBMIT");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // String c = (String) comboBox.getSelectedItem();
                java.sql.Date selectedDate = new java.sql.Date(dateChooser.getDate().getTime());
             String dateString = selectedDate.toString();
             java.sql.Date selectedDate1 = new java.sql.Date(dateChooser_1.getDate().getTime());

             // Convert the java.sql.Date object to a string
             String dateString1 = selectedDate1.toString();
            // System.out.println(dateString);
                String t1 = textField.getText();
                String unit = textField_1.getText();
                double cost = 0;
                try {
                    int units = Integer.parseInt(unit);
                    if (units <= 100) {
                        cost = 0; // Assuming the first 100 units are free as per the given data.
                    } else if (units <= 500) {
                        if (units <= 200) {
                            cost = (units - 100) * 2.25;
                        } else if (units <= 400) {
                            cost = (100 * 2.25) + ((units - 200) * 4.5);
                        } else {
                            cost = (100 * 2.25) + (200 * 4.5) + ((units - 400) * 6);
                        }
                    } else {
                        // For units above 500
                        if (units <= 600) {
                            cost = (300 * 4.5) + (100 * 6) + ((units - 500) * 8);
                        } else if (units <= 800) {
                            cost = (300 * 4.5) + (100 * 6) + (100 * 8) + ((units - 600) * 9);
                        } else if (units <= 1000) {
                            cost = (300 * 4.5) + (100 * 6) + (100 * 8) + (200 * 9) + ((units - 800) * 10);
                        } else {
                            cost = (300 * 4.5) + (100 * 6) + (100 * 8) + (200 * 9) + (200 * 10) + ((units - 1000) * 11);
                        }
                    }
                    String tot1 = String.valueOf(cost);
                    String query = "INSERT INTO bill VALUES (?, ?, ?, ?, ?,?)";
                    try (Connection con = ConnectionProvider.getCon();
                         PreparedStatement statement = con.prepareStatement(query)) {
                        statement.setString(1, t1);
                       // statement.setString(2, c);
                        statement.setString(2, unit);
                        statement.setString(3, tot1);
                        statement.setString(4, "notPaid");
                        statement.setString(5, dateString);
                        statement.setString(6, dateString1);
                        int rowsInserted = statement.executeUpdate();
                        if (rowsInserted > 0) {
                            JOptionPane.showMessageDialog(null, "Submitted");
                            setVisible(false);
                            Home frame = new Home();
                            frame.setUndecorated(true);
                            frame.setVisible(true);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input for units.");
                }
            }
        });
        btnNewButton.setBounds(1115, 689, 109, 36);
        contentPane.add(btnNewButton);
        
        JLabel lblNewLabel_11 = new JLabel("From");
        lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_11.setBounds(985, 231, 45, 13);
        contentPane.add(lblNewLabel_11);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(1090, 225, 134, 19);
        contentPane.add(dateChooser);
        
        JLabel lblNewLabel_12 = new JLabel("TO");
        lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_12.setBounds(985, 411, 45, 13);
        contentPane.add(lblNewLabel_12);
        
       dateChooser_1 = new JDateChooser();
        dateChooser_1.setBounds(1090, 405, 129, 19);
        contentPane.add(dateChooser_1);
        
        JButton btnNewButton_1 = new JButton("BACK");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        		Home frame = new Home();
        		 frame.setUndecorated(true);
                frame.setVisible(true);
        	}
        });
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 19));
        btnNewButton_1.setBounds(1319, 689, 115, 31);
        contentPane.add(btnNewButton_1);
        
        JLabel lblNewLabel_10 = new JLabel("New label");
        lblNewLabel_10.setIcon(new ImageIcon("C:\\Users\\yadha\\Downloads\\Untitled design.png"));
        lblNewLabel_10.setBounds(-795, 0, 2346, 890);
        contentPane.add(lblNewLabel_10);
    }
}