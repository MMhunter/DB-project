package petBoarding;

import java.sql.*;

/**
 * @author Hang
 * Database Singleton
 * Containing current DB Connection.
 */
public class PetBoardingDB {
	
	private static PetBoardingDB petBoardingDB = null;
	private Connection conn; // Current Connection
	public static String databaseURL = "jdbc:mysql://localhost/petBoarding";
	public static String databaseUserName = "root";
	public static String databaseUserPassword = "WS_MMhunter";
	
	/**
	 * Get the current DB Instance
	 * @return current Singleton
	 */
	public static PetBoardingDB get(){
		if(petBoardingDB == null) {
			petBoardingDB = new PetBoardingDB();
	      }
		return petBoardingDB;
	}
	
	public PetBoardingDB(){
		connectMysql();
	}
	
	/**
	 * Connect to Mysql Database with current configuration
	 */
	private void connectMysql(){
		try
        {
            String userName = databaseUserName;
            String password = databaseUserPassword;
            String url = databaseURL;
            Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            conn = DriverManager.getConnection (url, userName, password);
            System.out.println ("Database connection established");
        }
        catch (Exception e)
        {
            System.out.println ("Cannot connect to database server");
        }
	}
	
	/**
	 * Disconnect Mysql
	 */
	public void disconnectMysql(){
		if(conn != null)
		try
        {
            conn.close ();
            System.out.println ("Database connection terminated");
        }
        catch (Exception e) { /* ignore close errors */ }
	}
	
	public Connection getConn(){
		return conn;
	}

	public static String getDatabaseURL() {
		return databaseURL;
	}

	public static void setDatabaseURL(String databaseURL) {
		PetBoardingDB.databaseURL = databaseURL;
	}

	public static String getDatabaseUserName() {
		return databaseUserName;
	}

	public static void setDatabaseUserName(String databaseUserName) {
		PetBoardingDB.databaseUserName = databaseUserName;
	}

	public static String getDatabaseUserPassword() {
		return databaseUserPassword;
	}

	public static void setDatabaseUserPassword(String databaseUserPassword) {
		PetBoardingDB.databaseUserPassword = databaseUserPassword;
	}

	
}
