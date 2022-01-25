package dev.mvc.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.answer.AnswerProc")
public class AnswerProc implements AnswerProcInter{
    
    @Autowired
    private AnswerDAOInter answerDAO;

    // 답변 등록
    @Override
    public int answer_create(AnswerVO answerVO) {
        int cnt = this.answerDAO.answer_create(answerVO);
        return cnt;
    }

    @Override
    public AnswerVO answer_read(int answerno) {
        AnswerVO answerVO = this.answerDAO.answer_read(answerno);
        return answerVO;
    }
    

}
