package dev.mvc.park;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ParkCont {
    @Autowired
    @Qualifier("dev.mvc.park.ParkProc")
    private ParkProcInter parkProc;
    
    public ParkCont() {
        System.out.println("-> ParkCont created.");
    }
    
    
    /**
     * 목록 조회
     * @return
     */
    // http://localhost:9091/park/park_list.do
    @RequestMapping(value = "/park/park_list.do", method = RequestMethod.GET)
    public ModelAndView park_list() {
        ModelAndView mav = new ModelAndView();
        List<ParkVO> list = this.parkProc.park_list();
        
        mav.addObject("list", list);
        mav.setViewName("/park/park_list");
        
        return mav;
    }
    
    /**
     * 목록 + 검색
     * @param address
     * @return
     */
    // http://localhost:9091/park/park_list_search.do
    @RequestMapping(value = "/park/park_list_search.do", method = RequestMethod.GET)
    public ModelAndView park_list_search(@RequestParam(value="address", defaultValue="") String address) {
        ModelAndView mav = new ModelAndView(); 
        
        HashMap<String, Object> map = new HashMap<String, Object>(); 
        map.put("address", address); // #{address}
        
        // 검색 목록
        List<ParkVO> list = parkProc.park_list_search(map);
        mav.addObject("list", list);
        
        mav.setViewName("/park/park_list_search");
            
        return mav;
        
    }
    
    
    /**
     * 목록 + 검색 + 페이징
     * @param address
     * @param now_page
     * @return
     */
    @RequestMapping(value = "/park/park_list_search_paging.do", method = RequestMethod.GET)
    public ModelAndView park_list_search_paging(@RequestParam(value="address", defaultValue="") String address,
                                                                     @RequestParam(value = "now_page", defaultValue = "1") int now_page) {
        System.out.println("--> now_page: " + now_page);

        ModelAndView mav = new ModelAndView(); 
        
        HashMap<String, Object> map = new HashMap<String, Object>(); 
        map.put("address", address); // #{address}
        map.put("now_page", now_page);
        
        // 검색 목록
        List<ParkVO> list = parkProc.park_list_search_paging(map);
        mav.addObject("list", list);
        
        // 검색 레코드 개수
        int search_count = parkProc.search_count(map);
        mav.addObject("search_count", search_count);
        
        // 페이지 목록 문자열 생성
        String paging = parkProc.pagingBox(search_count, now_page, address);
        mav.addObject("paging", paging);
        mav.addObject("now_page", now_page);
        
        mav.setViewName("/park/park_list_search_paging");

        return mav;
        
    }
    
    
}
