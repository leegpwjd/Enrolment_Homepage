package org.university.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.university.dao.ListDAO;
import org.university.dao.OverlapDAO;
import org.university.dao.ScheduleDAO;
import org.university.dao.StudentDAO;
import org.university.model.Lecture;
import org.university.model.LectureSelect;
import org.university.model.Student;

/**
 * Servlet implementation class SignUpController
 */
@WebServlet("/university/*")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LectureSelect lecture = new LectureSelect();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] tokens = uri.split("/");
		String cmd = tokens[tokens.length - 1];

		if (cmd.equals("add")) {
			request.getRequestDispatcher("/WEB-INF/views/SignUpStudents.jsp").forward(request, response);
		} else if (cmd.equals("login")) {
			procLonInUpStudent(request, response);
		} else if (cmd.equals("home")) {
			request.getRequestDispatcher("/WEB-INF/views/Home.jsp").forward(request, response);
		} else if (cmd.equals("logout")) {
			procLogout(request, response);
		} else if (cmd.equals("enrolment")) {
			request.getRequestDispatcher("/WEB-INF/views/Enrolment.jsp").forward(request, response);
		} else if (cmd.equals("list")) {
			procList(request, response);
		} else if (cmd.equals("enrolmentList")) {
			procEnrolmentList(request, response);
		} else if (cmd.equals("schedule")) {
			procSchedule(request, response);
		}

	}

	private void procSchedule(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/Schedule.jsp").forward(request, response);
	}

	private void procList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] list = request.getParameterValues("list");
		String id = (String) request.getSession().getAttribute("id");

		ListDAO dao = new ListDAO();

	}

	private void procEnrolment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("college").equals("null") || request.getParameter("lsubject").equals("null")
				|| request.getParameter("grade").equals("null") || request.getParameter("department").equals("null")) {
			request.setAttribute("select", "error");
			request.setAttribute("selectCode", "select");
			request.setAttribute("lecture", lecture);

			procEnrolmentForm(request, response);
		} else {
			lecture.setCollege(request.getParameter("college"));
			lecture.setlSubject(request.getParameter("lsubject"));
			lecture.setGrade(Integer.parseInt(request.getParameter("grade")));
			lecture.setDepartment(request.getParameter("department"));

			request.setAttribute("selectCode", "select");
			request.setAttribute("lecture", lecture);

			procEnrolmentForm(request, response);
		}
		System.out.println(request.getParameter("college"));

	}

	private void procEnrolmentForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/views/Enrolment.jsp").forward(request, response);

	}

	private void procLogout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		request.getRequestDispatcher("/WEB-INF/views/Home.jsp").forward(request, response);
	}

	private void procLonInUpStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/LogInStudents.jsp").forward(request, response);

	}

	private void procSignUpStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Student student = new Student();

		student.setSid(request.getParameter("sid"));
		student.setSpw(request.getParameter("spw"));
		student.setSname(request.getParameter("sname"));
		student.setAge(Integer.parseInt(request.getParameter("age")));
		student.setAddress(request.getParameter("address"));
		student.setGender(request.getParameter("gender"));
		student.setSmajor(request.getParameter("smajor"));

		StudentDAO dao = new StudentDAO();
		if (dao.addStudent(student)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/Home.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("errorCode", "idDup");
			student.setSpw(null);
			request.setAttribute("student", student);
			procAddStudentForm(request, response);
		}
	}

	private void procAddStudentForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("action", "add");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/SignUpStudents.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] tokens = uri.split("/");
		String cmd = tokens[tokens.length - 1];

		if (cmd.equals("login")) {
			procLonInUpStudent(request, response);
		} else if (cmd.equals("add")) {
			procSignUpStudent(request, response);
		} else if (cmd.equals("loginTest")) {
			procLoginTest(request, response);
		} else if (cmd.equals("enrolment")) {
			procEnrolment(request, response);
		} else if (cmd.equals("addlist")) {
			procAddList(request, response);
		} else if (cmd.equals("cancel")) {
			procCancel(request, response);
		}else if (cmd.equals("cancel2")) {
			procCancel2(request, response);
		}

	}

	private void procEnrolmentList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/EnrolmentList.jsp").forward(request, response);

	}

	private void procCancel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String lCode = request.getParameter("cancel");
		String id = (String) request.getSession().getAttribute("id");

		ListDAO dao = new ListDAO();

		if (dao.Delete(lCode, id)) {
			request.setAttribute("selectCode", "select");
			request.setAttribute("lecture", lecture);
			procEnrolmentForm(request, response);
		}
	}
	private void procCancel2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String lCode = request.getParameter("cancel");
		String id = (String) request.getSession().getAttribute("id");

		ListDAO dao = new ListDAO();

		if (dao.Delete(lCode, id)) {
			request.setAttribute("selectCode", "select");
			request.setAttribute("lecture", lecture);
			procEnrolmentList(request, response);
		}
	}

	private void procAddList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String lCode = request.getParameter("apply");
		String id = (String) request.getSession().getAttribute("id");

		OverlapDAO overdao = new OverlapDAO();

		ListDAO dao = new ListDAO();
		if (!(overdao.overlap(id, lCode))) {
			if (dao.list(lCode, id)) {
				request.setAttribute("selectCode", "select");
				request.setAttribute("lecture", lecture);
				procEnrolmentForm(request, response);
			} else {
				request.setAttribute("overlap", "fail");
				request.setAttribute("selectCode", "select");
				request.setAttribute("lecture", lecture);
				procEnrolmentForm(request, response);
			}
		}else {
			request.setAttribute("overlap", "fail");
			request.setAttribute("selectCode", "select");
			request.setAttribute("lecture", lecture);
			procEnrolmentForm(request, response);
		}

	}

	private void procLoginTest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sId = (request.getParameter("sid"));
		String sPw = (request.getParameter("spw"));

		StudentDAO dao = new StudentDAO();
		if (dao.LoginTestStudent(sId, sPw)) {

			HttpSession session = request.getSession();

			session.setAttribute("login", "success");
			session.setAttribute("id", sId);

			request.getRequestDispatcher("/WEB-INF/views/Home.jsp").forward(request, response);

		} else {
			request.setAttribute("errorCode", "idDup");
			procLonInUpStudent(request, response);
		}
	}

}
