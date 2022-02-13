package kr.or.board.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.board.model.service.BoardService;
import kr.or.board.model.vo.Board;

@Controller
public class BoardController {
	@Autowired
	private BoardService service;
	
	//게시판 
	@RequestMapping(value="/board.do")
	public String board(Model model) {
		ArrayList<Board> list = service.boardList();
		System.out.println(list);
		model.addAttribute("list",list);
		return "board/boardList";
	}

}
