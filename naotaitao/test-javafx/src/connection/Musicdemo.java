package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import user.Music;

public class Musicdemo {
	public ArrayList<Music> getMusicList(int id) {
		ArrayList<Music> a = new ArrayList<Music>();
		Connection conn = Demo.getConnection();
		String sql = "select * from musictable   where aid= '"+id+"' ORDER BY morder ASC" ;
		Music m = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				m = new Music();
				m.setMusicname(rs.getString("musicname"));
				m.setAuthor(rs.getString("author"));
				m.setDuration(rs.getString("duration"));
				m.setMusicpath(rs.getString("musicpath"));
				m.setOrder(rs.getInt("morder"));
				
				a.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return a;
	}
}
