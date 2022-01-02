package dev.mvc.survey;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SurveyCont {

	@Autowired
	@Qualifier("dev.mvc.park.SurveyPorc")
	private SurveyProcInter surveyproc;

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

}
