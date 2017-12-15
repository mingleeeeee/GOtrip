package com.example.controller;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.example.dao.TourDAO;
import com.example.entity.Tour;
import com.example.storage.StorageService;

@Controller
@RequestMapping(value = "/user")
public class TourController {
	
	@Autowired
	TourDAO dao;

	private final StorageService storageService;

	@Autowired
	public TourController(StorageService storageService) {
		this.storageService = storageService;
	}

	@RequestMapping(value = "/tourCreate", method = RequestMethod.GET)
	public ModelAndView openFormCreate() {
		ModelAndView model = new ModelAndView("Tour/tourCreate");

		return model;
	}

	@RequestMapping(value = "/tourCreate", method = RequestMethod.POST)
	public ModelAndView processFormCreate(@ModelAttribute Tour list) {
		ModelAndView model = new ModelAndView("redirect:/user/tourRetrieveAll");

		MultipartFile pic = list.getPhotoFile();
		// 更改檔名後再儲存
		storageService.store(pic);
		list.setPhoto();// copy file name to the field photo

		// Authentication authentication =
		// SecurityContextHolder.getContext().getAuthentication();
		// String currentUserName = authentication.getname();
		// list.setAccount(); 等譽升
		dao.save(list);

		// model.addObject(list);
		return model;
	}

	@RequestMapping(value = { "/tourRetrieveAll" }, method = RequestMethod.GET)
	public ModelAndView retrieveTour() throws SQLException {
		Iterable<Tour> list = dao.findAll();
		ModelAndView model = new ModelAndView("Tour/tourList");
		model.addObject("tourlists", list);
		
		return model;
	}

	@RequestMapping(value = "/tourUpdate", method = RequestMethod.GET)
	public ModelAndView processFormUpdate(@RequestParam(value = "id", required = false, defaultValue = "1") 
					Long id) throws SQLException {
		ModelAndView model = new ModelAndView("Tour/tourUpdate");
		Tour list = dao.findOne(id);
		model.addObject("Tour", list);

		return model;

	}

	@RequestMapping(value = "/tourUpdate", method = RequestMethod.POST)
	public ModelAndView processFormUpdate(@ModelAttribute Tour list) throws SQLException {
		ModelAndView model = new ModelAndView("redirect:/user/tourRetrieveAll");
		
		if (!("").equals(list.getPhotoFile().getOriginalFilename())) {
			storageService.store(list.getPhotoFile());
			list.setPhoto();
		}
		dao.save(list);
		
		return model;
	}

	@RequestMapping(value = "/tourDelete", method = RequestMethod.GET)
	public ModelAndView deleteTour(
			@RequestParam(value = "id", required = false, defaultValue = "1") Long id) {
		ModelAndView model = new ModelAndView("redirect:/user/tourRetrieveAll");
		dao.delete(id);
		return model;
	}

}
