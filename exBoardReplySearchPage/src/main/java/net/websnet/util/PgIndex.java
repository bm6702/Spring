package net.websnet.util;

public class PgIndex {
	public static String pageList(int page, int totpage, String url, String addtag) {
		int idx_pre, idx_start;
		
		String s_home="";// 첫페이지로 이동
		String s_pre="";//Prev 저장변수
		String s_idx="";//번호저장변수	
		String s_next="";//Next저장변수
		String s_end="";//마지막 페이지 저장변수
		
		idx_start = ((page-1)/10)*10 + 1;  // 시작페이지 계산
		idx_pre = ((page-1)/10); //Preview 계산
		
		//Prev 표시부분
		if(idx_pre > 0) {
			s_pre = "<a href='"+url+"?page="+(idx_pre*10)+addtag+"'>"
					+ "<img src=/img/i_prev.gif width=30 height=7></a>";
		}else {
			s_pre = "<img src=/img/i_prev.gif width=30 height=7>";
		}
		//번호 표시부분
		for(int i=0; i<5; i++, idx_start++) {
			if(idx_start>totpage)
				break;
			if(idx_start == page) {
				s_idx = s_idx + " " + idx_start+ " ";
			}else{
				s_idx = s_idx + " <a href='"+url+"?page="+idx_start;
				s_idx = s_idx + addtag + "'> " + idx_start + " </a>";
			}
		}
		//Next 표시부분
		if(idx_start <= totpage) {
			s_next="<a href='"+url+"?page="+idx_start+addtag+"'>"
					+ "<img src=/img/i_next.gif width=30 height=7></a>";
		}else{
			s_next=" <img src=/img/i_next.gif width=30 height=7>";
		}
		
		String outHtml = s_home + s_pre + s_idx + s_next + s_end;
		return outHtml;
	}
}
