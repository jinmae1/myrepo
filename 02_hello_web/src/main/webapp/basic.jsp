<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// scriptlet
	int sum = 0;
	for(int i = 1; i <= 10; i++)
		sum += i;
	System.out.println("sum@basic.jsp = " + sum);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jsp Basic</title>
<script type="text/javascript">
	window.onload = () => {
		let v = 0;
		for(let i = 1; i <= 10; i++) 
			v += i;
	document.querySelector("#sum").innerHTML = v;
//	document.querySelector("#now").innerHTML = Date.now();
	now.innerHTML = Date.now();
	}
</script>
</head>
<body>
	<h1>JSP Basic</h1>
	<%-- jsp 주석: java 파일 변환 시 제거된다. --%>
	<!-- html 주석 -->
	
	<h2>1 ~ 10 합 구하기</h2>
	<p>서버단 결과: <span><%= sum %></span></p>
	<p>클라이언트단 결과: <span id="sum"></span></p>
	
	<h2>실행시각 Unix time 구하기</h2>
	<p>서버단 시각: <%= System.currentTimeMillis() %></p>
	<p>클라이언트단 시각: <span id="now"></span></p>
</body>
</html>