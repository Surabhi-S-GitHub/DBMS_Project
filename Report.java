package weather;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import Project.ConnectionProvider;
import java.sql.PreparedStatement;

import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.event.ActionEvent;

import java.awt.Font;
import javax.swing.ImageIcon;

public class Billprint extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel la4;
	String section;
	String circle;
	String distribution;
	String tariff;
	String phase;
	String sanctioned1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Billprint frame = new Billprint();
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
	
	public Billprint(String meter,double tot,double unitt,String from11,String to11,String ser,String supp) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0,1920,1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(33, 145, 1440, 626);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CUSTOMER ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(40, 144, 137, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CUSTOMER NAME");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(40, 234, 178, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("UNIT");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(44, 319, 85, 13);
		panel.add(lblNewLabel_2);
		
		JLabel l1 = new JLabel("");
		l1.setFont(new Font("Tahoma", Font.BOLD, 15));
		l1.setBounds(228, 144, 92, 13);
		panel.add(l1);
		
		JLabel l2 = new JLabel("");
		l2.setFont(new Font("Tahoma", Font.BOLD, 15));
		l2.setBounds(228, 234, 92, 13);
		panel.add(l2);
		
		JLabel l3 = new JLabel("");
		l3.setFont(new Font("Tahoma", Font.BOLD, 15));
		l3.setBounds(228, 319, 92, 13);
		panel.add(l3);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(514, 53, 746, 454);
		panel.add(textArea);
		
		
		JButton btnNewButton = new JButton("LOAD");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
	                    Connection con = ConnectionProvider.getCon();
	                    Statement st = con.createStatement();
	                    PreparedStatement pst = con.prepareStatement("SELECT detail.meterno, detail.user ,detail.Supply,from1,to1 ,detail.serviceno FROM detail INNER JOIN bill ON detail.meterno = bill.meterno WHERE detail.meterno = ?");
	                    pst.setString(1, meter);
	                    ResultSet rs = pst.executeQuery();
	                    PreparedStatement pst1 = con.prepareStatement("select section,circle,distribution,tariff,phase,sanctionedl from meterno where meterno1=?");
	                    pst1.setString(1, meter);
	                    ResultSet rs1 = pst1.executeQuery();
	                    if (rs1.next()) {
	                    	section= rs1.getString("section"); // Fetching the value of the first column (meterno)
	                         circle = rs1.getString("circle"); // Fetching the value of the second column (user)
	                       // String unitText = String.valueOf(unitt);
	                       // String totalAmountText = String.valueOf(tot);
	                        distribution= rs1.getString("distribution");
	                        tariff = rs1.getString("tariff");
	                        phase= rs1.getString("phase");
	                         sanctioned1= rs1.getString("sanctionedl");}

	                    if (rs.next()) {
	                    	String meterNumber = rs.getString(1); // Fetching the value of the first column (meterno)
	                        String userName = rs.getString(2); // Fetching the value of the second column (user)
	                        String unitText = String.valueOf(unitt);
	                        String totalAmountText = String.valueOf(tot);
	                        String san = rs.getString(3);
	                        String fro = rs.getString(4);
	                        String to = rs.getString(5);
	                       // String = rs.getString(5);
	                        
	                        l1.setText(rs.getString(1));
	                        l2.setText(rs.getString(2));
	                       // l3.setText(unitt);
	                        l3.setText(String.valueOf(unitt));
	                        la4.setText(String.valueOf(tot));
							//la4.setText(tot);
	                        String yourString = "This is the string you want to add.";
	                        textArea.append(yourString);
	                        StringBuilder billText = new StringBuilder();
	                        billText.append("\n");
	                        billText.append("\n");
	                       
	                      /*  SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
	                		Date date1 = new Date();
	                		lblNewLabel_2.setText(d.format(date1));
	                		contentPane.add(lblNewLabel_2);
	                		JLabel lblNewLabel_3 = new JLabel("New label");
	                		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
	                		lblNewLabel_3.setBounds(661, 74, 88, 13);
	                		contentPane.add(lblNewLabel_3);
	                		DateTimeFormatter da=DateTimeFormatter.ofPattern("HH:mm:ss");
	                		LocalDateTime now=LocalDateTime.now();
	                		lblNewLabel_3.setText(da.format(now));*/
	                        SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
	                        Date date1 = new Date();
	                        DateTimeFormatter da=DateTimeFormatter.ofPattern("HH:mm:ss");
	                		LocalDateTime now=LocalDateTime.now();

	                		/* billText.append("\t\t\t\tDate:"+d.format(date1)+"\n\t\t\t\tTime:"+da.format(now));	
	                		 billText.append("\n\\n\n");
	                		 billText.append("\n\t");
	                		 
	                        billText.append("         ").append("Customer ID                 : ").append("      ").append(meterNumber).append("\n");
	                        billText.append("\n");
	                        billText.append("\t");// Add space at the beginning and end
	                        billText.append("         ").append("Customer Name          : ").append("      ").append(userName).append("\n");
	                        billText.append("\n");
	                        billText.append("\t");
	                        billText.append("         ").append("Units Consumed          : ").append("      ").append(unitText).append("\n");
	                        billText.append("\n");
	                        billText.append("\t");
	                        billText.append("         ").append("Total Amount               : ").append("      ").append(totalAmountText).append("\n");
 
	                        // Set the text in the JLabels and JTextArea
	                       
	                        //billText.append("t\t    Time:"+da.format(now) );	
	                        textArea.setText(billText.toString());*/
	                		 StringBuilder billText1 = new StringBuilder();

	                	        // Create date and time formats
	                	        SimpleDateFormat d1= new SimpleDateFormat("dd-MM-yyyy");
	                	        Date date11 = new Date();
	                	        DateTimeFormatter da1 = DateTimeFormatter.ofPattern("HH:mm:ss");
	                	        LocalDateTime now1 = LocalDateTime.now();

	                	        // Billing details
	                	        
	                	        String billNumber = "001234";
	                	      //  String serviceNumber = "ABC12345";
	                	        String readingDate = "01-06-2024";
	                	        String billCycle = "Monthly";
	                	        String billType = "Regular";
	                	        String billPeriod = " to 31-05-2024";
	                	        String dueDate = "15-06-2024";
	                	        String dateString = "2024-06-19";
	                	        
	                	        // Parse the string to a LocalDate object
	                	        LocalDate date = LocalDate.parse(to11);
	                	        
	                	        // Add 20 days to the LocalDate object
	                	        LocalDate newDate = date.plusDays(20);
	                	        
	                	        // Convert the result back to a string
	                	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	                	        String newDateString = newDate.format(formatter);

	                	        // Header section
	                	        billText.append("====================================================================================================\n");
	                	        billText.append("                                     ELECTRICITY BILLING SYSTEM       \n");
	                	        billText.append("=====================================================================================================\n\n");
	                	        billText.append("\t\t\t\tDate: ").append(d.format(date1)).append("\n\n\t\t\t\tTime: ").append(da1.format(now1)).append("\n\n");

	                	        // Bill and service information
	                	        billText.append("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
	                	        billText.append(String.format("\t%-15s             : %s\n", "BILL NO", billNumber));
	                	        billText.append(String.format("\t%-15s         : %s\n", "METER NO", meterNumber));
	                	        billText.append(String.format("\t%-15s       : %s\n", "SERVICE NO", ser));
	                	        billText.append(String.format("\t%-15s: %s\n", "CUSTOMER NAME", userName));
	                	        billText.append(String.format("\t%-15s          : %s\n", "SECTION", section));
	                	        billText.append(String.format("\t%-15s            : %s\n", "CIRCLE", circle));
	                	        billText.append(String.format("\t%-15s      : %s\n", "DISTRIBUTION", distribution));
	                	        billText.append(String.format("\t%-15s            : %s\n", "TARIFF", tariff));
	                	        billText.append(String.format("\t%-15s           : %s\n", "PHASE", phase));
	                	        billText.append(String.format("\t%-15s        : %s %s\n", "sanctionedload", sanctioned1,"KW"));
	                	        billText.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

	                	        // Energy consumption section
	                	        billText.append("\t                                     ENERGY CONSUMPTION                 \n");
	                	        billText.append("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
	                	        billText.append(String.format("\t%-15s  %s\n", "READINGS", ""));
	                	        billText.append(String.format("\t%-15s      : %s\n", "DATE", to11));
	                	        billText.append(String.format("\t%-15s : %s\n", "BILL CYCLE", billCycle));
	                	        billText.append(String.format("\t%-15s      : %s\n", "TYPE", supp));
	                	        billText.append(String.format("\t%-15s: %s %s-10s  %s\n", "BILL PERIOD", from11,"TO",to11));
	                	        billText.append(String.format("\t%-13s: %s\n", "BILL AMOUNT", totalAmountText));
	                	        billText.append(String.format("\t%-15s  : %s\n", "DUE DATE",newDateString ));
	                	        billText.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

	                	        // Print the bill
	                	        System.out.println(billText.toString());
	                	        textArea.setText(billText.toString());
	                        
	                       

	                    } else {
	                        JOptionPane.showMessageDialog(null, "NO REPORT");
	                    }

	                } catch (Exception e1) {
	                    JOptionPane.showMessageDialog(null, e1);

	                }
			}
		});
		btnNewButton.setBounds(246, 472, 85, 35);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("TOTAL");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(44, 404, 73, 13);
		panel.add(lblNewLabel_3);
		
		 la4 = new JLabel("");
		la4.setBounds(228, 404, 73, 13);
		panel.add(la4);
		
		JButton btnNewButton_1 = new JButton("PRINT");
		btnNewButton_1.setBounds(1168, 556, 85, 35);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				
				try (BufferedWriter fileOut = new BufferedWriter(new FileWriter("C:\\Users\\yadha\\OneDrive\\Desktop\\my.doc"))) {
				    fileOut.write(textArea.getText());
				    // Ensure all data is written to the file
				    fileOut.flush();
				    // Close the BufferedWriter
				    fileOut.close();
				    JOptionPane.showMessageDialog(null, "File successfully written!");
				    textArea.print();
				    Home frame = new Home();
					frame.setUndecorated(true);
					setVisible(false);
					frame.setVisible(true);
					
				} catch (IOException e1) {
				    JOptionPane.showMessageDialog(null, "Error writing to file: " + e1.getMessage());
				    e1.printStackTrace();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
		JLabel lblNewLabel_4 = new JLabel("ELECTRICITY BILL REPORT");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_4.setBounds(583, 22, 353, 62);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\yadha\\Downloads\\Untitled design.png"));
		lblNewLabel_5.setBounds(-778, -12, 2329, 856);
		contentPane.add(lblNewLabel_5);
	}
}