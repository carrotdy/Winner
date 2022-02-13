package kr.or.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.member.model.vo.Member;

@Repository
public class MemberDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	//로그인
	public Member login(Member member) {
		return sqlSession.selectOne("member.login",member);
	}

	//회원가입_아이디 중복체크
	public Member IdChk(String memberId) {
		return sqlSession.selectOne("member.IdChk",memberId);
	}
}
