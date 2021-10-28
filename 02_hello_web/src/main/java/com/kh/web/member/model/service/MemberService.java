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
		JdbcTemplate.close(conn);
		return 0;
	}

}
