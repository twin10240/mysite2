package com.bigdata2017.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bigdata2017.web.Action;
import com.bigdata2017.web.util.WebUtil;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session != null) {
			// logout 처리
			session.removeAttribute("authUser");
			session.invalidate();
		}
		
		WebUtil.redirect(request.getContextPath(), request, response);
	}
}