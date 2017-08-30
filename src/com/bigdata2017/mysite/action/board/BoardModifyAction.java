package com.bigdata2017.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bigdata2017.mysite.dao.BoardDao;
import com.bigdata2017.mysite.vo.BoardVo;
import com.bigdata2017.web.Action;
import com.bigdata2017.web.util.WebUtil;

public class BoardModifyAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Long no = Long.parseLong(request.getParameter("no"));
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setNo(no);
		
		new BoardDao().updateInfo(vo);
		WebUtil.redirect(request.getContextPath() + "/board?a=view&no=" + no + "&update=success", request, response);
	}
}