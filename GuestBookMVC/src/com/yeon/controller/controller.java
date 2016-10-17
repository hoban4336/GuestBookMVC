package com.yeon.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yeon.guestbook.GuestBookDao;
import com.yeon.guestbook.GuestbookVo;

@WebServlet("/gb")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		if ("add".equals(action)) {

			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContent(content);
			
			GuestBookDao dao = new GuestBookDao();
			dao.insert(vo);

			response.sendRedirect("/GuestBookMVC/gb");
			
		} else if ("delete".equals(action)) {
			
			String num = request.getParameter("no");
			int no = Integer.parseInt(num);
			String password = request.getParameter("password");

			GuestbookVo gvo = new GuestbookVo();
			gvo.setNo(no);

			GuestBookDao dao = new GuestBookDao();
			dao.delete(gvo);

			response.sendRedirect("/GuestBookMVC/gb");
			
		} else if ("deleteform".equals(action)){
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/View/deleteform.jsp");
			rd.forward(request, response);
			
		}else{
			
			GuestBookDao dao = new GuestBookDao();
			List<GuestbookVo> list = dao.getlist();
			
			request.setAttribute("list", list);
	
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/View/index.jsp");
			rd.forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
