package dev.mvc.park;

import java.util.HashMap;
import java.util.List;

public interface ParkDAOInter {
    
    /**
     * 검색 레코드 갯수
     * @param hashMap
     * @return
     */
    public int search_count(HashMap<String, Object> hashMap);
    
    
    /**
     * 주차장 목록 + 검색 + 페이징
     * @param map
     * @return
     */
    public List<ParkVO>park_list_search_paging(HashMap<String, Object> map);
    
    
    /**
     * 주차장 등록
     * @param parkVO
     * @return
     */
    public int park_create(ParkVO parkVO);
    
    
    /**
     * 글 한 개 조회
     * @param parkno
     * @return
     */
    public ParkVO read(int parkno);
    
    
    
    /**
     * 회원별 등록한 주차장 목록
     * @param memberno
     * @return
     */
    public List<ParkVO> my_park(int memberno);
    
    
    /**
     * 회원별 등록한 주차장 목록 조회
     * @param parkno
     * @return
     */
    public ParkVO my_park_read(int parkno);
    
    
    /**
     * 주차장 정보 수정
     * @param parkVO
     * @return
     */
    public int my_park_update(ParkVO parkVO);
    
    
    /**
     * 주차장 이미지 파일 수정
     * @param parkVO
     * @return
     */
    public int my_park_update_file(ParkVO parkVO);
    
    
    /**
     * 주차장 삭제
     * @param parkno
     * @return
     */
    public int my_park_delete(int parkno);
    
    
    
    public List<ParkVO> only_address(String address);
    
    
    
    
}
