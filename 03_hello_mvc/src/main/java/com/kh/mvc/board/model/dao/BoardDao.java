package com.kh.mvc.board.model.dao;

import static com.kh.mvc.common.JdbcTemplate.close;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.mvc.board.model.vo.Board;

public class BoardDao {
	
	private Properties prop = new Properties();
	
	public BoardDao() {
		File file = new File(BoardDao.class.getResource("/board-query.properties").getPath());
		System.out.println("+++++++++++++++++" + file + "++++++++++++++++++++");
		try {
			prop.load(new FileReader(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Board> selectAllBoard(Connection conn, Map<String, Integer> param) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllBoard");
		ResultSet rset = null;
		List<Board> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.get("start"));
			pstmt.setInt(2, param.get("end"));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board();
				board.setNo(rset.getInt("no")); // number
				board.setTitle(rset.getString("title")); // varchar2, char
				board.setWriter(rset.getString("writer"));
				board.setContent(rset.getString("content"));
				board.setReadCount(rset.getInt("read_count"));
				board.setRegDate(rset.getDate("reg_date"));
				
				list.add(board);
			}

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int selectTotalBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectTotalBoardCount");
		ResultSet rset = null;
		int totalCount = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return totalCount;
	}

}
