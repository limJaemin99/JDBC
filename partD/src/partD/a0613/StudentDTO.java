package partD.a0613;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor	// 모든 필드 값을 파라미터로 받는 생성자를 생성
@NoArgsConstructor	// 파라미터가 없는 기본 생성자를 생성
// @RequiredArgsConstructor : final이나 @NonNull인 필드 값만 파라미터로 받는 생성자를 생성

//lombok을 사용하여 Student 클래스와 같은 내용의 StudentDTO 클래스 구현
public class StudentDTO {
	 
	private String stuno;
	private String name;
	private int age;
	private String address;
	private String email;
	
	
	
	
	
	
}//class end
