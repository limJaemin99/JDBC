package jdbc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import koreait.jdbc.day06.SingletonClass;

class SingletonClassTest {

	@Test
	void singletonTest() {
		
		//SingletonClass 타입의 객체를 getInstance() 메소드를 통해 s1 , s2 에 받음
		SingletonClass s1 = SingletonClass.getInstance();
		SingletonClass s2 = SingletonClass.getInstance();
	
		assertEquals(s1, s2);	//s1 과 s2 가 같은 객체인지?
								//▶ true / 둘 다 싱글톤 객체이므로
		
		//필드값이 모두 같으면 equals 로 true 가 되도록 하고싶다면
		//▶ equals 와 hashcode 를 재정의해야 한다. (불변객체)
		//▶ 이것이 VO 입니다. VO 는 테스트 케이스에서 객체를 비교할 때 사용할 수 있다.
		//▶ assertEquals 비교
	}

	
}//class end
