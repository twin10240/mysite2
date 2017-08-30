package com.bigdata2017.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bigdata2017.mysite.dao.BoardDao;
import com.bigdata2017.mysite.vo.BoardVo;
import com.bigdata2017.web.Action;
import com.bigdata2017.web.util.WebUtil;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*HttpSession session = request.getSession();
		if( session == null ) {
			WebUtil.redirect( request.getContextPath(), request, response);
			return;
		}

		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		if( authUser == null ) {
			WebUtil.redirect( request.getContextPath(), request, response);
			return;
		}*/
		
		Long no = Long.parseLong(request.getParameter("no"));
		BoardVo vo = new BoardDao().getView(no);
		
		request.setAttribute("vo", vo);
		WebUtil.forward( "/WEB-INF/views/board/view.jsp", request, response);
	}
}