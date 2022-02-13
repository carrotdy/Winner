package kr.or.board.model.vo;

import lombok.Data;

@Data
public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private int readCount;
	private int regDate;
	private int priority;
	private String fileName;
	private String filePath;
	private int ncCount;
}
