<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    String userId = request.getParameter("userId");
    String pwd = request.getParameter("pwd");
    String userName = request.getParameter("userName");
    String ssn = (String)request.getAttribute("ssn");
    String email = request.getParameter("email");
    String tel = (String)request.getAttribute("tel");
    String job = request.getParameter("job");
    String hobby = (String)request.getAttribute("hobbies");
    String msg = (String)request.getAttribute("msg");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script>
	const member = `회원 등록 정보
----------------------------------
아이디 : <%= userId %>
비밀번호 : <%= pwd %> 
이름 : <%= userName %>
주민번호 : <%= ssn %>
이메일 : <%= email %>
전화번호 : <%= tel %>
직업 : <%= job %>
취미 : <%= hobby %>
----------------------------------
처리결과 : <%= msg %>`;
alert(member);
location.href = "<%= request.getContextPath() %>";

</script>
</body>
</html>