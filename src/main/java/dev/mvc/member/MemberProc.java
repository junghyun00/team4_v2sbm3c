package dev.mvc.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("dev.mvc.member.MemberProc")
public class MemberProc implements MemberProcInter {
    @Autowired
    private MemberDAOInter memberDAO;
    
    public MemberProc(){
        System.out.println("-> MemberProc created.");
    }
    
    // id 중복 확인
    @Override
    public int checkID(String id) {
        int cnt = this.memberDAO.checkID(id);
        
        return cnt;
    }
    
    // 회원 생성
    @Override
    public int create(MemberVO memberVO) {
        int cnt = this.memberDAO.create(memberVO);
        
        return cnt;
    }
    
    // id로 회원 조회
    @Override
    public MemberVO readById(String id) {
        MemberVO memberVO = this.memberDAO.readById(id);
        return memberVO;
    }
    
    // 濡쒓렇�씤
    @Override
    public int login(Map<String, Object> map) {
        int cnt = this.memberDAO.login(map);
        return cnt;    
    }
    
    
    
    // 寃��깋 �젅肄붾뱶 媛��닔
    @Override
    public int search_count(HashMap<String, Object> hashMap) {
      int count = memberDAO.search_count(hashMap);
      return count;
    }
    
    // �쉶�썝 紐⑸줉 + 寃��깋 + �럹�씠吏�
    @Override
    public List<MemberVO> member_list(HashMap<String, Object> hashMap) {
        int begin_of_page = ((Integer)hashMap.get("now_page") - 1) * Member.RECORD_PER_PAGE;
        
        // �떆�옉 rownum 寃곗젙
        int start_num = begin_of_page + 1;
        
        //  醫낅즺 rownum
        int end_num = begin_of_page + Member.RECORD_PER_PAGE;
        
        hashMap.put("start_num", start_num);
        hashMap.put("end_num", end_num);
        
        List<MemberVO> list = this.memberDAO.member_list(hashMap);
        
        
        return list;
    }
    
    // �럹�씠吏� 紐⑸줉 臾몄옄�뿴 �깮�꽦
    @Override
    public String pagingBox(int search_count, int now_page, String id) {
        int total_page = (int)(Math.ceil((double)search_count/Member.RECORD_PER_PAGE)); // �쟾泥� �럹�씠吏� �닔 
        int total_grp = (int)(Math.ceil((double)total_page/Member.PAGE_PER_BLOCK)); // �쟾泥� 洹몃９  �닔
        int now_grp = (int)(Math.ceil((double)now_page/Member.PAGE_PER_BLOCK));  // �쁽�옱 洹몃９ 踰덊샇
        
        // 1 group: 1, 2, 3 ... 9, 10
        // 2 group: 11, 12 ... 19, 20
        // 3 group: 21, 22 ... 29, 30
        int start_page = ((now_grp - 1) * Member.PAGE_PER_BLOCK) + 1; // �듅�젙 洹몃９�쓽 �떆�옉  �럹�씠吏�  
        int end_page = (now_grp * Member.PAGE_PER_BLOCK);               // �듅�젙 洹몃９�쓽 留덉�留� �럹�씠吏�   
         
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
        int _now_page = (now_grp - 1) * Member.PAGE_PER_BLOCK;  
        if (now_grp >= 2){ // �쁽�옱 洹몃９踰덊샇媛� 2�씠�긽�씠硫� �럹�씠吏��닔媛� 11�럹�씠 �씠�긽�엫�쑝濡� �씠�쟾 洹몃９�쑝濡� 媛덉닔 �엳�뒗 留곹겕 �깮�꽦 
          str.append("<span class='span_box_1'><A href='"+Member.LIST_FILE+"?&id="+id+"&now_page="+_now_page+"'>�씠�쟾</A></span>"); 
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
            str.append("<span class='span_box_1'><A href='"+Member.LIST_FILE+"?id="+id+"&now_page="+i+"'>"+i+"</A></span>");   
          } 
        } 
     
        // 10媛� �떎�쓬 �럹�씠吏�濡� �씠�룞
        // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
        // �쁽�옱 �럹�씠吏� 5�씪寃쎌슦 -> �쁽�옱 1洹몃９: (1 * 10) + 1 = 2洹몃９�쓽 �떆�옉�럹�씠吏� 11
        // �쁽�옱 �럹�씠吏� 15�씪寃쎌슦 -> �쁽�옱 2洹몃９: (2 * 10) + 1 = 3洹몃９�쓽 �떆�옉�럹�씠吏� 21
        // �쁽�옱 �럹�씠吏� 25�씪寃쎌슦 -> �쁽�옱 3洹몃９: (3 * 10) + 1 = 4洹몃９�쓽 �떆�옉�럹�씠吏� 31
        _now_page = (now_grp * Member.PAGE_PER_BLOCK)+1; //  理쒕� �럹�씠吏��닔 + 1 
        if (now_grp < total_grp){ 
          str.append("<span class='span_box_1'><A href='"+Member.LIST_FILE+"?&id="+id+"&now_page="+_now_page+"'>�떎�쓬</A></span>"); 
        } 
        str.append("</DIV>"); 
         
        return str.toString(); 
    }
    
    
    // �쉶�썝 �젙蹂� 議고쉶
    @Override
    public MemberVO read(int memberno) {
        MemberVO memberVO = this.memberDAO.read(memberno);
        
        return memberVO;
    }
    
    
    // �쉶�썝 �젙蹂� �닔�젙 泥섎━
    @Override
    public int member_update(MemberVO memberVO) {
        int cnt = this.memberDAO.member_update(memberVO);
        return cnt;
    }
    
    
    // �쉶�썝�씠 �옄湲� �젙蹂� �닔�젙
    @Override
    public int member_update1(MemberVO memberVO) {
        int cnt = this.memberDAO.member_update(memberVO);
        return cnt;
    }
    
    
    
    // �쉶�썝 �궘�젣
    @Override
    public int member_delete(int memberno) {
        int cnt = this.memberDAO.member_delete(memberno);
        return cnt;
    }
    
    

    // �쁽�옱 �뙣�뒪�썙�뱶 寃��궗
    @Override
    public int passwd_check(HashMap<Object, Object> map) {
      int cnt = this.memberDAO.passwd_check(map);
      return cnt;
    }

    // �뙣�뒪�썙�뱶 蹂�寃�
    @Override
    public int passwd_update(HashMap<Object, Object> map) {
      int cnt = this.memberDAO.passwd_update(map);
      return cnt;
    }
    
    
    // 濡쒓렇�씤�맂 �쉶�썝 怨꾩젙�씤吏� 寃��궗�빀�땲�떎
    @Override
    public boolean isMember(HttpSession session) {
        boolean sw = false;  // 濡쒓렇�씤�븯吏� �븡�� 寃껋쑝濡� 珥덇린�솕
        int grade = 99;
        
        if (session != null) {
            String id = (String)session.getAttribute("id");
            
            if (session.getAttribute("grade") != null) {
                grade = (int)session.getAttribute("grade");
            }
            
            if (id != null && grade <= 20){   // grade媛� 20�씠�븯�땲源� grade媛� 10�씤 愿�由ъ옄�룄 �젒洹� 媛��뒫
                sw = true;
            }
                
        }
        return sw;
    }
    
    // 愿�由ъ옄�씤吏� 寃��궗
    @Override
    public boolean isAdmin(HttpSession session) {
        boolean sw = false;  // 濡쒓렇�씤�븯吏� �븡�� 寃껋쑝濡� 珥덇린�솕
        int grade = 99;
        
        if (session != null) {
            String id = (String)session.getAttribute("id");
            if (session.getAttribute("memberno") != null) {
                grade = (int)session.getAttribute("grade");
            }
            
            if (id != null && grade <= 10) {   // grade媛� 10 �씠�븯�땲源� 10�씤 愿�由ъ옄留� �젒洹� 媛��뒫
                sw = true;
            }
        }
        return sw;
    }

    @Override
    public MemberVO readByMemberno(int memberno) {
        MemberVO memberVO = this.memberDAO.readByMemberno(memberno);
        return memberVO;
    }

    

    
}
