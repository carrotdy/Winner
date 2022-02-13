package kr.or.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.board.model.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService service;
	
	//게시판 이동
	@RequestMapping(value="/board.do")
	public String board() {
		return "board/boardList";
	}
}
