package dev.mvc.qna;

import java.util.HashMap;
import java.util.List;

public interface QnaDAOInter {
    
    /**
     * 검색 레코드 갯수
     * @param hashMap
     * @return
     */
    public int search_count(HashMap<String, Object> hashMap);
    
    /**
     * qna 목록 + 검색 + 페이징
     * @param map
     * @return
     */
    public List<QnaVO>qna_list_search_paging(HashMap<String, Object> map);
    
    /**
     * qna 등록
     * @param QnaVO
     * @return
     */
    public int qna_create(QnaVO qnaVO);

}
