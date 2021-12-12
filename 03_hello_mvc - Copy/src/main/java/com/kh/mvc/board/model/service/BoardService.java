package com.kh.mvc.board.model.service;

import static com.kh.mvc.common.JdbcTemplate.close;
import static com.kh.mvc.common.JdbcTemplate.commit;
import static com.kh.mvc.common.JdbcTemplate.getConnection;
import static com.kh.mvc.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.mvc.board.model.dao.BoardDao;
import com.kh.mvc.board.model.vo.Attachment;
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

	/**
	 * 
	 * insertBoard
	 * insertAttachment * n
	 * 
	 * @param board
	 * @return
	 */
	public int insertBoard(Board board) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = boardDao.insertBoard(conn, board);
			
			// 방금 insert된 boardNo 조회: select seq_board_no.currval from dual
			int boardNo = boardDao.selectLastBoardNo(conn);
			System.out.println("[BoardService] boardNo = " + boardDao);
			
			List<Attachment> attachments = board.getAttachments();
			if(attachments != null) {
				// insert into attachment values(seq_attachment_no.nextval, 0, ?, ?, default)
				for(Attachment attach : attachments) {
					attach.setBoardNo(boardNo); // FK 컬럼값 설정(매우 중요)
					result = boardDao.insertAttachment(conn, attach);
				}
			}

			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}
	
}

