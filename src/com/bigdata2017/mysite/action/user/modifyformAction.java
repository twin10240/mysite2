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

public class modifyformAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo vo = (UserVo)session.getAttribute("authUser");
		
		Long no = vo.getNo();
		
		UserVo info = new UserDao().get(no);
		request.setAttribute("userInfo", info);
		System.out.println(info);
		
		WebUtil.forward("/WEB-INF/views/user/modifyform.jsp", request, response);
	}
}