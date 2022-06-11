package nguyenquocdat.gamedoanso.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 * Mục đích: Lớp Kết nối đến database bằng JDBC
 * Người tạo: Nguyễn Quốc Đạt
 * Ngày tạo: 28/05/2022
 */

public class JDBCConnection {
	public static Connection getConnection() {
		final String DATABASE = "jdbc:mysql://localhost:3306/game_doan_so";
		final String USERNAME = "root";
		final String PASSWORD = "1234";		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("***Connection to database failed");
		}
		
		return null;
	}
}
