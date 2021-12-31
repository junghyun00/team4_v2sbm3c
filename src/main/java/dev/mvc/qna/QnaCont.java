package dev.mvc.qna;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.member.MemberProcInter;
import dev.mvc.park.ParkVO;
import dev.mvc.tool.Tool;

@Controller
public class QnaCont {
    @Autowired
    @Qualifier("dev.mvc.qna.QnaProc")
    private QnaProcInter qnaProc;
    
    @Qualifier("dev.mvc.member.MemberProc")
    private MemberProcInter memberProc;
    
    /** 업로드 파일 절대 경로 */
    private String uploadDir = Qna.getUploadDir();
    
    /**
     * 목록 + 검색 + 페이징
     * @param address
     * @param now_page
     * @return
     */
    @RequestMapping(value = "/qna/qna_list_search_paging.do", method = RequestMethod.GET)
    public ModelAndView qna_list_search_paging(@RequestParam(value="title", defaultValue="") String title,
                                                                     @RequestParam(value = "now_page", defaultValue = "1") int now_page) {
        System.out.println("--> now_page: " + now_page);

        ModelAndView mav = new ModelAndView(); 
        
        HashMap<String, Object> map = new HashMap<String, Object>(); 
        map.put("title", title); // #{title}
        map.put("now_page", now_page);  // 페이지에 출력할 레코드의 범위를 산출하기위해 사용
        
        // 검색 목록
        List<QnaVO> list = qnaProc.qna_list_search_paging(map);
        mav.addObject("list", list);
        
        // 검색 레코드 개수
        int search_count = qnaProc.search_count(map);
        mav.addObject("search_count", search_count);
        
        // 페이지 목록 문자열 생성
        String paging = qnaProc.pagingBox(search_count, now_page, title);
        mav.addObject("paging", paging);
        mav.addObject("now_page", now_page);
        
        mav.setViewName("/qna/qna_list_search_paging");

        return mav;
        
    }
    
    
    /**
     * 등록 폼
     * @return
     */
    @RequestMapping(value = "/qna/qna_create.do", method = RequestMethod.GET)
    public ModelAndView qna_create() {
        ModelAndView mav = new ModelAndView();
        
        mav.setViewName("/qna/qna_create");
        
        return mav;
    }
    
    
    /**
     * 등록 처리
     * @param request
     * @param qnaVO
     * @return
     */
    @RequestMapping(value = "/qna/qna_create.do", method = RequestMethod.POST)
    public ModelAndView qna_create(HttpServletRequest request, QnaVO qnaVO) {
        ModelAndView mav = new ModelAndView();
        
        // ------------------------------------------------------------------------------
        // 파일 전송 코드 시작
        // ------------------------------------------------------------------------------
        String file1 = "";    // 원본 파일명 image
        String uploadDir = this.uploadDir;   // 파일 업로드 경로
        
        MultipartFile mf = qnaVO.getFile1MF();
        
        file1 = Tool.getFname(mf.getOriginalFilename()); // 원본 순수 파일명 산출

        qnaVO.setImg(file1);

        // ------------------------------------------------------------------------------
        // 파일 전송 코드 종료
        // ------------------------------------------------------------------------------
        System.out.println("-> qnano:" + qnaVO.getQnano());
        mav.addObject("qnano", qnaVO.getQnano());
        
        int cnt = this.qnaProc.qna_create(qnaVO);
        //cnt = 0;    // else 테스트
        
        mav.addObject("cnt", cnt);
        
        if (cnt == 1) {
            mav.setViewName("redirect:/qna/qna_list_search_paging.do");
        } else {
            mav.setViewName("/qna/msg");
        }
        
        return mav;
    }
    
    /**
     * 글 한 개 조회
     * @param qnano
     * @return
     */
    @RequestMapping(value="/qna/read.do", method=RequestMethod.GET )
    public ModelAndView read(int qnano) {
        ModelAndView mav = new ModelAndView();
        
        QnaVO qnaVO = this.qnaProc.read(qnano);
        mav.addObject("qnaVO", qnaVO);
        
        mav.setViewName("/qna/read");
        
        
        return mav;
    }
    
    /**
     * 삭제 폼
     * @param qnano
     * @return
     */
    @RequestMapping(value="/qna/qna_delete.do", method=RequestMethod.GET )
    public ModelAndView delete(int qnano) { 
      ModelAndView mav = new  ModelAndView();
      
      QnaVO qnaVO = this.qnaProc.read(qnano);
      // 삭제할 정보를 조회하여 확인
      mav.addObject("qnaVO", qnaVO);
           
      mav.setViewName("/qna/qna_delete");  // qna/delete.jsp
      
      return mav; 
    }

    /**
     * 삭제 처리 http://localhost:9091/qna/delete.do
     * 
     * @return
     */
    @RequestMapping(value = "/qna/qna_delete.do", method = RequestMethod.POST)
    public ModelAndView delete(HttpServletRequest request, QnaVO qnaVO, 
                                            int now_page,
                                            @RequestParam(value="word", defaultValue="") String word) {
      ModelAndView mav = new ModelAndView();
      int qnano = qnaVO.getQnano();
     
      int cnt = 0;
          // -------------------------------------------------------------------
          // 파일 삭제 코드 시작
          // -------------------------------------------------------------------
          // 삭제할 파일 정보를 읽어옴.
      QnaVO vo = qnaProc.read(qnano);
//          System.out.println("contentsno: " + vo.getContentsno());
//          System.out.println("file1: " + vo.getFile1());
      long size1 = 0;
      boolean sw = false;
          
     
      String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/qna/storage/"; // 절대 경로

          // System.out.println("sw: " + sw);
          // -------------------------------------------------------------------
          // 파일 삭제 종료 시작
          // -------------------------------------------------------------------
          
      cnt = this.qnaProc.delete(qnano); // DBMS 삭제
          
          // -------------------------------------------------------------------------------------
      System.out.println("-> qnano: " + vo.getQnano());
      System.out.println("-> word: " + word);
          
          // 마지막 페이지의 레코드 삭제시의 페이지 번호 -1 처리
      HashMap<String, Object> page_map = new HashMap<String, Object>();
      page_map.put("qnano", vo.getQnano());
      page_map.put("word", word);
          // 10번째 레코드를 삭제후
          // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면
          // 페이지수를 4 -> 3으로 감소 시켜야함.
      if (qnaProc.search_count(page_map) % Qna.RECORD_PER_PAGE == 0) {
        now_page = now_page - 1;
            
      }
          // -------------------------------------------------------------------------------------
          
      mav.addObject("now_page", now_page);
      mav.setViewName("redirect:/qna/qna_list_search_paging.do"); 

      mav.addObject("qnano", qnaVO.getQnano());
      System.out.println("-> qnano: " + qnaVO.getQnano());
      
      return mav; // forward
    }   
    
}