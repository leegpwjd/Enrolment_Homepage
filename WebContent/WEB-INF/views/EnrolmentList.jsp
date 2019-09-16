<%@page import="org.university.dao.ListDAO"%>
<%@page import="java.util.Arrays"%>
<%@page import="org.university.dao.LectureDAO"%>
<%@page import="org.university.model.Lecture"%>
<%@page import="org.university.model.LectureSelect"%>
<%@page import="org.university.dao.StudentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈페이지</title>
<link href='<%=request.getContextPath() + "/css/EnrolmentList.css"%>' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/css?family=Sunflower:300&display=swap" rel="stylesheet">
</head>

<body>
<%
	String sId = (String)request.getSession().getAttribute("id");
	if (sId == null) {%>
		<script>
			alert("로그인이 되어있지 않습니다. 로그인을 해주세요.")
			location.href = "/Enrolment_Program/university/home"
		</script>
<%} %>

<% 
	

	ListDAO ldao = new ListDAO();
	
	Lecture[] list = ldao.selectLecture(sId);
	
%>
	<form action = '<%= request.getContextPath() +"/university/cancel2" %>' method = "post">
	<h3>학생 수강신청 페이지</h3>
	<h1>The Human Computer</h1> 
	
	<input type = "button" class = "btu" value="Home" onclick = "location.href = '<%= request.getContextPath() +"/university/home" %>'">
	<input type = "button" class = "btu" value="logOut" onclick = "location.href = '<%= request.getContextPath() +"/university/logout" %>'">


==================================================================================================================================================================================================================	
		
		<h6>수강신청목록</h6>
		
		<table class = "list">
			<tr>
				<th>강의코드</th>
				<th>강의이름</th>
				<th>이수강의</th>
				<th>학년</th>
				<th>강의실</th>
				<th>전공</th>
				<th>강의 선택</th>
				<th>교수님</th>
				<th>강의시작</th>
				<th>강의끝</th>
				<th>수강취소</th>
			</tr>
			
			<%for(Lecture l : list){ %>
			<tr>
				<td><%= l.getlCode() %></td>
				<td><%= l.getlName() %></td>
				<td><%= l.getDepartment() %></td>
				<%if(l.getGrade() == 0){%>
				<td>-</td>
				<%}else{ %>
				<td><%= l.getGrade() %></td>
				<%} %>
				
				<td><%= l.getlRoom() %></td>
				
				<%if(l.getlSubject() == null){%>
				<td>-</td>
				<%}else{ %>
				<td><%= l.getlSubject() %></td>
				<%} %>
				
				<td><%= l.getsCount() %></td>
				<td><%= l.getpName() %></td>
				<td><%= l.getStart_time() %></td>
				<td><%= l.getEnd_time() %></td>
				<td><input type = 'submit' value = "<%=l.getlCode() %>" name = cancel></td>
			</tr>
			<%} %>
		</table>
</form>
<footer>
		<h4>The HUMAN Computer | 수원캠퍼스 : 경기 수원시 팔달구 갓매산로 38  | 전화번호 : 000-0000-0000</h4>
	</footer>
	
</body>
</html>