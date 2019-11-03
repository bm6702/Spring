package net.websnet.mapper;

import java.util.List;

import net.websnet.domain.BoardVO;
import net.websnet.domain.PageVO;

public interface BoardMapper {
	// 전체 카운트
	public int boardTotalCount();
	// 조건검색에 위한 카운트
	public int boardCountSearch(PageVO vo);
	
	//전체목록(페이지별)
	public List<BoardVO> boardList(PageVO vo);
	
	//전체목록( 검색기능추가)
	public List<BoardVO> boardListSearch(PageVO vo);
	
	//MaxIDX
	public int boardMaxIdx();
	
	// 등록
	public void boardWrite(BoardVO vo);
	
	//특정게시물 조회수증가(View)
	public void boardCount(int idx);

	//특정게시물 검색(View)
	public BoardVO boardSelect(int idx);
	
	// 답변글일 경우 글 깊이, 들여쓰기 조정
	public void boardDepth(BoardVO vo);
	
	// 게시글 수정(수정 성고시 정수 리턴)
	public int boardModify(BoardVO vo);
	
	// 삭제글의 답변글이 존재하는지 검사
	public int boardRealparent(int idx);
	
	// 글삭제(성공시 정수 리턴)
	public int boardDelete(BoardVO vo);
	
}
