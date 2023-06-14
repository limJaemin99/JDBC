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
public class JBuyDTO {
	
	private int buy_seq;
	private String customID;
	private String pCode;
	private int quantity;
	private String buy_date;
	
	@Override
	public String toString() {
		return String.format("buy_seq=%s, customID=%s, pCode=%s, quantity=%s, buy_date=%s",
				buy_seq, customID, pCode, quantity, buy_date);
	}
	
}
