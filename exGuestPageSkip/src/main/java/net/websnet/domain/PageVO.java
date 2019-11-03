package net.websnet.domain;

import lombok.Data;

@Data
public class PageVO {
	private int pagestart;
	private int endpage;
	private String search;
	private String key;
}
