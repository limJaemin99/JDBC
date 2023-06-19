package koreait.jdbc.day06;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import koreait.jdbc.day02.OracleUtility;
import koreait.jdbc.day04.JCustomerDTO;

public class JCustomerDAO2 {
	
	//싱글톤
	private static JCustomerDAO2 dao = new JCustomerDAO2();
	private JCustomerDAO2() {}
	public static JCustomerDAO2 getCustomerDAO2() {
		return dao;
	}
	
	public JCustomerDTO login(String id , String password) throws SQLException {
		Connection conn = OracleUtility.getConnection();
		String sql = "select * from j_customer where customer_id = ? and password = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		
		JCustomerDTO result = null;
		
		if(rs.next()) result = new JCustomerDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDate(5),rs.getString(6));
		
		conn.close();
		ps.close();
		rs.close();
		
		return result;	//result 가 null 이 아니면 로그인 성공
	}
	
	
	
	
	
	
}//class end
