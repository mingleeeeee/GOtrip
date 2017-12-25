package com.example.controller;

import java.security.Principal;
import java.sql.SQLException;
import javax.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.example.dao.AuthorityDAO;
import com.example.dao.CollectionDAO;
import com.example.dao.MemberDAO;
import com.example.dao.SpotDAO;
import com.example.dao.TourDAO;
import com.example.entity.Account;
import com.example.entity.Authority;
import com.example.entity.Collection;
import com.example.entity.Spot;
import com.example.entity.Tour;

@Controller
public class MemberController {
	
	@Autowired
	AuthorityDAO authoDao;
	
	@Autowired
	CollectionDAO colDao;
	
	@Autowired
	MemberDAO memberDao;
	
	@Autowired
	TourDAO tourDao;
	
	@Autowired
	SpotDAO spotDao;
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView("Member/loginAndRegister");
		Account account = new Account();
		model.addObject("account", account);

		return model;
	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		Account account = new Account();
		model.addAttribute("account", account);
		
		return "Member/loginAndRegister";
	}

	@RequestMapping(value = { "/registration" }, method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView model = new ModelAndView("Member/loginAndRegister");
		Account account = new Account();
		model.addObject("account", account);
		model.addObject("token", 1);
		
		return model;
	}

	@RequestMapping(value = { "/registration" }, method = RequestMethod.POST)
	public ModelAndView handleRegistration(@Valid @ModelAttribute Account account, 
			BindingResult bindingResult) {
		ModelAndView model = new ModelAndView("redirect:/");
		if (bindingResult.hasErrors()) {
			model = new ModelAndView("Member/loginAndRegister");
			model.addObject("token", 1);
			
			return model;
		}
		account.setEnabled(true);
		memberDao.save(account);
		Authority autho = new Authority();
		autho.setAuthority("ROLE_USER");
		autho.setAccountAutho(account);
		authoDao.save(autho);		
		model.addObject(account);

		return model;
	}

	/*@RequestMapping(value = { "/user/update" }, method = RequestMethod.GET)
	public ModelAndView update() {
		ModelAndView model = new ModelAndView("Member/update");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername(); // get logged in username
		Account account = memberDao.findOne(userName);
		model.addObject(account);

		return model;
	}

	@RequestMapping(value = { "/user/update" }, method = RequestMethod.POST)
	public ModelAndView processFormUpdate(@Valid @ModelAttribute Account account, BindingResult bindingResult) throws SQLException {
		ModelAndView model = new ModelAndView("redirect:/");
		if(bindingResult.hasErrors()) {
			model = new ModelAndView("Member/update");
			
			return model;
		}
		memberDao.save(account);
		
		return model;
	}
	*/
	@RequestMapping(value = "/user/updatePassword", method = RequestMethod.GET)
	public ModelAndView passwordUpdate(){
		ModelAndView model = new ModelAndView("Member/passwordUpdate");
		
		return model;
	}
	
	@RequestMapping(value = "/user/updatePassword", method = RequestMethod.POST)
	public ModelAndView saveNewPassword(@ModelAttribute("pwd2") String pwd, Principal principal){
		ModelAndView model = new ModelAndView("redirect:/");
		String username = principal.getName();
		Account account = memberDao.findOne(username);
		account.setPassword(pwd);
		memberDao.save(account);
		
		return model;
	}
	
	@RequestMapping(value = "/user/updateEmail", method = RequestMethod.GET)
	public ModelAndView emailUpdate(){
		ModelAndView model = new ModelAndView("Member/emailUpdate");
		
		return model;
	}
	
	@RequestMapping(value = "/user/updateEmail", method = RequestMethod.POST)
	public ModelAndView saveNewEmail(@ModelAttribute("email") String email, Principal principal){
		ModelAndView model = new ModelAndView("redirect:/");
		String username = principal.getName();
		Account account = memberDao.findOne(username);
		account.setEmail(email);
		memberDao.save(account);
		
		return model;
	}
	
	@RequestMapping(value = "/user/updateName", method = RequestMethod.GET)
	public ModelAndView nameUpdate(){
		ModelAndView model = new ModelAndView("Member/nameUpdate");
		
		return model;
	}
	
	@RequestMapping(value = "/user/updateName", method = RequestMethod.POST)
	public ModelAndView saveNewName(@ModelAttribute("memberName") String memberName, Principal principal){
		ModelAndView model = new ModelAndView("redirect:/");
		String username = principal.getName();
		Account account = memberDao.findOne(username);
		account.setName(memberName);
		memberDao.save(account);
		
		return model;
	}
	
	@RequestMapping(value = "/user/updatePhone", method = RequestMethod.GET)
	public ModelAndView phoneUpdate(){
		ModelAndView model = new ModelAndView("Member/phoneUpdate");
		
		return model;
	}
	
	@RequestMapping(value = "/user/updatePhone", method = RequestMethod.POST)
	public ModelAndView saveNewPhone(@ModelAttribute("phone") String phone, Principal principal){
		ModelAndView model = new ModelAndView("redirect:/");
		String username = principal.getName();
		Account account = memberDao.findOne(username);
		account.setPhone(phone);
		memberDao.save(account);
		
		return model;
	}
	
	@RequestMapping(value = "/user/collection", method = RequestMethod.GET)
	public ModelAndView collection(Principal principal){
		ModelAndView model = new ModelAndView("Member/collection");
		String username = principal.getName();
		Account account = memberDao.findOne(username);
		Iterable<Collection> cols = account.getCollectinos();
		model.addObject("cols", cols);
		model.addObject("tours", account.getTours());
		
		return model;
	}
	
	@RequestMapping(value = "/user/addCollection", method = RequestMethod.POST)
	public ModelAndView addToCollection(@RequestBody String json, Principal principal){
		ModelAndView model = new ModelAndView("redirect:/");
		JSONObject jsonObj = new JSONObject(json);
		String name = (String) jsonObj.get("name");
		String placeId = (String) jsonObj.get("placeId");
		String address = (String)jsonObj.get("address");
		Account acc = memberDao.findOne(principal.getName());
		
		Collection col = new Collection();
		col.setName(name);
		col.setPlaceId(placeId);
		col.setAccountCol(acc);
		col.setAddress(address);
		colDao.save(col);
			
		return model;
		
	}
	
	@RequestMapping(value = "/user/deleteCollection", method = RequestMethod.POST)
	public ModelAndView deleteFromCollection(@RequestBody String json){
		ModelAndView model = new ModelAndView("redirect:/");
		JSONObject jsonObj = new JSONObject(json);
		String id = (String)jsonObj.get("id");
		colDao.delete(Long.parseLong(id));
		
		return model;
	}
	
	@RequestMapping(value = "/user/saveToBasket", method = RequestMethod.POST)
	public ModelAndView saveColToBasket(@RequestBody String json){
		ModelAndView model = new ModelAndView("redirect:/");
		
		JSONObject jsonObj = new JSONObject(json);
		String arr[] = jsonObj.get("colList").toString().split(",");
		int day = Integer.parseInt(jsonObj.get("day").toString());
		Long tourId = Long.valueOf(jsonObj.get("tourId").toString());
		Tour tour = tourDao.findOne(tourId); 
		
		for(int i = 0; i < arr.length; i++){
			Collection col = colDao.findOne(new Long(arr[i]));
			Spot spot = new Spot();
			spot.setName(col.getName());
			spot.setDay(day);
			spot.setPlaceId(col.getPlaceId());
			spot.setTour(tour);
			spot.setSequence(20);
			spotDao.save(spot);
		}
		
		return model;
	}
}
