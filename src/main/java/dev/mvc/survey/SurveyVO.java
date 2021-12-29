package dev.mvc.survey;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SurveyVO {
	private int SURVEYNO;
	private int memberno;
	private String PURPOSEPARK;
	private String PREFERDAY;
	private String RESERVEPERIOD;
}
