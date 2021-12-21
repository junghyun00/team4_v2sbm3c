package dev.mvc.park;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.park.ParkProc")
public class ParkProc implements ParkProcInter{
    
    @Autowired
    private ParkDAOInter parkDAO;
    
    public ParkProc() {
        System.out.println("-> ParkProc created");
    }
    
    
    // 주차장 목록 조회
    @Override
    public List<ParkVO> park_list() {
        List<ParkVO> list = this.parkDAO.park_list();
        return list;
    }

    
    // 검색 목록
    @Override
    public List<ParkVO> park_list_search(HashMap<String, Object> hashMap) {
        List<ParkVO> list = parkDAO.park_list_search(hashMap);

        return list;
    }

}
