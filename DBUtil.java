package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	static Properties dbInfo = new Properties();


	//최초 무조건 실행, 모두가 공유하는 자원을 한번만 초기화 하는 로직으로 개발
	static {
		
		try {
			dbInfo.load(new FileReader("src/dbinfo.properties"));
			Class.forName(dbInfo.getProperty("jdbc.driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//접속 위치 반환, 요청시마다 새로 생성해서 제공함 (공유 안됨)
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbInfo.getProperty("jdbc.url"),
											dbInfo.getProperty("jdbc.id"),
											dbInfo.getProperty("jdbc.pw"));
	}

	
	// 모든 DAO 클래스들의 모든 메소드가 반드시 실행되는 자원반환 로직 메소드 : finally 블록 개선
	/* select : ResultSet -> Statement -> Connection 순서 */
	public static void close(Connection con, Statement stmt, ResultSet rset) {
		
		try {
			if (rset != null) {
				rset.close();
				rset = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* insert, update, delete : Statement -> Connection 순서 */
	public static void close(Connection con, Statement stmt) {
		
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
	