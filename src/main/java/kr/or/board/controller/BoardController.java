package kr.or.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	//글작성 이동
	@RequestMapping(value="/boardWriteFrm.do")
	public String boardWriteFrm() {
		return "board/boardWrite";
	}

	//글작성
	@RequestMapping(value="/boardWrite.do")
	public String boardWrite(Board b, Model model) {
		System.out.println("1 : "+ b);
		int result  = service.boardWrite(b);
		System.out.println("2 : "+ result);
		if(result > 0) {
			model.addAttribute("msg","게시물 작성 성공~!");
		}else {
			model.addAttribute("msg","게시물 작성 실패ㅠㅠ");
		}
		model.addAttribute("loc","/boardWrite.do");
		return "common/msg";
	}
	
	
	@RequestMapping(value = "/downFile.do")
	public void downloadFile(String filepath, String filename, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/board/");
		System.out.println(filepath);
		System.out.println(filename);
		String file = savePath+filepath;
		
		
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		
		String resFilename = "";
		
		boolean bool = request.getHeader("user-agent").indexOf("MSIE") != -1 || request.getHeader("user-agent").indexOf("Trident") != -1;
		if(bool) {
			resFilename = URLEncoder.encode(filename,"UTF-8");
			resFilename = resFilename.replaceAll("\\\\", "%20");
		}else {
			resFilename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
		}
		response.setContentType("application/octet-stream;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+resFilename);
		
		while(true) {
			int read = bis.read();
			if(read != -1) {
				bos.write(read);
			}else {
				break;
			}
		}
		
		bis.close();
		bos.close();
		
	}
}
