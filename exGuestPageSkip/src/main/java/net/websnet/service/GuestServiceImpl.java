package net.websnet.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import net.websnet.domain.GuestVO;
import net.websnet.domain.PageVO;
import net.websnet.mapper.GuestMapper;

@Service
@AllArgsConstructor
public class GuestServiceImpl implements GuestService {
	private static final Logger log = LoggerFactory.getLogger(GuestServiceImpl.class);

	@Setter(onMethod_=@Autowired)
	private GuestMapper mapper;
	
	/*
	 * @Override public List<GuestVO> guestList() {
	 *  log.info("guestList()....");
	 * return mapper.guestList(); 
	 * }
	 */

	@Override
	public void guestWrite(GuestVO vo) {
		log.info("guestWrite()....");
		 mapper.guestWrite(vo);
	}

	@Override
	public GuestVO guestView(int idx) {
		log.info("guestView()....");
		return mapper.guestView(idx)
				;
	}

	@Override
	public void guestReadCnt(int idx) {
		log.info("guestReadCnt()....");
		mapper.guestReadCnt(idx);
		
	}

	@Override
	public int guestCount() {
		return mapper.guestCount();
	}

	@Override
	public List<GuestVO> guestList(PageVO vo) {
		return mapper.guestList(vo);
	}
}
