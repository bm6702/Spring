package net.websnet.mapper;

import java.util.List;

import net.websnet.domain.GuestVO;
import net.websnet.domain.PageVO;

public interface GuestMapper {
	//전제 게시물 건수
	public int guestCount();
	//@Select("select * from guest_tbl")//xml로 이 부분을 분리시킨다
	public List<GuestVO> guestList(PageVO vo);//xml과 연동이 된다,mappertest에서 test
	//resources에 폴더 경로 똑같이 net webnet mapper 폴더 만들고 안에 xml
	
	//DB등록
	public void guestWrite(GuestVO vo);
	
	//View
	public GuestVO guestView(int idx);
	
	//조회수 증가
	public void guestReadCnt(int idx);
}
