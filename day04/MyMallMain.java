package koreait.jdbc.day04;

import java.sql.SQLException;
import java.util.ArrayList;
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
		// 2. 상품 목록 보기
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
		// 3. 상품명으로 검색하기 (유사검색 ▶ 검색어가 포함된 상품명을 조회하기)
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
		// 1. 회원 로그인 - 간단히 회원아이디를 입력해서 존재하면 로그인 성공
		System.out.println("\n<< 회원 로그인 >>");
		System.out.print("아이디 입력 : ");
		String customer_ID = sc.nextLine();
		JCustomerDAO cDao = new JCustomerDAO();
		JCustomerDTO loginCS = null;
		try {
			if(cDao.selectByID(customer_ID) != null) {
				loginCS = cDao.selectByID(customer_ID);
				System.out.println(loginCS);
			}
		} catch (SQLException e) {
			System.out.println("로그인 예외 : "+e.getMessage());
		}
		
		///////////////////////////////////////////////////////////
		// 4. 상품 장바구니 담기 - 장바구니 테이블이 없으므로 구매를 원하는 상품을 List 에 담기
		System.out.println("\n<< 장바구니 >>");
		List<JProductDTO> cart = new ArrayList<>();
		while(true) {
		System.out.println("장바구니에 담을 상품의 이름을 입력하세요.");
		System.out.println("※ '종료' 입력시 장바구니 메뉴를 종료합니다.");
		pName = sc.nextLine();
		if(pName.equals("종료")) {
			System.out.println("장바구니 메뉴를 종료합니다.");
			break;
		}
        
		try {
			JProductDTO pDto = pDao.selectOne(pName);
			cart.add(pDto);
			System.out.println("["+pName+"] 상품을 장바구니에 담았습니다.");
		} catch (SQLException e) {
			System.out.println("상품 검색 예외 : "+e.getMessage());
		}
		
		}//while end
		if(cart.size()>0) {
			System.out.println("장바구니 목록은 다음과 같습니다.");
			for (JProductDTO list : cart) {
				System.out.println(list);
			}
		}
		
		///////////////////////////////////////////////////////////
		// 5. 상품 구매(결제)하기 - 장바구니의 데이터를 '구매' 테이블에 입력하기 (여러개 데이터를 INSERT)
		System.out.println("<< 장바구니 상품 구매하기 >>");
		System.out.println("장바구니에 담긴 상품 중, 구매할 상품의 이름을 입력하세요.");
		System.out.println("※ '전체' 입력시 전체 상품들을 구매합니다.");
		System.out.println("※ '종료' 입력시 선택한 상품들을 구매합니다.");
		List<JProductDTO> buyCart = new ArrayList<>();
		JBuyDAO jDAO = new JBuyDAO();
		try {
			for (int i = 1; i <= cart.size(); i++) {
				System.out.print(i+". ");
				pName = sc.nextLine();
				if(pName.equals("전체")) {
					System.out.println("전체 상품 "+cart.size()+"개를 구매합니다.");
					for (JProductDTO bc : cart) {
						buyCart.add(bc);
					}
					// 이부분에 buyDAO 입력
//					jDAO.buyProduct(loginCS, buyCart); //메소드 수정으로 오류 발생하여 주석처리함
					break;
				}
				if(pName.equals("종료")) {
					System.out.println("선택한 "+buyCart.size()+"개의 상품을 구매합니다.");
					// 이부분에 buyDAO 입력
					
					break;
				}
				if(i==1&&pName.equals("종료")) {
					System.out.println("장바구니 상품 구매를 취소합니다.");
					break;
				}
				JProductDTO pDto;
				pDto = pDao.selectOne(pName);
				buyCart.add(pDto);
				if(i==cart.size()) {
					System.out.println("선택한 "+i+"개의 상품을 구매합니다.");
					// 이부분에 buyDAO 입력
				}
			}// for end
		} catch (SQLException e) {
			System.out.println("예외 : "+e.getMessage());
			}
		
		
		
		sc.close();
	}//main end
}//main class end
