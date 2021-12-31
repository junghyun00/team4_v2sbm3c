package dev.mvc.reservation;


import dev.mvc.park.ParkVO;

public interface ReservationProcInter {
    
    public int reservation_create(ReservationVO reservationVO);
    
    /**
     * 글 한 개 조회
     * @param reservationno
     * @return
     */
    public ReservationVO read(int reserveno);
}