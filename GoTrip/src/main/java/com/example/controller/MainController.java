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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	//導向首頁
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public ModelAndView index() throws SQLException {
		Iterable<Hot> hot = dao.findAll();
		ModelAndView model = new ModelAndView("Index/index");
		model.addObject("hots", hot);

		return model;
	}
	
	//導向旅遊提籃(行程表)
	@RequestMapping(value = { "/user/tourInfo" }, method = RequestMethod.GET)
	public ModelAndView tourInfo(@ModelAttribute("id") Long id) {
		ModelAndView model = new ModelAndView("Tour/tourInfo");
		Tour tour = tourDao.findOne(id);
		ArrayList<Iterable> daysArr = new ArrayList<Iterable>();
		for (int i = 0; i < tour.getDays(); i++) { //依不同天數取得當天景點
			Iterable<Spot> spots = spotDao.findByTourAndDayOrderBySequenceAsc(tour, i + 1);
			daysArr.add(spots);
		}
	
		model.addObject("tourId", tour.getId());
		model.addObject("allDays", daysArr);
		model.addObject("curName", getUserName());

		return model;
	}

	//導向景點搜尋頁面
	@RequestMapping(value = { "/user/spotSearch" }, method = RequestMethod.GET)
	public ModelAndView spotSearch(@ModelAttribute("id") int tourId, HttpSession session) {
		ModelAndView model = new ModelAndView("Tour/spotSearch");
		Tour tour = tourDao.findOne(new Long(tourId));
		
		//頁面預設顯示Day1景點
		Iterable<Spot> spots = spotDao.findByTourAndDayOrderBySequenceAsc(tour, 1);
		model.addObject("spots", spots);
		model.addObject("tour", tour);

		//天數資料串列
		ArrayList<Integer> dayList = new ArrayList<>();
		for (int i = 1; i <= tour.getDays(); i++)
			dayList.add(i);
		model.addObject("daysList", dayList);

		return model;
	}

	//更新景點資料庫(json格式為參數)
	@RequestMapping(value = { "/user/saveBasket" }, method = RequestMethod.POST)
	public ModelAndView updateBasket(@RequestBody String json) throws Exception {
		ModelAndView model = new ModelAndView("redirect:/");
		JSONObject jsonObj = new JSONObject(json); //json字串處理
		Tour whichTour = tourDao.findOne(new Long(jsonObj.get("tourId").toString()));
		Integer thisDay = new Integer(jsonObj.get("thisDay").toString());
		basketSave(jsonObj, whichTour, thisDay); //呼叫方法存入資料庫

		return model;
	}
	
	//回傳某天的行程景點List
	@RequestMapping(value = { "/user/retrieveNextSopts" }, method = RequestMethod.POST)
	@ResponseBody //將回傳值當作回應內容
	public List<Spot> nextSpots(@RequestBody String json) throws Exception {
		JSONObject jsonObj = new JSONObject(json);
		Integer nextDay = new Integer(jsonObj.get("nextDay").toString()); //取得json內的nextDay資料
		Integer thisDay = new Integer(jsonObj.get("thisDay").toString()); //取得json內的thisDay資料
		Tour whichTour = tourDao.findOne(new Long(jsonObj.get("tourId").toString()));
		basketSave(jsonObj, whichTour, thisDay); //儲存修改結果
		ArrayList<Spot> nextSpots = new ArrayList<Spot>();
		//儲存nextDay的所有景點於串列
		nextSpots = (ArrayList<Spot>) spotDao.findByTourAndDayOrderBySequenceAsc(whichTour, nextDay);

		return nextSpots;
	}

	//更新景點資料庫
	private void basketSave(JSONObject jsonObj, Tour whichTour, Integer whichDay) throws Exception {;

		/* handle json string */
		Gson gson = new Gson();
		JSONArray jsonArr = jsonObj.getJSONArray("things");
		Type listType = new TypeToken<ArrayList<Spot>>(){}.getType(); //用Gson套件設定json字串轉換格式
		ArrayList<Spot> spots = gson.fromJson(jsonArr.toString(), listType); //轉換json字串為Spot的ArrayList
		ArrayList<Spot> list = (ArrayList<Spot>) spotDao.findByTourAndDayOrderBySequenceAsc(whichTour, whichDay);
		
		//更新景點順序、刪除使用者移除的景點
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
		
		//新增使用者加入的景點於資料庫
		for (int j = 0; j < spots.size(); j++) {
			Spot s = spots.get(j);
			Spot newSpot = new Spot(s.getName(), s.getDay(), s.getPlaceId(), s.getSequence(), whichTour);
			spotDao.save(newSpot);
		}
	}

	//導向路線規劃頁面
	@RequestMapping(value = "/user/route", method = RequestMethod.GET)
	public ModelAndView handleRoute() {
		ModelAndView model = new ModelAndView("Tour/direction");

		return model;
	}
	
	//會傳當前使用者姓名
	public String getUserName(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		String name = memberDao.findOne(currentUserName).getName();
		
		return name;
	}
	
	//以json格式回傳單筆景點詳細資料(資料於modal顯示)
	@RequestMapping(value = "/user/spotDetail", method = RequestMethod.POST)
	@ResponseBody
	public String spotDetail(@RequestBody String json) throws JsonProcessingException {
		//ModelAndView model = new ModelAndView("redirect:/");
		JSONObject jsonObj = new JSONObject(json);
		Long id = Long.valueOf(jsonObj.get("id").toString());
		Spot spot = spotDao.findOne(id);
		ObjectMapper mapper = new ObjectMapper();
		String spotJson = mapper.writeValueAsString(spot); //物件轉json字串

		return spotJson;
	}
	
	//儲存單筆景點資料的修改結果
	@RequestMapping(value = "/user/saveSpotDetail", method = RequestMethod.POST)
	public ModelAndView saveSpotDetail(@RequestBody String json){
		ModelAndView model = new ModelAndView("redirect:/");
		JSONObject jsonObj = new JSONObject(json);
		Long id = Long.valueOf(jsonObj.get("id").toString());
		String name = jsonObj.get("name").toString();
		String note = jsonObj.get("note").toString();
		Spot spot = spotDao.findOne(id);
		spot.setName(name);
		spot.setNote(note);
		spotDao.save(spot);
			
		return model;
		
	}
	
}
