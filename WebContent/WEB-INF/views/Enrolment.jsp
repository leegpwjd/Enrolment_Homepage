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
<link href='<%=request.getContextPath() + "/css/Enrolment.css"%>' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/css?family=Sunflower:300&display=swap" rel="stylesheet">
</head>

<body>
<%
	String failCode = (String)request.getAttribute("overlap");
	if (failCode != null && failCode.equals("fail")) {%>
	<script>
		alert("이미 신청한 강의이거나 신청한 강의와 시간이 겹칩니다. 다시 선택해주세요")
	</script>
<%} %>

<%
	String Select = (String)request.getAttribute("select");
	if (Select != null && Select.equals("error")) {%>
		<script>
			alert("선택하지 않은 항목이 있습니다. 모두 선택해주세요.")
		</script>
<%} %>
	
<%
	String selectCode = (String)request.getAttribute("selectCode");

	String department = "";
	String grade = "";
	String lsubject = "";
	String college = "";
	
	LectureSelect lecture = (LectureSelect)request.getAttribute("lecture");
	
	if(lecture != null){
		department = lecture.getDepartment() == null ? "" : lecture.getDepartment();
		grade = lecture.getGrade() == 0 ? "" : String.valueOf(lecture.getGrade());
		lsubject = lecture.getlSubject() == null ? "" : lecture.getlSubject();
		college = lecture.getCollege() == null ? "" : lecture.getCollege();
	}
%>

<% LectureDAO dao = new LectureDAO();
	
	Lecture[] lecs = dao.selectLecture(department, grade, lsubject);	
%>
<% 
	String sId = (String)request.getSession().getAttribute("id");

	ListDAO ldao = new ListDAO();
	
	Lecture[] list = ldao.selectLecture(sId);
	
%>

<%
	if (sId == null) {%>
		<script>
			alert("로그인이 되어있지 않습니다. 로그인을 해주세요.")
			location.href = "/Enrolment_Program/university/home"
		</script>
<%} %>

	<form action = '<%= request.getContextPath() +"/university/enrolment" %>' method = "post">
	<h3>학생 수강신청 페이지</h3>
	<h1>The Human Computer</h1> 
	</div>
	<input type = "button" class = "btu" value="Home" onclick = "location.href = '<%= request.getContextPath() +"/university/home" %>'">
	<input type = "button" class = "btu" value="logOut" onclick = "location.href = '<%= request.getContextPath() +"/university/logout" %>'">

	<h2>각각의 항목을 선택하세요.</h2>
	
<div class = "sel">
	<td>대학: </td>
	<select name = "college" >
		<option value = "null">선택없음</option>
		<option value = "null">===========</option>
		<option value = "인문대">인문대</option>
		<option value = "자연대">자연대</option>
		<option value = "공대">공대</option>
		<option value = "예술대">예술대</option>
	</select>
	<td>전공: </td>
	<select name = "lsubject">
	
		<option value = "null">선택없음</option>
		<option value = "null">===========</option>
		<option value = "화학과">화학과</option>
		<option value = "컴퓨터공학과">컴퓨터공학과</option>
		<option value = "전자공학과">전자공학과</option>
		<option value = "실내디자인">실내디자인</option>
		<option value = "경영학과">경영학과</option>
		<option value = "복지학과">복지학과</option>
	</select>
	
	<td>학년: </td>
	<select name = "grade">
		<option value = "null">선택없음</option>
		<option value = "null">===========</option>
		<option value = "1">1학년</option>
		<option value = "2">2학년</option>
		<option value = "3">3학년</option>
		<option value = "4">4학년</option>
	</select>

	<td>이수과목: </td>
	<select name = "department">
		<option value = "null">선택없음</option>
		<option value = "null">===========</option>
		<option value = "기본교양">기본교양</option>
		<option value = "전공과목">전공과목</option>
		<option value = "균형교양">균형교양</option>
	</select>
	<input type = 'submit' class = "select_but" value = 'select'> 
	
</div>

<script>
	var college = '<%= college %>';
	var lsubject = '<%= lsubject %>';
	var grade = '<%= grade %>';
	var department = '<%= department %>';
	
	var names = ['college', 'lsubject', 'grade', 'department'];
	var values = [college, lsubject, grade, department];
	var selects = document.querySelectorAll('select');
	
	for(var i = 0; i < selects.length; i++){
		if(selects[i].name == names[i]){
			var options = document.querySelectorAll('select[name=' + names[i] + '] option');
			for (var j = 0; j < options.length; j++) {
				if (values[i] == options[j].value) {
					options[j].setAttribute('selected', true);
				}
			}
		}
	}

</script>
<br>
<br>
==================================================================================================================================================================================================================
	</form>
	<form action = '<%= request.getContextPath() +"/university/addlist" %>' method = "post">
	<h6>선택 가능 과목 강좌</h6>

	<%if (selectCode != null && selectCode.equals("select")) {%>
	
		
		<table class = "list">
			<tr>
				<th>강의이름</th>
				<th>이수강의</th>
				<th>학년</th>
				<th>강의실</th>
				<th>전공</th>
				<th>강의 선택</th>
				<th>교수님</th>
				<th>강의시작</th>
				<th>강의끝</th>
				<th>수강체크</th>
			</tr>
			
			<%for(Lecture l : lecs){ %>
			<tr>
				
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
				<td><%= l.getStart_time() %>교시</td>
				<td><%= l.getEnd_time() %>교시</td>
				<td><input type = 'submit' value = "<%=l.getlCode() %>" name = apply></td>
			</tr>
			<%} %>
		</table>
		<%} %>
		</form>
==================================================================================================================================================================================================================	
		<form action = '<%= request.getContextPath() +"/university/cancel" %>' method = "post">
		<h6>선택 과목 강좌</h6>
		<%if (selectCode != null && selectCode.equals("select")) {%>
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
				<th>강의요일</th>
				<th>강의시간</th>
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
				<td><%= l.getStart_time() %>교시</td>
				<td><%= l.getEnd_time() %>교시</td>
				<td><input type = 'submit' value = "<%=l.getlCode() %>" name = cancel></td>
			</tr>
			<%} %>
		</table>
		<%} %>
	
==================================================================================================================================================================================================================		

	<center><input type = "button" class = "button" value="수강신청완료" onclick = "location.href = '<%= request.getContextPath() +"/university/home" %>'" ></center>
		
	
</form>
<footer>
		<h4>The HUMAN Computer | 수원캠퍼스 : 경기 수원시 팔달구 갓매산로 38  | 전화번호 : 000-0000-0000</h4>
	</footer>
	
</body>
</html>