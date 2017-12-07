package com.example.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

import com.example.entity.Account;
import com.example.entity.Basket;
import com.example.entity.Spot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mysql.fabric.xmlrpc.base.Member;

@Controller
public class MemberController {
	

	@RequestMapping(value = { "/registration" }, method = RequestMethod.GET)
	public  ModelAndView registration() {
		ModelAndView model = new ModelAndView("Member/registration");
		Account account = new Account();
		model.addObject("account", account);
		return model;
	}
	@RequestMapping(value = { "/registration" }, method = RequestMethod.POST)
	public  ModelAndView handleRegistration(@Valid @ModelAttribute Account account, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
	         if (bindingResult.hasErrors()) {
	         model = new ModelAndView("Member/registration");
	         return model;
	       }
	       model.addObject(account);

		return model;
	}
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView("Member/login");

		return model;
	}
	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	public ModelAndView update() {
		ModelAndView model = new ModelAndView("Member/update");

		return model;
	}

}
