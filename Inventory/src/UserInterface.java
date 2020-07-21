import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UserInterface {

	private JFrame frame;
	private JTextField txtID;
	private JTextField txtBrand;
	private JTextField txtSize;
	private JTextField txtPrice;
	private JTextField txtCategory;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserInterface() {
		initialize();
	}
	
	public Connection getConnection() {
		Connection con;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mumanidb", "root", "Alanshearer9");
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Item> getItemList() {
		ArrayList<Item> itemList = new ArrayList<Item>();
		Connection conn = getConnection();
		
		String query = "SELECT * FROM mumanidb.items";
		Statement st;
		ResultSet rs;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			Item item;
			
			while(rs.next()) {
				item = new Item(rs.getInt("ID"),
								rs.getString("brand"),
								rs.getString("size"),
								rs.getFloat("price"),
								rs.getInt("category"));
				itemList.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itemList;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblID = new JLabel("Item ID:");
		lblID.setBounds(40, 56, 46, 14);
		frame.getContentPane().add(lblID);
		
		JLabel lblBrand = new JLabel("Brand:");
		lblBrand.setBounds(40, 89, 46, 14);
		frame.getContentPane().add(lblBrand);
		
		JLabel lblSize = new JLabel("Size:");
		lblSize.setBounds(40, 124, 46, 14);
		frame.getContentPane().add(lblSize);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(40, 158, 46, 14);
		frame.getContentPane().add(lblPrice);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(40, 194, 56, 14);
		frame.getContentPane().add(lblCategory);
		
		txtID = new JTextField();
		txtID.setBounds(116, 53, 112, 20);
		frame.getContentPane().add(txtID);
		txtID.setColumns(10);
		
		txtBrand = new JTextField();
		txtBrand.setBounds(116, 86, 112, 20);
		frame.getContentPane().add(txtBrand);
		txtBrand.setColumns(10);
		
		txtSize = new JTextField();
		txtSize.setBounds(116, 121, 112, 20);
		frame.getContentPane().add(txtSize);
		txtSize.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(116, 155, 112, 20);
		frame.getContentPane().add(txtPrice);
		txtPrice.setColumns(10);
		
		txtCategory = new JTextField();
		txtCategory.setBounds(116, 191, 112, 20);
		frame.getContentPane().add(txtCategory);
		txtCategory.setColumns(10);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(40, 249, 89, 23);
		frame.getContentPane().add(btnInsert);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(139, 249, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(88, 283, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(309, 56, 343, 307);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Item ID", "Brand", "Size", "Price", "Category"   
			}
		));
		scrollPane.setViewportView(table);
	}
}
