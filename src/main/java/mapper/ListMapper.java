package mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import domain.BoardVO;

public class ListMapper {

	public Collection<BoardVO> read() {
		//DB불러오기
				String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
				String user = "root";
				String password = "smart";
				String sql = " SELECT * FROM board ORDER BY num DESC ";
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				ArrayList<BoardVO> list = new ArrayList<BoardVO>();
				BoardVO vo = null;
				try{
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					conn = DriverManager.getConnection(url, user, password);
					stmt = conn.prepareStatement(sql);

					rs = stmt.executeQuery();
					while(rs.next()){
						vo = new BoardVO();
						vo.setNum(rs.getInt("num"));
						vo.setTitle(rs.getString("title"));
						vo.setWriter(rs.getString("writer"));
						vo.setWriterDate(rs.getTimestamp("writerDate"));
						list.add(vo);
					}
				} catch (Exception e){
					e.getLocalizedMessage();
				} finally {
					try{
						if(rs != null) rs.close();
						if(stmt != null) stmt.close();
						if(conn != null) conn.close();
					} catch(Exception e){
						e.getLocalizedMessage();
					}
				}
				return list;
	}

	public int totalRow() {
		
		//DB불러오기
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";
		String sql = " SELECT count(*) as cnt FROM board ORDER BY num DESC ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int totalRow = 0;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			if(rs.next()){
				totalRow = rs.getInt("cnt");
			}
		} catch (Exception e){
			e.getLocalizedMessage();
		} finally {
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch(Exception e){
				e.getLocalizedMessage();
			}
		}
		return totalRow;
	}
	
	public int totalRow(String field, String keyWord) {
		//DB불러오기
				String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
				String user = "root";
				String password = "smart";
				StringBuffer qry = new StringBuffer()
						.append(" SELECT count(*) cnt FROM gallery ")
						.append(" WHERE 1=1 ");
				if("title".equals(field)) {
					qry.append(" AND title LIKE concat('%',?,'%') ");
				}

				if("content".equals(field)) {
					qry.append(" AND content LIKE concat('%',?,'%') ");
				}

				if("titleContent".equals(field)) {
					qry.append(" AND (title LIKE concat('%',?,'%') OR content LIKE concat('%',?,'%')) ");
				}
				
				String sql = qry.toString();
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				int totalRow = 0;
				int idx = 1;
				
				try{
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					conn = DriverManager.getConnection(url, user, password);
					stmt = conn.prepareStatement(sql);
					
					if("title".equals(field)) {
						stmt.setString(idx++, keyWord);
					}
					if("content".equals(field)) {
						stmt.setString(idx++, keyWord);
					}
					if("titleContent".equals(field)) {
						stmt.setString(idx++, keyWord);
						stmt.setString(idx++, keyWord);				
					}

					rs = stmt.executeQuery();
					if(rs.next()){
						totalRow = rs.getInt("cnt");
					}
				} catch (Exception e){
					e.getLocalizedMessage();
				} finally {
					try{
						if(rs != null) rs.close();
						if(stmt != null) stmt.close();
						if(conn != null) conn.close();
					} catch(Exception e){
						e.getLocalizedMessage();
					}
				}
				return totalRow;
	}

	public Collection<BoardVO> read(int startPage, int pageRow, String field, String keyWord) {
		//DB불러오기
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";
		StringBuffer qry = new StringBuffer()
				.append(" SELECT * FROM gallery ")
				.append(" WHERE 1=1 ");
		if("title".equals(field)) {
			qry.append(" AND title LIKE concat('%',?,'%') ");
		}

		if("content".equals(field)) {
			qry.append(" AND content LIKE concat('%',?,'%') ");
		}

		if("titleContent".equals(field)) {
			qry.append(" AND (title LIKE concat('%',?,'%') OR content LIKE concat('%',?,'%')) ");
		}
		
		qry.append(" ORDER BY num DESC LIMIT ?, ? ");
		
		String sql = qry.toString();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo = null;
		int idx = 1;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(sql);
			if("title".equals(field)) {
				stmt.setString(idx++, keyWord);
			}
			if("content".equals(field)) {
				stmt.setString(idx++, keyWord);
			}
			if("titleContent".equals(field)) {
				stmt.setString(idx++, keyWord);
				stmt.setString(idx++, keyWord);				
			}
			stmt.setInt(idx++, startPage);
			stmt.setInt(idx++, pageRow);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				vo = new BoardVO();
				vo.setNum(rs.getInt("num"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setWriterDate(rs.getTimestamp("writerDate"));
				vo.setRealFileName(rs.getString("realFileName"));
				vo.setRealSaveFileName(rs.getString("realSaveFileName"));
				list.add(vo);
			}
		} catch (Exception e){
			e.getLocalizedMessage();
		} finally {
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch(Exception e){
				e.getLocalizedMessage();
			}
		}
		return list;
	}

}
