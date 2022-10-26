package MAIN;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;



public class CheckAccount {
	static final String sqlCheckAccount = "SELECT * FROM player WHERE account = ?";
	
	static PreparedStatement checkStatement, appendStatement;

	private ResultSet rs;
     public boolean enterGame=false;
     public int Id;
	public CheckAccount(String accont,String passwd) {
		
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/songs", prop);) {
			checkStatement = conn.prepareStatement(sqlCheckAccount);
			checkStatement.setString(1, accont);
		     rs=checkStatement.executeQuery();
		if(rs.next()==true) {
			System.out.println("Account is true");		
			if(BCrypt.checkpw(passwd, rs.getString("password"))) {
				System.out.println("Password is correct");				
				Id=rs.getInt("id");
				enterGame=true;
			}
			else {
				System.out.println("Password is wrong");
			}
		}
		else {
			
		}
		}catch(Exception e) {
			System.out.println(e.toString());
		}

		
	}
	
	
	
	

}
