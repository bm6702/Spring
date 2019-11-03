package net.websnet.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import net.websnet.domain.GuestVO;
import net.websnet.domain.PageVO;
import net.websnet.service.GuestService;
import net.websnet.util.PgIndex;
import net.websnet.util.SqlMark;


@Controller
@AllArgsConstructor // 생성자 자동으로 생성해달라
@RequestMapping("Guest/*") // 게스트안의 모든것 폴더이름이 Guest라
public class GuestController {
	private static final Logger log = LoggerFactory.getLogger(GuestController.class);
	private GuestService service;// 컨트롤러에서 서비스 자동 주입

	  @GetMapping("guest_list")
	   public void guestList(Model model, @RequestParam("page") int page) {
	      log.info("guestList()......");
	      String url = "guest_list";
	      String s_query= "", addtag="", query="", key="";
	      
	      int nowpage=1;
	      int maxlist =10;
	      int totpage =1;
	      int totcount = service.guestCount();
	      if(totcount % maxlist ==0) {
	         totpage = totpage / maxlist;
	      }else {
	         totpage = totpage / maxlist + 1;
	      }
	      if(totpage == 0)// 총페이지가 0이면 1로 초기화
	       totpage= 1;
	      if(page != 0 )
	         nowpage = page;
	      if(nowpage > totpage )
	         nowpage =totpage;
	      
	      int pagestart = (nowpage - 1)*maxlist +1;
	      int endpage = nowpage * maxlist;
	      int listcount = totcount - ((nowpage-1)*maxlist) +1;
	      
	      
	      PageVO vo = new PageVO();
	      vo.setEndpage(endpage);
	      vo.setPagestart(pagestart);
	      List list = service.guestList(vo);
	      
	      model.addAttribute("addtag", addtag);
	      model.addAttribute("url", url);
	      model.addAttribute("nowpage", nowpage);
	      model.addAttribute("totpage", totpage);
	      model.addAttribute("totcount", totcount);
	      model.addAttribute("pagestart", pagestart);
	      model.addAttribute("listcount", listcount);

	      model.addAttribute("list", list);
	      
	      model.addAttribute("listpage", PgIndex.pageList(nowpage, totpage, url, addtag));
	      
	   }
	//등록 폼으로 이동
	@GetMapping("guest_write")
	public void guestWrite() {
		log.info("guestWrite()....");
	}
	//등록처리
	@PostMapping("guest_write")
	public String guestWritePro(GuestVO vo) {
		log.info("guestWritePro()....");
		service.guestWrite(vo);
		return "redirect:/Guest/guest_list?page=1";
	}
	//View
	@GetMapping("guest_view")
	public void guestView(@RequestParam("idx") int idx,@RequestParam("page") String page,Model model) {
		log.info("guestView()....");
		//service.guestReadCnt(idx);//쿠키 때문에 분리해서 처리
		GuestVO vo = service.guestView(idx);
		vo.setContents(SqlMark.linkBreak(vo.getContents()));
		model.addAttribute("page", page);
		model.addAttribute("vo",vo);
		
	}
	
    //jsp파일이 없으니 redirect		
	@GetMapping("guestReadCnt")	
	public String guestReadCnt(@RequestParam("idx") int idx,@RequestParam("page") int page,
			HttpServletRequest request, HttpServletResponse response) {
		boolean found=false;
		Cookie info = null;
		Cookie[] cookies = request.getCookies();
		for(int i=0;i<cookies.length;i++) {
			info = cookies[i];
			if(info.getName().contentEquals("guestCookie"+idx)) {
				found=true;
				break;
			}
		}//쿠키 존재 확인
		String str=""+System.currentTimeMillis();
		if(!found) {
			info=new Cookie("guestCookie"+idx, str);
			info.setMaxAge(60*60);//1시간 짜리
			response.addCookie(info);
			service.guestReadCnt(idx);
			//없다면 +
		}
		log.info("guestReadCnt()....");
		//service.guestReadCnt(idx);위로 올리기
		return "redirect:/Guest/guest_view?idx="+idx+"&page="+page;
	}
	
}
