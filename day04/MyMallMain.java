package koreait.jdbc.day04;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MyMallMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/*
		1. 회원 로그인 - 간단히 회원아이디를 입력해서 존재하면 로그인 성공
		2. 상품 목록 보기
		3. 상품명으로 검색하기 (유사검색 ▶ 검색어가 포함된 상품명을 조회하기)
		4. 상품 장바구니 담기 - 장바구니 테이블이 없으므로 구매를 원하는 상품을 List 에 담기
		5. 상품 구매(결제)하기 - 장바구니의 데이터를 '구매' 테이블에 입력하기 (여러개 데이터를 INSERT)
		6. 나의 구매 내역 보기. 총 구매 금액을 출력해주기
		 */
		
		System.out.println("::::: 환영합니다 :::::");
		///////////////////////////////////////////////////////////
		System.out.println("<< 상품 소개 >>");
		JProductDAO pDao = new JProductDAO();
		try {
			List<JProductDTO> pDto = pDao.selectAll();
			for (JProductDTO product : pDto) {
				System.out.println(product);
			}
		} catch (SQLException e) {
			System.out.println("상품 소개 예외 : "+e.getMessage());
		}
		
		///////////////////////////////////////////////////////////
		System.out.println("\n<< 상품 검색 >>");
		System.out.print("검색어 입력 : ");
		String pName = sc.nextLine();
		try {
			List<JProductDTO> pDto = pDao.selectByPname(pName);
			for (JProductDTO product : pDto) {
				System.out.println(product);
			}
		} catch (SQLException e) {
			System.out.println("상품 검색 예외 : "+e.getMessage());
		}
		
		///////////////////////////////////////////////////////////
		System.out.println("\n<< 회원 로그인 >>");
		System.out.print("아이디 입력 : ");
		String customer_ID = sc.nextLine();
		JCustomerDAO cDao = new JCustomerDAO();
		try {
			if(cDao.selectByID(customer_ID) != null)
				System.out.println(cDao.selectByID(customer_ID));
		} catch (SQLException e) {
			System.out.println("로그인 예외 : "+e.getMessage());
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		sc.close();
	}//main end
}//main class end
