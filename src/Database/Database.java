package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private Connection connection;
	private Statement statement;
	private final String BD_NAME="SIO_PR1_DB";
	private final String USER="root";		
	private final String PASSWORD="root";
	private final String DRIVER="com.mysql.cj.jdbc.Driver";
	private final String SERVER="jdbc:mysql://localhost:8889/"+BD_NAME;
	
	public Database(){
		this.connectDriver();
	}
	
	//Method to connect to mysql driver
	public void connectDriver() {
		try {
			Class.forName(DRIVER);	
			connection = DriverManager.getConnection(SERVER, USER, PASSWORD);
			System.out.println("S'ha connectat al driver de la BD correctament");
		}catch(Exception e) {
			System.out.println("Error: No s'ha pogut connectar amb el driver de MySQL");
			e.printStackTrace();
		}
	}
	
	// Method to connect with the database
	public void connectBD() {
		try {
			statement = (Statement) connection.createStatement();
			System.out.println("S'ha connectat a la BD correctament");

		} catch (SQLException e) {
			System.out.println("Error: No s'ha pogut connectar amb la base de dades.");
			e.printStackTrace();
		}
	}

	// Method to disconnect de database
	public void disconnectBD(){
		try {
			statement.close();
			
		} catch (Exception e) {
			System.out.println("Error: No es possible tancar la base de dades.");
			e.printStackTrace();
		}
	}
	
	//Method to make a user query
	public String userQuery(String userId) throws SQLException{
		String id=null;
		PreparedStatement ps = connection.prepareStatement("SELECT id FROM User WHERE id LIKE ?");
		ps.setString(1, userId);
		ResultSet rs = ps.executeQuery();
			
		if(rs.isBeforeFirst()) {
			rs.next();
			id = rs.getString("id");
		}
		
		return id;
	}
	
	//Method to make a restaurant query
	public String restaurantQuery(String restId) throws SQLException {
		String id=null;
		PreparedStatement ps = connection.prepareStatement("SELECT id FROM Restaurant WHERE id LIKE ?");
		ps.setString(1, restId);
		ResultSet rs = ps.executeQuery();
			
		if(rs.isBeforeFirst()) {
			rs.next();
			id = rs.getString("id");
		}
		
		return id;
	}
	
	//Method to save a user-restaurant grade to the database
	public void saveRelation(String user, String restaurant, float grade) throws SQLException{
		PreparedStatement ps = connection.prepareStatement("INSERT INTO Relation(grade,id_user,id_rest) VALUES (?,?,?)");
		ps.setFloat(1, grade);
		ps.setString(2, user);
		ps.setString(3, restaurant);
		ps.executeUpdate();
	}
	
	//Method to save a user to the database
	public void saveUser(String id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO User(id) VALUES (?)");
		ps.setString(1, id);
		ps.executeUpdate();
	}
	
	//Method to save a restaurant grade to the database
	public void saveRestaurant(String id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO Restaurant(id) VALUES (?)");
		ps.setString(1, id);
		ps.executeUpdate();
	}
}
