package dev.mvc.reservation;

import dev.mvc.park.ParkVO;

public interface ReservationDAOInter {
    
    /**
     * 주차장 등록
     * @param parkVO
     * @return
     */
    public int reservation_create(ReservationVO reservationVO);


/**
 * 글 한 개 조회
 * @param reserveno
 * @return
 */
public ReservationVO read(int reserveno);


}