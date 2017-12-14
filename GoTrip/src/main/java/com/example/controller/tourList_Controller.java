package com.example.controller;

import java.io.File;
import java.sql.SQLException;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.dao.TourDAO;
import com.example.entity.Tour;
import com.example.storage.StorageService;

@Controller
public class tourList_Controller {
	@Autowired
	TourDAO dao;

	private final StorageService storageService;

	@Autowired
	public tourList_Controller(StorageService storageService) {
		this.storageService = storageService;
	}

	@RequestMapping(value = "/tourListCreate", method = RequestMethod.GET)
	public ModelAndView openFormCreate() {
		ModelAndView model = new ModelAndView("Tour/tourListCreate");

		return model;
	}

	@RequestMapping(value = "/tourListCreate", method = RequestMethod.POST)
	public ModelAndView processFormCreate(@ModelAttribute Tour list) {
		ModelAndView model = new ModelAndView("redirect:/tourlistRetrieveAll");
		
		MultipartFile pic = list.getPhotoFile();
		//更改檔名後再儲存
		storageService.store(pic);
		list.setPhoto();// copy file name to the field photo

		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//String currentUserName = authentication.getname();
		//list.setAccount(); 等譽升
		dao.save(list);
		

		
		//model.addObject(list);
		return model;
	}

	@RequestMapping(value = { "/tourlistRetrieveAll" }, method = RequestMethod.GET)
	public ModelAndView retrieveTour() throws SQLException {

		Iterable<Tour> list = dao.findAll();
		ModelAndView model = new ModelAndView("Tour/tourList");
		model.addObject("tourlists", list);
		return model;
	}

	@RequestMapping(value = "/tourListUpdate", method = RequestMethod.GET)
	public ModelAndView processFormUpdate(@RequestParam(value="id", required=false, defaultValue="1") Long id) throws SQLException {
		ModelAndView model = new ModelAndView("Tour/tourListUpdate");

//		System.out.println("id = " + id);
		  Tour list = dao.findOne(id);
	       model.addObject("Tour",list);
	   
	       
		return model;
		
		
	}

	@RequestMapping(value = "/tourListUpdate", method = RequestMethod.POST)
	public ModelAndView processFormUpdate(@ModelAttribute Tour list) throws SQLException {
		ModelAndView model = new ModelAndView("redirect:/tourlistRetrieveAll");
		 if (!("").equals(list.getPhotoFile().getOriginalFilename())){
	           storageService.store(list.getPhotoFile());
	           list.setPhoto();//copy file name to the field photo
	       }
	dao.save(list);
		return model;
	}

	@RequestMapping(value = "/tourListDelete", method = RequestMethod.GET)
	public ModelAndView deleteTour(@RequestParam(value = "id", required = false, defaultValue = "1") Long id) {
		ModelAndView model = new ModelAndView("redirect:/tourlistRetrieveAll");
		dao.delete(id);
		return model;
	}

}
