package connection;
import java.sql.*;
import java.util.ArrayList;

import user.Assert;
import user.CharData;
import user.User;
import user.userinformation;

public class AssertDao {

	
	public ArrayList<Assert> getAsserts() {
		ArrayList<Assert> u = new ArrayList<Assert>();
			Demo Demo = new Demo();
			Connection conn = Demo.getConnection();
			String sql = "select * from assertinfo ";
			try {
				PreparedStatement ptmt = conn.prepareStatement(sql);
//				ptmt.setString(1, id);
				ResultSet rs =ptmt.executeQuery();
				while(rs.next()) {
					Assert a = new Assert(rs.getString("assertNum"),rs.getString("type"),rs.getString("name"),
							rs.getString("status"),rs.getDate("time"),
							rs.getString("num"),rs.getString("source"),rs.getString("typeNum"));
					u.add(a);
				}
			}catch(SQLException se) {
				  se.printStackTrace();
			  } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return u;
		}
	
	public ArrayList<Assert> getLingyAsserts() {
		ArrayList<Assert> u = new ArrayList<Assert>();
			Demo Demo = new Demo();
			Connection conn = Demo.getConnection();
			String sql = "select * from fenpeiinfo,assertinfo WHERE fenpeiinfo.assertid= assertinfo.assertNum  ";
			try {
				PreparedStatement ptmt = conn.prepareStatement(sql);
//				ptmt.setString(1, id);
				ResultSet rs =ptmt.executeQuery();
				while(rs.next()) {
					Assert a = new Assert(rs.getString("assertNum"),rs.getString("name"),rs.getString("status"),
							rs.getString("num"),rs.getString("usedepartment"),
							rs.getString("fuzeren"),rs.getDate("fenpeitime"),rs.getString("fenpeibeizhu"));
					u.add(a);
				}
			}catch(SQLException se) {
				  se.printStackTrace();
			  } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return u;
		}
	
	public  ArrayList<CharData> setChardata(ArrayList<String> a) {
		ArrayList<CharData> ac = new ArrayList<CharData>();
		Demo Demo = new Demo();
		Connection conn = Demo.getConnection();
		
		
		for(String s:a) {
			try {
				CharData cd = new CharData();
				cd.setName(s);
				String sql = "select  assertinfo.status from assertinfo WHERE name=? ";
				PreparedStatement ptmt = conn.prepareStatement(sql);
				ptmt.setString(1, s);
				ResultSet rs =ptmt.executeQuery();
				int i = 0;
				while(rs.next()) {
					switch(rs.getString("status")) {
					case "闲置":
						cd.setXianzhi(cd.getXianzhi()+1);
						break;
					case "待领用":
						cd.setDaiLingyong(cd.getDaiLingyong()+1);
						break;	
					case "使用中":
						cd.setShiYongzhong(cd.getShiYongzhong()+1);
						break;
					case "待维修":
						cd.setDaiWeixiu(cd.getDaiWeixiu()+1);
						break;
					case "维修中":
						cd.setWeiXXiuzhong(cd.getWeiXXiuzhong()+1);
						break;
					case "报废":
						cd.setBaofei(cd.getBaofei()+1);
						break;
					}
					
					i++;
				}
				
				cd.setNum(i);
				ac.add(cd);
			}catch(SQLException se) {
				  se.printStackTrace();
			  } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return ac;
		
	}
	
	public boolean updatestatus(Assert a) {
		
		Demo Demo = new Demo();
		Connection conn = Demo.getConnection();
		String sql = "UPDATE assertinfo set status=? where assertNum=?";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1,a.getStatus());
			ptmt.setString(2,a.getAssertNum());
			ptmt.execute();
			return true;
		}catch(SQLException se) {
			  se.printStackTrace();
		  } catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public void deleteAssertByid(String s) {
		String sql = "delete from assertinfo where assertNum =" + s;
		Demo Demo = new Demo();
		Connection conn = Demo.getConnection();
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public boolean findassertbyAssertNum(String s) {
		
		String  sql1 = "select * from assertinfo where assertNum ="+s;
		Demo Demo = new Demo();
		Connection conn = Demo.getConnection();
        try {
        	PreparedStatement ptmt = conn.prepareStatement(sql1);
            ResultSet rs =ptmt.executeQuery();
            if(rs.next()) {
                System.out.print(rs.next());
                return true;
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
		return false;
		
	}
	
	
	public void approveAssert(Assert p) {
		Demo Demo = new Demo();
		Connection conn = Demo.getConnection();
		String sql = "INSERT INTO  requistioninfo(department,proposer,time,num,model,name,money,reason,approveOpinion)"+
				"values(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, p.getDepartment());
//			ptmt.setString(1, "教务处");
			ptmt.setString(2, p.getProposer());
			ptmt.setDate(3, new java.sql.Date(p.getApprovetime().getTime()));
			ptmt.setInt(4, p.getApprovenum());
			ptmt.setString(5, p.getModel());
			ptmt.setString(6, p.getName());
			ptmt.setFloat(7, p.getApprovemoney());
			ptmt.setString(8, p.getApprovereason());
			ptmt.setString(9, p.getApproveOpinion());
			
			ptmt.execute(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void fenpeiAssert(Assert p) {
		Demo Demo = new Demo();
		Connection conn = Demo.getConnection();
		String sql = "INSERT INTO  fenpeiinfo(assertid,usedepartment,fenpeibeizhu,fuzeren,fenpeitime,fenpeistatus)"+
				"values(?,?,?,?,?,?)";
		String sql1 = "UPDATE assertinfo set status=? where assertNum=?";
		
		
		
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, p.getAssertNum());
			ptmt.setString(2, p.getUsedepartment());
			ptmt.setString(3,p.getFenpeibeizhu() );
			ptmt.setString(4, p.getFuzeren());
			ptmt.setDate(5, new java.sql.Date(p.getFenpeitime().getTime()));
			ptmt.setString(6, p.getFenpeistatus());
			
			ptmt.execute(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql1);
			ptmt.setString(1, "待领用");
			ptmt.setString(2, p.getAssertNum());
			
			ptmt.execute(); 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void weixiuassert(Assert p) {
		Demo Demo = new Demo();
		Connection conn = Demo.getConnection();
		String sql = "INSERT INTO  assertmantaininfo(assertid,weixiuposition,weixiudays,weixiutime,weixiudepart,weixiumoney,weixiubeizhu)"+
				"values(?,?,?,?,?,?,?)";
		String sql1 = "UPDATE assertinfo set status=? where assertNum=?";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, p.getAssertNum());
			ptmt.setString(2, p.getWeixiuposition());
			ptmt.setInt(3,p.getWeixiudays() );
			ptmt.setDate(4, new java.sql.Date(p.getWeixiutime().getTime()));
			ptmt.setString(5, p.getWeixiudepart());
			
			ptmt.setFloat(6, p.getWeixiumoney());
			ptmt.setString(7, p.getWeixiubeizhu());
			ptmt.execute(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql1);
			ptmt.setString(1, "维修中");
			ptmt.setString(2, p.getAssertNum());
			
			ptmt.execute(); 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
