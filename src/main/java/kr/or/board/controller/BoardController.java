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
		model.addAttribute("list",list);
		return "board/boardList";
	}
	
	//상세보기 + 조회수
	@RequestMapping(value="/boardView.do")
	public String boardView(Board b, Model model) {
		Board board = service.boardView(b);
		model.addAttribute("board",board);
		return "board/boardView";
	}
	
	//삭제
	@RequestMapping(value="/boardDelete.do")
	public String boardDelete(int boardNo, Model model) {
		int result = service.boardDelete(boardNo);
		if(result > 0) {
			model.addAttribute("msg","게시물 삭제 성공~!");
		}else {
			model.addAttribute("msg","게시물 삭제 실패ㅠㅠ");
		}
		model.addAttribute("loc","/board.do?boardNo=" + boardNo);
		return "common/msg";
	}

}
