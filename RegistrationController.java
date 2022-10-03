package com.kiranacademy.onlineexam3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController 
{
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("saveuserdata")
	public ModelAndView saveuserdata(Users users)
	{
		Session session=factory.openSession();
		
		Transaction tx=session.beginTransaction();
		
			session.save(users);
			
		tx.commit();
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("login");
		modelAndView.addObject("message","registration successful.do login now");
		return modelAndView;
	}

}
