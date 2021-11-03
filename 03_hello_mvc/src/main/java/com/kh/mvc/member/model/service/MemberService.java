package com.kh.mvc.member.model.service;

import static com.kh.mvc.common.JdbcTemplate.*;
import java.sql.Connection;

import com.kh.mvc.member.model.dao.MemberDao;
import com.kh.mvc.member.model.vo.Member;

public class MemberService {

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
		Connection conn = getConnection();
		int result = memberDao.insertMemeber(conn, member);
		if (result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}
}
