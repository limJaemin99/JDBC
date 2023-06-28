package koreait.jdbc.day05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




//DAO 에는 입력과 출력은 포함시키지 않습니다.
//오직 어떤 형식의 데이터를 받아서 어떤 SQL 을 실행하여 어떤 값을 리턴할 것인가를 중점을 두고 정의하면 됩니다.
//DTO 는 데이터를 저장하는 목적의 클래스, DAO 는 어떤 동작을 할 것인지를 정의한 메소드만 있습니다.
public class MemberDAO {
	
	//싱글톤으로 만들어봅시다. (day06, 06-19) ------------------------------------
	private static MemberDAO dao = new MemberDAO();
	private MemberDAO() {}
	//메소드 이름은 getInstance 외에 내용을 알 수 있는 이름으로 정하기
	public static MemberDAO getMemberDAO() {
		return dao;
	}
	
		//메인메소드에서 싱글톤을 이용한 객체 가져오기 (day06, 06-19)---------
			//MemberDAO mDao = MemberDAO.getMemberDAO();
		//----------------------------------------------------------
	//-----------------------------------------------------------------------
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
