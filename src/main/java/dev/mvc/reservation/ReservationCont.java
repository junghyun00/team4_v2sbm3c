 package dev.mvc.reservation;


import java.util.HashMap;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.park.ParkProcInter;
import dev.mvc.park.ParkVO;
import dev.mvc.qna.Qna;
import dev.mvc.qna.QnaVO;

@Controller
public class ReservationCont {
    
    @Autowired
    @Qualifier("dev.mvc.reservation.ReservationProc")
    private ReservationProcInter reservationProc;
    
    @Autowired
    @Qualifier("dev.mvc.park.ParkProc")
    private ParkProcInter parkProc;
    
    @Autowired
    @Qualifier("dev.mvc.member.MemberProc")
    private MemberProcInter memberProc;
    
    public ReservationCont() {
        System.out.println("-> ReservationCont created.");
    }
    
    @RequestMapping(value = "/reservation/reservation_create.do", method = RequestMethod.GET)
    public ModelAndView reservation_create(int parkno, @RequestParam(value="memberno", defaultValue="") int memberno) {
        ModelAndView mav = new ModelAndView();
        
        MemberVO memberVO = this.memberProc.read(memberno);
        mav.addObject("memberVO", memberVO);
        
        ParkVO parkVO = this.parkProc.read(parkno);
        mav.addObject("parkVO", parkVO);
        
        mav.setViewName("/reservation/reservation_create");
  
        return mav;
    }

    /**
     * 등록 처리
     * @param request
     * @param reservationVO
     * @return
     */
    @RequestMapping(value = "/reservation/reservation_create.do", method = RequestMethod.POST)
    public ModelAndView reservation_create(HttpServletRequest request, ReservationVO reservationVO) {
        ModelAndView mav = new ModelAndView();
        
        mav.addObject("reserveno", reservationVO.getReserveno());
        mav.addObject("memberno", reservationVO.getMemberno());
            
        int cnt = this.reservationProc.reservation_create(reservationVO);
        //cnt = 0;    // else 테스트
        
        mav.addObject("cnt", cnt);
        
        if (cnt == 1) {
            mav.setViewName("redirect:/mypage/my_reser_join.do?memberno=" + reservationVO.getMemberno());
        } else {
            mav.setViewName("/reservation/msg");
        }
        
        return mav;
    }   
    
    
    /**
     * 글 한 개 조회
     * @param reservationno
     * @return
     */
    @RequestMapping(value="/reservation/reservation_info.do", method=RequestMethod.GET )
    public ModelAndView read(int reserveno) {
        ModelAndView mav = new ModelAndView();
        
        ReservationVO reservationVO = this.reservationProc.read(reserveno);
        mav.addObject("reservationVO",reservationVO);
        
        mav.setViewName("/reservation/reservation_info");
        
        
        return mav;
        
    }
    

    /**
     * 목록 + 검색 + 페이징
     * @param carno
     * @param now_page
     * @return
     */
    @RequestMapping(value = "/reservation/reservation_list_search_paging.do", method = RequestMethod.GET)
    public ModelAndView reservation_list_search_paging(@RequestParam(value="carno", defaultValue="") String carno,
                                                                     @RequestParam(value = "now_page", defaultValue = "1") int now_page) {
        System.out.println("--> now_page: " + now_page);

        ModelAndView mav = new ModelAndView(); 
        
        HashMap<String, Object> map = new HashMap<String, Object>(); 
        map.put("carno", carno); // #{carno}
        map.put("now_page", now_page);  // 페이지에 출력할 레코드의 범위를 산출하기위해 사용
        
        // 검색 목록
        List<ReservationVO> list = reservationProc.reservation_list_search_paging(map);
        mav.addObject("list", list);
        
        // 검색 레코드 개수
        int search_count = reservationProc.search_count(map);
        mav.addObject("search_count", search_count);
        
        // 페이지 목록 문자열 생성
        String paging = reservationProc.pagingBox(search_count, now_page, carno);
        mav.addObject("paging", paging);
        mav.addObject("now_page", now_page);
        
        mav.setViewName("/reservation/reservation_list_search_paging");

        return mav;
        
    }
    
    /**
     * 마이페이지 삭제 폼
     * @param reserveno
     * @return
     */
    @RequestMapping(value="/mypage/my_reservation_delete.do", method=RequestMethod.GET )
    @ResponseBody
    public String my_reservation_delete(int reserveno) { 
        ReservationVO reservationVO = this.reservationProc.read(reserveno);
        
        JSONObject json = new JSONObject();
        json.put("reserveno", reservationVO.getReserveno());
        json.put("memberno", reservationVO.getMemberno());
        json.put("parkno", reservationVO.getParkno());
        json.put("reservedate", reservationVO.getReservedate());
        json.put("reservetime", reservationVO.getReservetime());
        json.put("carno", reservationVO.getCarno());
        json.put("notice", reservationVO.getNotice());
        
        
        return json.toString();
    }

    
    /**
     * 마이페이지 삭제 처리 ajax
     * @return
     */
    @RequestMapping(value = "/mypage/my_reservation_delete.do", method = RequestMethod.POST)
    public ModelAndView my_reservation_delete(HttpServletRequest request, ReservationVO reservationVO) {
      ModelAndView mav = new ModelAndView();
      
      mav.addObject("memberno", reservationVO.getMemberno());
      
      int reserveno = reservationVO.getReserveno();
      
      int cnt = 0;
      cnt = this.reservationProc.reser_delete(reserveno);
      
      mav.setViewName("redirect:/mypage/my_reser_join.do");
      
      return mav; // forward
    }   
       
    
    
    /**
     * 관리자용 삭제 처리 ajax
     * 
     * @return
     */
    @RequestMapping(value = "/admin/reservation_delete.do", method = RequestMethod.POST)
    public ModelAndView delete_admin(HttpServletRequest request, ReservationVO reservationVO) {
      ModelAndView mav = new ModelAndView();
      
      int reserveno = reservationVO.getReserveno();
      System.out.println("reservationVO :" +reservationVO);
      
      mav.addObject("memberno", reservationVO.getMemberno());
      
      int cnt = 0;
      cnt = this.reservationProc.reser_delete(reserveno); 
      
      mav.setViewName("redirect:/admin/reser_list.do");
      
      return mav;
    }
    

    
    /**
     * 회원별 예약 목록 조회
     * @param reserveno
     * @return
     */
    @RequestMapping(value="/mypage/my_reser.do", method=RequestMethod.GET)
    public ModelAndView my_reser(int memberno) {
        ModelAndView mav = new ModelAndView();
        
        List<ReservationVO> list = this.reservationProc.my_reser(memberno);
        mav.addObject("list", list);
        
        ParkVO parkVO = this.parkProc.my_park_read(memberno);
        mav.addObject("parkVO", parkVO);
        
        mav.setViewName("/mypage/my_reser");
        
        return mav;
    }
    
    
    /**
     * 회원별 예약 목록 Park + Reservation join
     * @return
     */
    @RequestMapping(value="/mypage/my_reser_join.do", method=RequestMethod.GET)
    public ModelAndView my_reser_join(int memberno) {
        ModelAndView mav = new ModelAndView();
        
        MemberVO memberVO = this.memberProc.read(memberno);
        mav.addObject("memberVO", memberVO);
        
        List<Park_ReservationVO> list = this.reservationProc.my_reser_join(memberno);
               
        
        mav.addObject("list", list);
        System.out.println(list);
        
        mav.setViewName("/mypage/my_reser_join");
        
        
        
        return mav;
    }
    
    
    /**
     * 관리자 확인용 예약 목록 Park + Reservation join
     * @return
     */
    @RequestMapping(value="/admin/reser_list.do", method=RequestMethod.GET)
    public ModelAndView reser_list() {
        ModelAndView mav = new ModelAndView();
        
        List<Park_ReservationVO> list = this.reservationProc.reser_list();
        mav.addObject("list", list);
        
        mav.setViewName("/admin/reser_list");
        
        return mav;
    }
    
    
    /**
     * 예약 변경 수정
     * @param reserveno
     * @return
     */
    @RequestMapping(value="/mypage/my_reser_update.do", method=RequestMethod.GET)
    public ModelAndView my_reser_update(int reserveno) {
        ModelAndView mav = new ModelAndView();
        
        ReservationVO reservationVO = this.reservationProc.my_reser_update_read(reserveno);
        int memberno = reservationVO.getMemberno();
        int parkno = reservationVO.getParkno();
        
        
        ParkVO parkVO = this.parkProc.read(parkno);
        MemberVO memberVO = this.memberProc.read(memberno);
        mav.addObject("memberVO", memberVO);
        mav.addObject("parkVO", parkVO);
        mav.addObject("reservationVO", reservationVO);
        
//        System.out.println("1. memberVO = " + memberVO);
//        System.out.println("2. parkVO = " + parkVO);
//        System.out.println("3. reservationVO = " + reservationVO);
        
        
        
        mav.setViewName("/mypage/my_reser_update");
        
        return mav;
    }
    
    /**
     * 예약 변경 수정 처리
     * @param reserveno
     * @return
     */
    @RequestMapping(value="/mypage/my_reser_update.do", method=RequestMethod.POST)
    public ModelAndView my_reser_update(ReservationVO reservationVO) {
        ModelAndView mav = new ModelAndView();
        System.out.println(reservationVO);
        
        int cnt = this.reservationProc.my_reser_update(reservationVO);
        System.out.println(cnt);
        
        
        mav.addObject("cnt", cnt);
        mav.addObject("parkno", reservationVO.getParkno());
        mav.addObject("memberno", reservationVO.getMemberno());
        mav.addObject("reserveno", reservationVO.getReserveno());
        
        mav.setViewName("redirect:/mypage/my_reser_join.do");
        
        return mav;
    }
    
    
    
    
}