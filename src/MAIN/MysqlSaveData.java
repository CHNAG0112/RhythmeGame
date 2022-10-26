package MAIN;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class MysqlSaveData {
	static final String sqlCheckAccount = "SELECT account FROM player WHERE account = ?";
	static final String sqlAppendAccount = "INSERT INTO player (account,password) VALUES (?,?)";
	static PreparedStatement checkStatement, appendStatement;
	public String alert=""; 
	

	public MysqlSaveData(String accont,String passwd) {
		
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/songs", prop);) {
			checkStatement = conn.prepareStatement(sqlCheckAccount);
			appendStatement = conn.prepareStatement(sqlAppendAccount);
			if (!isDataRepeat(accont)) {
				if (appendData(accont, passwd)) {
					System.out.println("Success");
				}else {
				
					System.out.println("E2");
				}
			}else {
				alert="Account is repeat";
				System.out.println("E1");
			}
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}

		
	}
	
	static boolean isDataRepeat(String account) throws Exception {
		checkStatement.setString(1, account);
		ResultSet rs = checkStatement.executeQuery();
		return rs.next();
	}
	
	static boolean appendData(String account, String passwd)
			throws Exception {
			appendStatement.setString(1, account);
			appendStatement.setString(2, BCrypt.hashpw(passwd, BCrypt.gensalt()));			
			int n = appendStatement.executeUpdate();
			
			return n != 0;
		}

}
