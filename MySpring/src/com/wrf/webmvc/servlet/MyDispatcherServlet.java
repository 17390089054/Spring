package com.wrf.webmvc.servlet;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wrf.webmvc.annotation.MyAutoWired;
import com.wrf.webmvc.annotation.MyController;
import com.wrf.webmvc.annotation.MyRequestMapping;
import com.wrf.webmvc.annotation.MyRequestParam;
import com.wrf.webmvc.annotation.MyService;

public class MyDispatcherServlet extends HttpServlet{

	private Properties contextConfig=new Properties();
	private List<String> classNames=new ArrayList<String>();
	private Map<String,Object> ioc=new HashMap<String,Object>();
	//private Map<String, Method> handlerMapping=new HashMap<String,Method>();
	private List<Handler>handlerMapping=new ArrayList<Handler>();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//6 调用Dispatcher方法
		try {
			doDispatch(req,resp);
		}catch (Exception e) {
			//匹配出现异常则打印异常
			resp.getWriter().write("500 Exception:\n"+
			Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]", "").replaceAll(",\\s", "\r\n"));
		}  
	}

	/*private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//执行反射
		String url = req.getRequestURI();
		String contextPath = req.getContextPath();
		url=url.replace(contextPath, "").replaceAll("/+","/");
		System.out.println("url: "+url);
		if(!handlerMapping.containsKey(url)) {
			resp.getWriter().write("404 not Found!");
			return;
		}
		Method method=this.handlerMapping.get(url);
		System.out.println("Method:"+method);
	
	}*/
	
	

	private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		try {
			Handler handler=getHandler(req);
			//没有匹配上则返回404
			if(handler==null) {
				resp.getWriter().write("404 not found!");
				return;
			}
			
			//保存的方法参数列表
			Class<?>[] parameterTypes = handler.method.getParameterTypes();
			//保存所有参数自动赋值所需要的值
			Object[] parameterValues=new Object[parameterTypes.length];
			
			Map<String,String[]>params=req.getParameterMap();
			for(Map.Entry<String, String[]>param : params.entrySet()) {
				String value=Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");
				//如果找到匹配对象则开始填充参数值
				if(!handler.paramIndexMapping.containsKey(param.getKey())) {continue;}
				int index=handler.paramIndexMapping.get(param.getKey());
				parameterValues[index]=convert(parameterTypes[index],value);
			}
			//设置方法中的request和response对象
			int reqIndex = handler.paramIndexMapping.get(HttpServletRequest.class.getName());
			parameterValues[reqIndex]=req;
			int respIndex = handler.paramIndexMapping.get(HttpServletResponse.class.getName());
			parameterValues[respIndex]=resp;
			
			handler.method.invoke(handler.controller, parameterValues);					
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		//1.读取配置文件
		doLoadConfig(config.getInitParameter("contextConfigLocation"));
		//2 扫描所有相关的包
		doScanner(contextConfig.getProperty("scanPackage"));
		//3 加载相应的类并加入到IOC容器中
		doInstance();
		//4 自动注入
		doAutoWired();
		//5 初始化HandleMapping
		initHandleMapping();
		System.out.println("MySpringMVC 初始化完成");
	}
	
	//初始化映射
	private void initHandleMapping() {
		if(ioc.isEmpty()) return;
		for(Map.Entry<String, Object>entry:ioc.entrySet()) {
			Class<?> clazz = entry.getValue().getClass();
			if(!clazz.isAnnotationPresent(MyController.class)) continue;
			String baseUrl="";
			if(clazz.isAnnotationPresent(MyRequestMapping.class)) {
				MyRequestMapping requestMapping = clazz.getAnnotation(MyRequestMapping.class);
				baseUrl=requestMapping.value();
			}
			//Spring只认public方法
			Method[] methods = clazz.getMethods();
			for(Method method:methods) {
				if(!method.isAnnotationPresent(MyRequestMapping.class)) continue;
				MyRequestMapping requestMapping = method.getAnnotation(MyRequestMapping.class);
				String regex=(baseUrl+"/"+requestMapping.value()).replaceAll("/+", "/");
				//handlerMapping.put(url, method);
				Pattern pattern=Pattern.compile(regex);
				handlerMapping.add(new Handler(pattern,entry.getValue(),method));
				System.out.println("Mapping:"+regex+"---->"+method);
			}
		}
		
	}


	//DI 自动赋值
	private void doAutoWired() {
		if(ioc.isEmpty()) return;
		for(Map.Entry<String, Object> entry:ioc.entrySet()) {
			Field[] fields = entry.getValue().getClass().getDeclaredFields();
			for(Field field:fields) {
				if(!field.isAnnotationPresent(MyAutoWired.class))continue;
				MyAutoWired autoWired = field.getAnnotation(MyAutoWired.class);
				String beanName=autoWired.value();
				if("".equals(beanName.trim())) {
					beanName=field.getType().getName();
				}
				//确定注入
				field.setAccessible(true);
				try {
					field.set(entry.getValue(),ioc.get(beanName));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
					continue;
				}
			}
		}
	}

	
	//IOC容器初始化
	private void doInstance() {
		if(classNames.isEmpty()) return ;
		try {
			for(String className:classNames) {
				//映射加载相应的类
				Class<?> clazz = Class.forName(className);
				if(clazz.isAnnotationPresent(MyController.class)) {
					Object instance = clazz.newInstance();
					String beanName=lowerFirstCase(clazz.getSimpleName());
					ioc.put(beanName, instance);
				}else if(clazz.isAnnotationPresent(MyService.class)) {
					//1 默认类名首字母小写
					//2 优先使用自定义类名
					MyService service = clazz.getAnnotation(MyService.class);
					String beanName = service.value();
					if("".equals(beanName)) {
						beanName=lowerFirstCase(clazz.getSimpleName()); 
					}
					Object instance = clazz.newInstance();
					ioc.put(beanName, instance);
					//3 如果是接口,则注入他的实现类
					Class<?>[] interfaces = clazz.getInterfaces();	
					for(Class<?> i:interfaces) {
						ioc.put(i.getName(), instance);
					}
				}else {
					//忽略继续
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//类名转换为首字母小写
	private String lowerFirstCase(String simpleName) {
		char[] array=simpleName.toCharArray();
		array[0]+=32;
		return String.valueOf(array);
	}

	//扫描相应的包
	private void doScanner(String scanPacakage) {
		URL url = this.getClass().getClassLoader().getResource("/"+scanPacakage.replaceAll("\\.","/"));
		File classpathDir=new File(url.getFile());
		for(File file:classpathDir.listFiles() ) {
			if(file.isDirectory()) {
				doScanner(scanPacakage+"."+file.getName());
			}else {
				String className=scanPacakage+"."+file.getName().replace(".class","");
				classNames.add(className);
			}
		}
	}
	
	//读取加载配置文件
	private void doLoadConfig(String contextConfigLocation) {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
		try {
			contextConfig.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(null !=is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private Handler getHandler(HttpServletRequest req) throws Exception {
		if(handlerMapping.isEmpty()) {return null;}
		
		String url=req.getRequestURI();
		String contextPath=req.getContextPath();
		url=url.replace(contextPath,"").replaceAll("/+", "/");
		for(Handler handler:handlerMapping) {
			try {
				Matcher matcher=handler.pattern.matcher(url);
				//如果没有匹配上继续下一个匹配
				if(!matcher.matches()) {continue;}
				
				return handler;
				
			}catch(Exception e) {
				throw e;
			}
		}
		return null;
	}
	
	
	
	
	private Object convert(Class<?>type,String value) {
		if(Integer.class==type) {
			return Integer.valueOf(value);
		}
		return value;
	}
	
	
	
	
	private class Handler{
   
		//保存方法对应的实例
		protected Object controller;
		//保存映射的方法
		protected Method method;
		protected Pattern pattern;
		//参数顺序
		protected Map<String,Integer> paramIndexMapping;
		
		/**
		 * 构造一个Handler基本参数
		 * @param pattern
		 * @param controller
		 * @param method
		 */
		protected Handler(Pattern pattern,Object controller,Method method) {
			this.controller=controller;
			this.method=method;
			this.pattern=pattern;
			
			paramIndexMapping=new HashMap<String,Integer>();
			putParamIndexMapping(method);
		}

		private void putParamIndexMapping(Method method) {
			
			//提取方法中加了注解的参数
			Annotation[][] pa=method.getParameterAnnotations();
			for(int i=0;i<pa.length;i++) {
				for(Annotation a:pa[i]) {
					if(a instanceof MyRequestParam) {
						String paramName=((MyRequestParam) a).value();
						if(!"".equals(paramName.trim())){
							paramIndexMapping.put(paramName, i);
						}
					}
					
				}
				
			}
			
			//提取方法中的request和response参数
			Class<?>[]paramTypes=method.getParameterTypes();
			for(int i=0;i<paramTypes.length;i++) {
				Class<?>type=paramTypes[i];
				if(type==HttpServletRequest.class||type==HttpServletResponse.class) {
					paramIndexMapping.put(type.getName(),i);
				}
				
			}
		}
		
		@Override
		public String toString() {
			return "Handler [controller=" + controller + ", method=" + method + ", pattern=" + pattern
					+ ", paramIndexMapping=" + paramIndexMapping + "]";
		}
		
		
	}
	
	

}
