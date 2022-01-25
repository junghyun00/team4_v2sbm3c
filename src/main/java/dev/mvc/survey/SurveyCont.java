package dev.mvc.survey;


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

import lombok.extern.slf4j.Slf4j;

@Controller
public class SurveyCont {

	@Autowired
	@Qualifier("dev.mvc.park.SurveyPorc")
	private SurveyProcInter surveyproc;

	// http://localhost:9091/survey/surveyform.do
	@RequestMapping(value = "/survey.do", method = RequestMethod.GET)
	public ModelAndView survey() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("/survey/surveyform");

		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/survey_create.do", method = RequestMethod.GET)
	public String survey_create(SurveyVO surveyvo) {
		int cnt = this.surveyproc.survey_create(surveyvo);
		
		JSONObject json = new JSONObject();
		
		json.put("cnt", cnt);
		return json.toString();
	}
	
	// http://localhost:9091/survey/result.do
	@RequestMapping(value = "/survey/result.do", method = RequestMethod.GET)
	  public ModelAndView result(@RequestParam int purposepark, @RequestParam int preferday, @RequestParam int reserveperiod) {

	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("/survey/result");  // /WEB-INF/views/survey/result.jsp
	    
	    Map<String, Integer> param = new HashMap<>();
	    param.put("purposepark", purposepark);
	    param.put("preferday", preferday);
	    param.put("reserveperiod", reserveperiod);
	    mav.addObject("param", param);
	    
	    return mav;
	  }
	
    @RequestMapping(value = "/survey/result.do", method = RequestMethod.POST)
    public ModelAndView survey_result(int memberno) {

        ModelAndView mav = new ModelAndView();
        
        SurveyVO surveyVO = this.surveyproc.read(memberno);
        
        mav.addObject("surveyVO", surveyVO);
        // System.out.println("purposepark" + surveyVO.getPurposepark());
        mav.setViewName("/survey/result");
        
        Map<String, String> param = new HashMap<>();
        param.put("purposepark", "purposepark");
        mav.addObject("param", param);
        
        return mav;
    }

}
