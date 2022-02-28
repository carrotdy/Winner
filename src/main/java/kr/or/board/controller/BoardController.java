package kr.or.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.or.board.model.service.BoardService;
import kr.or.board.model.vo.Board;

@Controller
public class BoardController {
	@Autowired
	private BoardService service;
	
	//목록
	@RequestMapping(value="/board.do")
	public String board(int reqPage, String keyword, Model model) {
		HashMap<String, Object> map = service.boardList(reqPage, keyword);
	
		model.addAttribute("list", map.get("list"));
		model.addAttribute("pageNavi", map.get("pageNavi"));
		model.addAttribute("start", map.get("start"));
		model.addAttribute("fixPage", map.get("fixPage"));
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
	public String boardUpdate(Board b, MultipartFile upfile, Model model, HttpServletRequest request) {
		if(!upfile.isEmpty()) {  //첨부파일이 있는경우
			String savePath =  request.getServletContext().getRealPath("/resources/upload/board/");
			String filename = upfile.getOriginalFilename();
			String onlyFilename = filename.substring(0,filename.indexOf("."));
			String extention = filename.substring(filename.indexOf("."));
			String filepath = "";			
			
			int count = 0;
			while(true) {    
				if(count == 0) {
					filepath = onlyFilename + extention;  
				}else {
					filepath = onlyFilename + "_" + count + extention;  
				}
				File checkFile = new File(savePath+filepath);  
				if(!checkFile.exists()) { 
					break;
				}
				count++;    
			}  
			try {  //파일명 중복처리가 끝나면 파일 업로드
				FileOutputStream fos = new FileOutputStream(new File(savePath+filepath));
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				byte[] bytes = upfile.getBytes();
				bos.write(bytes);
				bos.close();
			} catch (FileNotFoundException e) {
				System.out.println(e.toString());
			} catch (IOException e) {
				System.out.println(e.toString());
			}
			b.setFilePath(filepath);
			b.setFileName(filename);
		}
		int result = service.boardUpdate(b);
		if(result > 0) {
			model.addAttribute("msg","게시물 수정 완료~!");
			System.out.println("b :"+b);
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
		model.addAttribute("loc","/board.do?reqPage=1");
		return "common/msg";
	}
	
	//게시글작성 이동
	@RequestMapping(value="/boardWriteFrm.do")
	public String boardWriteFrm() {
		return "board/boardWrite";
	}

	//게시글 작성
	@RequestMapping(value="/boardWrite.do")
	public String boardWrite(Board b, MultipartFile upfile, HttpServletRequest request, Model model) {
		if(!upfile.isEmpty()) {  //첨부파일이 있는경우
			String savePath =  request.getServletContext().getRealPath("/resources/upload/board/");
			String filename = upfile.getOriginalFilename();
			String onlyFilename = filename.substring(0,filename.indexOf("."));
			String extention = filename.substring(filename.indexOf("."));
			String filepath = "";			
			
			int count = 0;
			while(true) {    
				if(count == 0) {
					filepath = onlyFilename + extention;  
				}else {
					filepath = onlyFilename + "_" + count + extention;  
				}
				File checkFile = new File(savePath+filepath);  
				if(!checkFile.exists()) { 
					break;
				}
				count++;    
			}  
			try {  //파일명 중복처리가 끝나면 파일 업로드
				FileOutputStream fos = new FileOutputStream(new File(savePath+filepath));
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				byte[] bytes = upfile.getBytes();
				bos.write(bytes);
				bos.close();
			} catch (FileNotFoundException e) {
				System.out.println(e.toString());
			} catch (IOException e) {
				System.out.println(e.toString());
			}
			b.setFilePath(filepath);
			b.setFileName(filename);
		}
		System.out.println("b =" + b);
		int result  = service.boardWrite(b);
		if(result > 0) {
			model.addAttribute("msg","게시물 작성 성공~!");
		}else {
			model.addAttribute("msg","게시물 작성 실패ㅠㅠ");
		}
		model.addAttribute("loc","/board.do?reqPage=1");
		return "common/msg";
	}
	
	//파일다운로드
	@RequestMapping(value="/fileDown.do")
	public void downloadFile(String filepath, String filename, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/board/");
		String file = savePath+filepath;
		
		FileInputStream fis = new FileInputStream(file);  // 파일에 입출력을 하기 위한 스트림
		BufferedInputStream bis = new BufferedInputStream(fis);
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		
		String resFilename = "";
		
		boolean bool = request.getHeader("user-agent").indexOf("MSIE") != -1 || request.getHeader("user-agent").indexOf("Trident") != -1;
		if(bool) {   //브라우저가 IE인경우
			resFilename = URLEncoder.encode(filename,"UTF-8");
			resFilename = resFilename.replaceAll("\\\\", "%20");
		}else {   //그외 다른 브라우저인 경우
			resFilename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
		}
		response.setContentType("application/octet-stream;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+resFilename);  //다운로드할 파일 이름 지정
		
		while(true) {  //파일전송
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
	
	@RequestMapping(value="/boardSearch.do")
	public String boardSearch(int reqPage, Board b, Model model) {
		HashMap<String, Object> data = service.boardSearch(reqPage, b);
		model.addAttribute("pageNavi", data.get("pageNavi"));
		model.addAttribute("list", data.get("list"));
		model.addAttribute("start", data.get("start"));
		return "board/boardList";
	}
}
