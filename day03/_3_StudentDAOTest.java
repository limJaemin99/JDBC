package koreait.jdbc.day03;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _3_StudentDAOTest {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// 메인 메소드에서 DAO , DTO 테스트 해보기
		//////////////////////////////////////////////////////////
		
		System.out.println("1. INSERT 테스트");
		System.out.println("2023009 김땡구 17 강남구 - 데이터 입력");
		
		_2_StudentDAO dao = new _2_StudentDAO();
		_1_StudentDTO dto = new _1_StudentDTO("2023009", "김땡구", 17, "강남구");

		try {
			int count = dao.insert(dto);
			System.out.println("[학생 등록 "+count+"건 입력 성공]");
		} catch (SQLException e) {
			System.out.println("예외 - "+e.getMessage());
		}
		
		//////////////////////////////////////////////////////////
		
		System.out.println("\n2. UPDATE 테스트");
		System.out.println("2023009 김땡구 를 16 용산구 - 데이터 수정");
		dto = new _1_StudentDTO("2023009", "김땡구", 16, "용산구");
		
		try {
			int count = dao.update(dto);
			System.out.println("[학생 등록 "+count+"건 정보 수정 성공]");
		} catch (SQLException e) {
			System.out.println("예외 - "+e.getMessage());
		}
		
		//////////////////////////////////////////////////////////
		
		System.out.println("\n3. DELETE 테스트");
		System.out.println("2023009 - 데이터 삭제");
		
		try {
			int count = dao.delete("2023009");
			System.out.println("[학생 등록 "+count+"건 삭제 성공]");
		} catch (SQLException e) {
			System.out.println("예외 - "+e.getMessage());
		}
		
		//////////////////////////////////////////////////////////
		
		System.out.println("\n4. SELECT 테스트 1");
		System.out.println("전체 내용 출력");
		
		try {
			int count = dao.printAll();
			System.out.println("[총 "+count+"건 출력 완료]");
		} catch (SQLException e) {
			System.out.println("예외 - "+e.getMessage());
		}
		
		//////////////////////////////////////////////////////////
				
		System.out.println("\n5. SELECT 테스트 2");
		System.out.println("메소드에 출력문을 사용하지 않고 전체 내용 출력");
		
		try {
		List<_1_StudentDTO> list = dao.selectAll();
		for (_1_StudentDTO result : list) {
		System.out.println(result);
		}
		int count = list.size();
		System.out.println("[총 "+count+"건 출력 완료]");
		} catch (SQLException e) {
		System.out.println("\"예외 - \"+e.getMessage()");
		}
				
		//////////////////////////////////////////////////////////
		
		System.out.println("\n6. SELECT 테스트 3");
		System.out.println("학번을 입력해서 한명의 정보만 출력");
		
		System.out.print("▶ 학번을 입력하세요 : ");
		String stuno = sc.nextLine();
		
		try {
			dto = dao.selectOne(stuno);
			System.out.println(dto);
			System.out.println("["+dto.getName()+" 학생의 정보가 출력되었습니다]");
		} catch (SQLException e) {
			System.out.println("\"예외 - \"+e.getMessage()");
		}
		
		//////////////////////////////////////////////////////////
		
		
	}//main end
}//main class end
