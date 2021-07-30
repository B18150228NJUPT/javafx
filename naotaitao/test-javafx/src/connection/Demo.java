package connection;

import java.sql.*;  
public class Demo{  
  static final String JDBC_DRIVER ="com.mysql.cj.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://cdb-qtpj3kjq.cd.tencentcdb.com:10200/zichan";
  static final String USER = "javafx";
  static final String PASS = "1999105123y";
  private  Connection conn = null;
  
  public  Connection getConnection() {

      try {
          Class.forName("com.mysql.jdbc.Driver");
          conn = DriverManager.getConnection(DB_URL, USER, PASS);
      }catch(SQLException se) {
          se.printStackTrace();
      } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
      return conn;
  }
  
}  