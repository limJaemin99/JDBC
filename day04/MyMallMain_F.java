package koreait.jdbc.day04;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyMallMain_F {
	public static final String RESET = "\u001B[0m";
	public static final String FONT_RED = "\u001B[31m";     
	public static final String FONT_YELLOW = "\u001B[33m";     
	public static final String FONT_GREEN = "\u001B[32m";    
	public static final String FONT_BLUE = "\u001B[34m";   
	public static final String FONT_PURPLE = "\u001B[35m";
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("::::: 환영합니다 :::::");
		
		///////////////////////////////////////////////////////////
		// 2. 상품 목록 보기
		System.out.println();
		System.out.println(FONT_GREEN+"━".repeat(20)+" [상품 소개] "+"━".repeat(20)+RESET);
		
		JProductDAO pDao = new JProductDAO();
		
		try {
		List<JProductDTO> pDto = pDao.selectAll();
			for (JProductDTO product : pDto) {
				System.out.println(String.format("▶ 상품명 : %-10s\t"+FONT_GREEN+"┃"+RESET+"\t가격 : %-10s원",
						product.getPName(), product.getPrice()));
			}
			System.out.println(FONT_GREEN+"━".repeat(51)+RESET);
		} catch (SQLException e) {
		System.out.println("상품 소개 예외 : "+e.getMessage());
		}
		
		///////////////////////////////////////////////////////////
		// 3. 상품명으로 검색하기 (유사검색 ▶ 검색어가 포함된 상품명을 조회하기)
		System.out.println();
		System.out.println(FONT_BLUE+"━".repeat(18)+" [상품 코드 검색] "+"━".repeat(18)+RESET);
		System.out.println(FONT_RED+"⚠ '종료' 입력시 상품 코드 검색 메뉴를 종료합니다 ⚠"+RESET);
		
		String pName;
		
		while(true) {
			System.out.print("\n검색어 입력 : ");
			pName = sc.nextLine();
			
			if(pName.equals("종료")) {
				System.out.println("[상품 코드 검색 메뉴를 종료합니다]");
				System.out.println(FONT_BLUE+"━".repeat(51)+RESET);
				break;
			}
			
			try {
				List<JProductDTO> pDto = pDao.selectByPname(pName);
				for (JProductDTO product : pDto) {
					System.out.println(String.format("▶ 상품명 : %-10s \t상품 코드 : %-10s",
							product.getPName(),product.getPCode()));
				}
				if(pDto.size() == 0) System.out.println("※ 입력하신 상품과 관련된 상품이 없습니다 ※");
			} catch (SQLException e) {
				System.out.println("상품 검색 예외 : "+e.getMessage());
			}
		}//while end
		
		///////////////////////////////////////////////////////////
		// 1. 회원 로그인 - 간단히 회원아이디를 입력해서 존재하면 로그인 성공
		System.out.println();
		System.out.println(FONT_YELLOW+"━".repeat(19)+" [간편 로그인] "+"━".repeat(19)+RESET);
		System.out.println(FONT_RED+"⚠ '0000' 입력시 간편 로그인 메뉴를 종료합니다 ⚠"+RESET);
		
		String customer_ID = null;
		boolean isLogin = false;
		JCustomerDAO cDao = new JCustomerDAO();
		JCustomerDTO cDto = new JCustomerDTO();
		
		while(!isLogin) {
			System.out.print("ID 입력 : ");
			customer_ID = sc.nextLine();
			
			if(customer_ID.equals("0000")) {
				System.out.println("[간편 로그인 메뉴를 종료합니다]");
				System.out.println(FONT_YELLOW+"━".repeat(51)+RESET);
				break;
			}
			try {
				cDto = cDao.selectByID(customer_ID);
				if(cDto != null) {
					isLogin=true;
					System.out.println("★ "+cDto.getName() + " 고객님 환영합니다.");
					System.out.println(FONT_YELLOW+"━".repeat(51)+RESET);
				}
			} catch (SQLException e) {
				System.out.println("로그인 예외 : "+e.getMessage());
			}
		}//while end

		///////////////////////////////////////////////////////////
		// 4. 상품 장바구니 담기 - 장바구니 테이블이 없으므로 구매를 원하는 상품을 List 에 담기
		System.out.println();
		System.out.println(FONT_PURPLE+"━".repeat(21)+" [장바구니] "+"━".repeat(21)+RESET);
		
		String pCode;
		int quantity;
		JBuyDAO bDao = new JBuyDAO();
		List<JBuyDTO> cart = new ArrayList<>();
		
		while(true) {
			System.out.println("장바구니에 담을 상품의 '상품 코드'를 입력하세요.");
			System.out.println(FONT_RED+"⚠ '종료' 입력시 장바구니 메뉴를 종료합니다 ⚠"+RESET);
			
			pCode = sc.nextLine();
			
			if(pCode.equals("종료")) {
				System.out.println("장바구니 메뉴를 종료합니다.");
				System.out.println(FONT_PURPLE+"━".repeat(51)+RESET);
				break;
			}
			
			try {
				System.out.println("["+pDao.pcodeToName(pCode)+"] 상품을 몇개 담으시겠습니까?");
				quantity = Integer.parseInt(sc.nextLine());
				
				cart.add(new JBuyDTO(0, customer_ID, pCode, quantity, null));
				System.out.println("※ 장바구니 담기 완료 ※");
				System.out.println(FONT_PURPLE+"-".repeat(51)+RESET);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}//while end
		
		
		
		//장바구니 확인
		if(cart.size()>0) {
			System.out.println("▶▶▶ 장바구니 목록은 다음과 같습니다 ◀◀◀");
			System.out.println("-".repeat(51));
			for (JBuyDTO list : cart) {
				try {
					System.out.println(String.format("상품코드 : %-10s \t수량 : %-5d",
							pDao.pcodeToName(list.getPCode()),list.getQuantity()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}//for-each end
			System.out.println("-".repeat(51));
		}//if end
		
		//buy 테이블에 입력(insert)
			//buyDAO 에 메소드 생성
		System.out.println("▶▶▶ 장바구니에 담긴 상품을 결제하시겠습니까? (Y/N) ◀◀◀");
		if(sc.nextLine().toLowerCase().equals("y")) {
		
		try {
			bDao.credit(cart);
			System.out.println("구매를 완료했습니다.");
			System.out.println("\n현재까지 "+customer_ID+"님의 구매내역 입니다.");
			System.out.println("━".repeat(300));
			//구매내역 select id 출력 메소드
//			List<JBuyDTO> list =bDao.history(customer_ID);
//			for (JBuyDTO rst : list) {
//				System.out.println(String.format("상품코드 : %-10s \t수량 : %-5d \t구매일자 : %s",
//						pDao.pcodeToName(rst.getPCode()),rst.getQuantity(),rst.getBuy_date()));
//			}
			int count = bDao.history(customer_ID);
			System.out.println("━".repeat(150));
			System.out.println("★ 현재까지 총 "+count+"개의 구매내역이 있습니다 ★");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}//상품 결제 if문
		
		else {
			System.out.println("결제를 취소합니다. 프로그램 종료합니다.");
		}

			

		sc.close();
		
		
		
	}//main end
}//main class end
