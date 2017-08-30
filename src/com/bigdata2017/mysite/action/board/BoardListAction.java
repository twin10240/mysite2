package com.bigdata2017.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bigdata2017.mysite.dao.BoardDao;
import com.bigdata2017.mysite.vo.BoardVo;
import com.bigdata2017.web.Action;
import com.bigdata2017.web.util.WebUtil;

public class BoardListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page = request.getParameter("page");
		int rowNum = 0;
		
		if (page == null) {
			rowNum = 1;
		}else {
			rowNum = Integer.parseInt(page);
		}
		
		BoardDao dao = new BoardDao();
		List<BoardVo> list = dao.getList(rowNum);
		
		int count = dao.getListAll();
	    int startNum = ((rowNum-1)/5 ) * 5 + 1 ;
	    int nextPage = 0;
	    int prePage = 0;
	    
//	    System.out.println("startNum : " + startNum);
	    
		if ( (startNum + 4) * 5 < count ) {
	        nextPage = startNum + 5;
	     }
		
//		System.out.println("nextPage : " + nextPage);
		
		if (startNum > 5) {
			prePage = startNum - 5;
		}
		
//		System.out.println("prePage : " + prePage);
		
		request.setAttribute( "list", list );
		request.setAttribute( "rowNum", rowNum );

		// 페이지 관련
		request.setAttribute( "startNum", startNum );
		request.setAttribute("nextPage", nextPage);
		request.setAttribute("prePage", prePage);
		
		WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
	}
}