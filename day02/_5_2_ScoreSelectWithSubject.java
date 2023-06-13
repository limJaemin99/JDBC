package koreait.jdbc.day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//과목명을 입력하면 해당 과목 조회하는 ScoreSelectWithSubject 클래스
public class _5_2_ScoreSelectWithSubject {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String subject;
		
		Connection conn = OracleUtility.getConnection();
		
		System.out.print("조회할 과목을 입력하세요 ▶ ");
		subject = sc.nextLine();
		
		System.out.println(subject+" 과목을 수강한 학생 목록");
		
		scoreOfSubject(conn,subject);
		
		//닫아주기
		OracleUtility.close(conn);
		sc.close();
	}//main end

	private static void scoreOfSubject(Connection conn, String subject) {
		
		String sql = "select a.name, b.subject, b.jumsu\r\n"
				+ "from tbl_student a left join TBL_score b on a.stuno = b.stuno\r\n"
				+ "where subject = ?";
		
		try (PreparedStatement ps = conn.prepareStatement(sql)){
			
			ps.setString(1, subject);
			ps.execute();
			
			ResultSet result = ps.executeQuery();
			
			System.out.println(String.format("%s %10s %10s","이름","과목","점수"));
			
			//조건절에 사용하는 컬럼이 기본키 또는 유니크일때는 0 또는 1개 행이 조회되고 ▶ rs.next() 를 if 와 같이 사용
			//     			    기본키와 유니크가 아닐때는 0~n 개 행이 조회됩니다.  ▶ rs.next() 를 while 에 사용
			while(result.next()) {
				System.out.println(String.format("%s %10s %10s", result.getString(1),result.getString(2),result.getString(3)));
			}
			
			System.out.println(String.format("[총 결과 수 : %d]",ps.executeUpdate()));
			
			// 국어 과목을 수강한 학번에 tbl_student에 존재하지 않아서 목록에는 1개만뜨고, 조회는 4개가 뜸
			selectCount(conn, subject);
		} catch (SQLException e) {
			System.out.println("오류 : "+e.getMessage());
		}
		
	}//scoreOfSubject end
	
	
	
	private static void selectCount(Connection conn, String subject) {
		String sql="select count(*) \r\n"
				+ "from TBL_SCORE \r\n"
				+ "where subject = ?";
		//count 와 같은 함수 결과는 행 1개 , 컬럼 1개 이므로
		//다른 조회문과 다르게 if 문을 사용하지 않아도 된다. rs.next() 만 단독으로 사용 가능
		
		try(
				PreparedStatement ps = conn.prepareStatement(sql);
			){
				ps.setString(1, subject);	
				
				ResultSet rs =ps.executeQuery();
				int count=0;
				if(rs.next()) {
					count = rs.getInt(1);
				}
				//참고 :  입력한 과목의 건(행) 수를 조회할수 있습니다.
				System.out.println("과목 << " + subject + " >> " + count + " 건이 조회 되었습니다." );
			}
			catch (SQLException e) {
				System.out.println("데이터 조회에 문제가 생겼습니다. 상세내용 -" + e.getMessage());
				//결과 집합을 모두 소모했음 -> 조회 결과가 없는데 rs.getXXXX 메소드 실행할 때의 예외 메시지.
			}
		
	}//selectCount end
	
	
}//main class end
