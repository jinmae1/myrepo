package com.kh.web.member.model.service;

import java.sql.Connection;

import com.kh.web.common.JdbcTemplate;
import com.kh.web.member.model.dao.MemberDao;
import com.kh.web.member.model.vo.Member;

public class MemberService {
	private MemberDao memberDao = new MemberDao();
	
	public int insertMember(Member member) {
		Connection conn = JdbcTemplate.getConnection();
		System.out.println(conn);

		int result = memberDao.insertMember(conn, member);
		if (result > 0) JdbcTemplate.commit(conn);
		else JdbcTemplate.rollback(conn);
		JdbcTemplate.close(conn);
		return result;
	}

}
