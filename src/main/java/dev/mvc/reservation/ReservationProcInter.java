package dev.mvc.reservation;

import java.util.List;

public interface ReservationProcInter {
    
    public int reservation_create(ReservationVO reservationVO);
    
    /**
     * 글 한 개 조회
     * @param reservationno
     * @return
     */
    public ReservationVO read(int reserveno);
    
    
    
    /**
     * 회원별 예약 목록 조회
     * @param reserveno
     * @return
     */
    public List<ReservationVO> my_reser(int memberno);
    
    
    /**
     * 회원별 예약 목록 Park + Reservation join
     * @return
     */
    public List<Park_ReservationVO> my_reser_join(int memberno);
}