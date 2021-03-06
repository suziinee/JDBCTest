package step01.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest1 {

	
	
	public static void select() {

		try {
			//driver 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//접속
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			
			//oracle 접속 객체
			Connection con = DriverManager.getConnection(url, "SCOTT", "TIGER");
			
			//접속된 oracle과만 소통하는 문장 실행 객체
			Statement stmt = con.createStatement();
			
			//sql 문장 실행
			ResultSet rset = stmt.executeQuery("select * from dept");
			
			//실행 결과 활용
			while(rset.next()) { //rset에 데이터가 있는지 확인
				System.out.println(rset.getInt("deptno") + "\t" + 
									rset.getString("dname") + "\t" + 
									rset.getString("loc"));
			}
			
			//자원 반환 : ResultSet -> Statement -> Connection 순으로 진행
			rset.close();
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void insert() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url 
			= "jdbc:mysql://mydb-1.ckejbqh7xa7q.ap-northeast-2.rds.amazonaws.com:3306/playdata?serverTimezone=UTC";
			
			Connection con = DriverManager.getConnection(url, "encore", "playdata");
			Statement stmt = con.createStatement();
			
			int rset = stmt.executeUpdate("insert into dept values(60, 'a', 'b')");
			System.out.println(rset + "행 저장 성공");
		
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
