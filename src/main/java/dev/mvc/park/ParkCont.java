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
    @RequestMapping(value = "/park/park_list_search.do", method = RequestMethod.GET)
    public ModelAndView park_list_search(@RequestParam(value="address", defaultValue="") String address) {
        ModelAndView mav = new ModelAndView(); 
        
        HashMap<String, Object> map = new HashMap<String, Object>(); 
        map.put("address", address); // #{address}
        
        // 검색 목록
        List<ParkVO> list = parkProc.park_list_search(map);
        mav.addObject("list", list);
        
        mav.setViewName("park/park_list_search");
            
        return mav;
        
    }
    
    
}
