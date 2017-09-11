package com.mattr.displaydate.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DisplayDateController {
	@RequestMapping("/")
	public String index() {
		return "index.jsp";
	}
	@RequestMapping("/date")
	public String date(Model model) {
		DateFormat dateFormat = new SimpleDateFormat("EEEEE,dd MMMMM, yyyy ");
		Date date = new Date();
		model.addAttribute("date",dateFormat.format(date));
		return "date.jsp";
	}
	@RequestMapping("/time")
	public String time(Model model) {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		Date date = new Date();
		model.addAttribute("time",dateFormat.format(date));
		return "time.jsp";
	}
}
