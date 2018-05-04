package cn.web;

import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.webapp.WebAppContext;

public class WebApplication {
	private static int DEFAULT_PORT = 8080;
	private static String DEFAULT_CONTEXT_PATH = "/";
    private static String WEBAPP_INDEX = "/webapp/";
    private static String DEFAULT_APP_CONTEXT_PATH = "/webapp/WEB-INF/web.xml";
    
    public static void main(String[] args) {
		new WebApplication().start();
		System.out.println("启动web服务成功...");
	}
    
    public void start(){
		// 服务器的监听端口 
		Server server = new Server(DEFAULT_PORT);
		WebAppContext context = new WebAppContext();
		
		context.addServlet(DefaultServlet.class, "*.js").setInitParameter("useFileMappedBuffer", "false");
		context.addServlet(DefaultServlet.class, "*.css");
		context.addServlet(DefaultServlet.class, "*.jpg");
		context.addServlet(DefaultServlet.class, "*.png");
		context.addServlet(DefaultServlet.class, "*.gif");
		context.setDescriptor(DEFAULT_APP_CONTEXT_PATH);
		
		URL baseUrl = System.class.getResource(WEBAPP_INDEX);
		if (baseUrl == null) {
			System.out.println("不能找到资源目录：".concat(WEBAPP_INDEX));
			System.exit(0);
		}
		
		try {
			// 设置Web内容上下文路径
			context.setResourceBase(baseUrl.toURI().toASCIIString());
		} catch (URISyntaxException e1) {
			System.out.println("设置web资源目录失败...");
			System.exit(0);
		}
		// 设置上下文路径
		context.setContextPath(DEFAULT_CONTEXT_PATH);
		context.setParentLoaderPriority(true);
		server.setHandler(context);
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
