package dev.mvc.survey;

public interface SurveyDAOinter {
	public int survey_create(SurveyVO surveyvo);
	
	public SurveyVO read(int memberno);
}
