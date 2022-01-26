package dev.mvc.reservation;


import java.util.HashMap;
import java.util.List;

import dev.mvc.park.ParkVO;
import dev.mvc.qna.QnaVO;

import java.util.List;



public interface ReservationDAOInter {
    
    /**
     * 예약 기능
     * @param ReservationVO
     * @return
     */
    public int reservation_create(ReservationVO reservationVO);


    /**
     * 예약 정보 조회
     * @param reserveno
     * @return
     */
    public ReservationVO read(int reserveno);
    

    /**
     * 예약 총 개수
     * @param hashMap
     * @return
     */
    public int search_count(HashMap<String, Object> hashMap);
    
    /**
     * 예약 내역 페이징 처리
     * @param map
     * @return
     */
    public List<ReservationVO>reservation_list_search_paging(HashMap<String, Object> map);
    
    
    /**
     * �삁�빟 �궘�젣
     * @param reserveno
     * @return
     */
    public int reser_delete(int reserveno);

    
    /**
     * �쉶�썝蹂� �삁�빟 紐⑸줉 議고쉶
     * @param reserveno
     * @return
     */
    public List<ReservationVO> my_reser(int memberno);
    
    
    /**
     * �쉶�썝蹂� �삁�빟 紐⑸줉 Park + Reservation join
     * @return
     */
    public List<Park_ReservationVO> my_reser_join(int memberno);
    
    
    /**
     * 愿�由ъ옄 �솗�씤�슜 �삁�빟 紐⑸줉 Park + Reservation join
     * @return
     */
    public List<Park_ReservationVO> reser_list();
    
    
    /**
     * �삁�빟 �젙蹂� �닔�젙
     * @param reservationVO
     * @return
     */
    public int my_reser_update(ReservationVO reservationVO);
    
    

}