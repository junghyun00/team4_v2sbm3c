package dev.mvc.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MemberDAOInter {
    
    /**
     *  id 중복 체크
     * @param id
     * @return
     */
    public int checkID(String id);
    
    /**
     * 회원 가입
     * @param memberVO
     * @return
     */
    public int create(MemberVO memberVO);
    
    
    
    /**
     * id로 회원 정보 조회
     * @param id
     * @return
     */
    public MemberVO readById(String id);
    
    public MemberVO readByMemberno(int memberno);
    
    /**
     * 로그인 처리
     * @param map
     * @return
     */
    public int login(Map<String, Object> map);
    
    
    
    /**
     * 회원 검색 레코드 개수
     * @param hashMap
     * @return
     */
    public int search_count(HashMap<String, Object> hashMap);
    
    /**
     * 회원 목록 + 검색 + 페이징
     * @return
     */
    public List<MemberVO> member_list(HashMap<String, Object> map);
    
    
    /**
     * 수정을 위해 memberno로 회원 정보 조회
     * @param memberno
     * @return
     */
    public MemberVO read(int memberno);
    
    /**
     * 관리자가 회원 정보 수정
     * @param memberVO
     * @return
     */
    public int member_update(MemberVO memberVO);
    
    
    /**
     * 회원이 자기 정보 수정
     * @param memberVO
     * @return
     */
    public int member_update1(MemberVO memberVO);
    
    
    /**
     * 회원 삭제
     * @param parkno
     * @return
     */
    public int member_delete(int memberno);
    
    
    /**
     * 현재 패스워드 검사
     * @param map
     * @return 0: 일치하지 않음, 1: 일치함
     */
    public int passwd_check(HashMap<Object, Object> map);
    
    
    /**
     * 패스워드 변경
     * @param map
     * @return 변경된 패스워드 갯수
     */
    public int passwd_update(HashMap<Object, Object> map);
}
