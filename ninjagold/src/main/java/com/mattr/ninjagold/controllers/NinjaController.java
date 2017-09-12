package com.mattr.ninjagold.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
//@SessionAttributes("gold")
//@SessionAttributes("activities")
public class NinjaController {
//	//Session Variables
//	@ModelAttribute("gold")
//    public int getGold(){
//        return 0;
//    }
//	@ModelAttribute("activities")
//    public ArrayList<String> getActivities(){
//		ArrayList<String> activities = new ArrayList<String>();
//		return activities;
//    }
	//Render page
	@RequestMapping("/")
	public String index(HttpSession session) {
		if(session.getAttribute("gold") == null) {
			session.setAttribute("gold",0);
		}
		if(session.getAttribute("activities") == null) {
			ArrayList<String> activities = new ArrayList<String>();
			session.setAttribute("activities",activities);
		}
		
		
		return "gold";
	}
	//perform action when submit clicked
	@PostMapping(path="/process_money/{building}")
	public String process(HttpSession session, @PathVariable String building) {//Model model, HttpSession session, @RequestParam(value="building") String building,@ModelAttribute("gold") int gold,@ModelAttribute("activities") ArrayList<String> activities) {
		Random random = new Random();
		int earnings = 0;
		DateFormat dateFormat = new SimpleDateFormat("MMMMM dd yyyy HH:mm a");
		Date date = new Date();
		String timestamp = dateFormat.format(date);
		int gold = (Integer)session.getAttribute("gold");
		ArrayList<String> activities = (ArrayList<String>)session.getAttribute("activities");
		if(!building.equals("casino")) {
			//which building?
			if(building.equals("farm")) {
				earnings = random.nextInt(20-10)+10;//(max-min)+min
			}else if(building.equals("cave")) {
				earnings = random.nextInt(10-5)+5;
			}else if(building.equals("house")) {
				earnings = random.nextInt(5-2)+2;
			}
		
			//Set output for whatever random earnings were made.
			activities.add(String.format("<p style='color:green'>" + "Earned %d gold from the %s!(%s)",earnings, building, timestamp)+ "</p>");
			//Increase gold
			int newGold = (Integer)session.getAttribute("gold") + earnings;
//			session.setAttribute("gold", newGold);
//			return "redirect:/";
		}else {
			if(gold<=0) {
				activities.add(String.format("<p style='color:red'>You're broke, you can't go to the casino...(%s)",timestamp)+ "</p>");
			}else {
				earnings = random.nextInt(50+50)-50;
				if(earnings >= 0) {
					activities.add(String.format("<p style='color:green'>" + "Earned %d gold from the %s!(%s)",earnings, building, timestamp)+ "</p>");
				}else {
					activities.add(String.format("<p style='color:red'>" + "Entered a %s  and lost %d gold.. Ouch..(%s)",building, earnings, timestamp)+ "</p>");
				}
//				//Earnings added/subtracted from gold
//				int newGold = (int)session.getAttribute("gold") + earnings;
//				session.setAttribute("gold", newGold);
			}
		}
		//Earnings added/subtracted from gold
		int newGold = (int)session.getAttribute("gold") + earnings;
		session.setAttribute("gold", newGold);
		session.setAttribute("activities", activities);
		return "redirect:/";
	}
	@RequestMapping("/reset")
	public String reset(HttpSession session) {
		session.setAttribute("gold", null);
		session.setAttribute("activities", null);
		return "redirect:/";
	}
}
