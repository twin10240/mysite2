package com.bigdata2017.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bigdata2017.mysite.dao.UserDao;
import com.bigdata2017.mysite.vo.UserVo;
import com.bigdata2017.web.Action;
import com.bigdata2017.web.util.WebUtil;

public class modifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if( session == null ) {
			WebUtil.redirect( request.getContextPath(), request, response);
			return;
		}

		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		if( authUser == null ) {
			WebUtil.redirect( request.getContextPath(), request, response);
			return;
		}
		
		Long no = Long.parseLong(request.getParameter("no"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		new UserDao().updateInfo(new UserVo(no, name, password, gender));
		
		authUser.setName(name);
		WebUtil.redirect(request.getContextPath() + "/user?a=modifyform&update=success", request, response);
	}
}