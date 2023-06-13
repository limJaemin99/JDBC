package koreait.jdbc.day02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class _3_StudentDeleteMenu {
	public static void main(String[] args) {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";
		
		try (Connection conn = DriverManager.getConnection(url,user,password)){
			_3_StudentDeleteMenu.deleteStudent(conn);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}//main end
	
	private static void deleteStudent(Connection conn) {
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("삭제할 학생의 학번을 입력하세요.");
		String stuno = sc.nextLine();
		
		String sql = "delete from TBL_STUDENT where stuno = ?";
		
		try (PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, stuno);
			ps.execute();
			sc.close();
			System.out.println("삭제가 완료되었습니다.");
		} catch (SQLException e) {
			System.out.println("학번을 잘못 입력하셨습니다.");
		}
		
		
	}//deleteStudent end
	

	
}//main class end
