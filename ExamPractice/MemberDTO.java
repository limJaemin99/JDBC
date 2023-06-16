package ExamPractice;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	
	private int custno;
	private String custname;
	private String phone;
	private String address;
	private Date joindate;
	private String grade;
	private String city;
	
	
	
	@Override
	public String toString() {
		return String.format("custno=%d, custname=%s, phone=%s, address=%s, joindate=%s, grade=%s, city=%s",
				custno, custname, phone, address, joindate, grade, city);
	}
	
	
	
	
}
