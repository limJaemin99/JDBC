package Todo_Practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {
    private Connection connection;

    // 생성자
    public TodoDAO(Connection connection) {
        this.connection = connection;
    }
    
    
    // TodoDTO 객체를 저장하는 메서드
    public void save(TodoDTO dto) throws SQLException {
        // SQL 쿼리문 작성 및 PreparedStatement 생성
        String sql = "INSERT INTO TBL_STUDENT (stuno, name, age, address) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        
        // TodoDTO 객체의 필드 값 설정
        ps.setInt(1, dto.getStuno());
        ps.setString(2, dto.getName());
        ps.setInt(3, dto.getAge());
        ps.setString(4, dto.getAddress());
        
        // SQL 실행
        ps.executeUpdate();
        
        // 리소스 해제
        ps.close();
    }

 // TodoDTO 객체를 조회하는 메서드
    public List<TodoDTO> findAll() throws SQLException {
        // SQL 쿼리문 작성 및 PreparedStatement 생성
        String sql = "SELECT * FROM TBL_STUDENT";
        PreparedStatement ps = connection.prepareStatement(sql);
        
        // SQL 실행 및 결과 처리
        ResultSet result = ps.executeQuery();
        
        // 결과를 담을 리스트 생성
        List<TodoDTO> todoList = new ArrayList<>();
        
        // 결과 순회
        while (result.next()) {
            // TodoDTO 객체 생성
            TodoDTO dto = TodoDTO.builder()
                    .stuno(result.getInt("stuno"))
                    .name(result.getString("name"))
                    .age(result.getInt("age"))
                    .address(result.getString("address"))
                    .build();
            
            // 리스트에 TodoDTO 객체 추가
            todoList.add(dto);
        }
        
        // 리소스 해제
        result.close();
        ps.close();
        
        return todoList;
    }
    
    // TodoDTO 객체를 삭제하는 메서드
    public void delete(int stuno) throws SQLException {
        // SQL 쿼리문 작성 및 PreparedStatement 생성
        String sql = "DELETE FROM TBL_STUDENT WHERE stuno = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        
        // 파라미터 설정
        ps.setInt(1, stuno);
        
        // SQL 실행
        ps.executeUpdate();
        
        // 리소스 해제
        ps.close();
    }
}