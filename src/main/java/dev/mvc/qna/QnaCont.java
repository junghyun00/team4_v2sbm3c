package dev.mvc.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QnaCont {
    @Autowired
    @Qualifier("dev.mvc.qna.QnaProc")
    private QnaProcInter qnaProc;
    
    /**
     * 목록 조회
     * @return
     */
    // http://localhost:9091/qna/qna_list.do
    @RequestMapping(value = "/qna/qna_list.do", method = RequestMethod.GET)
    public ModelAndView qna_list() {
        ModelAndView mav = new ModelAndView();
        List<QnaVO> list = this.qnaProc.qna_list();
        
        mav.addObject("list", list);
        mav.setViewName("/qna/qna_list");
        
        return mav;
    }
    

}
