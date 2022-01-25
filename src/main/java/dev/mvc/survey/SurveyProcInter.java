package dev.mvc.survey;

public interface SurveyProcInter {
	public int survey_create(SurveyVO surveyvo);
	
	public SurveyVO read(int memberno);
}
