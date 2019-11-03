package net.websnet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import net.websnet.domain.MemberVO;
import net.websnet.service.MemberService;

@Controller
@AllArgsConstructor
@RequestMapping("Member")
public class MemberController {
   private static final Logger log = 
         LoggerFactory.getLogger(MemberController.class);
   private MemberService service; 
   
   @GetMapping("userinfo_list")//이것은 jsp파일 이름하고 동일하게 써야함
   public void memberList(Model model){//메소드 이름은 틀려도 괜찮
      log.info("리스트"+"memberList()...................");
      model.addAttribute("list", service.memberList());//여기서 리스트 값을 넣으니까
   }
   
   @GetMapping("userlogin_form")
   public void memberLogin() {
      log.info("로그인폼"+"memberLogin()....................");
   }
   
   @PostMapping("userlogin_form")
   public String memberLoginPro(MemberVO vo,Model model, HttpServletRequest request) {
      String passwd = service.userIdSearch(vo);
      if(passwd==null) {//아이디가 존재하지 않는다
         model.addAttribute("isRow",-1);
         return "/Member/userlogin_from";//로그인 입력폼
      }else {//id존재할경우
         if(passwd.equals(vo.getPasswd())) {//비번일치하면
         request.getSession().setAttribute("user", service.userLogin(vo));//이름을 유저로 세팅하였다
         request.getSession().setMaxInactiveInterval(1800);//세션유지
         return "/Member/userlogin_ok";
         }else {
        	 model.addAttribute("isRow",0);
        	 return "/Member/userlogin_form";//입력폼
         }
      }
   }
   @GetMapping("userLogout")
   public String memberLogout(HttpServletRequest request) {
      HttpSession session = request.getSession();
      session.invalidate();
      return "/Member/userinfo_list";


   }
}
