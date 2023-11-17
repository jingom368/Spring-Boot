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
import org.springframework.web.bind.annotation.RequestParam;

import com.board.service.BoardService;
import com.board.service.MasterService;

@Controller
public class MasterController {
	
	@Autowired
	MasterService service;
	
	@Autowired
	BoardService boardService;
	
	// 관리자 페이지
	@GetMapping("/master/sysManage")
	public void getSysManage() {
		
	}
	
	// 보드 수 확인
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
	
	// 댓글 수 확인
	@GetMapping("/master/numberReply")
	public void getNumberReply(Model model) throws Exception {
		
		List<HashMap<String, Object>> replyCount = service.replyCountGroupbyUserid();
		System.out.println(replyCount);
		
		model.addAttribute("replyCount", replyCount);
	}
	
	// 회원 수
	@GetMapping("/master/memberCount")
	public void getMemberCount(Model model) throws Exception {
		
		model.addAttribute("memberCount", service.memberCount());
	}
	
	// 파일 수정 리스트 보여주기
	@GetMapping("/master/fileModify")
	public void getFileModify(Model model) throws Exception {
		
		model.addAttribute("fileList", service.fileList());
	}
	
	// 파일 삭제 목록 만들기
	@GetMapping("/master/fileModifyX")
	public String getFileModifyX(@RequestParam("fileseqno") int fileseqno) throws Exception {
		
		boardService.deleteFileList(fileseqno);
		
		return "redirect:/master/fileModify";
	}
	
	// 파일 삭제 리스트 보여주기
	@GetMapping("/master/fileDelete")
	public void getFileDelete(Model model) throws Exception {
		
		model.addAttribute("fileList", service.fileDeleteList());
	}
	
	// 파일 삭제 하기
	@GetMapping("/master/fileDeleteX")
	public String getFileDeleteX(@RequestParam("fileseqno") int fileseqno) throws Exception {
		
		service.fileDelete(fileseqno);
		
		return "redirect:/master/fileDelete";
	}
}