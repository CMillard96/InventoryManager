import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Display {

	private JFrame frame;
	private JTextField txtBrand;
	Connection con;
	private JTextField txtItemID;
	private JTextField txtSize;
	private JTextField txtPrice;
	private JTextField txtCategory;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display window = new Display();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Display() {
		initialize();
		createConnection();
	}
	
	public void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mumanidb", "root", "Alanshearer9");
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 687, 369);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton btnInsert = new JButton("Insert Item");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Integer itemId = Integer.parseInt(txtItemID.getText());
					String brandName = txtBrand.getText();
					String size = txtSize.getText();
					Float price = Float.parseFloat(txtPrice.getText());
					Integer category = Integer.parseInt(txtCategory.getText());
					Statement stmt = con.createStatement();
					String dbOp = "INSERT INTO mumanidb.items (itemId, brand, size, price, categoryId) VALUES (" + itemId + ", '" + brandName + "', '" + size + "', " + price + ", " + category + ")";
					stmt.execute(dbOp);
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnInsert.setBounds(541, 68, 97, 23);
		frame.getContentPane().add(btnInsert);
		
		txtBrand = new JTextField();
		txtBrand.setBounds(136, 69, 86, 20);
		frame.getContentPane().add(txtBrand);
		txtBrand.setColumns(10);
		
		txtItemID = new JTextField();
		txtItemID.setBounds(40, 69, 86, 20);
		frame.getContentPane().add(txtItemID);
		txtItemID.setColumns(10);
		
		txtSize = new JTextField();
		txtSize.setBounds(232, 69, 86, 20);
		frame.getContentPane().add(txtSize);
		txtSize.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(328, 69, 86, 20);
		frame.getContentPane().add(txtPrice);
		txtPrice.setColumns(10);
		
		JLabel lblItemId = new JLabel("Item ID");
		lblItemId.setBounds(59, 44, 46, 14);
		frame.getContentPane().add(lblItemId);
		
		JLabel lblBrand = new JLabel("Brand");
		lblBrand.setBounds(154, 44, 46, 14);
		frame.getContentPane().add(lblBrand);
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setBounds(252, 44, 46, 14);
		frame.getContentPane().add(lblSize);
		
		txtCategory = new JTextField();
		txtCategory.setBounds(424, 69, 86, 20);
		frame.getContentPane().add(txtCategory);
		txtCategory.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(346, 44, 46, 14);
		frame.getContentPane().add(lblPrice);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(442, 44, 46, 14);
		frame.getContentPane().add(lblCategory);
	}
}
