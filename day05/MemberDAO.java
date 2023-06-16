package koreait.jdbc.day05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import koreait.jdbc.day02.OracleUtility;

public class MemberDAO {
	
	public void joinMember(MemberDTO mDto) {
		
		Connection conn = OracleUtility.getConnection();
		String sql = "insert into MEMBER_TBL_02 values(joinmem_seq.nextval, ? , ? , ? , sysdate , ? , ?)";
		PreparedStatement ps;
		
		try {
			conn.setAutoCommit(false);	//커밋 false
			
			ps = conn.prepareStatement(sql);
		
			ps.setString(1, mDto.getCustname());
			ps.setString(2, mDto.getPhone());
			ps.setString(3, mDto.getAddress());
			ps.setString(4, mDto.getGrade());
			ps.setString(5, mDto.getCity());
			ps.execute();
			
			conn.commit();
			conn.setAutoCommit(true);
			ps.close();
			conn.close();
			System.out.println("회원 등록이 완료되었습니다!");
		} catch (SQLException e) {
			System.out.println("예외 : "+e.getMessage());
			try {
				conn.rollback();
			} catch (Exception e2) {
				System.out.println("예외 : "+e2.getMessage());
			}//try-catch 2 end
		}//try-catch 1 end
		
	}//joinMember end
	
	
	public List<MemberDTO> selectAll() throws SQLException{
		Connection conn = OracleUtility.getConnection();
		String sql = "select custno , custname , phone , address , to_char(joindate,'yyyy-mm-dd') as joindate , grade , city "
				+ "from MEMBER_TBL_02 "
				+ "order by custno";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<MemberDTO> list = new ArrayList<>();
		
		while(rs.next()) {
			list.add(new MemberDTO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getString(7)));
		}
		
		conn.close();
		ps.close();
		rs.close();
		
		return list;
	}//selectAll end
	
	public void updateMember(MemberDTO mDto) {
		Connection conn = OracleUtility.getConnection();
		String sql = "update MEMBER_TBL_02 set custname = ? , phone = ? , address = ? , grade = ? , city = ? where custno = ?";
		PreparedStatement ps;
		
		try {
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, mDto.getCustname());
			ps.setString(2, mDto.getPhone());
			ps.setString(3, mDto.getAddress());
			ps.setString(4, mDto.getGrade());
			ps.setString(5, mDto.getCity());
			ps.setInt(6, mDto.getCustno());
			ps.execute();
			
			conn.commit();
			conn.setAutoCommit(true);
			ps.close();
			conn.close();
			System.out.println("__회원 수정이 완료되었습니다!");
		} catch (SQLException e) {
			System.out.println("예외 : "+e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e2) {
				System.out.println("예외 : "+e.getMessage());
			}//try-catch 2 end
		}//try-catch 1 end
		
	}//updateMember end
	
	
	
	
	
	
}//class end
