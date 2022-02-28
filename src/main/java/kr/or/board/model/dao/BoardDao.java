package kr.or.board.model.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.board.model.vo.Board;

@Repository
public class BoardDao {
	@Autowired
	private SqlSessionTemplate session;

	public ArrayList<Board> boardList(HashMap<String, Object> map) {
		List<Board> list = session.selectList("board.boardList", map);
		return (ArrayList<Board>)list;
	}

	public Board boardView(Board b) {
		return session.selectOne("board.boardView",b);
	}
	
	public int boardHit(Board b) {
		return session.update("board.boardHit",b);
	}

	public int boardUpdate(Board b) {
		return session.update("board.boardUpdate",b);
	}
	
	public int boardDelete(int boardNo) {
		return session.delete("board.boardDelete",boardNo);
	}

	public int boardWrite(Board b) {
		return session.insert("board.boardWrite",b);
	}

	public int totalCount() {
		return session.selectOne("board.totalCount");
	}

	public ArrayList<Board> priority() {
		List<Board> list = session.selectList("board.priority");
		return (ArrayList<Board>)list;
	}

	public int fixPageCount() {
		return session.selectOne("board.fixPageCount");
	}

	public ArrayList<Board> boardSearch(HashMap<String, Object> map) {
		List<Board> list = session.selectList("board.boardSearch",map);
		System.out.println("map="+map);
		return (ArrayList<Board>)list;
	}

	public int totalSearch(HashMap<String, Object> map) {
		return session.selectOne("board.totalSearch",map);
	}


}
