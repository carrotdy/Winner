package kr.or.board.model.dao;


import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.board.model.vo.Board;

@Repository
public class BoardDao {
	@Autowired
	private SqlSessionTemplate session;

	public ArrayList<Board> boardList() {
		List<Board> list = session.selectList("board.boardList");
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


}
