package cn.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

    private static final String SUCCESS = "success";

    @RequestMapping("/testModelAndView")
	public String testModelAndView(){
        return SUCCESS;
    }
}
