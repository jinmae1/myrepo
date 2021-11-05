package com.kh.mvc.member.model.service;

import static com.kh.mvc.common.JdbcTemplate.*;
import java.sql.Connection;

import com.kh.mvc.member.model.dao.MemberDao;
import com.kh.mvc.member.model.vo.Member;

public class MemberService {

	public static final String USER_ROLE = "U";
	public static final String ADMIN_ROLE = "A";
	private MemberDao memberDao = new MemberDao();

	public Member selectOneMember(String memberId) {
		// 1. Connection 객체 생성
		// 2. Dao에 쿼리실행 요청
		// 3. Connection 자원반납

		// 1.
		Connection conn = getConnection();
		
		// 2.
		Member member = memberDao.selectOneMember(conn, memberId);
		
		// 3.
		close(conn);

		return member;
	}

	public int insertMember(Member member) {
		Connection conn = null;
		int result = 0;
		try {
			// 1.Connection객체 생성
			conn = getConnection();
			
			// 2.Dao요청
			result = memberDao.insertMember(conn, member);
			
			// 3.트랜잭션처리
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			// 4.Connection 자원반납
			close(conn);
		}
		return result;
	}
	
	public int updateMember(Member member) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = memberDao.updateMember(conn, member);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
}
