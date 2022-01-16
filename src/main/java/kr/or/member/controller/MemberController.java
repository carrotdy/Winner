package kr.or.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.member.model.service.MemberService;
import kr.or.member.model.vo.Member;

@Controller
public class MemberController {
	@Autowired
	private MemberService service;

	//로그인이동
	@RequestMapping(value="/loginFrm.do")
	public String loginFrm() {
		return "member/loginFrm";
	}
	
	//로그인
	@RequestMapping(value="/login.do")
	public String login(Member member, HttpSession session, Model model) {
		Member m = service.login(member);
		if(m != null) {
			session.setAttribute("m", m);
			model.addAttribute("msg","로그인 성공~!");
		}else {
			model.addAttribute("msg","로그인 실패");
		}
		model.addAttribute("loc","/");
		return "common/msg";
	}
	
	//로그아웃
	@RequestMapping(value="/logout.do")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		return "redirect:/";
	}
	
	//회원가입이동
	@RequestMapping(value="/joinFrm.do")
	public String join() {
		return "member/join";
	}
	
	
}
