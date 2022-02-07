package step02.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.DBUtil;

public class DeptDAO {
	
	

	public static void main(String [] args) {
		select();
	}
	
	public static void select() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			rset = stmt.executeQuery("select * from dept");
			
			while(rset.next()) { 
				System.out.println(rset.getInt("deptno") + "\t" + 
									rset.getString("dname") + "\t" + 
									rset.getString("loc"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, stmt, rset);
		}
	}


	public static void insert() throws Exception {
		Connection con = null;
		Statement stmt = null;
		
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			int rset = stmt.executeUpdate("insert into dept values(60, 'a', 'b')");
			System.out.println(rset + "행 저장 성공");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtil.close(con, stmt);
		}
	}

	

}
