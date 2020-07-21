import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Display {

	private JFrame frame;
	private JTextField txtBrand;
	private JTextField txtItemID;
	private JTextField txtSize;
	private JTextField txtPrice;
	private JTextField txtCategory;
	Connection con;
	private JTable table;
	private JComboBox comboBox;

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
		bindCombo();
		
	}
	
	public void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mumanidb", "root", "Alanshearer9");
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		btnInsert.setBounds(534, 68, 97, 23);
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
		
		/* JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setRowCount(0);
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
		btnRefresh.setBounds(534, 130, 89, 23);
		frame.getContentPane().add(btnRefresh); */
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 127, 496, 180);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ItemId", "Brand", "Size", "Price", "Category"
			}
		));
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);}
		
		public void bindCombo() {
			Statement st;
			ResultSet rs;
			
			try {
				st = con.createStatement();
				rs = st.executeQuery("SELECT 'categoryId', 'categoryName' FROM mumanidb.categories");
				while(rs.next()) {
					comboBox.addItem((int)rs.getInt(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
		}
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Item> list = getData((int)comboBox.getSelectedItem());
				DefaultTableModel model = new DefaultTableModel();
				model.setColumnIdentifiers(new Object[] {"ItemId", "Brand", "Size", "Price", "Category"});
				Object[] row = new Object[5];
				for(int i = 0; i < list.size(); i++) {
					row[0] = list.get(i).getItemID();
					row[1] = list.get(i).getItemBrand();
					row[2] = list.get(i).getItemSize();
					row[3] = list.get(i).getItemPrice();
					row[4] = list.get(i).getItemCategory();
					model.addRow(row);
				}
				table.setModel(model);
			}
		});
		
		comboBox.setBounds(534, 171, 110, 23);
		frame.getContentPane().add(comboBox);
	}
	
	public ArrayList<Item> getData(int catID){
		ArrayList<Item> list = new ArrayList<Item>();
		
		Statement st;
		ResultSet rs;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT 'ItemId', 'Brand', 'Size', 'Price', 'Category' FROM 'mumanidb.items' WHERE 'Category' ="+ catID);
			
			Item I;
			while(rs.next()) {
				I = new Item(
						rs.getInt("ItemId"),
						rs.getString("Brand"),
						rs.getString("Size"),
						rs.getFloat("Price"),
						rs.getInt("Category"));
				list.add(I);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
