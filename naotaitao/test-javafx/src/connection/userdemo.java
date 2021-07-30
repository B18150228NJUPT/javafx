package connection;
import java.sql.*;

import user.User;
import user.userinformation;

public class userdemo {
	public User getUserById(String id) {
		User u = new User();
		Demo Demo = new Demo();
		Connection conn = Demo.getConnection();
		String sql = "select * from usertable where Userid =?";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, id);
			ResultSet rs =ptmt.executeQuery();
			while(rs.next()) {
				u.setPassword(rs.getString("Upass"));
				u.setName(rs.getString("Userid"));
				u.setPhone(rs.getString("phone"));
				u.setCollection(rs.getString("Uname"));
				u.setType(rs.getString("role"));
				userinformation.id = rs.getString("Userid");
			}
		}catch(SQLException se) {
			  se.printStackTrace();
		  } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	
	public void updateUserByid(User u)
	{
		Demo Demo = new Demo();
		Connection conn = Demo.getConnection();
		String sql = "UPDATE usertable set Uname=?,Upass=?,phone=? where Userid=?";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1,u.getCollection());
			ptmt.setString(2,u.getPassword());
			ptmt.setString(3,u.getPhone());
			ptmt.setString(4,userinformation.id);
			ptmt.execute();
		}catch(SQLException se) {
			  se.printStackTrace();
		  } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
