package cn.web.utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServiceTools {
	private static final Logger log = LoggerFactory.getLogger(ServiceTools.class);

    public static void mapToJson(HttpServletResponse response, Map<String, Object> map){
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        try{
            ObjectMapper om = new ObjectMapper();
            PrintWriter pw = response.getWriter();
            pw.write(om.writeValueAsString(map));
            pw.flush();
            pw.close();
        }catch(IOException e){
            log.error("转化Json时发生异常，返回Json数据失败...");
        }
    }
    
    public static void objectToJson(HttpServletResponse response, Object obj){
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        try{
            ObjectMapper om = new ObjectMapper();
            PrintWriter pw = response.getWriter();
            pw.write(om.writeValueAsString(obj));
            pw.flush();
            pw.close();
        }catch(IOException e){
            log.error("转化Json时发生异常，返回Json数据失败...");
        }
    }
    
    
}
