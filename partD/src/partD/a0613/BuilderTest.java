package partD.a0613;


public class BuilderTest {
	
	public static void main(String[] args) {
		
		// 객체 생성과 초기화 방법
		
		// 1) 기본 생성자
		Student s1 = new Student();
		s1.setStuno("2023019");
		s1.setName("김일구");
		s1.setEmail("kkkk@naver.com");
		s1.setAge(33);
		s1.setAddress("서울시");
		
		
		// 2) 커스텀 생성자
		Student s2 = new Student("2023018","김일팔",25,"kkkk@naver.com","서울시");
		// 커스텀 생성자의 단점 : 일부 필드만 초기화시킬때도 null 또는 0 의미없는 값을 적어줘야 한다.
			// 너무 코드가 많아진다.
		Student s3 = new Student("2023017", "김일칠", 0, null, null);
		
		
		// 3) 1번과 2번의 장점으로 만들어진 빌더 패턴
			// 순서 상관 X
		Student stu = Student.builder()	// builder() 메소드 리턴타입 StudentBuilder
				.address("서울시")		// address() 메소드 리턴타입 StudentBuilder
				.age(22)				// age() 메소드 리턴타입 StudentBuilder
				.name("김땡오")			// name() 메소드 리턴타입 StudentBuilder
				.stuno("1231")			// stuno() 메소드 리턴타입 StudentBuilder
				.email("kkkk@naver.com")// email() 메소드 리턴타입 StudentBuilder
				.build();
		
		// Builder 클래스가 필드 초기화를 담당하고, 마지막에 build 메소드에서 해당 객체를 리턴한다.
		
		System.out.println(stu);
		
		
		
		
		
		
		
	}

}
