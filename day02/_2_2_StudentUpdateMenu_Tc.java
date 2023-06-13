package koreait.jdbc.day02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class _2_2_StudentUpdateMenu_Tc {
	public static void main(String[] args) {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";
		
		_2_2_StudentUpdateMenu_Tc upst = new _2_2_StudentUpdateMenu_Tc();
		try (Connection conn = DriverManager.getConnection(url,user,password)){
			upst.updateStudent(conn);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
	}//main end

	private static void updateStudent(Connection conn) {
		Scanner sc = new Scanner(System.in);
		String stuno, address, age;
		String sql = "UPDATE TBL_STUDENT\r\n"
				+ "SET AGE = ?, ADDRESS = ?\r\n"
				+ "WHERE STUNO = ?";
		System.out.println("학생번호 0000 입력은 수정 취소 입니다.");
		
		System.out.println("학번 입력");
		stuno = sc.nextLine();
		if(stuno.equals("0000")) {
			System.out.println("학생 정보 수정을 취소합니다.");
			return;	//리턴에 값이 없을때는 단순하게 ★★★메소드를 종료한다★★★
		}
		
		System.out.println("나이 입력");
		age = sc.nextLine();
		
		System.out.println("주소 입력");
		address = sc.nextLine();
		
		try (PreparedStatement ps = conn.prepareStatement(sql);){
			
			ps.setInt(1, Integer.parseInt(age));
			ps.setString(2, address);
			ps.setString(3, stuno);
			
			ps.execute();
			//pstm.execute();	// ◀ insert, update, delete, select 모두 실행
			int count = ps.executeUpdate();	// ◀ 리턴값은 반영된 행의 개수
			System.out.println("학생 정보 수정 "+count+"건이 완료되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("SQL Exception : "+e.getMessage());
		}
		
	}
	
}//main class end
