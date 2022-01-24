package dev.mvc.recommend;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class RecommendVO {
    private int recommendno;
    private int memberno;
    private int purposepark;
    private int preferday;
    private int reserveperiod;
    private String rdate;
}
