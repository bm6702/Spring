package net.websnet.service;

import java.util.List;

import net.websnet.domain.GuestVO;
import net.websnet.domain.PageVO;

public interface GuestService {
	public int guestCount();
	
	public List<GuestVO> guestList(PageVO vo);

	public void guestWrite(GuestVO vo);
	
	public GuestVO guestView(int idx);
	
	public void guestReadCnt(int idx);
}
