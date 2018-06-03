package com.wrf.test;

import org.junit.Test;

public class Test01 {
	 class A{
		public A(){
			System.out.println("==This is A==");
		}

		public Object aaa(){
			Object object=new Object();
			return object;
		}
		
		public  <T>T aaa(Class<T> t){
			Object object=null;
			try {
				
				object=t.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return (T) object;
		}
		
	}
	
	class B{
		public B(){
			System.out.println("==This is B==");
		}
	}
	
	@Test
	public void test(){
		//B b=(B)new A().aaa();
		//System.out.println(b);
		
		B b2=new A().aaa(B.class);
		System.out.println(b2);
	}
	
}
