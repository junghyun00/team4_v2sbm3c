 package dev.mvc.reservation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.member.MemberProcInter;
import dev.mvc.park.ParkProcInter;
import dev.mvc.park.ParkVO;

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

        
        int cnt = this.reservationProc.reservation_create(reservationVO);
        //cnt = 0;    // else 테스트
        
        mav.addObject("cnt", cnt);
        
        if (cnt == 1) {
            mav.setViewName("redirect:/reservation/reservation_info.do");
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
        
        List<Park_ReservationVO> list = this.reservationProc.my_reser_join(memberno);
        mav.addObject("list", list);
        
        mav.setViewName("/mypage/my_reser_join");
        
        return mav;
    }
    
}