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
    
    /**
     * 글 한 개 조회
     * @param Qnano
     * @return
     */
    public QnaVO read(int qnano);
    
    /**
     * 회원별 등록한 QNA 목록
     * @param memberno
     * @return
     */
    public List<QnaVO> my_qna(int memberno);
    
    /**
     * 회원별 등록한 QNA 목록 조회
     * @param qnano
     * @return
     */
    public QnaVO my_qna_read(int qnano);
    
    /**
     * 글 정보 수정
     * @param qnaVO
     * @return
     */
    public int my_qna_update(QnaVO qnaVO);
    
    /**
     * 삭제
     * @param qnano
     * @return
     */
    public int my_qna_delete(int qnano);

}