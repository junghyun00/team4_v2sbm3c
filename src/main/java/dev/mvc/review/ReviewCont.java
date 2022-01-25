package dev.mvc.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ReviewCont {

	@Autowired
	@Qualifier("dev.mvc.review.Reviewproc")
	private ReviewProcInter reviewproc;
	
	@ResponseBody
    @RequestMapping(value = "/review/review_create.do", method = RequestMethod.POST)
    public String review_create(ReviewVO reviewvo) {
        int cnt = this.reviewproc.review_create(reviewvo);
        JSONObject json = new JSONObject();
        json.put("cnt", cnt);
        return json.toString();
    }
	@ResponseBody
    @RequestMapping(value = "/review/delete.do", method = RequestMethod.POST)
    public String review_delete(int reviewno) {
		int cnt = this.reviewproc.review_delete(reviewno);
        JSONObject json = new JSONObject();
        json.put("cnt", cnt);
        return json.toString();
    }
	
}
