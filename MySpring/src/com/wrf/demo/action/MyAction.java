package com.wrf.demo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wrf.demo.service.DemoService;
import com.wrf.webmvc.annotation.MyAutoWired;
import com.wrf.webmvc.annotation.MyController;
import com.wrf.webmvc.annotation.MyRequestMapping;
import com.wrf.webmvc.annotation.MyRequestParam;

@MyController
@MyRequestMapping("/demo")
public class MyAction {
	@MyAutoWired
	private DemoService demoService;
	
	@MyRequestMapping("/query.*.json")
	public void query(HttpServletRequest request,HttpServletResponse response,@MyRequestParam("name") String name) {
		try {
			request.setCharacterEncoding("UTF-8");
			String result=demoService.get(name);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@MyRequestMapping("/add.json")
	public void add(HttpServletRequest request,HttpServletResponse response,@MyRequestParam("a") Integer a,@MyRequestParam("b")Integer b) {
		try {
			response.getWriter().write("result:\t"+a+"+"+b+"="+(a+b));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	@MyRequestMapping("/remove/*.json")
	public void remove(HttpServletRequest request,HttpServletResponse response,@MyRequestParam("id") Integer id) {
		
		
	}
	
	
	 
	
}
