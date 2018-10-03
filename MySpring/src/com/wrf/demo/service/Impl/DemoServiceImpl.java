package com.wrf.demo.service.Impl;

import com.wrf.demo.service.DemoService;
import com.wrf.webmvc.annotation.MyService;

@MyService
public class DemoServiceImpl implements DemoService {

	@Override
	public String get(String name) {
		return "My name is "+name;
	}

}
