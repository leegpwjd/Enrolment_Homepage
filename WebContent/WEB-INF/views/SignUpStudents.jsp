<%@page import="org.university.model.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>

<link href='<%= request.getContextPath() + "/css/SignUp_style.css" %>' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/css?family=Sunflower:300&display=swap" rel="stylesheet">
</head>
<body>
<div class = "header">
	
	<h3>학생 수강신청 페이지</h3>
	<h1>The Human Computer</h1> 
	<h2>Sign Up</h2>

	</div>
	
	<input type = "button" class = "btu" value="Home" onclick = "location.href = '<%= request.getContextPath() +"/university/home" %>'">
	
	<%
	String errorCode = (String)request.getAttribute("errorCode");

	String sId = "";
	String sPw = "";
	String sName = "";
	String Age= "";
	String address = "";
	String gender = "";
	String sMajor = "";

	Student student = (Student)request.getAttribute("student");
	if (student != null) {
		sId = student.getSid() == null ? "" : student.getSid();
		sPw = student.getSpw() == null ? "" : student.getSpw();
		sName = student.getSname() == null ? "" : student.getSname();
		Age = student.getAge() == 0 ? "" : String.valueOf(student.getAge());
		address = student.getAddress() == null ? "" : student.getAddress();
		gender = student.getGender()==null?"":student.getGender();
		sMajor = student.getSmajor() == null ? "" : student.getSmajor();
	}
%>

<script>
      function validate() {
          var passwordInputs = document.querySelectorAll('input[type=password]');
          if (passwordInputs[0].value === passwordInputs[1].value) {
         return true;
      } else {
    	 var retVal = confirm("비밀번호를 잘못 입력하셨습니다. 다시 입력해주세요.");
         
    	 return false;
      }
    }
   </script>

	<%
		if (errorCode != null && errorCode.equals("idDup")) {
	%>
	<script>
	var retVal = confirm("학번을 잘못 입력하셨습니다. 다시 입력해주세요.");
	</script>
	
	<%} %>
	<form action= '<%= request.getContextPath() + "/university/add"%>' method="post">
	
		<table>
			<tr>
				<td>학번(*): </td>
				<td><input type='text' name='sid' value='<%= sId %>' required></td>
			</tr>
			
			<tr>
				<td>비밀번호(*): </td>
				<td><input type='password' name='spw' value='<%= sPw %>'></td>
			</tr>
			
			<tr>
				<td>비밀번호 확인(*): </td>
				<td><input type='password' name='spw2' ></td>
			</tr>
			
			<tr>
				<td>이름(*): </td>
				<td><input type='text' name='sname' value='<%= sName %>'required></td>
			</tr>
			
			<tr>
				<td>나이: </td>
				<td><input type='number' name='age' value='<%= Age %>'required></td>
			</tr>
			
			<tr>
				<td>주소: </td>
				<td><input type='text' name='address' value='<%= address %>'required></td>
			</tr>
			
			<tr>
				<td>성별(*): </td>
				<td>
				<input type='radio' name='gender' value='male' value='<%= gender %>'>남자
				<input type='radio' name='gender' value='female' value='<%= gender %>'>여자 
				</td>
			</tr>
			
			<tr>
				<td>전공(*): </td>
				<td>
					<select name='smajor' value='<%= sMajor %>'>
						<option>선택안함</option>
						<option >=================</option>
						<option value = '화학과'>화학과</option>
						<option value = '컴퓨터공학과'>컴퓨터공학과</option>
						<option value = '전자공학과'>전자공학과</option>
						<option value = '실내디자인'>실내디자인</option>
						<option value = '경영학과'>경영학과</option>
						<option value = '복지학과'>복지학과</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td></td>
				<td>
				<input type='submit' value='가입' onclick='return validate()'>
				<input type='reset' value='초기화'></td>
			</tr>
			
			
		</table>
		
	</form>
	<footer>
		<h4>The HUMAN Computer | 수원캠퍼스 : 경기 수원시 팔달구 갓매산로 38  | 전화번호 : 000-0000-0000</h4>
	</footer>
</body>
</html>