package koreait.jdbc.day04;


import java.sql.Date;

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

//DTO : JBuy , JProduct
public class JCustomerDTO {
	
	private String customer_ID;
	private String name;
	private String email;
	private int age;
	private Date reg_date;
	@Override
	public String toString() {
		return String.format("customer_ID=%s, name=%s, email=%s, age=%s, reg_date=%s",
				customer_ID, name, email, age, reg_date);
	}
	
}
