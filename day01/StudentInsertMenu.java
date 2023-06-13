package koreait.jdbc.day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class StudentInsertMenu {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// 입력용 변수
		int stuno = 0;			//학번
		String name = "";		//이름
		int age = 0;			//나이
		String address = "";	//주소
		int choice = 0;			//메뉴선택
		
		// 드라이버 연결용 변수
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";
		
		try (
			Connection conn = DriverManager.getConnection(url,user,password);
			){

		
		
		while(true) {
		System.out.println("━".repeat(10)+" 학생 정보 관리 시스템 입니다 "+"━".repeat(10));
		System.out.println("1.학생 등록  | 2.학생 정보 열람  |  3.종료");
		choice = Integer.parseInt(sc.nextLine());
		
		if(choice == 1) {
		System.out.println("=".repeat(10)+" 학생 등록 메뉴 입니다 "+"=".repeat(10));
		while(true) {
			String sql = "insert into TBL_STUDENT values (?,?,?,?)";
			PreparedStatement ppst = conn.prepareStatement(sql);
			
		System.out.println("[학생 번호 입력시 0000 입력은 종료입니다]");
		
		// 입력용 객체에 입력받기
		System.out.print("학번을 입력하세요 (7자리) : ");
		stuno = sc.nextInt();	sc.nextLine();
		if(stuno == 0000) break;	// 0000 입력시 종료
		
		System.out.print("이름을 입력하세요 : ");
		name = sc.nextLine();
		
		System.out.print("나이를 입력하세요 : ");
		age = sc.nextInt();		sc.nextLine();
		
		System.out.print("주소를 입력하세요 : ");
		address = sc.nextLine();
		
		// insert 하기
		ppst.setInt(1, stuno);
		ppst.setString(2, name);
		ppst.setInt(3, age);
		ppst.setString(4, address);
		
		ppst.execute();
		
		
		System.out.println(name+" 학생의 정보가 등록 되었습니다.");
		
		
			}//while end
		}//if choice 1 end
		if(choice ==2) {
			System.out.println("=".repeat(10)+" 학생 정보 열람 메뉴 입니다 "+"=".repeat(10));
			
			String sql2 = "select * from tbl_student";
			
			Statement st = conn.createStatement();
			
			ResultSet res = st.executeQuery(sql2);
			
			while(res.next()) {
				System.out.print("학번 : "+res.getInt(1)+"\t");
				System.out.print("이름 : "+res.getString(2)+"\t");
				System.out.print("나이 : "+res.getInt(3)+"\t");
				System.out.print("주소 : "+res.getString(4)+"\t");
				System.out.println();
			}
			
		}//if choice 2 end

		if(choice == 3) break;
		
		
		}//while 메뉴 end
		System.out.println("━".repeat(10)+" 학생 등록 메뉴를 종료합니다"+"━".repeat(10));
		sc.close();
		} catch(Exception e) {
			System.err.println("오류 : "+e.getMessage());
		}
		
		
		
		
	}//main end
}//main class end
