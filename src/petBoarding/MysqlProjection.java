package petBoarding;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MysqlProjection {
	
	public String name;
	public String type;
	
	public MysqlProjection(String name,String type){
		this.name = name;
		this.type = type.toUpperCase();
	}

	public String getValue(ResultSet rs) {
		try{
			switch(type){
				case "INT":
					return String.format("%20d",rs.getInt (name));
				case "FLOAT":
					return String.format("%20.2f",rs.getFloat (name));
				case "STRING":
					return String.format("%20s",rs.getString (name));
				case "DATE":
					DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
					return String.format("%20s",df.format(rs.getDate (name)));
				default:
					return String.format("%20s",rs.getString (name));
			}
		}catch(SQLException e){
			//DO NOTHING
		}
		return "";
	}
}
