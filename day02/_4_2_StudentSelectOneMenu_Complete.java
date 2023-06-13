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
public class _4_2_StudentSelectOneMenu_Complete {
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
			ps.setString(1, stuno);
			
	        ResultSet result = ps.executeQuery();
	        System.out.println("result 객체의 구현 클래스 : "+result.getClass().getName());
	        
	        if(result.next()) {	// ★ 주의 : 테이블 컬럼의 구조를 알아야 인덱스를 정할 수 있다.
	        System.out.println("학번 : "+result.getInt(1));
//	        System.out.println("학번 : "+result.getInt("STUNO"));	 //인덱스 대신 컬럼명으로 함       
	        System.out.println("이름 : "+result.getString(2));			// "" 주의★
//	        System.out.println("이름 : "+result.getString("NAME"));
	        System.out.println("나이 : "+result.getInt(3));
//	        System.out.println("나이 : "+result.getInt("AGE"));
	        System.out.println("주소 : "+result.getString(4));
//	        System.out.println("주소 : "+result.getString("ADDRESS"));
	        } else System.out.println("! 조회된 결과가 없습니다 !");
	        
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
			System.out.println("데이터 조회에 오류가 발생했습니다 : "+e.getMessage());
		}
	}//selectOneStudent end
	
   
	
	
}//main class end
