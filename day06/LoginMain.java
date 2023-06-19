package koreait.jdbc.day06;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Scanner;

import com.google.common.hash.Hashing;

import koreait.jdbc.day04.JCustomerDTO;


//LoginMain에서 사용자에게 아이디 >>> , 패스워드 >>>
	//'로그인 성공했습니다.' 또는 '없는 계정이거나 비밀번호가 틀립니다.' 출력
public class LoginMain {
	public static final String RESET = "\u001B[0m";    
	public static final String FONT_RED = "\u001B[31m";    
	public static final String FONT_GREEN = "\u001B[32m";   
	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		
		String id;
		String password;
		int count = 0;
		JCustomerDAO2 dao = JCustomerDAO2.getCustomerDAO2();
		JCustomerDTO dto = null;
		boolean isLogin = true;
		
		while(isLogin) {
		System.out.print("아이디를 입력하세요 : ");
		id = sc.nextLine();
		
		System.out.print("비밀번호를 입력하세요 : ");
		password = Hashing.sha256()
				.hashString(sc.nextLine(), StandardCharsets.UTF_8)
				.toString();
		
		dto = dao.login(id, password);
		if(dto == null) {
			++count;
			System.out.println(FONT_RED+count+"회 틀렸습니다."+RESET);
			if(count == 5) {
				System.out.println(FONT_RED+"로그인 오류 5회 누적으로 프로그램을 종료합니다."+RESET);
				System.exit(1);
			}
			System.out.println("없는 계정이거나 비밀번호가 틀립니다.");
			System.out.println();
		} else {
			System.out.println(FONT_GREEN+dto.getName()+" 님 환영합니다!"+RESET);
			isLogin = false;
		}
		}//while end
		
		sc.close();
		
	}//main end
}//main class end
