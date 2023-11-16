package com.board.controller;

import java.math.BigDecimal;
import java.security.Provider.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.board.service.MasterService;

@Controller
public class MasterController {
	
	@Autowired
	MasterService service;
	
	@GetMapping("/master/sysManage")
	public void getSysManage() {
		
	}
	
	@GetMapping("/master/numberPost")
	public void getNumberPost(Model model) throws Exception {
		List<HashMap<String, Object>> boardCount = service.boardCountGroupbyUserid();
		System.out.println(boardCount);
		
		// 데이터 타입 확인 절차
		for (HashMap<String, Object> map : boardCount) {
		    String userid = (String) map.get("userid");
		    String username = (String) map.get("username");
		    Integer boardcount = ((BigDecimal) map.get("boardcount")).intValue();

		    System.out.println("userid : " + userid);
		    System.out.println("Username : " + username);
		    System.out.println("boardCount : " + boardcount);
		}
		
		model.addAttribute("boardCount", boardCount);
	}
	
	@GetMapping("master/numberReply")
	public void getNumberReply(Model model) throws Exception {
		
		List<HashMap<String, Object>> replyCount = service.replyCountGroupbyUserid();
		System.out.println(replyCount);
		
		model.addAttribute("replyCount", replyCount);
	}
	
}
