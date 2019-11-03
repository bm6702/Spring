package net.websnet.mapper;

import java.util.List;

import net.websnet.domain.MemberVO;

public interface MemberMapper {
	 public List<MemberVO> memberList();
	 
	 //ID 검색 비밀번호 리턴
	 public String userIdSearch(MemberVO vo);
	 //로그인(ID,PASS) 검사
	 public MemberVO userLogin(MemberVO vo);
}
