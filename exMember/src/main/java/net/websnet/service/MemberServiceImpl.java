package net.websnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import net.websnet.domain.MemberVO;
import net.websnet.mapper.MemberMapper;

@Service//이것을 써줘야지 이것이 서비스라고 인식을 한다
public class MemberServiceImpl implements MemberService {
   @Setter(onMethod_=@Autowired)
   private MemberMapper mapper;
   
   @Override
   public List<MemberVO> memberList() {
      return mapper.memberList();
   }

@Override
public String userIdSearch(MemberVO vo) {
	return mapper.userIdSearch(vo);
}

@Override
public MemberVO userLogin(MemberVO vo) {
	return mapper.userLogin(vo);
}

}