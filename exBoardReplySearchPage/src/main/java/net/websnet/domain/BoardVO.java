package net.websnet.domain;

import lombok.Data;

@Data
public class BoardVO {
	private int idx;
	private String name;
	private String pass;
	private String email;
	private String subject;
	private String contents;
	private String regdate;
	private int readcnt;
	private int parent;
	private int realparent;
	private int depth;
	private int indent;
	private String ip;
}
