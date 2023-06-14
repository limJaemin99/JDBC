package koreait.jdbc.day04;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JProductDTO {
	private String pCode;
	private String category;
	private String pName;
	private int price;
	
	@Override
	public String toString() {
		return String.format("pCode=%s, category=%s, pName=%s, price=%s",
				pCode, category, pName, price);
	}
	
}
