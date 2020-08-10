import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.util.Date;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserInterface {

	private JFrame frame;
	private JTextField txtID;
	private JTextField txtBrand;
	private JTextField txtSize;
	private JTextField txtCost;
	private JTextField txtCategory;
	private JTable table;
	private JTextField txtPrice;
	private JTextField txtDateSold;

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
		displayItems();
	}
	
	public Connection getConnection() {
		Connection con;
		try {		
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mumanidb", "root", "Alanshearer9");
			return con;
		} catch (ClassNotFoundException | SQLException e) {
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
				item = new Item(rs.getInt("itemId"),
								rs.getString("brand"),
								rs.getString("size"),
								rs.getFloat("cost"),
								rs.getInt("categoryId"),
								rs.getFloat("price"),
								rs.getDate("dateSold"));
				itemList.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itemList;
	}
	
	public void displayItems() {
		ArrayList<Item> list = getItemList();
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		Object[] row = new Object[7];
		for(int i = 0; i < list.size(); i++) {
			row[0] = list.get(i).getItemID();
			row[1] = list.get(i).getItemBrand();
			row[2] = list.get(i).getItemSize();
			row[3] = list.get(i).getItemCost();
			row[4] = list.get(i).getItemCategory();
			row[5] = list.get(i).getItemPrice();
			row[6] = list.get(i).getDateSold();
			
			model.addRow(row);
		}
	}
	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 826, 550);
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
		
		JLabel lblCost = new JLabel("Cost:");
		lblCost.setBounds(40, 158, 46, 14);
		frame.getContentPane().add(lblCost);
		
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
		
		txtCost = new JTextField();
		txtCost.setBounds(116, 155, 112, 20);
		frame.getContentPane().add(txtCost);
		txtCost.setColumns(10);
		
		txtCategory = new JTextField();
		txtCategory.setBounds(116, 191, 112, 20);
		frame.getContentPane().add(txtCategory);
		txtCategory.setColumns(10);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Integer itemId = Integer.parseInt(txtID.getText());
				String brandName = txtBrand.getText();
				String size = txtSize.getText();
				Float cost = Float.parseFloat(txtCost.getText());
				Integer category = Integer.parseInt(txtCategory.getText());
				
				String insertQuery = "INSERT INTO mumanidb.items (itemId, brand, size, cost, categoryId) VALUES (?,?,?,?,?)";
				
				Connection con = getConnection();
				try {
					PreparedStatement pstmt = con.prepareStatement(insertQuery);
					pstmt.setInt(1, itemId);
					pstmt.setString(2,  brandName);
					pstmt.setString(3, size);
					pstmt.setFloat(4, cost);
					pstmt.setInt(5, category);
					pstmt.executeUpdate();
					displayItems();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnInsert.setBounds(40, 306, 89, 23);
		frame.getContentPane().add(btnInsert);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Integer itemId = Integer.parseInt(txtID.getText());
				String brand = txtBrand.getText();
				String size = txtSize.getText();
				Float cost = Float.parseFloat(txtCost.getText());
				Integer category = Integer.parseInt(txtCategory.getText());
				
				String updateQuery = "UPDATE mumanidb.items SET brand = ?, size = ?, cost = ?, category = ? WHERE itemId = ?";
				
				Connection con = getConnection();
				try {
					PreparedStatement pstmt = con.prepareStatement(updateQuery);
					pstmt.setString(1, brand);
					pstmt.setString(2, size);
					pstmt.setFloat(3,  cost);
					pstmt.setInt(4, category);
					pstmt.setInt(5,  itemId);
					pstmt.executeUpdate();
					displayItems();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnUpdate.setBounds(139, 306, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Integer itemId = Integer.parseInt(txtID.getText());
				String deleteQuery = "DELETE FROM mumanidb.items WHERE itemId = ?";
				
				Connection con = getConnection();
				try {
					PreparedStatement pstmt = con.prepareStatement(deleteQuery);
					pstmt.setInt(1, itemId);
					pstmt.executeUpdate();
					displayItems();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnDelete.setBounds(88, 340, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(299, 56, 458, 364);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				txtID.setText(model.getValueAt(i, 0).toString());
				txtBrand.setText(model.getValueAt(i, 1).toString());
				txtSize.setText(model.getValueAt(i, 2).toString());
				txtCost.setText(model.getValueAt(i, 3).toString());
				txtCategory.setText(model.getValueAt(i, 4).toString());
				txtPrice.setEditable(true);
				txtDateSold.setEditable(true);
				
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item ID", "Brand", "Size", "Cost", "Category", "Price", "Date Sold"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnSold = new JButton("Mark as Sold");
		btnSold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				Integer itemId = Integer.parseInt(txtID.getText());
				
				Float price = Float.parseFloat(txtPrice.getText());
				String dateSold = txtDateSold.getText();
				
				
				String markSold = "UPDATE mumanidb.items SET price = ?, dateSold = ? WHERE itemId = ?";
				
				Connection con = getConnection();
				try {
					Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateSold);
					java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
					PreparedStatement pstmt = con.prepareStatement(markSold);
					pstmt.setFloat(1, price);
					pstmt.setDate(2, sqlDate);
					pstmt.setInt(3, itemId);
					pstmt.executeUpdate();
					displayItems();
				} catch (SQLException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
		}});
		
		
		
		btnSold.setBounds(88, 397, 101, 23);
		frame.getContentPane().add(btnSold);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(40, 229, 46, 14);
		frame.getContentPane().add(lblPrice);
		
		JLabel lblDateSold = new JLabel("Date Sold:");
		lblDateSold.setBounds(40, 264, 56, 14);
		frame.getContentPane().add(lblDateSold);
		
		txtPrice = new JTextField();
		txtPrice.setEditable(false);
		txtPrice.setBounds(116, 226, 112, 20);
		frame.getContentPane().add(txtPrice);
		txtPrice.setColumns(10);
		
		txtDateSold = new JTextField();
		txtDateSold.setEditable(false);
		txtDateSold.setBounds(116, 261, 112, 20);
		frame.getContentPane().add(txtDateSold);
		txtDateSold.setColumns(10);
	}
}
