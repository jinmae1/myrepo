package com.kh.mvc.board.model.service;

import static com.kh.mvc.common.JdbcTemplate.close;
import static com.kh.mvc.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.mvc.board.model.dao.BoardDao;
import com.kh.mvc.board.model.vo.Board;

public class BoardService {

	private BoardDao boardDao = new BoardDao();

	public List<Board> selectAllBoard(Map<String, Integer> param) {
		Connection conn = getConnection();
		List<Board> list = boardDao.selectAllBoard(conn, param);
		close(conn);
		return list;
	}

	public int selectTotalBoardCount() {
		Connection conn = getConnection();
		int totalCount = boardDao.selectTotalBoardCount(conn);
		close(conn);
		return totalCount;
	}
}

