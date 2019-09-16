<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
 String logout = request.getParameter("logout");
	String displayError = null;
	if(logout != null){
		session.invalidate();
		displayError="성공적으로 로그아웃하였습니다.";
	}
	String error = (String)request.getAttribute("error");
	if(error != null ){
		displayError = error;
	}
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<link href='<%= request.getContextPath() + "/css/LogIn_style.css" %>' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/css?family=Sunflower:300&display=swap" rel="stylesheet">

</head>

<body>

<div class = "header">
	
	<h3>학생 수강신청 페이지</h3>
	<h1>The Human Computer</h1> 
	<h2>LogIn</h2>

	</div>
	
	
	
	
	<input type = "button" class = "btu" value="Home" onclick = "location.href = '<%= request.getContextPath() +"/university/home" %>'">
	
	<h5>학번과 비밀번호를 입력해주세요.</h5>
	
	
	
	<%if(displayError != null ){ %>
		<h4 class='error'><%= displayError %></h4>
	<%} %>
		
	<form action= '<%= request.getContextPath() + "/university/loginTest"%>' method="post">
	
		<table>
			<tr>
				<td>학번(*): </td>
				<td><input type='text' name='sid' required></td>
			</tr>
			
			<tr>
				<td>비밀번호(*): </td>
				<td><input type='password' name='spw' ></td>
			</tr>
			</table>
			
			<%
		String action = (String)request.getAttribute("errorCode");
	
		if(action != null && action.equals("idDup")){
	%>
		<h6>학번 또는 비밀번호를 잘못입력했습니다. 다시 입력해주세요.</h6>
	<%} %>
			
				<center><button class='btn-login' type='submit'>LogIn</button></center>
	</form>
	
	<footer>
		<h4>The HUMAN Computer | 수원캠퍼스 : 경기 수원시 팔달구 갓매산로 38  | 전화번호 : 000-0000-0000</h4>
	</footer>
</body>
</html>