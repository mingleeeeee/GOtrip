package com.example.controller;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
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

import com.example.dao.HotSpotDAO;
import com.example.dao.MemberDAO;
import com.example.dao.SpotDAO;
import com.example.dao.TourDAO;
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
	TourDAO tourDao;

	@Autowired
	HotSpotDAO dao;
	
	@Autowired
	MemberDAO memberDao;
	
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public ModelAndView index() throws SQLException {
		Iterable<Hot> hot = dao.findAll();
		ModelAndView model = new ModelAndView("Index/index");
		model.addObject("hots", hot);

		return model;
	}
	
	@RequestMapping(value = { "/user/tourInfo" }, method = RequestMethod.GET)
	public ModelAndView tourInfo(@ModelAttribute("id") Long id, HttpSession session) {
		ModelAndView model = new ModelAndView("Tour/tourInfo");
		Tour tour = tourDao.findOne(id);
		ArrayList<Iterable> daysArr = new ArrayList<Iterable>();
		for (int i = 0; i < tour.getDays(); i++) {
			Iterable<Spot> spots = spotDao.findByTourAndDayOrderBySequenceAsc(
					tour, i + 1);
			daysArr.add(spots);
		}
	
		model.addObject("tourId", tour.getId());
		model.addObject("allDays", daysArr);
		model.addObject("curName", getUserName());

		return model;
	}

	@RequestMapping(value = { "/user/spotSearch" }, method = RequestMethod.GET)
	public ModelAndView spotSearch(@ModelAttribute("id") int tourId, HttpSession session) {
		ModelAndView model = new ModelAndView("Tour/spotSearch");
		Tour tour = tourDao.findOne(new Long(tourId));
		Iterable<Spot> spots = spotDao.findByTourAndDayOrderBySequenceAsc(tour, 1);
		model.addObject("spots", spots);
		model.addObject("tour", tour);

		ArrayList<Integer> dayList = new ArrayList<>();
		for (int i = 1; i <= tour.getDays(); i++)
			dayList.add(i);
		model.addObject("daysList", dayList);

		return model;
	}

	@RequestMapping(value = { "/user/saveBasket" }, method = RequestMethod.POST)
	public ModelAndView updateBasket(@RequestBody String json) throws Exception {
		ModelAndView model = new ModelAndView("redirect:/");
		JSONObject jsonObj = new JSONObject(json);
		Tour whichTour = tourDao.findOne(new Long(jsonObj.get("tourId").toString()));
		Integer thisDay = new Integer(jsonObj.get("thisDay").toString());
		basketSave(jsonObj, whichTour, thisDay);

		return model;
	}
	
	@RequestMapping(value = { "/user/retrieveNextSopts" }, method = RequestMethod.POST)
	@ResponseBody
	public List<Spot> nextSpots(@RequestBody String json) throws Exception {
		JSONObject jsonObj = new JSONObject(json);
		Integer nextDay = new Integer(jsonObj.get("nextDay").toString());
		Integer thisDay = new Integer(jsonObj.get("thisDay").toString());
		Tour whichTour = tourDao.findOne(new Long(jsonObj.get("tourId").toString()));
		basketSave(jsonObj, whichTour, thisDay);
		ArrayList<Spot> nextSpots = new ArrayList<Spot>();
		nextSpots = (ArrayList<Spot>) spotDao.findByTourAndDayOrderBySequenceAsc(whichTour, nextDay);

		return nextSpots;
	}

	private void basketSave(JSONObject jsonObj, Tour whichTour, Integer whichDay) throws Exception {;

		/* handle json string */
		Gson gson = new Gson();
		JSONArray jsonArr = jsonObj.getJSONArray("things");
		Type listType = new TypeToken<ArrayList<Spot>>(){}.getType();
		ArrayList<Spot> spots = gson.fromJson(jsonArr.toString(), listType);
		ArrayList<Spot> list = (ArrayList<Spot>) spotDao.findByTourAndDayOrderBySequenceAsc(whichTour, whichDay);
		
		for (int k = 0; k < list.size(); k++) {
			Spot spot1 = list.get(k);
			boolean isFind = false;
			
			for (int i = 0; i < spots.size(); i++) {
				Spot spot2 = spots.get(i);
				if (spot1.getId().intValue() == spot2.getId().intValue()) {
					spot1.setSequence(spot2.getSequence());
					spotDao.save(spot1);
					isFind = true;
					spots.remove(i);
					break;
				}
			}
			if (!isFind)
				spotDao.delete(spot1);
		}
		for (int j = 0; j < spots.size(); j++) {
			Spot s = spots.get(j);
			Spot newSpot = new Spot(s.getName(), s.getDay(), s.getPlaceId(), s.getSequence(), whichTour);
			spotDao.save(newSpot);
		}
	}

	@RequestMapping(value = "/user/route", method = RequestMethod.GET)
	public ModelAndView handleRoute() {
		ModelAndView model = new ModelAndView("Tour/direction");

		return model;
	}
	
	public String getUserName(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		String name = memberDao.findOne(currentUserName).getName();
		
		return name;
	}
	
}
