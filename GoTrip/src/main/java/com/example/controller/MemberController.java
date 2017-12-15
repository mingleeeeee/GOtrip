package com.example.controller;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import antlr.collections.List;

import com.example.dao.MemberDAO;
import com.example.entity.Account;
import com.example.entity.Basket;
import com.example.entity.Spot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mysql.fabric.xmlrpc.base.Member;

@Controller
public class MemberController {
	
	@Autowired
	 MemberDAO dao;
	

	@RequestMapping(value = { "/registration" }, method = RequestMethod.GET)
	public  ModelAndView registration() {
		ModelAndView model = new ModelAndView("Member/registration");
		Account account = new Account();
		model.addObject("account", account);
		return model;
	}
	@RequestMapping(value = { "/registration" }, method = RequestMethod.POST)
		public  ModelAndView handleRegistration(@Valid @ModelAttribute Account account, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView("Index/index");
	         if (bindingResult.hasErrors()) {
	         model = new ModelAndView("Member/registration");
	         return model;
	       }
	         
	       dao.save(account);
	       model.addObject(account);

		return model;
	}
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView("Member/login");

		return model;
	}
	@RequestMapping("/login-error")
	 public String loginError(Model model) {
	  model.addAttribute("loginError", true);
	  return "Member/login";
	 }
	
	@RequestMapping(value = { "/update" }, method = RequestMethod.GET)
	public ModelAndView update() {
		ModelAndView model = new ModelAndView("Member/update");
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	      String userName = user.getUsername(); //get logged in username
	      Account account = dao.findOne(userName);
	       model.addObject(account);
	      
		  
		return model;
	}
	
	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	public ModelAndView processFormUpdate(@ModelAttribute Account account) throws SQLException{
		ModelAndView model = new ModelAndView("redirect:/");
	       dao.save(account);             
	       return model;
	}

}
