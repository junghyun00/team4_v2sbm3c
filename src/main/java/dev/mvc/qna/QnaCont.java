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
    

}
