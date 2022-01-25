package dev.mvc.review;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewlistVO {
	public String id;
	public String cmt;
	public int grade;
	public String cmt_date;
	public int memberno;
}
