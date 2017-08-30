package com.bigdata2017.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bigdata2017.mysite.dao.BoardDao;
import com.bigdata2017.web.Action;
import com.bigdata2017.web.util.WebUtil;

public class BoarddeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));
		
		new BoardDao().delete(no);
		
		WebUtil.redirect(request.getContextPath() + "/board?&delete=success", request, response);
	}
}
