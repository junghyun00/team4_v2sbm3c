package dev.mvc.reservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.park.ParkVO;
import dev.mvc.qna.QnaDAOInter;
import dev.mvc.reservation.ReservationProcInter;

@Component("dev.mvc.reservation.ReservationProc")
public class ReservationProc implements ReservationProcInter{
    
    @Autowired
    private ReservationDAOInter reservationDAO;
    
    public ReservationProc() {
        System.out.println("-> ReservationProc created");
    }

    @Override
    public int reservation_create(ReservationVO reservationVO) {
        int cnt = this.reservationDAO.reservation_create(reservationVO);
        return cnt;
    }
    // 글 한 개 조회
    @Override
    public ReservationVO read(int reserveno) {
        ReservationVO reservationVO = this.reservationDAO.read(reserveno);
        

        
        
        return reservationVO;
    }
    
    
    
    // 회원별 예약 목록 조회
    @Override
    public List<ReservationVO> my_reser(int memberno) {
        List<ReservationVO> list = this.reservationDAO.my_reser(memberno);
        
        return list;
    }
    
    
    // 회원별 예약 목록 Park + Reservation join
    @Override
    public List<Park_ReservationVO> my_reser_join(int memberno) {
        List<Park_ReservationVO> list = this.reservationDAO.my_reser_join(memberno);
        
        return list;
    }
   

}