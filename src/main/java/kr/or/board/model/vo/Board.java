package kr.or.board.model.vo;

import lombok.Data;

@Data
public class Board {
	private int boardNo;            // 게시판번호
	private String boardTitle;		// 제목
	private String boardContent;	// 내용
	private String boardWriter;		// 작성자
	private int readCount;			// 조회수
	private String regDate;			// 작성날짜(YYYY-MM-DD hh24:mi)
	private int priority;			// 고정공지사항
	private String fileName;		// 파일이름
	private String filePath;		// 첨부파일경로
	private int ncCount;			// 댓글수
	private String searchType;
	private String keyword;
}
