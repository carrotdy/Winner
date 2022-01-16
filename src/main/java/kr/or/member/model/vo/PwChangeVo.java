package kr.or.member.model.vo;

import lombok.Data;

@Data
public class PwChangeVo {
	private String memberId;
	private String oldPassword;
	private String newPassword;
}
