package dev.mvc.park;

import java.util.HashMap;
import java.util.List;

public interface ParkDAOInter {
    
    /**
     * 주차장 목록 조회
     * select id="park_list" resultType="dev.mvc.park.ParkVO"
     * @return
     */
    public List<ParkVO> park_list();
    
    
    /**
     * 목록 + 검색
     * @param map
     * @return
     */
    public List<ParkVO>park_list_search(HashMap<String, Object> map);
    
    
    /**
     * 검색 레코드 갯수
     * @param hashMap
     * @return
     */
    public int search_count(HashMap<String, Object> hashMap);
    
    
    /**
     * 목록 + 검색 + 페이징
     * @param map
     * @return
     */
    public List<ParkVO>park_list_search_paging(HashMap<String, Object> map);
    
    
    

}
