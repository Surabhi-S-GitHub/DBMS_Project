package weather;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Project.ConnectionProvider;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Customr extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTable table_1;
    private JTextField textField;
    private Image backgroundImage;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Customr frame = new Customr();
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
    public Customr() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1920, 1080);
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        backgroundImage = new ImageIcon("C:\\Users\\yadha\\Downloads\\Untitled design.png").getImage();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(416, 133, 802, 177);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setOpaque(false);
        ((DefaultTableModel) table.getModel()).fireTableDataChanged();
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "meterno", "NAME", "EMAILID", "PHONENO" }
        ));

        JButton btnNewButton = new JButton("CUSTOMER DETAIL ");
        btnNewButton.setBounds(667, 38, 245, 44);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    btnNewButton.setEnabled(false);
                    Connection con = ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT detail.meterno, user, email, phone FROM detail");

                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();

                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0); // Clear existing rows

                    while (rs.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            row[i - 1] = rs.getString(i);
                        }
                        model.addRow(row);
                    }

                    rs.close();
                    st.close();
                    con.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }

            }
        });
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("print");
        btnNewButton_1.setBounds(1115, 357, 85, 21);
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    table.print(JTable.PrintMode.NORMAL);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
        contentPane.add(btnNewButton_1);

        JPanel panel = new JPanel();
        panel.setBounds(74, 368, -13, 10);
        contentPane.add(panel);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(401, 560, 817, 251);
        scrollPane_1.setOpaque(false);
        scrollPane_1.getViewport().setOpaque(false);
        contentPane.add(scrollPane_1);

        table_1 = new JTable();
        table_1.setOpaque(false);
        ((DefaultTableModel) table_1.getModel()).fireTableDataChanged();
        scrollPane_1.setViewportView(table_1);
        table_1.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "METERNO", "USER", "UNIT", "TOT", "STATUS1", "FROM", "TO" }
        ));

        JButton btnNewButton_2 = new JButton("SEARCH");
        btnNewButton_2.setBounds(934, 460, 102, 21);
        btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String s = textField.getText();
                    // btnNewButton_2.setEnabled(false);
                    Connection con = ConnectionProvider.getCon();
                    PreparedStatement pst = con.prepareStatement("SELECT detail.meterno, user, unit, tot, status1, FROM1, TO1 FROM detail, bill WHERE detail.meterno = bill.meterno AND detail.meterno=?");
                    pst.setString(1, s);
                    ResultSet rs = pst.executeQuery();

                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();

                    DefaultTableModel model1 = (DefaultTableModel) table_1.getModel();
                    model1.setRowCount(0); // Clear existing rows

                    while (rs.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            row[i - 1] = rs.getString(i);
                        }
                        model1.addRow(row);
                    }

                    rs.close();
                    pst.close();
                    con.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }

            }
        });
        contentPane.add(btnNewButton_2);

        textField = new JTextField();
        textField.setForeground(new Color(51, 153, 51));
        textField.setBounds(1104, 463, 96, 19);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JButton btnNewButton_3 = new JButton("BACK");
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        		Home frame = new Home();
				frame.setUndecorated(true);
				frame.setVisible(true);
        	}
        });
        btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_3.setBounds(1407, 811, 85, 21);
        contentPane.add(btnNewButton_3);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\yadha\\Downloads\\Untitled design.png"));
        lblNewLabel.setBounds(-925, -28, 2477, 891);
        contentPane.add(lblNewLabel);
    }
}