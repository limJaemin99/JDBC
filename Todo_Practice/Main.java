package Todo_Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        // 데이터베이스 연결 정보
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "iclass";
        String password = "0419";
        
        // Connection 객체 생성
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
        	
        	System.out.println("등록할 학생의 [학번]을 입력하세요 (7자리)");
        	String temp1 = sc.nextLine();
        	int stuno = Integer.parseInt(temp1);
        	
        	System.out.println("등록할 학생의 [이름]을 입력하세요");
        	String name = sc.nextLine();
        	
        	System.out.println("등록할 학생의 [나이]를 입력하세요.");
        	String temp2 = sc.nextLine();
        	int age = Integer.parseInt(temp2);
        	
        	System.out.println("등록할 학생의 [주소]를 입력하세요.");
        	String address = sc.nextLine();
        	
        
        	// TodoDAO 객체 생성
            TodoDAO todoDAO = new TodoDAO(connection);
            
            // TodoDTO 객체 생성
            TodoDTO todoDTO = TodoDTO.builder()
                    .stuno(stuno)
                    .name(name)
                    .age(age)
                    .address(address)
                    .build();
            
            // TodoDTO 객체 저장
            todoDAO.save(todoDTO);
            
            // 모든 TodoDTO 객체 조회
            List<TodoDTO> todoList = todoDAO.findAll();
            
            // 조회 결과 출력
            for (TodoDTO todo : todoList) {
                System.out.println(todo);
            }
            
            // TodoDTO 객체 삭제
            int stunoToDelete = 1;
            todoDAO.delete(stunoToDelete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
