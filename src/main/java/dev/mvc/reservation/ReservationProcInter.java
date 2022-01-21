package dev.mvc.reservation;

import java.util.HashMap;
import java.util.List;

import dev.mvc.park.ParkVO;

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
     * 페이지 목록 문자열 생성
     * @param now_page 현재 페이지
     * @param address 검색어
     * @return
     */
    public String pagingBox(int search_count, int now_page, String address);
    


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
     * 예약 정보 수정용 조회
     * @param reservationVO
     * @return
     */
    public ReservationVO my_reser_update_read(int reserveno);
    
    /**
     * 예약 정보 수정
     * @param reservationVO
     * @return
     */
    public int my_reser_update(ReservationVO reservationVO);
    
    
    
    
    
    public Park_ReservationVO my_reser_join_read(int reserveno);

}

