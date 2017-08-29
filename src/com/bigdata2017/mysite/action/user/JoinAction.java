package com.bigdata2017.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bigdata2017.mysite.dao.UserDao;
import com.bigdata2017.mysite.vo.UserVo;
import com.bigdata2017.web.Action;
import com.bigdata2017.web.util.WebUtil;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter( "name" );
		String email = request.getParameter( "email" );
		String password = request.getParameter( "password" );
		String gender = request.getParameter( "gender" );
		
		System.out.println( name );
		System.out.println( email );
		System.out.println( password );
		System.out.println( gender );
		
		new UserDao().insert(new UserVo(name, password, email, gender));
		WebUtil.redirect(request.getContextPath() + "/user?a=joinsuccess", request, response);
	}

}
