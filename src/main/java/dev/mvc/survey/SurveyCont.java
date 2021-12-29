package dev.mvc.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.park.ParkProcInter;

@Controller
public class SurveyCont {

	@Autowired
	@Qualifier("dev.mvc.park.SurveyPorc")
	private SurveyProcInter surveyproc;

	@RequestMapping(value = "/survey.do", method = RequestMethod.GET)
	public ModelAndView park_create() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("/survey/surveyform");

		return mav;
	}

}
