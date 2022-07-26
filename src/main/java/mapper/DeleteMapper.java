package mapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.BoardVO;

public class DeleteMapper {

	public void delete(int num) {
		//DB불러오기
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";
		String sql = " DELETE FROM board WHERE num = ? ";
		Connection conn = null;
		PreparedStatement stmt = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, num);
			stmt.executeUpdate();

			
		} catch (Exception e){
			e.getLocalizedMessage();
		} finally {
			try{
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch(Exception e){
				e.getLocalizedMessage();
			}
		}
	}

}
