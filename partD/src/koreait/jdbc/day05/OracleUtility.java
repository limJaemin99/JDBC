package koreait.jdbc.day05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleUtility {	//Connection 생성하여 제공(return)해주고, 리소스 해제 메소드를 작성
	
	public static Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
//		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "iclass";
		String password = "0419";
		
		try {
//			Class.forname(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 및 사용에 문제가 생겼습니다 : "+e.getMessage());
		}
		
		return conn;	// 생성된 Connection 리턴
	}// getConnection end
	
	public static void close(Connection conn) {
		try {
			if(conn != null) {
				conn.close();	// conn 이 null 객체라면 close 에서 오류 ▶ 조건식 추가
				System.out.println("! 연결 종료 !");
			} else System.out.println("Connection 이 null 입니다.");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 종료 오류 : "+e.getMessage());
		}
	}//close end
	
}//main class end
