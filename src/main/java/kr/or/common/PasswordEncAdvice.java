package kr.or.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.or.member.model.vo.Member;
import kr.or.member.model.vo.PwChangeVo;

@Component
@Aspect
public class PasswordEncAdvice {
	@Autowired
	private SHA256Enc enc;  // 의존성으로 바로 넣을 수 있도록
	
	@Pointcut(value="execution(* kr.or.member.model.service..*Service.*Member(kr.or.member.model.vo.Member))")
	public void encPointcut() {}
	
	@Before(value="encPointcut()")
	public void encPassword(JoinPoint jp) throws Exception{
		String methodName = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		Member m = (Member)args[0];
		String inputPass = m.getMemberPw();       // 암호화 전 비밀번호
		String encPass = enc.encData(inputPass);  // 암호화 비밀번호
		m.setMemberPw(encPass);  // member객체에 바뀐 비밀번호가 들어간다
		System.out.println("메소드명 : " + methodName);
		System.out.println("암호화 전 비밀번호 : " + inputPass);
		System.out.println("암호화 비밀번호 : " + encPass);
	}


	@Pointcut(value="execution(* kr.or.member.model.service..*Service.changePw(..))")
	public void changePwPointcut() {}
	
	@Before(value="changePwPointcut()")
	public void changePw(JoinPoint jp) throws Exception {
		Object[] args = jp.getArgs();
		PwChangeVo pc = (PwChangeVo)args[0];
		String oldPw = pc.getOldPassword();
		String newPw = pc.getNewPassword();
		pc.setOldPassword(enc.encData(oldPw));
		pc.setNewPassword(enc.encData(newPw));
	}
	
}	