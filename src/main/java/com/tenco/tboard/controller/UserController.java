package com.tenco.tboard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.tenco.tboard.model.User;
import com.tenco.tboard.repository.UserRepositoryImpl;
import com.tenco.tboard.repository.interfaces.UserRepository;


@WebServlet("/user/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserRepository userRepository;
	
	@Override
	public void init() throws ServletException {
		userRepository = new UserRepositoryImpl();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getPathInfo();
		
		switch (action) {
		case "/signup": 
			request.getRequestDispatcher("/WEB-INF/views/user/signup.jsp").forward(request, response);
			break;
		case "/signin":
			request.getRequestDispatcher("/WEB-INF/views/user/signin.jsp").forward(request, response); 
			break;
		case "/logout":
			handleLogout(request, response);
			break;
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			break;
		}
		
	}
	
	/**
	 * 로그아웃 기능 처리 
	 * @throws IOException 
	 * http://localhost:8080/t-board/user/logout
	 */
	private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/user/signin");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		switch (action) {
		case "/signup": 
			handleSignup(request, response);
			break;
		case "/signin":
			 handleSignin(request, response); 
			break;
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			break;
		}
		
	}


	private void handleSignin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User principal = userRepository.getUserByUsernameAndPassword(username, password);
		
		if(principal != null && principal.getPassword().equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("principal", principal);
			response.sendRedirect(request.getContextPath() + "/board/list");
		} else {
			request.setAttribute("errorMessage", "잘못된 요청입니다.");
			request.getRequestDispatcher("/WEB-INF/views/user/signin.jsp").forward(request, response);
		}
	}


	private void handleSignup(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 데이터 추출 
		String username = request.getParameter("username") ; 
		String password = request.getParameter("password") ; 
		String email = request.getParameter("email");
		
		// 데이터 유효성 검사 생략 
		
		User user = User.builder()
					.username(username)
					.password(password)
					.email(email)
					.build();
		// TODO 삭제 예정 
		System.out.println(user);
		int result = userRepository.addUser(user);
		if(result != 0) {
			response.sendRedirect(request.getContextPath() + "/user/signin");
		} else {
			 response.setContentType("text/html; charset=UTF-8");
			 PrintWriter out = response.getWriter();
			 out.println("<script> alert('잘못된 요청입니다'); history.back();  </script>");
		}
	}
}