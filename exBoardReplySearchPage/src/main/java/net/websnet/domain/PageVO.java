package net.websnet.domain;

import lombok.Data;

@Data
public class PageVO {
	// 페이지 처리용
	private int pagestart;
	private int endpage;
	
	//검색용
	private String search;
	private String key;
	
}
