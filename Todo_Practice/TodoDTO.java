package Todo_Practice;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// DTO 는 가변객체 이므로 Setter 를 생성한다.

@Getter
@Setter
@ToString
@Builder
public class TodoDTO {
    private int stuno;
    private String name;
    private int age;
    private String address;
    
	public TodoDTO(int stuno, String name, int age, String address) {
		super();
		this.stuno = stuno;
		this.name = name;
		this.age = age;
		this.address = address;
	}

    
    
}
