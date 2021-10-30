package com.kh.web.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.web.common.JdbcTemplate;
import com.kh.web.member.model.vo.Member;

public class MemberDao {
	
	private Properties prop = new Properties();
	
	public MemberDao() {
		final String sqlConfigPath = JdbcTemplate.class.getResource("/member-query.properties").getPath();
		System.out.println(sqlConfigPath);
		try {
			prop.load(new FileReader(sqlConfigPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertMember(Connection conn, Member member) {
		String sql = prop.getProperty("insertMember");
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2,  member.getPwd());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getSsn());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getTel());
			pstmt.setString(7, member.getJob());
			pstmt.setString(8, member.getHobby());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		return result;
	}

}
