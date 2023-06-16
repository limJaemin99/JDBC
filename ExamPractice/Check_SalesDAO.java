package ExamPractice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day02.OracleUtility;

public class Check_SalesDAO {

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
	
	
}//class end
