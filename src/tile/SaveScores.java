package tile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;
import MAIN.BCrypt;

public class SaveScores {
	static final String selSongsData = "SELECT * FROM scores WHERE id = ?";
	static final String insertSongsData = "INSERT INTO scores"
			+ " (id,songName,scores,marvelousCount,perfectCount,goodCount,greatCount,missCount) "
			+ "VALUES (?,?,?,?,?,?,?,?)";
	static PreparedStatement checkStatement, appendStatement;
	public String alert = "";
	public static LinkedList<LinkedList<String>> table = new LinkedList<>();

	public SaveScores(int myId, String songName, int scores, int marvelousCount, int perfectCount, int goodCount,
			int greatCount, int missCount) {
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/songs", prop);) {
			checkStatement = conn.prepareStatement(selSongsData);
			appendStatement = conn.prepareStatement(insertSongsData);
			appendStatement.setInt(1, myId);
			appendStatement.setString(2, songName);
			appendStatement.setInt(3, scores);
			appendStatement.setInt(4, marvelousCount);
			appendStatement.setInt(5, perfectCount);
			appendStatement.setInt(6, goodCount);
			appendStatement.setInt(7, greatCount);
			appendStatement.setInt(8, missCount);
			appendStatement.execute();
//			checkStatement.setInt(1, myId);
//
//			ResultSet rs = checkStatement.executeQuery();

//			while (rs.next()) {
//				LinkedList<String> tableIn = new LinkedList<>();
//				Integer a = rs.getInt("songId");
//				rs.getString("songName");
//				Integer b = rs.getInt("scores");
//				Integer c = rs.getInt("marvelousCount");
//				Integer d = rs.getInt("perfectCount");
//				Integer e = rs.getInt("goodCount");
//				Integer f = rs.getInt("greatCount");
//				Integer h = rs.getInt("missCount");
//
//				tableIn.add(a.toString());
//				tableIn.add(rs.getString("songName"));
//				tableIn.add(b.toString());
//				tableIn.add(c.toString());
//				tableIn.add(d.toString());
//				tableIn.add(e.toString());
//				tableIn.add(f.toString());
//				tableIn.add(h.toString());
//				table.add(tableIn);
//			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	
	
	public static void getHistory(int id) throws SQLException {
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");		
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/songs", prop);) {
			checkStatement = conn.prepareStatement(selSongsData);			
			checkStatement.setInt(1,id);			
			ResultSet rs=checkStatement.executeQuery();		
			while(rs.next()) {
			LinkedList<String> tableIn=new LinkedList<>(); 
		    Integer a= rs.getInt("songId");	  
			rs.getString("songName");					
			Integer b=rs.getInt("scores");
			Integer c=rs.getInt("marvelousCount");
			Integer d=rs.getInt("perfectCount");
			Integer e=rs.getInt("goodCount");
			Integer f=rs.getInt("greatCount");
			Integer h=rs.getInt("missCount");		
			tableIn.add(a.toString());
			tableIn.add(rs.getString("songName"));
			tableIn.add(b.toString());
			tableIn.add(c.toString());
			tableIn.add(d.toString());
			tableIn.add(e.toString());
			tableIn.add(f.toString());
			tableIn.add(h.toString());
			table.add(tableIn);
			}
	}
	

}
}