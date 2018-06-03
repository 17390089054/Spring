package com.wrf.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Student {
	private String stuName;
	private Integer stuAge;
	private String stuSex;
	private String stuGrade;
	private List<String>list;
	private Set<String>set;
	private Map<String,Object>map;
	
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public Integer getStuAge() {
		return stuAge;
	}
	public void setStuAge(Integer stuAge) {
		this.stuAge = stuAge;
	}
	public String getStuSex() {
		return stuSex;
	}
	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}
	public String getStuGrade() {
		return stuGrade;
	}
	public void setStuGrade(String stuGrade) {
		this.stuGrade = stuGrade;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public Set<String> getSet() {
		return set;
	}
	public void setSet(Set<String> set) {
		this.set = set;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	@Override
	public String toString() {
		return "Student [stuName=" + stuName + ", stuAge=" + stuAge + ", stuSex=" + stuSex + ", stuGrade=" + stuGrade
				+ ", list=" + list + ", set=" + set + ", map=" + map + "]";
	}
	
}
