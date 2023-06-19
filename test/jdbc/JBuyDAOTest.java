package jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import koreait.jdbc.day04.JBuyDAO;
import koreait.jdbc.day04.JBuyDTO;

//테스트 케이스 입니다.
//테스트 할 메소드 앞에는 @Test 어노테이션을 작성하기. @DisplayName 는 테스트 내용 작성.
//테스트 결과는 성공 또는 실패 입니다. 테스트 메소드에는 대부분의 경우 리턴이 없습니다.
class JBuyDAOTest {
	
	private JBuyDAO dao = new JBuyDAO();
	
	@DisplayName("buy 테이블에 insert 성공하면 리턴값은 1(기대값)이 되어야합니다.")
	@Test
	void insertTest() {
		JBuyDTO buy = JBuyDTO.builder()
				.customID("hongGD")
				.pCode("")
				.quantity(5).build();
		int i = dao.insertMany(buy);
		
		//성공 또는 실패 결과를 확인하는 테스트 메소드를 실행하기
		assertEquals(1,i);	//assertEquals(기대값,실제값);
							//기대값과 실제값이 같으면 성공
		
//		assertEquals(0,i);		fail
	}//insertTest end
	
	
	@DisplayName("buy 테이블에서 buy_seq 컬럼으로 조회하면 null이 아닌 DTO 가 리턴된다.")
	@Test
	void selectOneTest() throws SQLException {
		JBuyDTO buy = dao.selectOne(1);
//		JBuyDTO buy = dao.selectOne(0);		fail
			assertNotNull(buy);
		
	}//selectOneTest end
	
	
	@Disabled
	@Test
	void test() {
		fail("테스트를 비활성화 하는 연습.");
	}
	
	//테스트 메소드 아닌것도 정의하여 호출할 수 있습니다.
	void print() {
		System.out.println("테스트중입니다.");
	}
	
}//class end
