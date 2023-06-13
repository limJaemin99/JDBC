package koreait.jdbc.day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//모든 학생 조회하는 StudentSelectAllMenu 클래스 : 1줄에 1개행을 출력하세요.
public class _5_1_StudentSelectAllMenu {
	public static void main(String[] args) {
		
		Connection conn = OracleUtility.getConnection();
		
		System.out.println("━━━ 모든 학생을 조회합니다.");
		
		selectAllStudent(conn);
		
		//닫아주기
		OracleUtility.close(conn);
	}//main end

	private static void selectAllStudent(Connection conn) {
		
		String sql = "select * from tbl_student";
		
		try (PreparedStatement ps = conn.prepareStatement(sql);){
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.print(rs.getInt(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getInt(3)+"\t");
				System.out.print(rs.getString(4));
				System.out.println();
			}
			
			System.out.println("학생 조회 완료");
			
		} catch (SQLException e) {
			System.out.println("오류 : "+e.getMessage());
		}
		
	}//selectAllStudent end
	
	
}//main class end
