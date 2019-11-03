package net.websnet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import net.websnet.domain.BoardVO;
import net.websnet.domain.PageVO;
import net.websnet.service.BoardService;
import net.websnet.util.PgIndex;

@Controller
@RequestMapping("/Board/*")
@AllArgsConstructor
public class BoardController {
	private static final Logger log = 
			LoggerFactory.getLogger(BoardController.class);
	
	private BoardService service;
	
	@GetMapping("board_list")
	public void boardList(@RequestParam("page") int page, Model model) {
		log.info("boardList()......");
		String url="board_list", addtag="";
		
		int nowpage=1; // 현재페이지 초기화
		int maxlist=10;// 페이지당 글 수
		int totpage=1; // 총 페이지 초기화
		int totcount = service.boardTotalCount();// 총 글수 구하기
		if(totcount % maxlist ==0) {
			totpage=totcount/maxlist;
		}else {
			totpage=totcount/maxlist+1;
		}
		if(totpage==0) totpage=1;
		if(page != 0) nowpage=1;
		if(nowpage > totpage) nowpage=totpage;
		
		// 현재 페이지 시작번호
		int pagestart = (nowpage -1) * maxlist +1;
		int endpage = nowpage * maxlist;
		
		int listcount = totcount - ((nowpage-1)*maxlist);
		
		PageVO vo = new PageVO();
		vo.setPagestart(pagestart);
		vo.setEndpage(endpage);
		
		model.addAttribute("list", service.boardList(vo));
		model.addAttribute("addtag", addtag);
		model.addAttribute("url", url);
		model.addAttribute("nowpage", nowpage);
		model.addAttribute("totpage", totpage);
		model.addAttribute("totcount", totcount);
		model.addAttribute("listcount", listcount);
	
		model.addAttribute("listPage", PgIndex.pageList(nowpage, totpage, url, addtag));
	}
	
	@PostMapping("board_list")
	public void boardListSearch(@RequestParam("page") int page, PageVO vo, Model model) {
		log.info("boardListSearch()......");
		String url="board_list", addtag="";
		
		int nowpage=1; // 현재페이지 초기화
		int maxlist=10;// 페이지당 글 수
		int totpage=1; // 총 페이지 초기화
		int totcount = service.boardCountSearch(vo);// 총 글수 구하기
		if(totcount % maxlist ==0) {
			totpage=totcount/maxlist;
		}else {
			totpage=totcount/maxlist+1;
		}
		if(totpage==0) totpage=1;
		if(page != 0) nowpage=1;
		if(nowpage > totpage) nowpage=totpage;
		
		// 현재 페이지 시작번호
		int pagestart = (nowpage -1) * maxlist +1;
		int endpage = nowpage * maxlist;
		
		int listcount = totpage - (nowpage-1)*maxlist;
		
		//PageVO vo = new PageVO();
		vo.setPagestart(pagestart);
		vo.setEndpage(endpage);
		
		model.addAttribute("list", service.boardListSearch(vo));
		model.addAttribute("addtag", addtag);
		model.addAttribute("url", url);
		model.addAttribute("nowpage", nowpage);
		model.addAttribute("totpage", totpage);
		model.addAttribute("totcount", totcount);
		model.addAttribute("listcount", listcount);
	
		model.addAttribute("listPage", PgIndex.pageList(nowpage, totpage, url, addtag));
	}
	
	@GetMapping("board_write")
	public void boardWrite(@ModelAttribute("page") int page) {
		log.info("boardWrite()");
	}
	
	@PostMapping("board_write")
	public String boardWritePro(BoardVO vo, @ModelAttribute("page") int page) {
		int parent = vo.getParent();
		int indent = vo.getIndent();
		int depth = vo.getDepth();
		int idx=0;
		idx = service.boardMaxIdx();
		if(parent != 0) {// 답변글 일경우 처리
			service.boardDepth(vo);
			vo.setDepth(depth+1);
			vo.setIndent(indent+1);
		}else{ // 처음 작성된 글 일 경우
			vo.setParent(idx);			
		}
		
		vo.setIdx(idx);
		service.boardWrite(vo);
		
		return "redirect:/Board/board_list";		
	}
	
	@GetMapping("board_view")
	public void boardView(@RequestParam("idx") int idx, @ModelAttribute("page") int page, Model model) {
		log.info("boardView()");
		service.boardCount(idx);
		model.addAttribute("vo", service.boardSelect(idx));
	}
	// 답변입력 폼
	@GetMapping("board_reply")
	public void boardReply(@ModelAttribute("page") int page, @RequestParam("idx") int idx, Model model) {
		log.info("boardReply()");
		
		model.addAttribute("vo", service.boardSelect(idx));
	}
	//수정 입력폼
	@GetMapping("board_modify")
	public void boardModify(@ModelAttribute("page") int page, @RequestParam("idx") int idx, Model model) {
		log.info("boardModify()");
		
		model.addAttribute("vo", service.boardSelect(idx));
	}
/*	//수정 처리
	@PostMapping("board_modify")
	public String boardModifyPro(BoardVO vo) {
		log.info("boardModifyPro()..");
		int row=service.boardModify(vo);
		return "redirect:/Board/board_list";
	}
*/	
	// 수정처리(비번 체크)
	@PostMapping("board_modify")
	public void boardModifyPro(BoardVO vo, Model model,@ModelAttribute("page") int page) {
		log.info("boardModifyPro()..");
		int row=service.boardModify(vo);
		model.addAttribute("row", row);
	}
	// 삭제(비번입력 폼)
	@GetMapping("board_delete")
	public void boardDelete(@ModelAttribute("page") int page, @ModelAttribute("idx") int idx) {
		log.info("boardDelete()..");
	}

	// 삭제처리
	@PostMapping("board_delete_pro")
	public void boardDeletePro(@ModelAttribute("page") int page, BoardVO vo, Model model) {
		log.info("boardDeletePro()..");
		int cnt = service.boardRealparent(vo.getIdx());// 답변글 수 카운트
		int row=0;
		if(cnt==0) {//답변글이 없을 경우
			row=service.boardDelete(vo);
		}else {
			row=-1; // 답변글이 있을 경우
		}
		model.addAttribute("row", row);
	}
}
