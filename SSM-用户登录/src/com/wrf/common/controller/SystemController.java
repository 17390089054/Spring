package com.wrf.common.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wrf.user.model.User;
import com.wrf.user.service.UserServices;

@Controller
@RequestMapping("/sys")
public class SystemController extends BaseController{
	@Autowired(required=false)
	private UserServices us;
	/**
	 * 跳转到登录页面 
	 * @return login.jsp
	 */
	@GetMapping("/login")
	public String login(){
		return "view/login";
	}
	
	/**
	 * 跳转到主页
	 * @return index.jsp
	 */
	@GetMapping("/index")
	public String Index(){
		return "view/index";
	}
	
	@PostMapping(value="/login")
	public String Login(@RequestParam("u")String account,String password,HttpSession session,ModelMap modelMap){
		try {
			User user = us.login(account, password);
			session.setAttribute("user", user);
			return "redirect:/sys/index";
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("account",account);
			modelMap.addAttribute("msg",e.getMessage());
			return "view/login";
		}
		
	}
	
	
	

}
