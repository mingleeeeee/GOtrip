package com.example.controller;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.dao.HotSpotDAO;
import com.example.entity.Hot;
import com.example.storage.StorageService;

@Controller
public class HotSpotController {
  @Autowired
 HotSpotDAO dao;
  private final StorageService storageService;

  @Autowired
  public HotSpotController(StorageService storageService) {
      this.storageService = storageService;
  }
   
 @RequestMapping(value = {"/HotRead",}, method = RequestMethod.GET)
    public ModelAndView HotRead() throws SQLException{
    Iterable<Hot> hots = dao.findAll();
       ModelAndView model = new ModelAndView("Hotspot/HotRead");
       model.addObject("hots",hots);
       return model;
    }
 //HotRead2是給一般使用者看的,無修改功能
 @RequestMapping(value = {"/HotRead2",}, method = RequestMethod.GET)
 public ModelAndView HotRead2() throws SQLException{
 Iterable<Hot> hots = dao.findAll();
    ModelAndView model = new ModelAndView("Hotspot/HotRead2");
    model.addObject("hots",hots);
    return model;
 }
 
    @RequestMapping(value = "/HotUpdate2", method = RequestMethod.GET)
    public ModelAndView openFormUpdate(@Valid @RequestParam(value="id", required=false, defaultValue="1") Long id) {
    ModelAndView model = new ModelAndView("Hotspot/HotUpdate");
    Hot hot = dao.findOne(id);
    model.addObject("hot", hot);
    
    return model;
 }

    
    @RequestMapping(value = "/HotUpdate2", method = RequestMethod.POST)
    public ModelAndView processFormUpdate(@Valid @ModelAttribute Hot hot, BindingResult bindingResult) throws SQLException {
       ModelAndView model = new ModelAndView("redirect:/HotRead");
       if (bindingResult.hasErrors()){
    	   model = new ModelAndView("Hotspot/HotRead");
       }
       //if photo is updated
       if (!("").equals(hot.getPhotoFile().getOriginalFilename())){
           storageService.store(hot.getPhotoFile());
           hot.setPhoto();//copy file name to the field photo
       }
       dao.save(hot);             

       return model;
    }
    /*
    @RequestMapping(value = "/HotUpdate2", method = RequestMethod.POST)
    public ModelAndView processFormUpdate(@ModelAttribute Hot hot) throws SQLException {
       ModelAndView model = new ModelAndView("redirect:/HotRead");
       dao.save(hot);             
       return model;
    }*/
    
    

    @RequestMapping(value = "/readbyid", method = RequestMethod.GET)
    public ModelAndView openFormUpdate2(@RequestParam(value="id", required=false, defaultValue="1") Long id) {
    ModelAndView model = new ModelAndView("Hotspot/Readbyid");
    
    Hot hot = dao.findOne(id);
    model.addObject("hot", hot);
    
    return model;
 }
    
    
 
}