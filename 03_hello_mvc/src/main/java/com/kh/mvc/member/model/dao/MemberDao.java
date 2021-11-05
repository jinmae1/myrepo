package com.kh.mvc.member.model.dao;

import static com.kh.mvc.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.mvc.member.model.vo.Member;
import com.kh.mvc.member.model.Exception.MemberException;
import com.kh.mvc.member.model.dao.MemberDao;

public class MemberDao {
	
	private Properties prop = new Properties();
	
	public MemberDao() {
		String filepath = MemberDao.class.getResource("/member-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
			System.out.println("prop@dao = " + prop);
			System.out.println(filepath);
			System.out.println(MemberDao.class.getResource("/member-query.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Member selectOneMember(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectOneMember");
		ResultSet rset = null;
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
//				String id = rset.getString("member_id");
				String password = rset.getString("password");
				String memberName = rset.getString("member_name");
				String memberRole = rset.getString("member_role");
				String gender = rset.getString("gender");
				Date birthday = rset.getDate("birthday");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("enroll_date");
				member = new Member(memberId, password, memberName, memberRole, gender, birthday, email, phone, address, hobby, enrollDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return member;
	}

	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		int result = 0;
		System.out.println("sql@MemberDao = " + sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberRole());
			pstmt.setString(5, member.getGender());
			pstmt.setDate(6, member.getBirthday());
			pstmt.setString(7, member.getEmail());
			pstmt.setString(8, member.getPhone());
			pstmt.setString(9, member.getAddress());
			pstmt.setString(10, member.getHobby());
			
			result = pstmt.executeUpdate();
		}
			catch(SQLException e) {
				throw new MemberException("회원가입 오류!", e);
//				e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		int result = 0;
		System.out.println("sql@MemberDao = " + sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getMemberName());
			pstmt.setString(3, member.getGender());
			pstmt.setDate(4, member.getBirthday());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getAddress());
			pstmt.setString(8, member.getHobby());
			pstmt.setString(9, member.getMemberId());
			
			result = pstmt.executeUpdate();

		} catch(SQLException e) {
			throw new MemberException("회원정보 수정 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		int result = 0;
		System.out.println("sql@MemberDao = " + sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
//			pstmt.setString(2, member.getPassword());
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			throw new MemberException("회원삭제 오류", e);
		} finally {
			close(pstmt);
		}

		return result;
	}

}
