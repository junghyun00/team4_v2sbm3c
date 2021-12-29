package dev.mvc.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.qna.QnaProc")
public class QnaProc implements QnaProcInter {
    
    @Autowired
    private QnaDAOInter qnaDAO;
    
    // QNA 목록 조회
    @Override
    public List<QnaVO> qna_list() {
        List<QnaVO> list = this.qnaDAO.qna_list();
        return list;
    }
}
