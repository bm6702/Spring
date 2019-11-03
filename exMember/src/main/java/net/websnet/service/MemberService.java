package net.websnet.service;

import java.util.List;

import net.websnet.domain.MemberVO;

public interface MemberService {
   public List<MemberVO> memberList();
   
   public String userIdSearch(MemberVO vo);
   public MemberVO userLogin(MemberVO vo);
   
}