package koreait.jdbc.day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OracleConnection_logging {
    
    private static final Logger logger = Logger.getLogger(OracleConnection_logging.class.getName());
    
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String driver = "oracle.jdbc.driver.OracleDriver";
        String user = "iclass";
        String password = "0419";
        
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Class.forName(driver);
            logger.info("연결 상태 = " + conn);
            
            if (conn != null) {
                logger.info("오라클 데이터베이스 연결 성공!");
            } else {
                logger.info("오라클 데이터베이스 연결 실패!");
            }
            
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "ClassNotFoundException = 드라이버 경로가 잘못되었습니다.", e);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException = url 또는 user 또는 password 가 잘못되었습니다.", e);
        }
    }
    
}
