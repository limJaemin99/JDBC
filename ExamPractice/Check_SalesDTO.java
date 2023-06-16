package ExamPractice;

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
public class Check_SalesDTO {
	int custno;
	String custname;
	String grade;
	int sales;
	
	@Override
	public String toString() {
		return String.format("custno=%d, custname=%s, grade=%s, sales=%d",
				custno, custname, grade, sales);
	}
	
	
}
