package Todo_Practice;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

//VO 는 불변 객체이므로 다음과 같은 방법으로 객체를 불변으로 만들 수 있다.
	// 1. 필드를 private final final로 선언
	// 2. 커스텀 생성자를 통한 초기화 : 값을 입력받아 생성자에서 초기화하므로 객체는 불변이 된다.
	// 3. Getter 메서드만 제공 : Getter만 제공하고, Setter 메서드를 제공하지 않는다.
		// 이로써 외부에서는 필드 값을 변경할 수 없게 된다.


@Builder
@Getter
@ToString
public class TodoVO {
		private final int stuno;
		private final String name;
		private final int age;
		private final String address;
		
		public TodoVO(int stuno, String name, int age, String address) {
			super();
			this.stuno = stuno;
			this.name = name;
			this.age = age;
			this.address = address;
		}
		
		
		
}
