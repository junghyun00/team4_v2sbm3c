package dev.mvc.team4;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class HomeCont {
    public HomeCont() {
       System.out.println("-> HomeCont created.");
    }
    
    // http://localhost:9091
    @RequestMapping(value = {"/", "/index.do"}, method = RequestMethod.GET)
    public ModelAndView home() {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/index");  // /WEB-INF/views/index.jsp
      
      return mav;
    }
    
}
