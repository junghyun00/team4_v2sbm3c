package dev.mvc.park;

import java.util.HashMap;
import java.util.List;

public interface ParkProcInter {
    
    /**
     * 주차장 목록 조회
     * select id="park_list" resultType="dev.mvc.park.ParkVO"
     * @return
     */
    public List<ParkVO> park_list();
    
    
    /**
     * 검색 + 페이징 목록
     * @param map
     * @return
     */
    public List<ParkVO>park_list_search(HashMap<String, Object> map);

}
