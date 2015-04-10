package petBoarding;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author Hang
 * Operation SupperClass
 */
public class JDBCOperation {
	
	/**
	 * @return
	 * Get Current Connection
	 */
	private Connection getConn(){
		return PetBoardingDB.get().getConn();
	}
	public void insert(String sql) throws SQLException{
		Statement s = getConn().createStatement();
		int count;
		count = s.executeUpdate(sql);
		s.close();
		System.out.println ("Insertion Complete. "+count + " rows were inserted");
	}
	
	/**
	 * @param sql SQL To be executed;
	 * @throws SQLException
	 * Execute Update
	 */
	public void update(String sql) throws SQLException{
		Statement s = getConn().createStatement();
		int count;
		count = s.executeUpdate(sql);
		s.close();
		System.out.println ("Update Complete. "+count + " rows were affected");
	}
	
	/**
	 * @param sql SQL To be executed;
	 * @throws SQLException
	 * Execute Deletion
	 */
	public void delete(String sql) throws SQLException{
		Statement s = getConn().createStatement();
		int count;
		count = s.executeUpdate(sql);
		s.close();
		System.out.println ("Deletion Complete. "+count + " rows were deleted");
	}
	
	
	/**
	 * @param sql SQL To be executed;
	 * @param projections projection fields
	 * @throws SQLException
	 * Execute Query
	 */
	public void query(String sql,List<MysqlProjection> projections) throws SQLException{
		Statement s = getConn().createStatement();
		int count = 0;
		s.executeQuery(sql);
		ResultSet rs = s.getResultSet ();
		//Generate Headers;
		if(projections != null){
			System.out.println("----------------------");
	        System.out.println("RESULT:");
	        System.out.println("----------------------");
			for(MysqlProjection proj:projections){
				System.out.print(String.format("%20s",proj.name));
			}
			System.out.print("\n");
		}
		//Generate Results;
		while (rs.next ())
		   {
			   count ++;
			   if(projections == null) continue;
		       for(MysqlProjection proj:projections){
		    	   System.out.print(proj.getValue(rs));
		       }
		       System.out.print("\n");
		   }
		rs.close ();
		s.close();
		if(projections != null) System.out.println("----------------------");
		System.out.println ("Query Complete. "+count + " rows were fetched");
		System.out.println("----------------------");
	}
	
	
	/**
	 * @param sql SQL To be executed;
	 * @param projections projection fields
	 * Execute an SQL
	 */
	public void executeQuery(String sql,List<MysqlProjection> projections){
		sql = sql.replace("\n", "").replace("\r", "").trim();
		System.out.println("EXECUTEING QUERY: "+sql);
		//Get The First Word in the SQL
		String oprType = sql.split(" ")[0].toUpperCase();
		
		//IF the SQL Contains Words like Drop, Alter ,CREATE, we believe it could be a dangerous SQL and forbidden it from being executed 
		if(sql.toUpperCase().contains("DROP ") || sql.toUpperCase().contains("ALTER") || sql.toUpperCase().contains("CREATE")){
			System.out.println("Damn What are u gonna do? Don't attempt to change table structure dude!");
		}
		//If the operation is a modification, redirect to execute Update
		else if(oprType.equals("INSERT") || oprType.equals("UPDATE") || oprType.equals("DELETE")) executeUpdate(sql);
		//execute the query sql
		else{
			try{
				query(sql,projections);
			}catch(SQLException e){
				showError(e);
			}
		}
	}
	/**
	 * @param sql SQL To be executed;
	 * Execute an SQL
	 */
	public void executeQuery(String sql){
		executeQuery(sql,null);
	}
	
	/**
	 * @param sql SQL To be executed;
	 * Execute an SQL When it is of Update Type;
	 */
	public void executeUpdate(String sql){
		//Get The First Word in the SQL
		String oprType = sql.split(" ")[0].toUpperCase();
		
		//Determine What kind of Operation to do base on the first Word
		switch(oprType){
			case "INSERT":
				try{
					insert(sql);
				}
				catch(SQLException e){
					showError(e);
				}
				break;
			case "UPDATE":
				try{
					update(sql);
				}
				catch(SQLException e){
					showError(e);
				}
				break;
			case "DELETE":
				try{
					delete(sql);
				}
				catch(SQLException e){
					showError(e);
				}
				break;	
			default:
				try{
					Statement s = getConn().createStatement();
					s.executeQuery(sql);
					s.close();
					System.out.println ("Query '"+sql+"' executed");
				}
				catch(SQLException e){
					showError(e);
				}
		}
	}
	
	/**
	 * @param e error
	 * Handling Error
	 */
	public void showError(SQLException e){
		System.out.println ("Error message: " + e.getMessage ());
	    System.out.println ("Error number: " + e.getErrorCode ());
	}
}
