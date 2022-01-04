 package dev.mvc.reservation;


import java.util.HashMap;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.member.MemberProcInter;
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
    
    @Qualifier("dev.mvc.member.MemberProc")
    private MemberProcInter memberProc;
    
    public ReservationCont() {
        System.out.println("-> ReservationCont created.");
    }
    
    @RequestMapping(value = "/reservation/reservation_create.do", method = RequestMethod.GET)
    public ModelAndView reservation_create(int parkno) {
        ModelAndView mav = new ModelAndView();
        
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
        System.out.println(reservationVO.getReserveno());   
        
        
            
        int cnt = this.reservationProc.reservation_create(reservationVO);
        //cnt = 0;    // else 테스트
        
        mav.addObject("cnt", cnt);
        
        if (cnt == 1) {
            mav.setViewName("redirect:/reservation/reservation_info.do?reserveno=" + reservationVO.getReserveno());
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
     * 삭제 폼
     * @param reserveno
     * @return
     */
    @RequestMapping(value="/reservation/reservation_delete.do", method=RequestMethod.GET )
    public ModelAndView delete(int reserveno) { 
      ModelAndView mav = new  ModelAndView();
      
      ReservationVO reservationVO = this.reservationProc.read(reserveno);
      // 삭제할 정보를 조회하여 확인
      mav.addObject("reservationVO", reservationVO);
           
      mav.setViewName("/reservation/reservation_delete");  // reservation/delete.jsp
      
      return mav; 
    }

    /**
     * 삭제 처리 http://localhost:9091/reservation/delete.do
     * 
     * @return
     */
    @RequestMapping(value = "/reservation/reservation_delete.do", method = RequestMethod.POST)
    public ModelAndView delete(HttpServletRequest request, ReservationVO reservationVO, 
                                            int now_page,
                                            @RequestParam(value="word", defaultValue="") String word) {
      ModelAndView mav = new ModelAndView();
      int reserveno = reservationVO.getReserveno();
     
      int cnt = 0;
          // -------------------------------------------------------------------
          // 파일 삭제 코드 시작
          // -------------------------------------------------------------------
          // 삭제할 파일 정보를 읽어옴.
      ReservationVO vo = reservationProc.read(reserveno);
//          System.out.println("contentsno: " + vo.getContentsno());
//          System.out.println("file1: " + vo.getFile1());
      long size1 = 0;
      boolean sw = false;
          
     
      /*
       * String upDir = System.getProperty("user.dir") +
       * "/src/main/resources/static/reservation/storage/"; // 절대 경로
       */
          // System.out.println("sw: " + sw);
          // -------------------------------------------------------------------
          // 파일 삭제 종료 시작
          // -------------------------------------------------------------------
          
      cnt = this.reservationProc.delete(reserveno); // DBMS 삭제
          
          // -------------------------------------------------------------------------------------
      System.out.println("-> reserveno: " + vo.getReserveno());
      System.out.println("-> word: " + word);
          
          // 마지막 페이지의 레코드 삭제시의 페이지 번호 -1 처리
      HashMap<String, Object> page_map = new HashMap<String, Object>();
      page_map.put("reserveno", vo.getReserveno());
      page_map.put("word", word);
          // 10번째 레코드를 삭제후
          // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면
          // 페이지수를 4 -> 3으로 감소 시켜야함.
      if (reservationProc.search_count(page_map) % Reservation.RECORD_PER_PAGE == 0) {
        now_page = now_page - 1;
            
      }
          // -------------------------------------------------------------------------------------
          
      mav.addObject("now_page", now_page);
      mav.setViewName("redirect:/reservation/reservation_list_search_paging.do"); 

      mav.addObject("reserveno", reservationVO.getReserveno());
      System.out.println("-> reserveno: " + reservationVO.getReserveno());
      
      return mav; // forward
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
        
        List<Reservation_ReservationVO> list = this.reservationProc.my_reser_join(memberno);
        mav.addObject("list", list);
        
        mav.setViewName("/mypage/my_reser_join");
        
        return mav;
    }
    

}