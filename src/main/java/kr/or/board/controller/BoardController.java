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
	
	//목록
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
	
	//수정이동
	@RequestMapping(value="/boardUpdateFrm.do")
	public String boardUpdateFrm(Board b, Model model) {
		Board board = service.boardView(b);
		model.addAttribute("board",board);
		return "board/boardUpdateFrm";
	}
	
	//수정
	@RequestMapping(value="/boardUpdate.do")
	public String boardUpdate(Board b, Model model) {
		int result = service.boardUpdate(b);
		if(result > 0) {
			model.addAttribute("msg","게시물 수정 완료~!");
		}else {
			model.addAttribute("msg","게시물 수정 실패ㅠㅠ");
		}
		model.addAttribute("loc","/boardView.do?boardNo="+b.getBoardNo());
		return "common/msg";
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
