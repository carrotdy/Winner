package kr.or.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.member.model.dao.MemberDao;
import kr.or.member.model.vo.Member;

@Service
public class MemberService {
	@Autowired
	private MemberDao dao;

	//로그인
	public Member login(Member member) {
		return dao.login(member);
	}

	//회원가입_아이디 중복체크
	public Member IdChk(String memberId) {
		return dao.IdChk(memberId);
	}
}
