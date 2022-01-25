package dev.mvc.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MemberCont {
	
    @Autowired
    @Qualifier("dev.mvc.member.MemberProc")
    private MemberProcInter memberProc;
    
    public MemberCont(){
        System.out.println("-> MemberCont created.");
      }
    
    
    /**
     * id 以묐났 泥댄겕, JSON 異쒕젰
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/member/checkID.do", method=RequestMethod.GET ,
                           produces = "text/plain;charset=UTF-8" )
    public String checkID(String id) {
        int cnt = this.memberProc.checkID(id);
        
        JSONObject json = new JSONObject();
        json.put("cnt", cnt);
       
        return json.toString(); 
    }
    
    
    /**
     * �벑濡� �뤌
     * @return
     */
     @RequestMapping(value="/member/create.do", method=RequestMethod.GET )
     public ModelAndView create() {
         ModelAndView mav = new ModelAndView();
   
         mav.setViewName("/member/create"); // webapp/member/create.jsp
  
         return mav; // forward
     }
    
    
     /**
      * �벑濡� 泥섎━
      * @param memberVO
      * @return
      */
     @RequestMapping(value="/member/create.do", method=RequestMethod.POST)
     public ModelAndView create(MemberVO memberVO){
         ModelAndView mav = new ModelAndView();
         
         memberVO.setGrade(20); // 湲곕낯 �쉶�썝 媛��엯 �벑濡� 20 吏��젙   愿�由ъ옄 = 10, �씪諛� �쉶�썝 = 20
         
         int cnt= memberProc.create(memberVO);
         
         MemberVO memberVO2 = memberProc.readById(memberVO.getId());
         mav.addObject("memberno", memberVO2.getMemberno());
         
         if (cnt == 1) {
             mav.setViewName("/survey/surveyform");
         } else {
             mav.setViewName("/member/create_msg");
         }
        
         return mav;
         
     }
     
     
     
     /**
      * 濡쒓렇�씤 �뤌
      * @return
      */
     // http://localhost:9091/member/login.do 
     @RequestMapping(value = "/member/login.do", method = RequestMethod.GET)
     public ModelAndView login(HttpServletRequest request,
                                                    @RequestParam(value="return_url", defaultValue="") String return_url) {
       ModelAndView mav = new ModelAndView();
       
       Cookie[] cookies = request.getCookies();
       Cookie cookie = null;

       String ck_id = ""; // id ���옣
       String ck_id_save = ""; // id ���옣 �뿬遺�瑜� 泥댄겕
       String ck_passwd = ""; // passwd ���옣
       String ck_passwd_save = ""; // passwd ���옣 �뿬遺�瑜� 泥댄겕

       if (cookies != null) {
         for (int i=0; i < cookies.length; i++){
           cookie = cookies[i]; // 荑좏궎 媛앹껜 異붿텧
           
           if (cookie.getName().equals("ck_id")){
             ck_id = cookie.getValue(); 
           }else if(cookie.getName().equals("ck_id_save")){
             ck_id_save = cookie.getValue();  // Y, N
           }else if (cookie.getName().equals("ck_passwd")){
             ck_passwd = cookie.getValue();         // 1234
           }else if(cookie.getName().equals("ck_passwd_save")){
             ck_passwd_save = cookie.getValue();  // Y, N
           }
         }
       }
       
       mav.addObject("ck_id", ck_id); 
       mav.addObject("ck_id_save", ck_id_save);
       mav.addObject("ck_passwd", ck_passwd);
       mav.addObject("ck_passwd_save", ck_passwd_save);
       mav.addObject("return_url", return_url);
       
       mav.setViewName("/member/login");
       return mav;
     }
      
     
     /**
      * Cookie 湲곕컲 濡쒓렇�씤 泥섎━
      * @param request Cookie瑜� �씫湲곗쐞�빐 �븘�슂
      * @param response Cookie瑜� �벐湲곗쐞�빐 �븘�슂
      * @param session 濡쒓렇�씤 �젙蹂대�� 硫붾え由ъ뿉 湲곕줉
      * @param id  �쉶�썝 �븘�씠�뵒
      * @param passwd �쉶�썝 �뙣�뒪�썙�뱶
      * @param id_save �쉶�썝 �븘�씠�뵒 Cookie�뿉 ���옣 �뿬遺�
      * @param passwd_save �뙣�뒪�썙�뱶 Cookie�뿉 ���옣 �뿬遺�
      * @return
      */
     // http://localhost:9091/member/login.do 
     @RequestMapping(value = "/member/login.do", 
                                method = RequestMethod.POST)
     public ModelAndView login_cookie_proc(
                                HttpServletRequest request,
                                HttpServletResponse response,
                                HttpSession session,
                                String id, String passwd,
                                @RequestParam(value="id_save", defaultValue="") String id_save,
                                @RequestParam(value="passwd_save", defaultValue="") String passwd_save,
                                @RequestParam(value="return_url", defaultValue="") String return_url) {
       ModelAndView mav = new ModelAndView();
       Map<String, Object> map = new HashMap<String, Object>();
       map.put("id", id);
       map.put("passwd", passwd);
       
       int count = memberProc.login(map);
       //count = 0;  // else �뀒�뒪�듃
       
       if (count == 1) { // 濡쒓렇�씤 �꽦怨�
         // System.out.println(id + " 濡쒓렇�씤 �꽦怨�");
         MemberVO memberVO = memberProc.readById(id);
         session.setAttribute("memberno", memberVO.getMemberno()); // �꽌踰꾩쓽 硫붾え由ъ뿉 湲곕줉
         session.setAttribute("id", id);
         session.setAttribute("name", memberVO.getName());
         session.setAttribute("grade", memberVO.getGrade());
         
         // -------------------------------------------------------------------
         // id 愿��젴 荑좉린 ���옣
         // -------------------------------------------------------------------
         if (id_save.equals("Y")) { // id瑜� ���옣�븷 寃쎌슦, Checkbox瑜� 泥댄겕�븳 寃쎌슦
           Cookie ck_id = new Cookie("ck_id", id);
           ck_id.setPath("/");  // root �뤃�뜑�뿉 荑좏궎瑜� 湲곕줉�븿�쑝濡� 紐⑤뱺 寃쎈줈�뿉�꽌 荑좉린 �젒洹� 媛��뒫
           ck_id.setMaxAge(60 * 60 * 72 * 10); // 30 day, 珥덈떒�쐞
           response.addCookie(ck_id); // id ���옣
         } else { // N, id瑜� ���옣�븯吏� �븡�뒗 寃쎌슦, Checkbox瑜� 泥댄겕 �빐�젣�븳 寃쎌슦
           Cookie ck_id = new Cookie("ck_id", "");
           ck_id.setPath("/");
           ck_id.setMaxAge(0);
           response.addCookie(ck_id); // id ���옣
         }
         // id瑜� ���옣�븷吏� �꽑�깮�븯�뒗  CheckBox 泥댄겕 �뿬遺�
         Cookie ck_id_save = new Cookie("ck_id_save", id_save);
         ck_id_save.setPath("/");
         ck_id_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
         response.addCookie(ck_id_save);
         // -------------------------------------------------------------------

         // -------------------------------------------------------------------
         // Password 愿��젴 荑좉린 ���옣
         // -------------------------------------------------------------------
         if (passwd_save.equals("Y")) { // �뙣�뒪�썙�뱶 ���옣�븷 寃쎌슦
           Cookie ck_passwd = new Cookie("ck_passwd", passwd);
           ck_passwd.setPath("/");
           ck_passwd.setMaxAge(60 * 60 * 72 * 10); // 30 day
           response.addCookie(ck_passwd);
         } else { // N, �뙣�뒪�썙�뱶瑜� ���옣�븯吏� �븡�쓣 寃쎌슦
           Cookie ck_passwd = new Cookie("ck_passwd", "");
           ck_passwd.setPath("/");
           ck_passwd.setMaxAge(0);
           response.addCookie(ck_passwd);
         }
         // passwd瑜� ���옣�븷吏� �꽑�깮�븯�뒗  CheckBox 泥댄겕 �뿬遺�
         Cookie ck_passwd_save = new Cookie("ck_passwd_save", passwd_save);
         ck_passwd_save.setPath("/");
         ck_passwd_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
         response.addCookie(ck_passwd_save);
         // -------------------------------------------------------------------
         System.out.println("-> return_url: " + return_url);
         
         if (return_url.length() > 0) { // �쁾
           mav.setViewName("redirect:" + return_url);  
         } else {
           mav.setViewName("redirect:/");
         }
           
       } else {
         mav.setViewName("/member/login_fail_msg"); 
       }
           
       return mav;
     }
     
     
     /**
      * 濡쒓렇�븘�썐 泥섎━
      * @param session
      * @return
      */
     @RequestMapping(value="/member/logout.do", method=RequestMethod.GET)
     public ModelAndView logout(HttpSession session){
       ModelAndView mav = new ModelAndView();
       session.invalidate(); // 紐⑤뱺 session 蹂��닔 �궘�젣
       
       mav.setViewName("redirect:/");
       
       return mav;
     }
     
     
     /**
      * �쉶�썝 紐⑸줉 + 寃��깋 + �럹�씠吏�
      * @param address
      * @param now_page
      * @return
      */
     @RequestMapping(value = "/admin/member_list.do", method = RequestMethod.GET)
     public ModelAndView member_list(@RequestParam(value="id", defaultValue="") String id,
                                          @RequestParam(value = "now_page", defaultValue = "1") int now_page) {
         
         ModelAndView mav = new ModelAndView(); 
         
         HashMap<String, Object> map = new HashMap<String, Object>(); 
         map.put("id", id); // #{address}
         map.put("now_page", now_page);  // �럹�씠吏��뿉 異쒕젰�븷 �젅肄붾뱶�쓽 踰붿쐞瑜� �궛異쒗븯湲곗쐞�빐 �궗�슜
         
         // 寃��깋 紐⑸줉
         List<MemberVO> list = memberProc.member_list(map);
         mav.addObject("list", list);
         
         // 寃��깋 �젅肄붾뱶 媛쒖닔
         int search_count = memberProc.search_count(map);
         mav.addObject("search_count", search_count);
         
         // �럹�씠吏� 紐⑸줉 臾몄옄�뿴 �깮�꽦
         String paging = memberProc.pagingBox(search_count, now_page, id);
         mav.addObject("paging", paging);
         mav.addObject("now_page", now_page);
         
         mav.setViewName("/admin/member_list");

         return mav;
     }
     
     
     
     /**
      * �쉶�썝 議고쉶
      * @param memberno
      * @return
      */
     @RequestMapping(value="/admin/member_update.do", method=RequestMethod.GET)
     public ModelAndView member_update(int memberno){
       ModelAndView mav = new ModelAndView();
       
       MemberVO memberVO = this.memberProc.read(memberno);
       mav.addObject("memberVO", memberVO);
       
       mav.setViewName("/admin/member_update"); 
       
       return mav;
     }
     
     
     
     /**
      * �쉶�썝 �젙蹂� �닔�젙 泥섎━
      * @param memberVO
      * @return
      */
     @RequestMapping(value="/admin/member_update.do", method=RequestMethod.POST)
     public ModelAndView member_update(MemberVO memberVO){
       ModelAndView mav = new ModelAndView();
       
       int cnt= memberProc.member_update(memberVO);
       
       mav.addObject("cnt", cnt);
       mav.addObject("memberno", memberVO.getMemberno());

       mav.setViewName("redirect:/admin/member_list.do");
       
       return mav;
     }
     
     
     
     /**
      * �쉶�썝 �궘�젣
      * @param memberno
      * @return
      */
     @RequestMapping(value="/admin/member_delete.do", method=RequestMethod.GET)
     public ModelAndView member_delete(int memberno){
       ModelAndView mav = new ModelAndView();
       
       MemberVO memberVO = this.memberProc.read(memberno);
       mav.addObject("memberVO", memberVO);
       mav.setViewName("/admin/member_delete"); // /member/delete.jsp
       
       return mav; // forward
     }
    
     
     
     /**
      * �쉶�썝 �궘�젣 泥섎━
      * @param memberVO
      * @return
      */
     @RequestMapping(value="/admin/member_delete.do", method=RequestMethod.POST)
     public ModelAndView member_delete_proc(int memberno){
       ModelAndView mav = new ModelAndView();
       
       MemberVO memberVO = this.memberProc.read(memberno);
       
       int cnt= memberProc.member_delete(memberno);
       mav.addObject("cnt", cnt);
       mav.addObject("memberno", memberVO.getMemberno());

       mav.setViewName("redirect:/admin/member_list.do");
       
       return mav;
     }
     
     
     
     /**
      * �뙣�뒪�썙�뱶瑜� 蹂�寃쏀빀�땲�떎.
      * @param memberno
      * @return
      */
     @RequestMapping(value="/mypage/passwd_update.do", method=RequestMethod.GET)
     public ModelAndView passwd_update(int memberno){
       ModelAndView mav = new ModelAndView();
       
       MemberVO memberVO = this.memberProc.read(memberno);
       mav.addObject("memberVO", memberVO);
       
       mav.setViewName("/mypage/passwd_update");
       
       return mav;
     }
     
//     /**
//      * �뙣�뒪�썙�뱶瑜� 蹂�寃� ajax
//      * @param memberno
//      * @return
//      */
//     @RequestMapping(value="/mypage/passwd_update.do", method=RequestMethod.GET)
//     @ResponseBody
//     public String passwd_update_ajax(int memberno) {
//         MemberVO memberVO = this.memberProc.read(memberno);
//       //System.out.println(memberVO);
//         
//         JSONObject json = new JSONObject();
//         json.put("memberno", memberVO.getMemberno());
//         json.put("id", memberVO.getId());
//         json.put("passwd", memberVO.getPasswd());
//         json.put("name", memberVO.getName());
//         json.put("address", memberVO.getAddress());
//         json.put("phone", memberVO.getPhone());
//         json.put("email", memberVO.getEmail());
//         json.put("grade", memberVO.getGrade());
//         
//         //System.out.println(json);
//         
//         return json.toString();
//     }
     
     
     /**
      * �뙣�뒪�썙�뱶 蹂�寃� 泥섎━
      * @param memberno �쉶�썝 踰덊샇
      * @param current_passwd �쁽�옱 �뙣�뒪�썙�뱶
      * @param new_passwd �깉濡쒖슫 �뙣�뒪�썙�뱶
      * @return
      */
     @RequestMapping(value="/mypage/passwd_update.do", method=RequestMethod.POST)
     public ModelAndView passwd_update(int memberno, String current_passwd, String new_passwd){
       ModelAndView mav = new ModelAndView();
       
       MemberVO memberVO = this.memberProc.read(memberno);
       mav.addObject("name", memberVO.getName());
       mav.addObject("id", memberVO.getId());
       
       
       // �쁽�옱 �뙣�뒪�썙�뱶 寃��궗
       HashMap<Object, Object> map = new HashMap<Object, Object>();
       map.put("memberno", memberno);
       map.put("passwd", current_passwd);
       
       int cnt = memberProc.passwd_check(map);
       int update_cnt = 0; // 蹂�寃쎈맂 �뙣�뒪�썙�뱶 �닔
       
       
       if (cnt == 1) { // �쁽�옱 �뙣�뒪�썙�뱶媛� �씪移섑븯�뒗 寃쎌슦
         map.put("passwd", new_passwd); // �깉濡쒖슫 �뙣�뒪�썙�뱶瑜� ���옣
         update_cnt = memberProc.passwd_update(map); // �뙣�뒪�썙�뱶 蹂�寃� 泥섎━
         //update_cnt=0;   // update_cnt�쓽 else �뀒�뒪�듃
         
         if (update_cnt == 1) {
             mav.addObject("code", "passwd_update_success");   // �뙣�뒪�썙�뱶 蹂�寃� �꽦怨�
         } else {
             cnt =0;    // �뙣�뒪�썙�뱶�뒗 �씪移섑뻽�쑝�굹 蹂�寃쎌� �떎�뙣�븿
             mav.addObject("code", "passwd_update_fail");   // �뙣�뒪�썙�뱶 蹂�寃� �떎�뙣
         }
         
         mav.addObject("update_cnt", update_cnt);  // 蹂�寃쎈맂 �뙣�뒪�썙�뱶�쓽 媛��닔    
       } else {
           mav.addObject("code", "passwd_fail");   // �뙣�뒪�썙�뱶媛� �씪移섑븯吏� �븡�뒗 寃쎌슦
       }
       
       mav.addObject("url", "/mypage/msg");  
       mav.setViewName("/mypage/msg");
       
       return mav;
     }
     
     
     
     
     /**
      * �쉶�썝�씠 �옄湲� �젙蹂� �닔�젙
      * @param memberno
      * @return
      */
     @RequestMapping(value="/mypage/me_update.do", method=RequestMethod.GET)
     public ModelAndView member_update1(int memberno){
       ModelAndView mav = new ModelAndView();
       
       MemberVO memberVO = this.memberProc.read(memberno);
       mav.addObject("memberVO", memberVO);
       
       
       mav.setViewName("/mypage/me_update"); 
       
       return mav;
     }
     
     
     
     /**
      * �쉶�썝�씠 �옄湲� �젙蹂� �닔�젙 泥섎━
      * @param memberVO
      * @return
      */
     @RequestMapping(value="/mypage/me_update.do", method=RequestMethod.POST)
     public ModelAndView member_update1(MemberVO memberVO){
       ModelAndView mav = new ModelAndView();
       
       int cnt= memberProc.member_update(memberVO);
       
       mav.addObject("cnt", cnt);
       mav.addObject("memberno", memberVO.getMemberno());

       mav.setViewName("redirect:/");
       
       return mav;
     }
     
}
