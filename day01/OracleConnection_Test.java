package koreait.jdbc.day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 다른 DBMS 클라이언트 프로그램과 같이 DB를 사용할 수 있는 동작을 구현
// 이 소스는 제일 먼저 해야할 것 - '데이터베이스 연결' 입니다.

// try - catch - finally 사용
public class OracleConnection_Test {
	public static void main(String[] args) {
		//★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★//
		// 0. Connection 은 인터페이스로, 직접 객체를 생성하지 않고 구현 클래스가 필요하다.
			// 그러나 DB에서는 DB드라이버가 접속하려는 DB의 종류에 따라 알아서(★프록시(대행하다)) 구현 클래스와 구현 객체를 만든다.
			// 이것이 DB드라이버가 필요한 이유이다.
		Connection conn = null;
		//★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★//
		
		// 1. 아래 4개의 필수 연결 정보를 설정합니다.
		// 접속하고자 하는 서버의 주소 (포털 접속할 때 https://www.naver.com 과 비슷한 개념)
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		// oracle.jdbc.driver ▶ ojdbc6.jar 에 포함된 패키지명
		// OracleDriver ▶ 오라클 드라이버 클래스 이름
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		// 사용자 정보
		String user = "iclass";
		String password = "0419";
		
		try {
			// 2. 드라이버 클래스를 메모리에 로드(올리기)
				// ↑ 연결 객체를 생성해주는 역할
			Class.forName(driver);
			
			// 3. DriverManager 클래스는 연결 객체를 만듭니다. - 2번의 객체를 동작시킵니다.
				// 이때 2번에서 만든 객체, 즉 DBMS 에 따라 구현 객체가 만들어집니다.
			
				// 이렇게 만들어진 객체는 컴파일 상에만 존재하는것이므로 사용자는 확인할 수 없다.
				// 만약 확인하고 싶다면 java 의 log 메소드를 사용해야한다.
			conn = DriverManager.getConnection(url, user, password);
			
			
			// 4. 3번의 결과로 오라클 DB에 맞는 연결객체가 생성된다.
			System.out.println("연결 상태 = "+conn);
			if(conn != null) {
			System.out.println("오라클 데이터베이스 연결 성공!");
			// conn 객체의 구현 클래스 확인 ★★★
				// oracle.jdbc.driver.T4CConnection
			System.out.println("conn 객체의 구현 클래스 : "+conn.getClass().getName());
			} else System.out.println("오라클 데이터 베이스 연결 실패!");
			
		} catch (ClassNotFoundException e) {	//ClassNotFoundException, SQLException 처리 필요
			System.out.println("ClassNotFoundException = 드라이버 경로가 잘못됐습니다.");
			e.printStackTrace();	//Exception 발생의 모든 원인을 cascade 형식으로 출력
			
		} catch(SQLException e) {
			System.out.println("SQLException = url 또는 user 또는 password 가 잘못됐습니다.");
			e.printStackTrace();	//Exception 발생의 모든 원인을 cascade 형식으로 출력
			
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {
				
			}
		}//finally end
		
		
	/*
		API : Application Programming Interface - 인터페이스는 '소통'
		▶ 서로 다른 소프트웨어 시스템 간의 연결을 위한 방식(라이브러리로 제공됩니다)
		
		라이브러리 : 자바 라이브러리와 같이 특정 기능을 제공하는 클래스들의 집합 (확장자는 압축 형태 '.jar')
					
		JDBC : 자바와 DBMS를 연결하는 api / 오라클은 ojdbcX.jar (여기에서 X는 오라클 jdbc 버전 표시)
	*/
	
		
		
	}//main end
}//main class end
