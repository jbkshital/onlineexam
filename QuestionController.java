package com.kiranacademy.onlineexam3;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionController {

	@RequestMapping("endexam")
	public ModelAndView endexam(HttpServletRequest request)
	{
		
		HttpSession httpsession=request.getSession(); // httpsession===>[username=jbk] HttpSession object
		HashMap<Integer,Answer> hashmap=(HashMap) httpsession.getAttribute("submittedDetails");
		
		Collection<Answer> allAnswers=hashmap.values();
		
		//scoe=2
		
		for (Answer answer : allAnswers) 
		{
			if(answer.originalAnswer.equals(answer.submittedAnswer))
			{
				httpsession.setAttribute("score",(Integer)httpsession.getAttribute("score")+1);
			}
		}
		
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("score");
		modelAndView.addObject("finalscore",httpsession.getAttribute("score"));
		modelAndView.addObject("allAnswers",allAnswers);
		
		httpsession.invalidate(); // all attributes from Session object is removed
		
		return modelAndView;
	}
	
	@RequestMapping("next")
	public ModelAndView next(HttpServletRequest request)
	{
		HttpSession httpsession=request.getSession(); // httpsession===>[username=jbk] HttpSession object
		
		Integer i=(Integer) httpsession.getAttribute("qno");//2
		int nextQno=i+1;//3
		
		List<Questions> list=(List<Questions>) httpsession.getAttribute("allquestions");
		
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("questions");
		
		if(nextQno<list.size())
		{
			Questions question=list.get(nextQno);
		
			modelAndView.addObject("question",question);
		
			httpsession.setAttribute("qno",nextQno);//2
		
		    HashMap<Integer,Answer> hashmap=(HashMap) httpsession.getAttribute("submittedDetails");
			Answer answer=hashmap.get(question.qno);
			
			String previousAnswer="";
			
			if(answer!=null)
			{
				previousAnswer=answer.getSubmittedAnswer();
			}
		
			modelAndView.addObject("previousAnswer",previousAnswer);
		}
		
		else
		{
			modelAndView.addObject("question",list.get(list.size()-1));
			modelAndView.addObject("message","click on previous button");
		}
		
		return modelAndView;
		
	}
	
	@RequestMapping("previous")
	public ModelAndView previous(HttpServletRequest request)
	{
		HttpSession httpsession=request.getSession(); 
		
		Integer i=(Integer) httpsession.getAttribute("qno");
		int previousQno=i-1;
		
		List<Questions> list=(List<Questions>) httpsession.getAttribute("allquestions");
		
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("questions");
		
		if(previousQno>=0)
		{
			Questions question=list.get(previousQno);
		
			modelAndView.addObject("question",question);
		
			httpsession.setAttribute("qno",previousQno);
			
			HashMap<Integer,Answer> hashmap=(HashMap) httpsession.getAttribute("submittedDetails");
			Answer answer=hashmap.get(question.qno);
			
			String previousAnswer="";
			
			if(answer!=null)
			{
				previousAnswer=answer.getSubmittedAnswer();
			}
		
			modelAndView.addObject("previousAnswer",previousAnswer);
		
			
		}
		
		else
		{
			modelAndView.addObject("question",list.get(0));
			modelAndView.addObject("message","click on next button");
		}
		
		return modelAndView;
		
	}
	
	// 1. Answer class object is created by spring using default constructor
	// Answer answer=new Answer()
	// answer==>[qno=0 question=null submittedAnswer=null originalAnswer=null]
	
	// answer.setQno(request.getParameter("qno"))
	// answer.setQuestion(request.getParameter("question"))
	// answer.setSubmittedAnswer(request.getParameter("submittedAnswer"))
	// answer.setoriginalAnswer(request.getParameter("originalAnswer"))
	
	@RequestMapping("saveResponse")
	public void saveResponse(Answer answer,HttpServletRequest request)
	{
		System.out.println(answer);
		
		HttpSession httpsession=request.getSession();
		List<Questions> list=(List<Questions>) httpsession.getAttribute("allquestions");
		
		// [[qno=1 question=---,answer="4"][qno=2 question=--- answer=8][qno=3]] List	
		// 
		for (Questions questions : list) 
		{
			
			if(questions.qno==answer.qno)
			{
				String ans=questions.answer;
				answer.setOriginalAnswer(ans);
				break;
			}
		}
		
		System.out.println(answer);
		
		HashMap<Integer,Answer> map=(HashMap) httpsession.getAttribute("submittedDetails");
		map.put(answer.qno, answer);
		
		System.out.println(map);
		
	}

}
