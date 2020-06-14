package com.team.housebackapi.controller;

import com.team.housebackapi.entity.Users;
import com.team.housebackapi.service.UsersService;
import com.team.housebackapi.util.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private UsersService usersService;
	@RequestMapping("regUser")
	public BaseResult regUser(Users users){
		//调用业务
		int temp=usersService.regUser(users);
		if(temp>0)
			return new BaseResult(BaseResult.RESULT_SUCCESS,"");
		else
			return new BaseResult(BaseResult.RESULT_FAIL,"出错啦!");
	}
	@RequestMapping("userLogin")
	public BaseResult userLogin(HttpSession session, String username, String password){
		//调用业务
		Users users=usersService.Login(username,password);
		if(users==null){
			return new BaseResult(BaseResult.RESULT_FAIL,"用户名密码不正确");
		}else
		{
			//只要登入请使用session保存登入人的信息
			session.setAttribute("logininfo",users);
			session.setMaxInactiveInterval(6000); //10分钟
			return new BaseResult(BaseResult.RESULT_SUCCESS,"");
		}
	}
}