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

}
