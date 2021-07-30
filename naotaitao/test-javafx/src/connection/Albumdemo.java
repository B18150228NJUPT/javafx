package connection;
import java.sql.*;
import java.util.ArrayList;

import user.Album;
import user.Music;
import user.Preview;

public class Albumdemo {
	public Album getAlbumbyid(int id) {
		Album a = new Album();
		Connection conn = Demo.getConnection();
		String sql = "select * from albumtable where  id=?";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, id);
			ResultSet rs =ptmt.executeQuery();
			while(rs.next()) {
			//	password = rs.getString("password");
				a.setScore(rs.getFloat("score"));
				a.setAuthor(rs.getString("author"));
				a.setName(rs.getString("name"));
				a.setImagepath(rs.getString("imagepath"));
			}
		}catch(SQLException se) {
			  se.printStackTrace();
		  } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	
	public void updateScore(Preview p) {
		Connection conn = Demo.getConnection();
		float oldScore = 0;
		String sql = "select score from albumtable where  id=?";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1,p.getAid());
			ResultSet rs =ptmt.executeQuery();
			while(rs.next()) {
				oldScore = rs.getFloat("score");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		float newScore = (oldScore+(float)p.getRatescore())/2;
		String sql2 = "UPDATE albumtable set score=?  where id=? ";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql2);
			ptmt.setFloat(1, newScore);
			ptmt.setInt(2,p.getAid());

			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Album> getAlbumbyName(String search) {
		ArrayList<Album> a = new ArrayList<Album>();
		Connection conn = Demo.getConnection();
		String sql = null;
		Album ab = null;
		String[] s1 = search.split(" ");
		ArrayList<String> al = new ArrayList<String>();
		for(int i=0;i<s1.length; i++)
		{
			if(!s1[i].equals(""))
			{
				al.add(s1[i]);
			}
		}
//		System.out.print(" al.size: "+al.size());
		if(al.size()==1) {
			sql ="SELECT * FROM albumtable WHERE name REGEXP '"+al.get(0)+"' ";
		}
		else
		{
			//System.out.print(al.get(0)+"  "+al.get(1));
			sql ="SELECT * FROM albumtable WHERE name REGEXP '"+al.get(0)+"' ";
			//You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version 
			//for the right syntax to use near 'a.id=b.id' at line 
//			sql ="SELECT  a.*,b.* FROM"
//					+ "(SELECT * from albumtable WHERE name REGEXP '"+al.get(0)+"')  as a"
//					+ "INNER JOIN"
//					+ "(SELECT * FROM albumtable WHERE name REGEXP '"+al.get(1)+"') as b"
//					+ "on a.id=b.id" ;
			
		}
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ab = new Album();
				ab.setId(rs.getInt("id"));
				ab.setImagepath(rs.getString("imagepath"));
				ab.setName(rs.getString("name"));
				ab.setAuthor(rs.getString("author"));
				ab.setScore(rs.getFloat("score"));
				
				a.add(ab);
			}
		}catch(SQLException se) {
			  se.printStackTrace();
		  } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	
	public ArrayList<Album> getAlbumbyIds(String collection) {
		ArrayList<Album> a = new ArrayList<Album>();
		Connection conn = Demo.getConnection();
		String sql = null;
		Album ab = null;
		String [] c2 = collection.split("/");
		for(int i=0;i<c2.length-1; i++) {
			try {
				sql = "SELECT * FROM albumtable WHERE id=?";
				PreparedStatement ptmt = conn.prepareStatement(sql);
				ptmt.setInt(1,Integer.parseInt(c2[i]));
				ResultSet rs =ptmt.executeQuery();
				while(rs.next()) {
						ab = new Album();
						ab.setId(rs.getInt("id"));
						ab.setScore(rs.getFloat("score"));
						ab.setAuthor(rs.getString("author"));
						ab.setName(rs.getString("name"));
						ab.setImagepath(rs.getString("imagepath"));
						a.add(ab);
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return a;
	}
	
	
}