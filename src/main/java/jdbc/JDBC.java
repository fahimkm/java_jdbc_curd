package jdbc;
import java.io.*;
import java.sql.*;

//functions in class JDBC can perform all CRUD operations
public class JDBC {	
	private static String url;
	private static String user;
	private static String password;
	
	//input connection details through constructor 
	JDBC(String url,String user,String password){
		JDBC.url = url;
		JDBC.user= user;
		JDBC.password = password;
	}
//function connect() establishes connection with the database	
	public static Connection connect() throws SQLException {
		return DriverManager.getConnection(url, user, password);
		        }
//insert parameterized queries 	
	public static void insert(String name,int id,String status) {
		Connection conn = null;
		try {
			conn = connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        PreparedStatement ps;
		try {
			ps = conn.prepareStatement("INSERT INTO emp1 VALUES(?, ?, ?)");
			ps.setString(1,name);
	        ps.setInt(2,id);
	        ps.setString(3,status);
	        ps.execute(); 
	        System.out.println("data inserted");
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}

	//read data from table 
	public static void retrieve(String query) {
		Connection conn = null;
		try {
			conn = connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String name = rs.getString("Name");
				Integer id = rs.getInt("ID");
				String status = rs.getString("Status");
				System.out.printf( "%-15s %15s %15s %n","ID","NAME","STATUS");
				System.out.printf( "%-15s %15s %15s %n",id,name,status);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//perform update and delete operations 	
	}
	public static void queries(String query) {
		Connection conn = null;
		String[] action = query.toLowerCase().split(" ");
		
		try {
			conn = connect();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			if(action[0].equals("create")) {
			System.out.println("Table created");
			}
			else if(action[0].equals("drop")){
				System.out.println("Table dropped");
			}
			else if(action[0].equals("delete")){
				System.out.println("item deleted");
			}
			else if(action[0].equals("update")) {
				System.out.println("table updated");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
