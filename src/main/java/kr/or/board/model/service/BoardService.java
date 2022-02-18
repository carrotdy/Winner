package kr.or.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.board.model.dao.BoardDao;
import kr.or.board.model.vo.Board;

@Service
public class BoardService {
	@Autowired
	private BoardDao dao;

	public HashMap<String, Object> boardList(int reqPage) {
		int numPerPage = 10;  //한페이지당 게시물 수
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		
		ArrayList<Board> list = dao.boardList(map);
		
		int totalCount = dao.totalCount();  // 전체 게시물 수 조회
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage + 1;
		}

		int pageNaviSize=5;  // 페이지 네비 길이(1~5, 6~10...)
		int pageNo = 1;
		if(reqPage>4) {
			pageNo= reqPage-2;
			if(totalPage - reqPage < (pageNaviSize-1)) {
				pageNo = totalPage-(pageNaviSize-1);
			}
		}
		
		String pageNavi = "<ul class='pagination pagination-lg'>";
		if(pageNo != 1) {  // 이전버튼
			pageNavi += "<li class='page-item disabled'><a class='page-link' href='/board.do?reqPage="+(reqPage-1)+"'>&lt;</a></li>";
		}
		for(int i=0;i<pageNaviSize;i++) {  // 페이지숫자
			if(pageNo == reqPage) {
				pageNavi += "<li class='page-item active'><a class='page-link' href='/board.do?reqPage="+pageNo+"'>"+pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'><a class='page-link' href='/board.do?reqPage="+pageNo+"'>"+pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo > totalPage) {  // 최종 페이지보다 네이비게이션 시작번호가 더 클경우 break
				break;
			}
		}
		if(pageNo <= totalPage) {  // 다음버튼
			pageNavi += "<li class='page-item'><a class='page-link' href='/board.do?reqPage="+(reqPage+1)+"'>&gt;</a><li>";
		}
		pageNavi += "</ul>";
		
		map.put("pageNavi", pageNavi);
		map.put("list",	list);
		
		return map;
	}

	@Transactional
	public Board boardView(Board b) {
		dao.boardHit(b);
		Board board = dao.boardView(b);
		return board;
	}
	
	@Transactional
	public int boardUpdate(Board b) {
		return dao.boardUpdate(b);
	}
	
	@Transactional
	public int boardDelete(int boardNo) {
		return dao.boardDelete(boardNo);
	}

	@Transactional
	public int boardWrite(Board b) {
		return dao.boardWrite(b);
	}


}
