package dev.mvc.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.park.SurveyPorc")
public class SurveyProc implements SurveyProcInter{
	
	@Autowired
	private SurveyDAOinter surveydao;

	@Override
	public int survey_create(SurveyVO surveyvo) {
		int cnt = this.surveydao.survey_create(surveyvo);
		return cnt;
	}

}
