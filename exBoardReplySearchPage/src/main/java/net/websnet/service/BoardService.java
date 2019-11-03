package net.websnet.service;

import java.util.List;

import net.websnet.domain.BoardVO;
import net.websnet.domain.PageVO;

public interface BoardService {
	//전체목록 카운트
	public int boardTotalCount();
	//검색조건에 맞는 목록 카운트
	public int boardCountSearch(PageVO vo);
	// 전체 목록(페이지별 검색조건 없음)
	public List<BoardVO> boardList(PageVO vo);
	// 전체 목록(페이지별 검색조건 포함)
	public List<BoardVO> boardListSearch(PageVO vo);
	
	public int boardMaxIdx();
	
	public void boardWrite(BoardVO vo);
	
	// 조회수 증가
	public void boardCount(int idx);
	// 특정 게시물 검색(View)
	public BoardVO boardSelect(int idx);
	
	// 답변글 글 깊이
	public void boardDepth(BoardVO vo);
	
	// 게시글 수정
	public int boardModify(BoardVO vo);
	
	// 삭제글의 답변글이 존재하는지 검사
	public int boardRealparent(int idx);
	
	// 글삭제(성공시 정수 리턴)
	public int boardDelete(BoardVO vo);

}
