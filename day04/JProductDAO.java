package koreait.jdbc.day04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day02.OracleUtility;

public class JProductDAO {	// 구매와 관련된 CRUD 실행 SQL
	
	// 메소드 이름은 insert , update , delete , select , selectByPname 등등으로 이름을 작성하세요. 
	//2. 상품 목록 보기
	public List<JProductDTO> selectAll() throws SQLException{
		Connection conn = OracleUtility.getConnection();
		String sql = "select * from J_product";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		List<JProductDTO> list = new ArrayList<>();
		while(rs.next()){
			list.add(new JProductDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
		}
		ps.close();
		conn.close();
		
		return list;
		
	}//selectAll end
	
	
	//3. 상품명으로 검색하기 (유사검색 ▶ 검색어가 포함된 상품명을 조회하기)
	public List<JProductDTO> selectByPname(String pname) throws SQLException{
		Connection conn = OracleUtility.getConnection();
		String sql = "select * from j_product where pname like '%' || ? || '%'";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, pname);
		ResultSet rs = ps.executeQuery();
		
		List<JProductDTO> list = new ArrayList<>();
		while(rs.next()) {
			list.add(new JProductDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
		}
		return list;
	}//selectByPname end
	
	
	public JProductDTO selectOne(String pCode) throws SQLException{
		Connection conn = OracleUtility.getConnection();
		String sql = "select * from J_product where pcode = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, pCode);
		
		ResultSet rs = ps.executeQuery();
		
		JProductDTO dto = new JProductDTO();
		if(rs.next()) {
		dto = new JProductDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
		}
		
		return dto;
	}
	
	
	
	public String pcodeToName(String pCode) throws SQLException{
		Connection conn = OracleUtility.getConnection();
		String sql = "select pname from J_product where pcode = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, pCode);
		
		ResultSet rs = ps.executeQuery();
		
		String result = null;
		if(rs.next()) {
			result = rs.getString(1);
		}
		
		return result;
	}
	
}//class end
