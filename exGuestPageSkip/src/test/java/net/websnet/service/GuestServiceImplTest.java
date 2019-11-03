package net.websnet.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.websnet.domain.PageVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")

@Log4j
public class GuestServiceImplTest {
	@Setter(onMethod_=@Autowired)
	private GuestService service;
	
	@Test
	public void testGuestList(PageVO vo) {
		service.guestList(vo).forEach(guest->log.info(guest));
	}
}
