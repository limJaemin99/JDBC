package koreait.jdbc.day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Todo_Practice.TodoDTO;

//SELECT 부터는 OracleUtility 클래스를 통해 Connection 을 만들어 주도록 하겠습니다.
public class _4_1_StudentSelectOneMenu {
	public static void main(String[] args) {
		
		//OracleUtility 클래스 사용
		Connection conn = OracleUtility.getConnection();
		
		System.out.println("━━━━━ 학생을 학번으로 조회하는 메뉴 ━━━━━");
		
		selectOneStudent(conn);
		
		
		//OracleUtility 클래스 사용
		OracleUtility.close(conn);
		
	}//main end

	private static void selectOneStudent(Connection conn) {
		Scanner sc = new Scanner(System.in);
		
		String stuno;
		String sql = "SELECT * FROM TBL_STUDENT WHERE STUNO = ?";
		
		System.out.println("조회할 학생의 학번을 입력하세요.");
		stuno = sc.nextLine();
		
		try (PreparedStatement ps = conn.prepareStatement(sql)){
			//sql 실행하고 select 는 조회 데이터를 결과로 받아 자바 변수에 저장해야 합니다.
			ps.setString(1, stuno);
			
			// SQL 실행 및 결과 처리
	        ResultSet result = ps.executeQuery();
	        System.out.println("result 객체의 구현 클래스 : "+result.getClass().getName());
	        	//▶ oracle.jdbc.driver.OracleResultSetImpl 클래스 객체로 만들어집니다.
	        
	        //result.next() : 데이터를 가져올 커서(위치)를 다음 행으로 이동합니다.
	        //조회 결과 유무를 알려면 '제일 먼저 실행해야할 메소드 - 조회 결과 첫번째 행으로 이동' 입니다.
	        System.out.println("조회 결과가 있을까요? ▶ "+result.next());
	        //조회된 result 에서 특정 컬럼값을 가져오기 할 때,
	        	//컬럼의 데이터 타입을 확인하고 getXXXX 메소드 정하기.
	        //getXXXX 메소드의 인자는 컬럼의 인덱스 또는 컬럼 이름입니다.
	        System.out.println("조회 결과 첫번째 컬럼의 값 : "+result.getInt(1));
	        System.out.println("조회 결과 두번째 컬럼의 값 : "+result.getString(2));
	        System.out.println("조회 결과 세번째 컬럼의 값 : "+result.getInt(3));
	        System.out.println("조회 결과 네번째 컬럼의 값 : "+result.getString(4));
	        
	        System.out.println("다음 조회 결과 행이 또 있을까요? ▶ "+result.next());
	        
	        // 아래 내용은 혼자 해본거 //
	        
//			// 결과를 담을 객체 생성
//	        StringBuilder rst = new StringBuilder();
//			while(result.next()) {
//			
//			rst.append(result.getString(1)+" ".repeat(5))
//			.append(result.getString(2)+" ".repeat(5))
//			.append(result.getString(3)+" ".repeat(5))
//			.append(result.getString(4));
//			
//			System.out.println(rst);
//			}
			
			
		} catch (SQLException e) {
			//결과 집합을 모두 소모했음 ▶ 조회 결과가 없는데 result.getXXXX 메소드 실행할때의 예외 메세지
			System.out.println("데이터 조회에 오류가 발생했습니다 : "+e.getMessage());
		}
	}//selectOneStudent end
	
	
   
	
	
}//main class end
