package com.bigdata2017.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bigdata2017.mysite.dao.BoardDao;
import com.bigdata2017.mysite.vo.BoardVo;
import com.bigdata2017.mysite.vo.UserVo;
import com.bigdata2017.web.Action;
import com.bigdata2017.web.util.WebUtil;

public class BoardWirteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		
		
		new BoardDao().newInsert(new BoardVo(title, content, authUser.getNo()));
		WebUtil.redirect( request.getContextPath() + "/board", request, response );
	}
}
