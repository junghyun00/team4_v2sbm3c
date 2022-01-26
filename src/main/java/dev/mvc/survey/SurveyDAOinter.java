package dev.mvc.survey;

public interface SurveyDAOinter {
	//	설문 조사 저장
	public int survey_create(SurveyVO surveyvo);
	// 설문 조사 조회	
	public SurveyVO read(int memberno);
}
