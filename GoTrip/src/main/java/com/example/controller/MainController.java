package com.example.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
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

import com.example.entity.Basket;
import com.example.entity.Spot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
public class MainController {

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public ModelAndView openForm() {
		ModelAndView model = new ModelAndView("Index/index");

		return model;
	}

	@RequestMapping(value = { "/TourList" }, method = RequestMethod.GET)
	public ModelAndView tourList() {
		ModelAndView model = new ModelAndView("Tour/tourList");

		return model;
	}

	@RequestMapping(value = { "/TourInfo" }, method = RequestMethod.GET)
	public ModelAndView tourInfo() {
		ModelAndView model = new ModelAndView("Tour/tourInfo");

		return model;
	}

	@RequestMapping(value = { "/SpotSearch" }, method = RequestMethod.GET)
	public ModelAndView spotSearch() {
		ModelAndView model = new ModelAndView("Tour/spotSearch");

		return model;
	}

	@RequestMapping(value = { "/SaveBasket" }, method = RequestMethod.POST)
	public ModelAndView basketSave(@RequestBody String json) throws Exception {
		ModelAndView model = new ModelAndView("redirect:/TourInfo");
		
		Gson gson = new Gson();
		JSONObject jsonObj = new JSONObject(json);
		JSONArray jsonArr = jsonObj.getJSONArray("things");
		Type listType = new TypeToken<ArrayList<Spot>>() {}.getType();
		ArrayList<Spot> spots = gson.fromJson(jsonArr.toString(), listType);
		//Spot aa = ((Spot)jsonArr.get(0)).
		for(int i = 0; i < spots.size(); i++)
			System.out.println(spots.get(i).getName() + " and " + spots.get(i).getPlaceId());

		return model;
	}
}
