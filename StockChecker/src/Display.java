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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Display {

	private JFrame frame;
	private JTextField txtBrand;
	private JTextField txtItemID;
	private JTextField txtSize;
	private JTextField txtPrice;
	private JTextField txtCategory;
	Connection con;
	private JTable table;

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
		frame.setBounds(100, 100, 819, 369);
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
					PreparedStatement stmt = con.prepareStatement("INSERT INTO mumanidb.items VALUES (?,?,?,?,?)");
					//String dbOp = "INSERT INTO mumanidb.items (itemId, brand, size, price, categoryId) VALUES (" + itemId + ", '" + brandName + "', '" + size + "', " + price + ", " + category + ")";
					stmt.setInt(1, itemId);
					stmt.setString(2, brandName);
					stmt.setString(3, size);
					stmt.setFloat(4, price);
					stmt.setInt(5, category);
					stmt.execute();
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnInsert.setBounds(646, 68, 97, 23);
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
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"ItemId", "Brand", "Size", "Price", "Category"},
			},
			new String[] {
				"ItemId", "Brand", "Size", "Price", "Category"
			}
		));
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBounds(40, 138, 471, 155);
		frame.getContentPane().add(table);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				Statement stmt;
				try {
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM mumanidb.items");
					while (rs.next()) {
						Integer itemId = rs.getInt("itemId");
						String brandName = rs.getString("brand");
						String size = rs.getString("size");
						Float price = rs.getFloat("price");
						Integer category = rs.getInt("categoryId");
						tableModel.addRow(new Object[] {itemId, brandName, size, price, category});
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnRefresh.setBounds(613, 201, 89, 23);
		frame.getContentPane().add(btnRefresh);
	}
}
