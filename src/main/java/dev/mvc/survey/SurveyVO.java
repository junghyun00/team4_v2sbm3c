package dev.mvc.survey;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SurveyVO {
	private int surveyno;
	private int memberno;
	private int purposepark;
	private int preferday;
	private int reserveperiod;
	private String rdate;
}
