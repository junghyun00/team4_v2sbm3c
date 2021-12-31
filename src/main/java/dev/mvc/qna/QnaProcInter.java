package dev.mvc.qna;

import java.util.HashMap;
import java.util.List;

import dev.mvc.park.ParkVO;

public interface QnaProcInter {
    
    /**
     * 검색 레코드 갯수
     * @param hashMap
     * @return
     */
    public int search_count(HashMap<String, Object> hashMap);
    
    
    /**
     * 목록 + 검색 + 페이징
     * @param map
     * @return
     */
    public List<QnaVO> qna_list_search_paging(HashMap<String, Object> map);
    
    
    /**
     * 페이지 목록 문자열 생성
     * @param now_page 현재 페이지
     * @param address 검색어
     * @return
     */
    public String pagingBox(int search_count, int now_page, String address);
    
    
    /**
     * 주차장 등록
     * @param qnaVO
     * @return
     */
    public int qna_create(QnaVO qnaVO);
    
    /**
     * 글 한 개 조회
     * @param qnano
     * @return
     */
    public QnaVO read(int qnano);
    
    /**
     * 삭제
     * @param qnano
     * @return
     */
    public int delete(int qnano);

}
