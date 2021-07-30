package connection;

import java.sql.*;
import java.util.ArrayList;

import user.Album;
import user.Preview;
import user.User;
import user.userinformation;

public class Previewdemo {
	public void addPeview(Preview p) {
		Connection conn = Demo.getConnection();
		String sql = "INSERT INTO previewtable(preview,aid,uid,ratescore)"+
				"values(?,?,?,?)";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, p.getContent());
			ptmt.setInt(2, p.getAid());
			ptmt.setInt(3, p.getUid());
			ptmt.setInt(4, p.getRatescore());
			ptmt.execute(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Preview> getPreviewByAlbumId(int id) {
		ArrayList<Preview>  a = new ArrayList<Preview>();
		Preview p = new Preview();
		Connection conn = Demo.getConnection();
		String sql = "select * from previewtable where aid = '"+id+"'";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				p = new Preview();
				p.setUid(rs.getInt("uid"));
				p.setContent(rs.getString("preview"));
				p.setRatescore(rs.getInt("ratescore"));
				
				a.add(p);
			}
		}catch(SQLException se) {
			  se.printStackTrace();
		  } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	
		
	
}
