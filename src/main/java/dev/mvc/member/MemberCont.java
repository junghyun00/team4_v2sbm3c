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
    private MemberProcInter memberProc = null;
    
    public MemberCont(){
        System.out.println("-> MemberCont created.");
      }
    
    
    /**
     * id 중복 체크, JSON 출력
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
     * 등록 폼
     * @return
     */
     @RequestMapping(value="/member/create.do", method=RequestMethod.GET )
     public ModelAndView create() {
         ModelAndView mav = new ModelAndView();
   
         mav.setViewName("/member/create"); // webapp/member/create.jsp
  
         return mav; // forward
     }
    
    
     /**
      * 등록 처리
      * @param memberVO
      * @return
      */
     @RequestMapping(value="/member/create.do", method=RequestMethod.POST)
     public ModelAndView create(MemberVO memberVO){
         ModelAndView mav = new ModelAndView();
         
         memberVO.setGrade(20); // 기본 회원 가입 등록 20 지정   관리자 = 10, 일반 회원 = 20
         
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
      * 로그인 폼
      * @return
      */
     // http://localhost:9091/member/login.do 
     @RequestMapping(value = "/member/login.do", method = RequestMethod.GET)
     public ModelAndView login(HttpServletRequest request,
                                                    @RequestParam(value="return_url", defaultValue="") String return_url) {
       ModelAndView mav = new ModelAndView();
       
       Cookie[] cookies = request.getCookies();
       Cookie cookie = null;

       String ck_id = ""; // id 저장
       String ck_id_save = ""; // id 저장 여부를 체크
       String ck_passwd = ""; // passwd 저장
       String ck_passwd_save = ""; // passwd 저장 여부를 체크

       if (cookies != null) {
         for (int i=0; i < cookies.length; i++){
           cookie = cookies[i]; // 쿠키 객체 추출
           
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
      * Cookie 기반 로그인 처리
      * @param request Cookie를 읽기위해 필요
      * @param response Cookie를 쓰기위해 필요
      * @param session 로그인 정보를 메모리에 기록
      * @param id  회원 아이디
      * @param passwd 회원 패스워드
      * @param id_save 회원 아이디 Cookie에 저장 여부
      * @param passwd_save 패스워드 Cookie에 저장 여부
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
       //count = 0;  // else 테스트
       
       if (count == 1) { // 로그인 성공
         // System.out.println(id + " 로그인 성공");
         MemberVO memberVO = memberProc.readById(id);
         session.setAttribute("memberno", memberVO.getMemberno()); // 서버의 메모리에 기록
         session.setAttribute("id", id);
         session.setAttribute("name", memberVO.getName());
         session.setAttribute("grade", memberVO.getGrade());
         
         // -------------------------------------------------------------------
         // id 관련 쿠기 저장
         // -------------------------------------------------------------------
         if (id_save.equals("Y")) { // id를 저장할 경우, Checkbox를 체크한 경우
           Cookie ck_id = new Cookie("ck_id", id);
           ck_id.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
           ck_id.setMaxAge(60 * 60 * 72 * 10); // 30 day, 초단위
           response.addCookie(ck_id); // id 저장
         } else { // N, id를 저장하지 않는 경우, Checkbox를 체크 해제한 경우
           Cookie ck_id = new Cookie("ck_id", "");
           ck_id.setPath("/");
           ck_id.setMaxAge(0);
           response.addCookie(ck_id); // id 저장
         }
         // id를 저장할지 선택하는  CheckBox 체크 여부
         Cookie ck_id_save = new Cookie("ck_id_save", id_save);
         ck_id_save.setPath("/");
         ck_id_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
         response.addCookie(ck_id_save);
         // -------------------------------------------------------------------

         // -------------------------------------------------------------------
         // Password 관련 쿠기 저장
         // -------------------------------------------------------------------
         if (passwd_save.equals("Y")) { // 패스워드 저장할 경우
           Cookie ck_passwd = new Cookie("ck_passwd", passwd);
           ck_passwd.setPath("/");
           ck_passwd.setMaxAge(60 * 60 * 72 * 10); // 30 day
           response.addCookie(ck_passwd);
         } else { // N, 패스워드를 저장하지 않을 경우
           Cookie ck_passwd = new Cookie("ck_passwd", "");
           ck_passwd.setPath("/");
           ck_passwd.setMaxAge(0);
           response.addCookie(ck_passwd);
         }
         // passwd를 저장할지 선택하는  CheckBox 체크 여부
         Cookie ck_passwd_save = new Cookie("ck_passwd_save", passwd_save);
         ck_passwd_save.setPath("/");
         ck_passwd_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
         response.addCookie(ck_passwd_save);
         // -------------------------------------------------------------------
         System.out.println("-> return_url: " + return_url);
         
         if (return_url.length() > 0) { // ★
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
      * 로그아웃 처리
      * @param session
      * @return
      */
     @RequestMapping(value="/member/logout.do", method=RequestMethod.GET)
     public ModelAndView logout(HttpSession session){
       ModelAndView mav = new ModelAndView();
       session.invalidate(); // 모든 session 변수 삭제
       
       mav.setViewName("redirect:/");
       
       return mav;
     }
     
     
     /**
      * 회원 목록 + 검색 + 페이징
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
         map.put("now_page", now_page);  // 페이지에 출력할 레코드의 범위를 산출하기위해 사용
         
         // 검색 목록
         List<MemberVO> list = memberProc.member_list(map);
         mav.addObject("list", list);
         
         // 검색 레코드 개수
         int search_count = memberProc.search_count(map);
         mav.addObject("search_count", search_count);
         
         // 페이지 목록 문자열 생성
         String paging = memberProc.pagingBox(search_count, now_page, id);
         mav.addObject("paging", paging);
         mav.addObject("now_page", now_page);
         
         mav.setViewName("/admin/member_list");

         return mav;
     }
     
     
     
     /**
      * 회원 조회
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
      * 회원 정보 수정 처리
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
      * 회원 삭제
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
      * 회원 삭제 처리
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
      * 패스워드를 변경합니다.
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
//      * 패스워드를 변경 ajax
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
      * 패스워드 변경 처리
      * @param memberno 회원 번호
      * @param current_passwd 현재 패스워드
      * @param new_passwd 새로운 패스워드
      * @return
      */
     @RequestMapping(value="/mypage/passwd_update.do", method=RequestMethod.POST)
     public ModelAndView passwd_update(int memberno, String current_passwd, String new_passwd){
       ModelAndView mav = new ModelAndView();
       
       MemberVO memberVO = this.memberProc.read(memberno);
       mav.addObject("name", memberVO.getName());
       mav.addObject("id", memberVO.getId());
       
       
       // 현재 패스워드 검사
       HashMap<Object, Object> map = new HashMap<Object, Object>();
       map.put("memberno", memberno);
       map.put("passwd", current_passwd);
       
       int cnt = memberProc.passwd_check(map);
       int update_cnt = 0; // 변경된 패스워드 수
       
       
       if (cnt == 1) { // 현재 패스워드가 일치하는 경우
         map.put("passwd", new_passwd); // 새로운 패스워드를 저장
         update_cnt = memberProc.passwd_update(map); // 패스워드 변경 처리
         //update_cnt=0;   // update_cnt의 else 테스트
         
         if (update_cnt == 1) {
             mav.addObject("code", "passwd_update_success");   // 패스워드 변경 성공
         } else {
             cnt =0;    // 패스워드는 일치했으나 변경은 실패함
             mav.addObject("code", "passwd_update_fail");   // 패스워드 변경 실패
         }
         
         mav.addObject("update_cnt", update_cnt);  // 변경된 패스워드의 갯수    
       } else {
           mav.addObject("code", "passwd_fail");   // 패스워드가 일치하지 않는 경우
       }
       
       mav.addObject("url", "/mypage/msg");  
       mav.setViewName("/mypage/msg");
       
       return mav;
     }
     
     
     
     
     /**
      * 회원이 자기 정보 수정
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
      * 회원이 자기 정보 수정 처리
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
