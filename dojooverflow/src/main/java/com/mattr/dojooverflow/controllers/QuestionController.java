package com.mattr.dojooverflow.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mattr.dojooverflow.models.Answer;
import com.mattr.dojooverflow.models.Question;
import com.mattr.dojooverflow.models.Tag;
import com.mattr.dojooverflow.services.QAndAService;
import com.mattr.dojooverflow.services.TagService;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	private QAndAService service;
	private TagService tService;
	public QuestionController(QAndAService service, TagService tService) {
		this.service = service;
		this.tService = tService;
	}
	
	@RequestMapping("")//dashboard
	public String index(Model model) {
		ArrayList<Question> questions = (ArrayList<Question>)service.allQuestions();
		model.addAttribute("questions", questions);
		return "dash";
	}
	
	//Open Question form "new"
	@RequestMapping("new")
	public String viewForm() {
		return "newQuestion";
	}
	
	//Post new question "new"
	@PostMapping("new")
	public String postQuestion(@RequestParam(value="tags") String newTags,@RequestParam(value="question") String question, RedirectAttributes redirectAttributes) {
		if(question.length() < 5) {
			//flash message error
			redirectAttributes.addFlashAttribute("errors", "There must be a question at least 5 characters long.");
			return "newQuestion";
		}else {
			Question newQ = new Question(question);
			Question saved = service.addQ(newQ);
			List<Tag> qTags = tService.generateTags(newTags);
			if(qTags != null) {
				saved.setTags(qTags);
				service.addQ(saved);
			}
			return "redirect:/questions";
		}
	}
	
	//Open Question Page "{id}"
	@RequestMapping("{id}")
	public String viewQ(Model model,@PathVariable("id") Long id,@Valid @ModelAttribute("answer") Answer answer, BindingResult result) {
		Question question = service.questionAt(id);
		model.addAttribute("question", question);
		return "viewQuestion";
	}
	
	
	//Post an answer for that question "{id}"
	@PostMapping("{id}")
	public String addAnswer(@PathVariable("id") Long id,@RequestParam(value="answer") String answerStr) {
		if(answerStr.length()<1) {
			return "redirect:/questions/"+id;
		}else {
			Question question = service.questionAt(id);
			Answer answer = new Answer(answerStr);
			answer.setQuestion(question);
			service.addAnswer(answer);
			return "redirect:/questions/"+id;
		}
//		System.out.println("Post this bitch");
//		//		Question question = service.questionAt(id);
////		answer.setQuestion(question);
////		if(result.hasErrors()) {
////			System.out.println(result.getAllErrors());
//			return "redirect:/question/"+id;
////		}else {
////			service.addAnswer(answer);
////			return "redirect:/question/"+id;
////		}
////		model.addAttribute("question", question);
		
	}
	
}
