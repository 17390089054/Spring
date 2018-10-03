package com.wrf.demo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wrf.demo.service.DemoService;



public class TwoAction {
	private DemoService demoService;
	
	public void edit(HttpServletRequest request,HttpServletResponse response, String name) {
		String result=demoService.get(name);
		try {
			response.getWriter().write(result);;
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
