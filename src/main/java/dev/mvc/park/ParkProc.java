package dev.mvc.park;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;



@Component("dev.mvc.park.ParkProc")
public class ParkProc implements ParkProcInter{
    
    @Autowired
    private ParkDAOInter parkDAO;
    
    public ParkProc() {
        System.out.println("-> ParkProc created");
    }
    
    
    // 검색 레코드 갯수
    @Override
    public int search_count(HashMap<String, Object> hashMap) {
      int count = parkDAO.search_count(hashMap);
      return count;
    }
    
    
    // 목록 + 검색 + 페이징
    @Override
    public List<ParkVO> park_list_search_paging(HashMap<String, Object> hashMap) {
        int begin_of_page = ((Integer)hashMap.get("now_page") - 1) * Park.RECORD_PER_PAGE;
        
        // 시작 rownum 결정
        int start_num = begin_of_page + 1;
        //  종료 rownum
        int end_num = begin_of_page + Park.RECORD_PER_PAGE;
        
        hashMap.put("start_num", start_num);
        hashMap.put("end_num", end_num);
        
        List<ParkVO> list = this.parkDAO.park_list_search_paging(hashMap);
        
        return list;
    }

    
    // 페이지 목록 문자열 생성
    @Override
    public String pagingBox(int search_count, int now_page, String address) {
        int total_page = (int)(Math.ceil((double)search_count/Park.RECORD_PER_PAGE)); // 전체 페이지 수 
        int total_grp = (int)(Math.ceil((double)total_page/Park.PAGE_PER_BLOCK)); // 전체 그룹  수
        int now_grp = (int)(Math.ceil((double)now_page/Park.PAGE_PER_BLOCK));  // 현재 그룹 번호
        
        // 1 group: 1, 2, 3 ... 9, 10
        // 2 group: 11, 12 ... 19, 20
        // 3 group: 21, 22 ... 29, 30
        int start_page = ((now_grp - 1) * Park.PAGE_PER_BLOCK) + 1; // 특정 그룹의 시작  페이지  
        int end_page = (now_grp * Park.PAGE_PER_BLOCK);               // 특정 그룹의 마지막 페이지   
         
        StringBuffer str = new StringBuffer(); 
         
        str.append("<style type='text/css'>"); 
        str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1.1em;}"); 
        str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1.1em;}"); 
        str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1.1em;}"); 
        str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1.1em;}"); 
        str.append("  .span_box_1{"); 
        str.append("    text-align: center;");    
        str.append("    border-radius: 5px 5px 5px 5px;");  
        str.append("    font-size: 1.1em;"); 
        str.append("    border: 1px;"); 
        str.append("    border-style: solid;"); 
        str.append("    border-color: #cccccc;"); 
        str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
        str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
        str.append("  }"); 
        str.append("  .span_box_2{"); 
        str.append("    border-radius: 5px 5px 5px 5px;");    
        str.append("    text-align: center;");  
        str.append("    background-color: #343a40;"); 
        str.append("    color: #FFFFFF;"); 
        str.append("    font-size: 1.1em;"); 
        str.append("    border: 1px;"); 
        str.append("    border-style: solid;"); 
        str.append("    border-color: #cccccc;"); 
        str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
        str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
        str.append("  }"); 
        str.append("</style>"); 
        str.append("<DIV id='paging'>"); 
//        str.append("현재 페이지: " + nowPage + " / " + totalPage + "  "); 
     
        // 이전 10개 페이지로 이동
        // now_grp: 1 (1 ~ 10 page)
        // now_grp: 2 (11 ~ 20 page)
        // now_grp: 3 (21 ~ 30 page) 
        // 현재 2그룹일 경우: (2 - 1) * 10 = 1그룹의 마지막 페이지 10
        // 현재 3그룹일 경우: (3 - 1) * 10 = 2그룹의 마지막 페이지 20
        int _now_page = (now_grp - 1) * Park.PAGE_PER_BLOCK;  
        if (now_grp >= 2){ // 현재 그룹번호가 2이상이면 페이지수가 11페이 이상임으로 이전 그룹으로 갈수 있는 링크 생성 
          str.append("<span class='span_box_1'><A href='"+Park.LIST_FILE+"?&address="+address+"&now_page="+_now_page+"'>이전</A></span>"); 
        } 
     
        // 중앙의 페이지 목록
        for(int i=start_page; i<=end_page; i++){ 
          if (i > total_page){ // 마지막 페이지를 넘어갔다면 페이 출력 종료
            break; 
          } 
      
          if (now_page == i){ // 목록에 출력하는 페이지가 현재페이지와 같다면 CSS 강조(차별을 둠)
            str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
          }else{
            // 현재 페이지가 아닌 페이지는 이동이 가능하도록 링크를 설정
            str.append("<span class='span_box_1'><A href='"+Park.LIST_FILE+"?address="+address+"&now_page="+i+"'>"+i+"</A></span>");   
          } 
        } 
     
        // 10개 다음 페이지로 이동
        // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
        // 현재 페이지 5일경우 -> 현재 1그룹: (1 * 10) + 1 = 2그룹의 시작페이지 11
        // 현재 페이지 15일경우 -> 현재 2그룹: (2 * 10) + 1 = 3그룹의 시작페이지 21
        // 현재 페이지 25일경우 -> 현재 3그룹: (3 * 10) + 1 = 4그룹의 시작페이지 31
        _now_page = (now_grp * Park.PAGE_PER_BLOCK)+1; //  최대 페이지수 + 1 
        if (now_grp < total_grp){ 
          str.append("<span class='span_box_1'><A href='"+Park.LIST_FILE+"?&address="+address+"&now_page="+_now_page+"'>다음</A></span>"); 
        } 
        str.append("</DIV>"); 
         
        return str.toString(); 
    }

    
    // 주차장 등록
    @Override
    public int park_create(ParkVO parkVO) {
        int cnt = this.parkDAO.park_create(parkVO);
        return cnt;
    }

    
    // 글 한 개 조회
    @Override
    public ParkVO read(int parkno) {
        ParkVO parkVO = this.parkDAO.read(parkno);
        
        return parkVO;
    }

    
    // 회원별 등록한 주차장 목록
    @Override
    public List<ParkVO> my_park(int memberno) {
        List<ParkVO> list = this.parkDAO.my_park(memberno);
        
        return list;
    }


    // 회원별 등록한 주차장 목록 조회
    @Override
    public ParkVO my_park_read(int parkno) {
        ParkVO parkVO = this.parkDAO.my_park_read(parkno);
        
        return parkVO;
    }


    // 주차장 정보 수정용 조회
    @Override
    public ParkVO read_my_park_update(int parkno) {
        ParkVO parkVO = this.parkDAO.read(parkno);
        
        return parkVO;
    }
    

    // 주차장 정보 수정
    @Override
    public int my_park_update(ParkVO parkVO) {
        int cnt = this.parkDAO.my_park_update(parkVO);
        
        return cnt;
    }
    
    
    // 주차장 이미지 파일 수정
    @Override
    public int my_park_update_file(ParkVO parkVO) {
        int cnt = this.parkDAO.my_park_update_file(parkVO);
        
        return cnt;
    }
    

    // 주차장 삭제
    @Override
    public int my_park_delete(int parkno) {
        System.out.println("gggg");
        int cnt = this.parkDAO.my_park_delete(parkno);
        System.out.println("proc cnt : "+cnt);
        
        return cnt;
    }
    
    
    
    
    //////////////////// admin 용 ///////////////////////
    // 페이지 목록 문자열 생성
    @Override
    public String admin_pagingBox(int search_count, int now_page, String address) {
        int total_page = (int)(Math.ceil((double)search_count/Park.RECORD_PER_PAGE)); // 전체 페이지 수 
        int total_grp = (int)(Math.ceil((double)total_page/Park.PAGE_PER_BLOCK)); // 전체 그룹  수
        int now_grp = (int)(Math.ceil((double)now_page/Park.PAGE_PER_BLOCK));  // 현재 그룹 번호
        
        // 1 group: 1, 2, 3 ... 9, 10
        // 2 group: 11, 12 ... 19, 20
        // 3 group: 21, 22 ... 29, 30
        int start_page = ((now_grp - 1) * Park.PAGE_PER_BLOCK) + 1; // 특정 그룹의 시작  페이지  
        int end_page = (now_grp * Park.PAGE_PER_BLOCK);               // 특정 그룹의 마지막 페이지   
         
        StringBuffer str = new StringBuffer(); 
         
        str.append("<style type='text/css'>"); 
        str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1.1em;}"); 
        str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1.1em;}"); 
        str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1.1em;}"); 
        str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1.1em;}"); 
        str.append("  .span_box_1{"); 
        str.append("    text-align: center;");    
        str.append("    border-radius: 5px 5px 5px 5px;");  
        str.append("    font-size: 1.1em;"); 
        str.append("    border: 1px;"); 
        str.append("    border-style: solid;"); 
        str.append("    border-color: #cccccc;"); 
        str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
        str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
        str.append("  }"); 
        str.append("  .span_box_2{"); 
        str.append("    border-radius: 5px 5px 5px 5px;");    
        str.append("    text-align: center;");  
        str.append("    background-color: #343a40;"); 
        str.append("    color: #FFFFFF;"); 
        str.append("    font-size: 1.1em;"); 
        str.append("    border: 1px;"); 
        str.append("    border-style: solid;"); 
        str.append("    border-color: #cccccc;"); 
        str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
        str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
        str.append("  }"); 
        str.append("</style>"); 
        str.append("<DIV id='paging'>"); 
//        str.append("현재 페이지: " + nowPage + " / " + totalPage + "  "); 
     
        // 이전 10개 페이지로 이동
        // now_grp: 1 (1 ~ 10 page)
        // now_grp: 2 (11 ~ 20 page)
        // now_grp: 3 (21 ~ 30 page) 
        // 현재 2그룹일 경우: (2 - 1) * 10 = 1그룹의 마지막 페이지 10
        // 현재 3그룹일 경우: (3 - 1) * 10 = 2그룹의 마지막 페이지 20
        int _now_page = (now_grp - 1) * Park.PAGE_PER_BLOCK;  
        if (now_grp >= 2){ // 현재 그룹번호가 2이상이면 페이지수가 11페이 이상임으로 이전 그룹으로 갈수 있는 링크 생성 
          str.append("<span class='span_box_1'><A href='"+Park.ADMIN_LIST_FILE+"?&address="+address+"&now_page="+_now_page+"'>이전</A></span>"); 
        } 
     
        // 중앙의 페이지 목록
        for(int i=start_page; i<=end_page; i++){ 
          if (i > total_page){ // 마지막 페이지를 넘어갔다면 페이 출력 종료
            break; 
          } 
      
          if (now_page == i){ // 목록에 출력하는 페이지가 현재페이지와 같다면 CSS 강조(차별을 둠)
            str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
          }else{
            // 현재 페이지가 아닌 페이지는 이동이 가능하도록 링크를 설정
            str.append("<span class='span_box_1'><A href='"+Park.ADMIN_LIST_FILE+"?address="+address+"&now_page="+i+"'>"+i+"</A></span>");   
          } 
        } 
     
        // 10개 다음 페이지로 이동
        // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
        // 현재 페이지 5일경우 -> 현재 1그룹: (1 * 10) + 1 = 2그룹의 시작페이지 11
        // 현재 페이지 15일경우 -> 현재 2그룹: (2 * 10) + 1 = 3그룹의 시작페이지 21
        // 현재 페이지 25일경우 -> 현재 3그룹: (3 * 10) + 1 = 4그룹의 시작페이지 31
        _now_page = (now_grp * Park.PAGE_PER_BLOCK)+1; //  최대 페이지수 + 1 
        if (now_grp < total_grp){ 
          str.append("<span class='span_box_1'><A href='"+Park.ADMIN_LIST_FILE+"?&address="+address+"&now_page="+_now_page+"'>다음</A></span>"); 
        } 
        str.append("</DIV>"); 
         
        return str.toString(); 
    }


    @Override
    public List<ParkVO> only_address(String address) {
        List <ParkVO> list = this.parkDAO.only_address(address);
        return list;
    }

   

    
    
    
    
    
}
