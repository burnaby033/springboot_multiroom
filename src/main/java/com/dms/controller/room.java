package com.dms.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/multiRoom")
public class room {

	@GetMapping("/room")
	public String roomController(Model model, HttpServletRequest request) {
		String roomId = request.getParameter("id");
		
		model.addAttribute("roomId", roomId);
		return "room";
	}
}
