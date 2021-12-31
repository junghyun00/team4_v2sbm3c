package dev.mvc.reservation;

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
   

}