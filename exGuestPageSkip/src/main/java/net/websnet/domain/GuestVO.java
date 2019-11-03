package net.websnet.domain;

import lombok.Data;

@Data
public class GuestVO {
	private int idx;
	private String name;
	private String subject;
	private String  contents;
	private String regdate;
	private int readcnt;
	
}
