package dev.mvc.reservation;


import java.util.HashMap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.park.ParkVO;
import dev.mvc.qna.Qna;
import dev.mvc.qna.QnaDAOInter;
import dev.mvc.qna.QnaVO;
import dev.mvc.reservation.ReservationProcInter;

@Component("dev.mvc.reservation.ReservationProc")
public class ReservationProc implements ReservationProcInter{
    
    @Autowired
    private ReservationDAOInter reservationDAO;
    
    public ReservationProc() {
        System.out.println("-> ReservationProc created");
    }
    
    //주차장 예약     
    @Override
    public int reservation_create(ReservationVO reservationVO) {
        int cnt = this.reservationDAO.reservation_create(reservationVO);
        return cnt;
    }
    //예약 내역 조회
    @Override
    public ReservationVO read(int reserveno) {
        ReservationVO reservationVO = this.reservationDAO.read(reserveno);
        
        return reservationVO;
    }
    
    // 예약 내역 개수 확인
    @Override
    public int search_count(HashMap<String, Object> hashMap) {
      int count = reservationDAO.search_count(hashMap);
      return count;
    }
    
    
    // 紐⑸줉 + 寃��깋 + �럹�씠吏�
    @Override
    public List<ReservationVO> reservation_list_search_paging(HashMap<String, Object> hashMap) {
        int begin_of_page = ((Integer)hashMap.get("now_page") - 1) * Reservation.RECORD_PER_PAGE;
        
        // �떆�옉 rownum 寃곗젙
        int start_num = begin_of_page + 1;
        
        //  醫낅즺 rownum
        int end_num = begin_of_page + Qna.RECORD_PER_PAGE;
        
        hashMap.put("start_num", start_num);
        hashMap.put("end_num", end_num);
        
        List<ReservationVO> list = this.reservationDAO.reservation_list_search_paging(hashMap);
        
        return list;
    }

    
    // �럹�씠吏� 紐⑸줉 臾몄옄�뿴 �깮�꽦
    @Override
    public String pagingBox(int search_count, int now_page, String address) {
        int total_page = (int)(Math.ceil((double)search_count/Reservation.RECORD_PER_PAGE)); // �쟾泥� �럹�씠吏� �닔 
        int total_grp = (int)(Math.ceil((double)total_page/Reservation.PAGE_PER_BLOCK)); // �쟾泥� 洹몃９  �닔
        int now_grp = (int)(Math.ceil((double)now_page/Reservation.PAGE_PER_BLOCK));  // �쁽�옱 洹몃９ 踰덊샇
        
        // 1 group: 1, 2, 3 ... 9, 10
        // 2 group: 11, 12 ... 19, 20
        // 3 group: 21, 22 ... 29, 30
        int start_page = ((now_grp - 1) * Reservation.PAGE_PER_BLOCK) + 1; // �듅�젙 洹몃９�쓽 �떆�옉  �럹�씠吏�  
        int end_page = (now_grp * Reservation.PAGE_PER_BLOCK);               // �듅�젙 洹몃９�쓽 留덉�留� �럹�씠吏�   
         
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
        str.append("    padding:1px 6px 1px 6px; /*�쐞, �삤瑜몄そ, �븘�옒, �쇊履�*/"); 
        str.append("    margin:1px 2px 1px 2px; /*�쐞, �삤瑜몄そ, �븘�옒, �쇊履�*/"); 
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
        str.append("    padding:1px 6px 1px 6px; /*�쐞, �삤瑜몄そ, �븘�옒, �쇊履�*/"); 
        str.append("    margin:1px 2px 1px 2px; /*�쐞, �삤瑜몄そ, �븘�옒, �쇊履�*/"); 
        str.append("  }"); 
        str.append("</style>"); 
        str.append("<DIV id='paging'>"); 
//        str.append("�쁽�옱 �럹�씠吏�: " + nowPage + " / " + totalPage + "  "); 
     
        // �씠�쟾 10媛� �럹�씠吏�濡� �씠�룞
        // now_grp: 1 (1 ~ 10 page)
        // now_grp: 2 (11 ~ 20 page)
        // now_grp: 3 (21 ~ 30 page) 
        // �쁽�옱 2洹몃９�씪 寃쎌슦: (2 - 1) * 10 = 1洹몃９�쓽 留덉�留� �럹�씠吏� 10
        // �쁽�옱 3洹몃９�씪 寃쎌슦: (3 - 1) * 10 = 2洹몃９�쓽 留덉�留� �럹�씠吏� 20
        int _now_page = (now_grp - 1) * Reservation.PAGE_PER_BLOCK;  
        if (now_grp >= 2){ // �쁽�옱 洹몃９踰덊샇媛� 2�씠�긽�씠硫� �럹�씠吏��닔媛� 11�럹�씠 �씠�긽�엫�쑝濡� �씠�쟾 洹몃９�쑝濡� 媛덉닔 �엳�뒗 留곹겕 �깮�꽦 
          str.append("<span class='span_box_1'><A href='"+Reservation.LIST_FILE+"?&address="+address+"&now_page="+_now_page+"'>�씠�쟾</A></span>"); 
        } 
     
        // 以묒븰�쓽 �럹�씠吏� 紐⑸줉
        for(int i=start_page; i<=end_page; i++){ 
          if (i > total_page){ // 留덉�留� �럹�씠吏�瑜� �꽆�뼱媛붾떎硫� �럹�씠 異쒕젰 醫낅즺
            break; 
          } 
      
          if (now_page == i){ // 紐⑸줉�뿉 異쒕젰�븯�뒗 �럹�씠吏�媛� �쁽�옱�럹�씠吏��� 媛숇떎硫� CSS 媛뺤“(李⑤퀎�쓣 �몺)
            str.append("<span class='span_box_2'>"+i+"</span>"); // �쁽�옱 �럹�씠吏�, 媛뺤“ 
          }else{
            // �쁽�옱 �럹�씠吏�媛� �븘�땶 �럹�씠吏��뒗 �씠�룞�씠 媛��뒫�븯�룄濡� 留곹겕瑜� �꽕�젙
            str.append("<span class='span_box_1'><A href='"+Reservation.LIST_FILE+"?address="+address+"&now_page="+i+"'>"+i+"</A></span>");   
          } 
        } 
     
        // 10媛� �떎�쓬 �럹�씠吏�濡� �씠�룞
        // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
        // �쁽�옱 �럹�씠吏� 5�씪寃쎌슦 -> �쁽�옱 1洹몃９: (1 * 10) + 1 = 2洹몃９�쓽 �떆�옉�럹�씠吏� 11
        // �쁽�옱 �럹�씠吏� 15�씪寃쎌슦 -> �쁽�옱 2洹몃９: (2 * 10) + 1 = 3洹몃９�쓽 �떆�옉�럹�씠吏� 21
        // �쁽�옱 �럹�씠吏� 25�씪寃쎌슦 -> �쁽�옱 3洹몃９: (3 * 10) + 1 = 4洹몃９�쓽 �떆�옉�럹�씠吏� 31
        _now_page = (now_grp * Reservation.PAGE_PER_BLOCK)+1; //  理쒕� �럹�씠吏��닔 + 1 
        if (now_grp < total_grp){ 
          str.append("<span class='span_box_1'><A href='"+Reservation.LIST_FILE+"?&address="+address+"&now_page="+_now_page+"'>�떎�쓬</A></span>"); 
        } 
        str.append("</DIV>"); 
         
        return str.toString(); 
    }
    
    
    public int reser_delete(int reserveno) {
        int cnt = this.reservationDAO.reser_delete(reserveno);
        
        return cnt;
    }
    
    
    
    // �쉶�썝蹂� �삁�빟 紐⑸줉 議고쉶
    @Override
    public List<ReservationVO> my_reser(int memberno) {
        List<ReservationVO> list = this.reservationDAO.my_reser(memberno);
        
        return list;
    }
    
    
    // �쉶�썝蹂� �삁�빟 紐⑸줉 Park + Reservation join
    @Override
    public List<Park_ReservationVO> my_reser_join(int memberno) {
        List<Park_ReservationVO> list = this.reservationDAO.my_reser_join(memberno);
        
        return list;
    }

    
    // 愿�由ъ옄 �솗�씤�슜 �삁�빟 紐⑸줉 Park + Reservation join
    @Override 
    public List<Park_ReservationVO> reser_list() {
        List<Park_ReservationVO> list = this.reservationDAO.reser_list();
        
        return list;
    }
    
    
    // �삁�빟 �젙蹂� �닔�젙�슜 議고쉶
    @Override
    public ReservationVO my_reser_update_read(int reserveno) {
        ReservationVO reservationVO = this.reservationDAO.read(reserveno);
        
        return reservationVO;
    }
    
    // �삁�빟 �젙蹂� �닔�젙
    @Override
    public int my_reser_update(ReservationVO reservationVO) {
        int cnt = this.reservationDAO.my_reser_update(reservationVO);
        
        return cnt;
    }



    


}