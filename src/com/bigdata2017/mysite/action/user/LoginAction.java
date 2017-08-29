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

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo vo = new UserDao().get(email, password);
		
		// 인증 실패 시
		if (vo == null) {
			//(리다이렉트 방식)
			//WebUtil.redirect("mysite2/user?a=loginform&result=fail", request, response);
			
			//forward 방식
			WebUtil.forward("/WEB-INF/views/user/loginform_fail.jsp", request, response);
			return;
		}
		
		// 인증 처리(로그인 처리, 세션 처리)
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", vo);
		
		//main으로 리다이렉트
		WebUtil.redirect(request.getContextPath(), request, response);
	}
}
