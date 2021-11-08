<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Member> list = (List<Member>) request.getAttribute("list");
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!-- 관리자용 admin.css link -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css" />
<section id="memberList-container">
	<h2>회원관리</h2>
	
	<table id="tbl-member">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>회원권한</th>
				<th>성별</th>
				<th>생년월일</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>취미</th>
				<th>가입일</th>
			</tr>
		</thead>
		<tbody>
<%
	for(Member member : list) {
%>
		<tr>
			<td><%= member.getMemberId() %></td>
			<td><%= member.getMemberName() %></td>
			<td>
				<form
					name="memberRoleUpdateFrm"
					action="<%= request.getContextPath() %>/admin/memberRoleUpdate""		
					method="POST">
					<input type="hidden" name="memberId" value="<%= member.getMemberId() %>" />
					<select name="memberRole" class="member-role">
						<option value="<%= MemberService.USER_ROLE %>" <%= "U".equals(member.getMemberRole()) ? "selected" : "" %>>일반</option>
						<option value="<%= MemberService.ADMIN_ROLE %>" <%= "A".equals(member.getMemberRole()) ? "selected" : "" %>>관리자</option>
				</select>
				</form>
			</td>
			<td><%= member.getGender() %></td>
			<td><%= member.getBirthday() != null ? member.getBirthday() : "" %></td>
			<td><%= member.getEmail() != null ? member.getEmail() : "" %></td>
			<td><%= member.getPhone() %></td>
			<td><%= member.getAddress() %></td>
			<td><%= member.getHobby() != null ? member.getHobby() : "" %></td>
			<td><%= member.getEnrollDate() %></td>
		</tr>
<%
	}
%>
		
		</tbody>
	</table>
</section>

<script>
$(".member-role").change((e) => {
	const $select = $(e.target);	
	const memberRole = $select.val();
	console.log(memberRole);
	if(confirm(`회원의 권한을 [\${memberRole}]로 변경하시겠습니까?`)) {
		const $frm = $select.parent();
		$frm.submit();
	} else {
		// selected 초기값으로 복원
		$selected.children("[selected]").prop("selected", true);
	}
});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
