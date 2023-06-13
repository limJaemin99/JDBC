package koreait.jdbc.day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// try - with - resources 사용
public class OracleConnection_WithResources {
	public static void main(String[] args) {

//		Connection conn = null;

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "iclass";
		String password = "0419";
		
		//try - with - resources 형식 : try 에 자원 객체 선언하기
		try ( // 자원 해제 close 가 필요한 객체 생성과 변수 선언하기
			Connection conn = DriverManager.getConnection(url, user, password);
			  // 2개 이상의 구문을 작성할 수 있다. (구분은 ;)
			
			){
			// 현재 버전에서는 DriverManager 실행시키므로 Class.forName(driver); 가 없어도 실행 가능 ★
			Class.forName(driver);
			System.out.println("연결 상태 = "+conn);
			if(conn != null) System.out.println("오라클 데이터베이스 연결 성공!");
			else System.out.println("오라클 데이터 베이스 연결 실패!");
			
			// SQL 문법 작성
			String queryJoin = "select a.*, b.subject, b.jumsu, b.teacher, b.term from TBL_STUDENT a join tbl_score b on a.stuno=b.stuno";
					
			// 데이터베이스에 전송할 문서 객체 생성
			Statement st = conn.createStatement();
			
			// 선택된 결과를 받는다
			ResultSet result = st.executeQuery(queryJoin);
			
			// 행 숫자 세기용
			int count=0;
			
			while(result.next()) {
				System.out.print(++count+" ┃  ");
				System.out.print("학번 : "+result.getString("stuno")+"  ┃  ");
				if(result.getString("name").contains("O"))  System.out.print("이름 : "+result.getString("name")+"   ┃  ");
				else System.out.print("이름 : "+result.getString("name")+"  ┃  ");
				System.out.print("나이 : "+result.getString("age")+"  ┃  ");
				System.out.print("주소 : "+result.getString("address")+"  ┃  ");
				System.out.print("과목 : "+result.getString("subject")+"  ┃  ");
				System.out.print("점수 : "+result.getString("jumsu")+"  ┃  ");
				System.out.print("선생님 : "+result.getString("teacher")+"  ┃  ");
				System.out.print("학기 : "+result.getString("term")+"  ┃  ");
				System.out.println();
			}
			
			
		} 
		catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException = 드라이버 경로가 잘못됐습니다.");
			e.printStackTrace();
		}
		catch(SQLException e) {
			System.out.println("SQLException = url 또는 user 또는 password 가 잘못됐습니다.");
			e.printStackTrace();
		} 
		
		
	
		
		
	
		
		
	}//main end
}//main class end
