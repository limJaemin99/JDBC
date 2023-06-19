package koreait.jdbc.day05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day02.OracleUtility;

public class Check_SalesDAO {
	
	//싱글톤 작성 (day06, 06-19) ------------------------------------------
	private static Check_SalesDAO dao = new Check_SalesDAO();
	private Check_SalesDAO() {}
	public static Check_SalesDAO getCheck_SalesDAO() {
		return dao;
	}
	
			//메인메소드에서 싱글톤을 이용한 객체 가져오기 (day06, 06-19)---------
			//Check_SalesDAO cDao = Check_SalesDAO.getCheck_SalesDAO();
			//----------------------------------------------------------
	//------------------------------------------------------------------
	
	
	public List<Check_SalesDTO> selectAll() throws SQLException {
		Connection conn = OracleUtility.getConnection();
		String sql = "select * from check_sales";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Check_SalesDTO> list = new ArrayList<>();
		
		while(rs.next()) {
			list.add(new Check_SalesDTO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
		}
		
		rs.close();
		ps.close();
		conn.close();
		
		return list;
		
	}//selectAll end
	
	/*
	view SQL
	create or replace view Check_sales
	as
	select m.custno , m.custname , decode(m.grade,'A','VIP','B','일반','C','직원') as grade , c.sales
	from MEMBER_TBL_02 m join custSales c on m.custno = c.custno;
	*/
}//class end
