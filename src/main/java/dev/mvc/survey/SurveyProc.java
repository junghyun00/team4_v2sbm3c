package dev.mvc.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.park.SurveyPorc")
public class SurveyProc implements SurveyProcInter{
	
	@Autowired
	private SurveyDAOinter surveydao;

	//설문 조사 저장	
	@Override
	public int survey_create(SurveyVO surveyvo) {
		int cnt = this.surveydao.survey_create(surveyvo);
		return cnt;
	}

	//설문 조사 조회
    @Override
    public SurveyVO read(int memberno) {
        SurveyVO surveyVO = this.surveydao.read(memberno);
        return surveyVO;
    }

  

}
