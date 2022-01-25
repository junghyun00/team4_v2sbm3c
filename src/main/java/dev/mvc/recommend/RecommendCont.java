package dev.mvc.recommend;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;

@Controller
public class RecommendCont {

    @Autowired
    @Qualifier("dev.mvc.recommend.RecommendProc")
    private RecommendProcInter recommendProc;
    
    @Autowired
    @Qualifier("dev.mvc.member.MemberProc")
    private MemberProcInter memberProc;
    
    // http://localhost:9091/recommend/recommend_surveyform.do
    @RequestMapping(value = "/recommend_surveyform.do", method = RequestMethod.GET)
    public ModelAndView survey(int memberno) {
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("memberno", memberno);
//        System.out.println(memberno);
        mav.setViewName("/recommend/recommend_surveyform");

        return mav;
    }
    
    @ResponseBody
    @RequestMapping(value = "/recommend/recommend_create.do", method = RequestMethod.GET)
    public String recommend_create(RecommendVO recommendVO) {
        
        ModelAndView mav = new ModelAndView();
        
        MemberVO memberVO2 = memberProc.readByMemberno(recommendVO.getMemberno());
        mav.addObject("memberno", memberVO2.getMemberno());
//        System.out.println("memberno" + memberVO2.getMemberno());
        
        int cnt = this.recommendProc.recommend_create(recommendVO);
        recommendVO = this.recommendProc.read(recommendVO.getMemberno());
        
        mav.addObject("memberVO", recommendVO);
        JSONObject json = new JSONObject();
        
        json.put("cnt", cnt);
        
        return json.toString();
    }
    
    // http://localhost:9091/recommend/result.do
    @RequestMapping(value = "/recommend/result.do", method = RequestMethod.GET)
      public ModelAndView result(@RequestParam int purposepark, @RequestParam int preferday, @RequestParam int reserveperiod) {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/recommend/result");  // /WEB-INF/views/recommend/result.jsp
        
        Map<String, Integer> param = new HashMap<>();
        param.put("purposepark", purposepark);
        param.put("preferday", preferday);
        param.put("reserveperiod", reserveperiod);
        mav.addObject("param", param);
        
        return mav;
      }
    
    @RequestMapping(value = "/recommend/result.do", method = RequestMethod.POST)
    public ModelAndView survey_result(int memberno) {

        ModelAndView mav = new ModelAndView();
        
        RecommendVO recommendVO = this.recommendProc.read(memberno);
        
        mav.addObject("recommendVO", recommendVO);
        mav.setViewName("/recommend/result");
        
        Map<String, String> param = new HashMap<>();
        param.put("purposepark", "purposepark");
        mav.addObject("param", param);
        
        return mav;
    }
}
