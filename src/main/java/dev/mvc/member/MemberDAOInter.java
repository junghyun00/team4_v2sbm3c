package dev.mvc.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MemberDAOInter {
    
    /**
     * id 중복 체크
     * @param id
     * @return
     */
    public int checkID(String id);
    
    /**
     * 회원 생성
     * @param memberVO
     * @return
     */
    public int create(MemberVO memberVO);
    
    
    
    /**
     * 회원 정보 조회
     * @param id
     * @return
     */
    public MemberVO readById(String id);
    
    public MemberVO readByMemberno(int memberno);
    
    /**
     * 濡쒓렇�씤 泥섎━
     * @param map
     * @return
     */
    public int login(Map<String, Object> map);
    
    
    
    /**
     * �쉶�썝 寃��깋 �젅肄붾뱶 媛쒖닔
     * @param hashMap
     * @return
     */
    public int search_count(HashMap<String, Object> hashMap);
    
    /**
     * �쉶�썝 紐⑸줉 + 寃��깋 + �럹�씠吏�
     * @return
     */
    public List<MemberVO> member_list(HashMap<String, Object> map);
    
    
    /**
     * �닔�젙�쓣 �쐞�빐 memberno濡� �쉶�썝 �젙蹂� 議고쉶
     * @param memberno
     * @return
     */
    public MemberVO read(int memberno);
    
    /**
     * 愿�由ъ옄媛� �쉶�썝 �젙蹂� �닔�젙
     * @param memberVO
     * @return
     */
    public int member_update(MemberVO memberVO);
    
    
    /**
     * �쉶�썝�씠 �옄湲� �젙蹂� �닔�젙
     * @param memberVO
     * @return
     */
    public int member_update1(MemberVO memberVO);
    
    
    /**
     * �쉶�썝 �궘�젣
     * @param parkno
     * @return
     */
    public int member_delete(int memberno);
    
    
    /**
     * �쁽�옱 �뙣�뒪�썙�뱶 寃��궗
     * @param map
     * @return 0: �씪移섑븯吏� �븡�쓬, 1: �씪移섑븿
     */
    public int passwd_check(HashMap<Object, Object> map);
    
    
    /**
     * �뙣�뒪�썙�뱶 蹂�寃�
     * @param map
     * @return 蹂�寃쎈맂 �뙣�뒪�썙�뱶 媛��닔
     */
    public int passwd_update(HashMap<Object, Object> map);
}
