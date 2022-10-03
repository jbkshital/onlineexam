package com.kiranacademy.onlineexam3;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {
	
	//REST - object's state
	// state transfer
	
	@RequestMapping("getStudent")
	public Student getStudent()
	{
		Student student=new Student();
		student.rno=1;
		student.marks=100;
		
		return student; // {"rno":1,"marks":100}
	}
	
	@RequestMapping("getRemainingTime")
	public int getRemainingTime(HttpServletRequest request)
	{
		HttpSession httpsession=request.getSession();
		httpsession.setAttribute("timeremaining",(Integer)httpsession.getAttribute("timeremaining")-1);
		System.out.println(" remaining time is " + httpsession.getAttribute("timeremaining"));
		return (int) httpsession.getAttribute("timeremaining");
	}

}
