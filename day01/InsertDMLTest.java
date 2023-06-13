package koreait.jdbc.day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

// 학생 성적처리 프로그램중 새로운 학생을 등록(입력) 하는 기능을 만들어봅시다. (테이블에 insert 실행)
public class InsertDMLTest {
	public static void main(String[] args) {


		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "iclass";
		String password = "0419";
		
		try (
			Connection conn = DriverManager.getConnection(url, user, password);
			){
			
			System.out.println("연결 상태 = "+conn);
			if(conn != null) System.out.println("오라클 데이터베이스 연결 성공!");
			
		// DB 연결 완료 후에 sql 실행하기
			
			
			// insert SQL 작성 : 제약조건 위반이 되지 않는 값으로 입력하기.
			String sql = "insert into TBL_STUDENT values ('2023001','김땡땡',16,'경기도')";
			// PreparedStatement 객체를 생성하면서 실행할 SQL 을 설정합니다.
			// PreparedStatement 객체는 Connection 객체 메소드로 만듭니다.
			// Connection 구현객체는 DBMS 종류에 따라 생성되고,
				// PreparedStatement 객체도 그에 따라 구현 객체가 결정됩니다.
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// PreparedStatement 의 구현 클래스 확인 ★★★
				// oracle.jdbc.driver.OraclePreparedStatementWrapper
			System.out.println("PreparedStatement 객체의 구현 클래스 : "+pstmt.getClass().getName());
			
			pstmt.execute();
			pstmt.close();
			
		} catch(SQLException e) {
			System.err.print("오류 메세지 : "+e.getMessage());
		} 
		
	/*
		Statement 인터페이스는 sql 쿼리 처리와 관련된 방법을 정의합니다.
		객체는 SQL 쿼리문을 데이터베이스에 전송합니다. Connection 객체를 통해서 만듭니다.
		
		preparedStatement : Statement 의 자식 인터페이스
		▶ 특징은 sql을 먼저 컴파일하고 sql 실행에 필요한 값은 실행할 때 매개변수로 전달하는 방식입니다.
	*/
		
	
		
		
	}//main end
}//main class end
