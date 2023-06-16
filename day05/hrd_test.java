package koreait.jdbc.day05;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class hrd_test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		boolean isRun = true;
		boolean isEnd = true;
		int menuSel1 = 0;
		int menuSel2 = 0;
		String choice;
		
		System.out.println("                   [쇼핑몰 회원 관리 프로그램]");
		
		while(isRun) {
		System.out.println("★★★★★★★★★★★★★★ 원하시는 메뉴를 선택해주세요 ★★★★★★★★★★★★★★");
		System.out.println("1.회원등록 ┃ 2.회원목록조회 ┃ 3.회원정보수정 ┃ 4.회원매출조회 ┃ 5.종료");
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		menuSel1 = Integer.parseInt(sc.nextLine());
		
		//MemberDTO 에 사용할 변수는 while문이 반복될때마다 초기화
		int custno = 0;
		String custname = null;
		String phone = null;
		String address = null;
		String grade = null;
		String city = null;
		
		MemberDAO mDao = new MemberDAO();
		MemberDTO mDto = new MemberDTO();
		Check_SalesDAO cDao = new Check_SalesDAO();
		
		switch(menuSel1) {
		case 1 :
			System.out.println("[회원 등록 메뉴입니다]");
			
			
			System.out.println("등록할 회원의 정보를 입력해주세요.");
			System.out.println("-".repeat(30));
			System.out.print("회원성명 : ");		custname = sc.nextLine();
			System.out.print("회원전화 : ");	phone = sc.nextLine();
			System.out.print("회원주소 : ");	address = sc.nextLine();
			System.out.print("고객등급[A:VIP B:일반 C:직원] : ");		grade = sc.nextLine();
			System.out.print("도시코드 : ");	city = sc.nextLine();
			
			mDto = new MemberDTO(0,custname,phone,address,null,grade,city);
			
			mDao.joinMember(mDto);
			System.out.println("-".repeat(30));
			break;
			
		case 2 :
			System.out.println("[회원 목록 조회 메뉴입니다]");
			System.out.println("━".repeat(100));	//수정하기
			
			try {
			List<MemberDTO> list = mDao.selectAll();
			System.out.println("회원번호┃회원성명┃전화번호┃주소┃가입일자┃고객등급┃거주지역");
				for (MemberDTO selMem : list) {					//확인용 탭 추가	//나중에 Tostring 정의해서 출력할 예정
					System.out.print(selMem.getCustno());		System.out.print("\t");
					System.out.print(selMem.getCustname());		System.out.print("\t");
					System.out.print(selMem.getPhone());		System.out.print("\t");
					System.out.print(selMem.getAddress());		System.out.print("\t");
					System.out.print(selMem.getJoindate());		System.out.print("\t");
					System.out.print(selMem.getGrade());		System.out.print("\t");
					System.out.print(selMem.getCity());
					System.out.println();
				}
			} catch (SQLException e) {
				System.out.println("예외 : "+e.getMessage());
			}
			
			System.out.println("━".repeat(100));	//수정하기
			break;
			
		case 3 :
			System.out.println("[회원 정보 수정 메뉴입니다]");
			
			System.out.println("수정할 회원 번호를 입력하세요.");
			custno = Integer.parseInt(sc.nextLine());
			System.out.println("-".repeat(30));
			
			while(isEnd) {
			System.out.println("수정하실 내용을 골라주세요.");
			System.out.println("1.회원이름 ┃ 2.회원전화 ┃ 3.회원주소 ┃ 4.고객등급 ┃ 5.도시코드 ┃ 6.전체내용 ┃ 7.종료");
			menuSel2 = Integer.parseInt(sc.nextLine());
			
				switch(menuSel2) {
				case 1 :
					System.out.print("수정할 이름을 입력해주세요 : ");
					custname = sc.nextLine();
					System.out.print("▶ 다른 내용을 추가로 수정하시겠습니까? [Y/N]");
					choice = sc.nextLine();
					if(choice.toUpperCase().equals("N")) isEnd = false;
					break;
					
				case 2 :
					System.out.print("수정할 전화번호를 입력해주세요 : ");
					phone = sc.nextLine();
					System.out.print("▶ 다른 내용을 추가로 수정하시겠습니까? [Y/N]");
					choice = sc.nextLine();
					if(choice.toUpperCase().equals("N")) isEnd = false;
					break;
				
				case 3 :
					System.out.print("수정할 주소를 입력해주세요 : ");
					address = sc.nextLine();
					System.out.print("▶ 다른 내용을 추가로 수정하시겠습니까? [Y/N]");
					choice = sc.nextLine();
					if(choice.toUpperCase().equals("N")) isEnd = false;
					break;
					
				case 4 :
					System.out.print("수정할 고객등급을 입력해주세요 : ");
					grade = sc.nextLine();
					System.out.print("▶ 다른 내용을 추가로 수정하시겠습니까? [Y/N]");
					choice = sc.nextLine();
					if(choice.toUpperCase().equals("N")) isEnd = false;
					break;
				
				case 5 :
					System.out.print("수정할 도시코드를 입력해주세요 : ");
					city = sc.nextLine();
					System.out.print("▶ 다른 내용을 추가로 수정하시겠습니까? [Y/N]");
					choice = sc.nextLine();
					if(choice.toUpperCase().equals("N")) isEnd = false;
					break;
			
				case 6 :
					System.out.print("수정할 이름을 입력해주세요 : ");		custname = sc.nextLine();
					System.out.print("수정할 전화번호를 입력해주세요 : ");		phone = sc.nextLine();
					System.out.print("수정할 주소를 입력해주세요 : ");		address = sc.nextLine();
					System.out.print("수정할 고객등급을 입력해주세요 : ");		grade = sc.nextLine();
					System.out.print("수정할 도시코드를 입력해주세요 : ");		city = sc.nextLine();
					isEnd = false;
					break;
				
				case 7 :
					isEnd = false;
					break;
				}//switch(menuSel2) end
			}//while(isEnd) end
			mDto = new MemberDTO(custno,custname,phone,address,null,grade,city);
			mDao.updateMember(mDto);
			break;
			
		case 4 :
			System.out.println("[회원 매출 조회 메뉴입니다]");
			System.out.println("━".repeat(100));	//수정하기

			try {
				List<Check_SalesDTO> cDtoList = cDao.selectAll();
				System.out.println("회원번호┃회원성명┃고객등급┃매출");
				for (Check_SalesDTO rst : cDtoList) {
					System.out.print(rst.getCustno());		System.out.print("\t");
					System.out.print(rst.getCustname());	System.out.print("\t");
					System.out.print(rst.getGrade());		System.out.print("\t");
					System.out.print(rst.getSales());		System.out.print("\t");
					System.out.println();
				}
			} catch (Exception e) {
				System.out.println("예외 : "+e.getMessage());
			}
			
			break;
		case 5 :
			System.out.println("프로그램을 종료합니다.");
			isRun = false;
			break;
		}//switch(menuSel1) end
		
		}//while(isRun) end
		
		sc.close();
	}//main end
}//main class end
