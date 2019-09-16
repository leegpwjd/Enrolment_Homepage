<%@page import="org.university.dao.StudentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈페이지</title>
<link href='<%= request.getContextPath() + "/css/Home.css" %>' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/css?family=Sunflower:300&display=swap" rel="stylesheet">
</head>

<%
	String action = (String)session.getAttribute("login");
%>

<%
	boolean isFound = false;
	
	if(action != null && action.equals("success")){
		isFound = true;
	}
%>

<body>
<div class = "header">
	
	<h3>학생 수강신청 페이지</h3>
	<h1>The Human Computer</h1> 
	<h2>Home</h2>
	</div>
	<input type = "button" class = "btu" value="Home" onclick = "location.href = '<%= request.getContextPath() +"/university/home" %>'">
	<%if(isFound){%>
	<input type = "button" class = "btu" value="logOut" onclick = "location.href = '<%= request.getContextPath() +"/university/logout" %>'">
	<%} %>
	<h6>안녕하세요. The Human Computer 학생 수강신청 페이지입니다.</h6>
	
	<h5>The Human Computer</h5>
	
	
	<% if(!isFound){%>
	<center><button class='btn-login' type='submit' onclick="location.href = '<%= request.getContextPath() + "/university/login"%>'">LogIn</button>
	<button class='btn-login' type='submit' onclick="location.href = '<%= request.getContextPath() + "/university/add"%>'">Sign Up</button></center>
	<%} else{%>
	<center><button class='btn-login' type='submit' onclick="location.href = '<%= request.getContextPath() + "/university/enrolmentList"%>'">수강신청목록</button>
	<button class='btn-login' type='submit' onclick="location.href = '<%= request.getContextPath() + "/university/enrolment"%>'">수강신청</button>
	<button class='btn-login' type='submit' onclick="location.href = '<%= request.getContextPath() + "/university/schedule"%>'">수강신청시간표</button></center>
	<%} %>
	
	
	<footer>
		<h4>The HUMAN Computer | 수원캠퍼스 : 경기 수원시 팔달구 갓매산로 38  | 전화번호 : 000-0000-0000</h4>
	</footer>
	
</body>
</html>