package com.example.controller;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.assertj.core.util.Lists;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
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

import com.example.dao.HotSpotDAO;
import com.example.dao.SpotDAO;
import com.example.dao.TourDAO;
import com.example.entity.Basket;
import com.example.entity.Hot;
import com.example.entity.Spot;
import com.example.entity.Tour;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
public class MainController {

	@Autowired
	SpotDAO spotDao;
	
	@Autowired
	Basket basket;
	
	@Autowired
	TourDAO tourDao;
	@Autowired
	 HotSpotDAO dao;
	
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public ModelAndView index() throws SQLException{
    Iterable<Hot> hot = dao.findAll();
       ModelAndView model = new ModelAndView("index/index");
       model.addObject("hots",hot);
       return model;
    }
	
	@RequestMapping(value = { "/TourList" }, method = RequestMethod.GET)
	public ModelAndView tourList() {
		ModelAndView model = new ModelAndView("Tour/tourList");

		return model;
	}

	@RequestMapping(value = { "/TourInfo" }, method = RequestMethod.GET)
	public ModelAndView tourInfo(/*get tour id*/HttpSession session){
		ModelAndView model = new ModelAndView("Tour/tourInfo");
		
		Tour tour = tourDao.findOne(new Long(1));
		ArrayList<Iterable> daysArr = new ArrayList<Iterable>();
		for(int i = 0; i < tour.getDays(); i++){
			Iterable<Spot> spots = spotDao.findByTourAndDayOrderBySequenceAsc(tour, i+1);
			daysArr.add(spots);
		}
		session.setAttribute("whichTour", 1);
		model.addObject("alldays", daysArr);

		return model;
	}
	
	
	@RequestMapping(value = { "/SpotSearch" }, method = RequestMethod.GET)
	public ModelAndView spotSearch(@RequestParam(value="whichDay", required=false, defaultValue="1") 
					Integer whichDay, HttpSession session) {
		ModelAndView model = new ModelAndView("Tour/spotSearch");
		Tour tour = tourDao.findOne(new Long(1));
		Iterable<Spot> spots = spotDao.findByTourAndDayOrderBySequenceAsc(tour, whichDay);
		model.addObject("spots", spots);
		session.setAttribute("whichDay", whichDay);
		basket.setSpots((ArrayList<Spot>)spots);
		
		return model;
	}
	
	
	
	@RequestMapping(value = { "/SaveBasket" }, method = RequestMethod.POST)
	public ModelAndView basketSave(@RequestBody String json, HttpSession session) throws Exception {
		ModelAndView model = new ModelAndView("redirect:/TourInfo");
		
		/*handle json string*/
		Gson gson = new Gson();
		JSONObject jsonObj = new JSONObject(json);
		JSONArray jsonArr = jsonObj.getJSONArray("things");
		Type listType = new TypeToken<ArrayList<Spot>>() {}.getType();
		ArrayList<Spot> spots = gson.fromJson(jsonArr.toString(), listType);
		ArrayList<Spot> list = (ArrayList<Spot>)basket.getSpots();
		int whichDay = (int)session.getAttribute("whichDay");
		Tour whichTour = tourDao.findOne(new Long(session.getAttribute("whichTour").toString()));
		
		for(int k = 0; k < list.size(); k++){
			Spot spot1 = list.get(k);
			boolean isFind = false;
			for(int i = 0; i < spots.size(); i++){
				Spot spot2 = spots.get(i);
				
				if(spot1.getId().intValue() == spot2.getId().intValue()){
					spot1.setSequence(spot2.getSequence());
					spotDao.save(spot1);
					isFind = true;
					spots.remove(i);
					break;
				}
			}
			if(!isFind)
				spotDao.delete(spot1);
		}
		for(int j = 0; j < spots.size(); j++){
			Spot s = spots.get(j);
			Spot newSpot = new Spot(s.getName(), whichDay, s.getPlaceId(), s.getSequence(), whichTour);
			spotDao.save(newSpot);
		}
		
		return model;
	}
	
}
