<%@page import="org.university.dao.ScheduleDAO"%>
<%@page import="org.university.model.Schedule"%>
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
<link href='<%=request.getContextPath() + "/css/Schedule.css"%>'
	rel='stylesheet' type='text/css'>
<link
	href="https://fonts.googleapis.com/css?family=Sunflower:300&display=swap"
	rel="stylesheet">
</head>

<body>
	<%
		String sId = (String) request.getSession().getAttribute("id");
		if (sId == null) {
	%>
	<script>
		alert("로그인이 되어있지 않습니다. 로그인을 해주세요.")
		location.href = "/Enrolment_Program/university/home"
	</script>
	<%
		}
	%>

	<form action='<%=request.getContextPath() + "/university/cancel2"%>'
		method="post">
		<h3>학생 수강신청 페이지</h3>
		<h1>The Human Computer</h1>

		<input type="button" class="btu" value="Home"
			onclick="location.href = '<%=request.getContextPath() + "/university/home"%>'">
		<input type="button" class="btu" value="logOut"
			onclick="location.href = '<%=request.getContextPath() + "/university/logout"%>'">
		<%
			String id = (String) request.getSession().getAttribute("id");
			ScheduleDAO s = new ScheduleDAO();
			s.schedule(id);
		%>

		<%
			for (int j = 1; j < 8; j++) {
				for (int k = 0; k < 2; k++) {
					if (s.getMons()[j][k] == null) {
						s.getMons()[j][k] = "";
					}
				}
			}
		for (int j = 1; j < 8; j++) {
			for (int k = 0; k < 2; k++) {
				if (s.getTues()[j][k] == null) {
					s.getTues()[j][k] = "";
				}
			}
		}
		for (int j = 1; j < 8; j++) {
			for (int k = 0; k < 2; k++) {
				if (s.getWeds()[j][k] == null) {
					s.getWeds()[j][k] = "";
				}
			}
		}
		for (int j = 1; j < 8; j++) {
			for (int k = 0; k < 2; k++) {
				if (s.getThus()[j][k] == null) {
					s.getThus()[j][k] = "";
				}
			}
		}
		for (int j = 1; j < 8; j++) {
			for (int k = 0; k < 2; k++) {
				if (s.getFris()[j][k] == null) {
					s.getFris()[j][k] = "";
				}
			}
		}
		%>

		<h5>시간표</h5>

		<table class="table_style">
			<p></p>
			<tr>
				<td></td>
				<td>월</td>
				<td>화</td>
				<td>수</td>
				<td>목</td>
				<td>금</td>
			</tr>

			<tr>
				<td>1</td>
				<td><b><%=s.getMons()[1][0]%></b><br> <%=s.getMons()[1][1]%></td>
				<td><b><%=s.getTues()[1][0]%></b><br> <%=s.getTues()[1][1]%></td>
				<td><b><%=s.getWeds()[1][0]%></b><br> <%=s.getWeds()[1][1]%></td>
				<td><b><%=s.getThus()[1][0]%></b><br> <%=s.getThus()[1][1]%></td>
				<td><b><%=s.getFris()[1][0]%></b><br> <%=s.getFris()[1][1]%></td>
			</tr>

			<tr>
				<td>2</td>
				<td><b><%=s.getMons()[2][0]%></b><br> <%=s.getMons()[2][1]%></td>
				<td><b><%=s.getTues()[2][0]%></b><br> <%=s.getTues()[2][1]%></td>
				<td><b><%=s.getWeds()[2][0]%></b><br> <%=s.getWeds()[2][1]%></td>
				<td><b><%=s.getThus()[2][0]%></b><br> <%=s.getThus()[2][1]%></td>
				<td><b><%=s.getFris()[2][0]%></b><br> <%=s.getFris()[2][1]%></td>
			</tr>

			<tr>
				<td>3</td>
				<td><b><%=s.getMons()[3][0]%></b><br> <%=s.getMons()[3][1]%></td>
				<td><b><%=s.getTues()[3][0]%></b><br> <%=s.getTues()[3][1]%></td>
				<td><b><%=s.getWeds()[3][0]%></b><br> <%=s.getWeds()[3][1]%></td>
				<td><b><%=s.getThus()[3][0]%></b><br> <%=s.getThus()[3][1]%></td>
				<td><b><%=s.getFris()[3][0]%></b><br> <%=s.getFris()[3][1]%></td>
			</tr>

			<tr>
				<td>4</td>
				<td><b><%=s.getMons()[4][0]%></b><br> <%=s.getMons()[4][1]%></td>
				<td><b><%=s.getTues()[4][0]%></b><br> <%=s.getTues()[4][1]%></td>
				<td><b><%=s.getWeds()[4][0]%></b><br> <%=s.getWeds()[4][1]%></td>
				<td><b><%=s.getThus()[4][0]%></b><br> <%=s.getThus()[4][1]%></td>
				<td><b><%=s.getFris()[4][0]%></b><br> <%=s.getFris()[4][1]%></td>
			</tr>

			<tr>
				<td>5</td>
				<td><b><%=s.getMons()[5][0]%></b><br> <%=s.getMons()[5][1]%></td>
				<td><b><%=s.getTues()[5][0]%></b><br> <%=s.getTues()[5][1]%></td>
				<td><b><%=s.getWeds()[5][0]%></b><br> <%=s.getWeds()[5][1]%></td>
				<td><b><%=s.getThus()[5][0]%></b><br> <%=s.getThus()[5][1]%></td>
				<td><b><%=s.getFris()[5][0]%></b><br> <%=s.getFris()[5][1]%></td>
			</tr>

			<tr>
				<td>6</td>
				<td><b><%=s.getMons()[6][0]%></b><br> <%=s.getMons()[6][1]%></td>
				<td><b><%=s.getTues()[6][0]%></b><br> <%=s.getTues()[6][1]%></td>
				<td><b><%=s.getWeds()[6][0]%></b><br> <%=s.getWeds()[6][1]%></td>
				<td><b><%=s.getThus()[6][0]%></b><br> <%=s.getThus()[6][1]%></td>
				<td><b><%=s.getFris()[6][0]%></b><br> <%=s.getFris()[6][1]%></td>
			</tr>

			<tr>
				<td>7</td>
				<td><b><%=s.getMons()[7][0]%></b><br> <%=s.getMons()[7][1]%></td>
				<td><b><%=s.getTues()[7][0]%></b><br> <%=s.getTues()[7][1]%></td>
				<td><b><%=s.getWeds()[7][0]%></b><br> <%=s.getWeds()[7][1]%></td>
				<td><b><%=s.getThus()[7][0]%></b><br> <%=s.getThus()[7][1]%></td>
				<td><b><%=s.getFris()[7][0]%></b><br> <%=s.getFris()[7][1]%></td>
			</tr>


		</table>


	</form>
	<footer>
		<h4>The HUMAN Computer | 수원캠퍼스 : 경기 수원시 팔달구 갓매산로 38 | 전화번호 :
			000-0000-0000</h4>
	</footer>

</body>
</html>