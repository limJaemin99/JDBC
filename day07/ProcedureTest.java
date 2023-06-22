package koreait.jdbc.day07;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import koreait.jdbc.day02.OracleUtility;

public class ProcedureTest {
	public static void main(String[] args) {
		
		Connection conn = OracleUtility.getConnection();
		String sql = "{ call max_custom(? , ?) }";	//저장 프로시저 max_custom 호출 sql.
													//★★{ } 안에서 호출하기★★
		try (	//prepareCall 은 저장 프로시저 실행하기 위한 객체 생성 메소드
				CallableStatement cstmt = conn.prepareCall(sql);
		){	
			//IN 매개변수가 있을 경우 ▶ cstmt.setXXXX(); 메소드로 값을 입력한다.

			//아래 내용은 OUT 매개변수일 경우
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.registerOutParameter(2, Types.NUMERIC);
			cstmt.executeUpdate();	//실행
			
			System.out.println("[가장 많은 구매 수량으로 제품을 구입한 고객 정보]");
			System.out.println("고객 성명 : "+cstmt.getString(1));
			System.out.println("고객 나이 : "+cstmt.getInt(2));
			
		} catch (SQLException e) {
			System.out.println("오류 : "+e.getMessage());
		}
		
		
		
		
		
	}//main end
}//main class end
