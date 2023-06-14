package koreait.jdbc.day03;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DAO_Test {


	    @Test
	    public void testUpdate() {
	        _1_StudentDTO student = new _1_StudentDTO("1", "Jane Smith", 22, "456 Elm St");
	        _2_StudentDAO dao = new _2_StudentDAO();

	        try {
	            int result = dao.update(student);
	            Assertions.assertEquals(1, result); // 예상 결과와 실제 결과 비교
	        } catch (SQLException e) {
	            e.printStackTrace();
	            Assertions.fail("Exception occurred during update operation");
	        }
	    }

	    @Test
	    public void testPrintAll() {
	        _2_StudentDAO dao = new _2_StudentDAO();

	        try {
	            int count = dao.printAll();
	            Assertions.assertTrue(count > 0); // 결과의 개수가 0보다 큰지 확인
	        } catch (SQLException e) {
	            e.printStackTrace();
	            Assertions.fail("Exception occurred during printAll operation");
	        }
	    }

	    @Test
	    public void testDelete() {
	        String stuno = "1";
	        _2_StudentDAO dao = new _2_StudentDAO();

	        try {
	            int result = dao.delete(stuno);
	            Assertions.assertEquals(1, result); // 예상 결과와 실제 결과 비교
	        } catch (SQLException e) {
	            e.printStackTrace();
	            Assertions.fail("Exception occurred during delete operation");
	        }
	    }

	    @Test
	    public void testSelectOne() {
	        String stuno = "1";
	        _2_StudentDAO dao = new _2_StudentDAO();

	        try {
	            _1_StudentDTO student = dao.selectOne(stuno);
	            Assertions.assertNotNull(student); // 결과가 null이 아닌지 확인
	            System.out.println(student); // 결과 출력
	        } catch (SQLException e) {
	            e.printStackTrace();
	            Assertions.fail("Exception occurred during selectOne operation");
	        }
	    }

}
