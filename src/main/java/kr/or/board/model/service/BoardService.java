package kr.or.board.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.board.model.dao.BoardDao;
import kr.or.board.model.vo.Board;

@Service
public class BoardService {
	@Autowired
	private BoardDao dao;

	public ArrayList<Board> boardList() {
		ArrayList<Board> list = dao.boardList();
		return list;
	}

	@Transactional
	public Board boardView(Board b) {
		dao.boardHit(b);
		Board board = dao.boardView(b);
		return board;
	}
}
