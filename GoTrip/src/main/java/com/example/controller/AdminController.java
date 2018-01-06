package com.example.controller;

import java.security.Principal;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.dao.AuthorityDAO;
import com.example.dao.MemberDAO;
import com.example.entity.Account;
import com.example.entity.Authority;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class AdminController {

	@Autowired
	MemberDAO memberDao;
	
	@Autowired
	AuthorityDAO authoDao;
	
	@RequestMapping(value = "/admin/memberList", method = RequestMethod.GET)
	public ModelAndView retrieveMember(){
		ModelAndView model = new ModelAndView("Admin/memberList");
		Iterable<Account> members = memberDao.findUsers();
		model.addObject("members", members);
		model.addObject("curName", getUserName());
		
		return model;
	}
	
	@RequestMapping(value = "/admin/memberUpdate", method = RequestMethod.POST)
	@ResponseBody
	public String updateMember(@RequestBody String json) throws JsonProcessingException{
		JSONObject jsonObj = new JSONObject(json);
		String username = jsonObj.get("username").toString();
		Account account = memberDao.findOne(username);
		//ObjectMapper mapper = new ObjectMapper();
		//String accountJson = mapper.writeValueAsString(account);
		String value = account.getUsername() +  "," + account.getName() + "," + 
					account.getEmail() + "," + account.getPhone();
		
		return value;
	}
	
	@RequestMapping(value = "/admin/updateMemberDb", method = RequestMethod.POST)
	public ModelAndView updateMemberDb(@ModelAttribute Account acc){
		ModelAndView model = new ModelAndView("redirect:/admin/memberList");
		Account account = memberDao.findOne(acc.getUsername());
		account.setEmail(acc.getEmail());
		account.setName(acc.getName());
		account.setPhone(acc.getPhone());
		memberDao.save(account);
		
		return model;
	}
	
	@RequestMapping(value = "/admin/memberDelete", method = RequestMethod.POST)
	public ModelAndView deleteMember(@RequestBody String json){
		ModelAndView model = new ModelAndView("redirect:/admin/memberList");
		JSONObject jsonObj = new JSONObject(json);
		String username = jsonObj.get("username").toString();
		memberDao.delete(username);
		
		return model;
	}
	
	public String getUserName(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		String name = memberDao.findOne(currentUserName).getName();
		
		return name;
	}
	
	@RequestMapping(value = "/admin/adminList", method = RequestMethod.GET)
	public ModelAndView retrieveAdmin(Principal principal){
		ModelAndView model = new ModelAndView("Admin/adminList");
		Iterable<Account> admins = memberDao.findAdmins();
		model.addObject("admins", admins);
		model.addObject("curName", getUserName());
		model.addObject("curUser", principal.getName());
		
		return model;
	}
	
	@RequestMapping(value = "/admin/updateAdminDb", method = RequestMethod.POST)
	public ModelAndView updateAdminDb(@ModelAttribute Account acc){
		ModelAndView model = new ModelAndView("redirect:/admin/adminList");
		Account account = memberDao.findOne(acc.getUsername());
		account.setEmail(acc.getEmail());
		account.setName(acc.getName());
		account.setPhone(acc.getPhone());
		memberDao.save(account);
		
		return model;
	}
	
	@RequestMapping(value = "/admin/createAdmin", method = RequestMethod.POST)
	public ModelAndView adminCreate(@ModelAttribute Account acc){
		ModelAndView model = new ModelAndView("redirect:/admin/adminList");
		System.out.println(acc.getUsername() + "," + acc.getEmail()+ "," +acc.getName()+ "," + acc.getPhone()+ "," +acc.getPassword());
		acc.setEnabled(true);
		memberDao.save(acc);
		Authority autho = new Authority();
		autho.setAccountAutho(acc);
		autho.setAuthority("ROLE_ADMIN");
		authoDao.save(autho);
		
		return model;
	}
}
