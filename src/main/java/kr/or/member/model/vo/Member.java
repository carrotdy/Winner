package kr.or.member.model.vo;

import lombok.Data;

@Data
public class Member {
	private int MemberNo;		//회원번호
	private String memberId;    //아이디
	private String memberPw;    //비밀번호
	private String memberName;  //이름
	private String phone;		//핸드폰번호
	private String postCode;    //우편번호
	private String address1;    //상세주소1
	private String address2;    //상세주소2
	private String email;       //이메일
	private String enrollDate;  //가입일
	private String filePath;    //파일경로
	private int memberLevel;    //회원레밸 0.관리자 1.일반회원 2.블랙회원 
}
