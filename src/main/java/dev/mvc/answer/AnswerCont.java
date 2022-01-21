package dev.mvc.answer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.member.MemberProcInter;
import dev.mvc.qna.QnaProcInter;
import dev.mvc.qna.QnaVO;

@Controller
public class AnswerCont {
    @Autowired
    @Qualifier("dev.mvc.answer.AnswerProc")
    private AnswerProcInter answerProc;
    
    @Autowired
    @Qualifier("dev.mvc.qna.QnaProc")
    private QnaProcInter qnaProc;
    
    @Autowired
    @Qualifier("dev.mvc.member.MemberProc")
    private MemberProcInter memberProc;
    
    /**
     * 등록 폼
     * 
     * @return
     */
    @RequestMapping(value = "/answer/answer_create.do", method = RequestMethod.GET)
    public ModelAndView answer_create(HttpSession session, 
            @RequestParam(value = "qnano", defaultValue = "1") int qnano) {
        ModelAndView mav = new ModelAndView();

        QnaVO qnaVO = this.qnaProc.read(qnano);
        mav.addObject("qnaVO", qnaVO);
        
        if (memberProc.isMember(session)) {
            mav.setViewName("/answer/answer_create");
        } else {
            mav.setViewName("/member/login_need"); 
        }
        

        return mav;
    }
    
    /**
     * 등록 처리
     * 
     * @param request
     * @param answerVO
     * @return
     */
    
    @RequestMapping(value = "/answer/answer_create.do", method = RequestMethod.POST)
    public ModelAndView answer_create(HttpServletRequest request, AnswerVO answerVO) {
        ModelAndView mav = new ModelAndView();
        
        mav.addObject("answerno", answerVO.getAnswerno());
        
        int cnt = this.answerProc.answer_create(answerVO);
        
        mav.addObject("qnano",  answerVO.getQnano());
        mav.addObject("cnt", cnt);
        
        if (cnt == 1) {
            mav.setViewName("redirect:/qna/qna_list_search_paging.do");
        } else {
            mav.setViewName("/answer/msg");
        }

        return mav;
        
    
    }
    
}
