package dev.mvc.reservation;


import java.util.HashMap;
import java.util.List;

import dev.mvc.park.ParkVO;
import dev.mvc.qna.QnaVO;

import java.util.List;



public interface ReservationDAOInter {
    
    /**
     * 주차장 등록
     * @param ReservationVO
     * @return
     */
    public int reservation_create(ReservationVO reservationVO);


    /**
     * 글 한 개 조회
     * @param reserveno
     * @return
     */
    public ReservationVO read(int reserveno);
    

    /**
     * 검색 레코드 갯수
     * @param hashMap
     * @return
     */
    public int search_count(HashMap<String, Object> hashMap);
    
    /**
     * Reservation 목록 + 검색 + 페이징
     * @param map
     * @return
     */
    public List<ReservationVO>reservation_list_search_paging(HashMap<String, Object> map);
    
    
    /**
     * 예약 삭제
     * @param reserveno
     * @return
     */
    public int reser_delete(int reserveno);

    
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
    
    
    /**
     * 관리자 확인용 예약 목록 Park + Reservation join
     * @return
     */
    public List<Park_ReservationVO> reser_list();
    
    
    /**
     * 예약 정보 수정
     * @param reservationVO
     * @return
     */
    public int my_reser_update(ReservationVO reservationVO);
 

}