package com.fise.iot.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fise.iot.common.annotation.Authority;
import com.fise.iot.common.annotation.ControllerLog;
import com.fise.iot.common.pojo.AjaxResult;
import com.fise.iot.common.pojo.PageAjax;
import com.fise.iot.model.AuthRole;
import com.fise.iot.model.AuthUser;
import com.fise.iot.service.RoleService;
import com.fise.iot.service.UserService;

@Controller
@RequestMapping("/register/")
public class RegisterController extends BaseController {

	@Autowired
	private UserService userService;


	@ControllerLog("查询用户名是否存在")
	@RequestMapping("checkUserName")
	@ResponseBody
	public boolean queryPage(AuthUser user) {
		int count = userService.queryCount(user);
		if(count > 0)return false;
		return true;
	}


	@ControllerLog("注册用户")
	@RequestMapping("register")
	public String add(AuthUser user) {
		user.setUseable(1);
		user.setRoleid(26);
		userService.saveUser(user);
		return "common/success";
	}


}
