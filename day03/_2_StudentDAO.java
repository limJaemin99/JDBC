package koreait.jdbc.day03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day02.OracleUtility;

// DAO : Data Access(접근 - 읽기와 쓰기) Object
	// SQL 실행 메소드를 모아놓은 클래스

/*
	[StudentDAO 내용 요약]
	
	● INSERT , UPDATE 는 SQL 파라미터에 전달한 데이터의 타입을 DTO
	
	● DELETE 는 SQL 파라미터에 전달한 데이터의 타입을 원시형 또는 String
		만약, DELETE SQL 의 조건절 컬럼이 여러개일때는 DTO 가 될 수 있다. (Map 도 종종 사용한다)
	
	● INSERT , UPDATE , DELETE 는 정수 리턴값으로 반영된 행의 개수를 전달.
	
	[메소드 설명]
	● selectOne : SQL 파라미터에 전달할 데이터를 메소드인자로 받음.
	● selectAll : 파라미터 없으며 여러개의 행을 저장할 객체는 List타입
	
*/
public class _2_StudentDAO {
	
	//나중에 DB 를 쉽게 코딩하기 위한 프레임워크를 사용하면 Exception 처리 안해도 됩니다.
	public int insert(_1_StudentDTO student) throws SQLException {
		
		Connection conn = OracleUtility.getConnection();
		
		String sql = "insert into TBL_STUDENT VALUES(? , ? , ? , ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, student.getStuno());
		ps.setString(2, student.getName());
		ps.setInt(3, student.getAge());
		ps.setString(4, student.getAddress());
		
		int result = ps.executeUpdate();
		
		ps.close();
		conn.close();
		
		return result;
		
	}//insertOne end
	
	
	
	public int update(_1_StudentDTO student) throws SQLException {
		
		Connection conn = OracleUtility.getConnection();
		String sql = "update TBL_STUDENT set name = ? , age = ? , address = ? where stuno = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, student.getName());
		ps.setInt(2, student.getAge());
		ps.setString(3, student.getAddress());
		ps.setString(4, student.getStuno());
//		ps.setInt(4, Integer.parseInt(student.getStuno()));	//int 타입으로 입력할 경우
		
		int result = ps.executeUpdate();
		
		ps.close();
		conn.close();
		
		return result;
		
	}//update end
	
	public int printAll () throws SQLException {
		
		Connection conn = OracleUtility.getConnection();
		String sql  = "select * from tbl_student";
		PreparedStatement ps =conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		// 결과를 담을 배열 생성
		ArrayList<_1_StudentDTO> list = new ArrayList<>();

		while(rs.next()) {
			_1_StudentDTO dto = new _1_StudentDTO(rs.getString("stuno"),rs.getString("name"),rs.getInt("age"),rs.getString("address"));
			list.add(dto);
		}
		for (_1_StudentDTO rst : list) {
			System.out.println(rst);
		}
		
		
		int count = ps.executeUpdate();
		rs.close();
		conn.close();
		
		return count;
		
		/*
		//// 선생님이 작성하신 코드 ////
		
		while(rs.next()){
			_1_StudentDTO dto = new _1_StudentDTO(rs.getString("stuno"),rs.getString("name"),rs.getInt("age"),rs.getString("address"));
			list.add(dto);
		}
		
		return list;
		///////////////////////////
		리턴타입을 List<StudentDTO> 로 정하고,
		메소드에서 출력문을 작성하지 않고,
		main 메소드에서 for-each 문을 돌려 출력하는 방법
		*/
		
	}// printAlll end
	
	public List<_1_StudentDTO> selectAll() throws SQLException {
		
		Connection conn = OracleUtility.getConnection();
		String sql = "select * from tbl_student";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
			
		List<_1_StudentDTO> list = new ArrayList<>();
			
		while(rs.next()) {
			_1_StudentDTO dto = new _1_StudentDTO(rs.getString("stuno"), rs.getString("name"), rs.getInt("age"), rs.getString("address"));
			list.add(dto);
		}
		
		ps.close();
		rs.close();
		conn.close();
		
		return list;
	}// selectAll end
	
	
	
	
	public int delete(String stuno) throws SQLException{
		
		Connection conn = OracleUtility.getConnection();
		
		String sql = "DELETE FROM TBL_STUDENT WHERE STUNO = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, stuno);
		
		int result = ps.executeUpdate();
		
		ps.close();
		conn.close();
		
		return result;
		
	}//delete end
	
	public _1_StudentDTO selectOne(String stuno) throws SQLException{
		
		Connection conn = OracleUtility.getConnection();
		String sql = "SELECT * FROM TBL_STUDENT WHERE STUNO = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, stuno);
		
		ResultSet rs = ps.executeQuery();
		
		_1_StudentDTO dto = null;
		
		if(rs.next()) {
			
			String name = rs.getString("NAME");
			int age = rs.getInt("AGE");
			String address = rs.getString("ADDRESS");
			
			dto = new _1_StudentDTO(stuno,name,age,address);
			
		}
		
		return dto;
	}//selectOne
	
	
	
	
	
	
	
}//class end
