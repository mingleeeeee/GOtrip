package com.example.controller;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	
	
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView openForm() {
       ModelAndView model = new ModelAndView("Index/index");
       return model;
    }
    @RequestMapping(value = {"/TourList"}, method = RequestMethod.GET)
   public ModelAndView TourList(){
    	ModelAndView model = new ModelAndView("Tour/TourList");
	   return model;
   }
    

}
