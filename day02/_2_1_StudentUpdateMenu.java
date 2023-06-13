package koreait.jdbc.day02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class _2_1_StudentUpdateMenu {
	public static void main(String[] args){
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";
		
		_2_1_StudentUpdateMenu upst = new _2_1_StudentUpdateMenu();
		
		try (Connection conn = DriverManager.getConnection(url,user,password)){
			upst.updateStudent(conn);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}//main end
	
	
	private static void updateStudent (Connection connection) throws Exception{
		Scanner sc = new Scanner(System.in);
		
		String stuno, name, address;
		int age = 0;
		
		String sql = "UPDATE TBL_STUDENT SET NAME = ?, AGE = ?, ADDRESS = ? WHERE STUNO = ?";
		
		System.out.println("학번 입력");
		stuno = sc.nextLine();
		
		System.out.println("이름 입력");
		name = sc.nextLine();
		
		System.out.println("나이 입력");
		String temp2 = sc.nextLine();
		age = Integer.parseInt(temp2);
		
		System.out.println("주소 입력");
		address = sc.nextLine();
		
		try (PreparedStatement pstm = connection.prepareStatement(sql);){
			
			pstm.setString(1, name);
			pstm.setInt(2, age);
			pstm.setString(3, address);
			pstm.setString(4, stuno);
			
			pstm.execute();
		} catch (SQLException e) {
			System.out.println("SQL Exception");
		}
		
	}//updateStudent
	
	
	
	
}//main class end
