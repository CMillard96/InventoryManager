import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StockChecker {

	public static void main(String[] args) {
		StockChecker pro = new StockChecker();
		pro.createConnection();

	}
	
	void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mumanidb", "root", "Alanshearer9");
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ITEMS");
			while(rs.next()) {
				String name = rs.getString("brand");
				System.out.println(name);
			}
			
			
			System.out.println("Database Connection Success!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
