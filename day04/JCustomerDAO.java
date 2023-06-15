package koreait.jdbc.day04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import koreait.jdbc.day02.OracleUtility;

public class JCustomerDAO {	// 구매와 관련된 CRUD 실행 SQL
	// 메소드 이름은 insert , update , delete , select , selectByPname 등등으로 이름을 작성하세요. 
	
	
	//1. 회원 로그인 - 간단히 회원아이디를 입력해서 존재하면 로그인 성공
	public JCustomerDTO selectByID(String customid) throws SQLException{
		Connection conn = OracleUtility.getConnection();
		String sql = "select * from j_customer where customer_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, customid);
		
		ResultSet rs = ps.executeQuery();
		JCustomerDTO temp = null;
		if(rs.next()) {
			temp = new JCustomerDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDate(5));
		} else {
			System.out.println("※ 존재하지 않는 아이디입니다 ※\n");
			//프로그램 종료 메소드
//			System.exit(0);
		}
		
		ps.close();
		conn.close();
		
		return temp;
	}//selectByID end
	
	
	
}//class end
