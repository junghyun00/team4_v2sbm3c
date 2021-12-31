package dev.mvc.park;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;

import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class ParkCont {
    @Autowired
    @Qualifier("dev.mvc.park.ParkProc")
    private ParkProcInter parkProc;
    
    @Qualifier("dev.mvc.member.MemberProc")
    private MemberProcInter memberProc;
    
    
    /** 업로드 파일 절대 경로 */
    private String uploadDir = Park.getUploadDir();
    
    
    public ParkCont() {
        System.out.println("-> ParkCont created.");
    }
    
    
    /**
     * 목록 + 검색 + 페이징
     * @param address
     * @param now_page
     * @return
     */
    @RequestMapping(value = "/park/park_list_search_paging.do", method = RequestMethod.GET)
    public ModelAndView park_list_search_paging(@RequestParam(value="address", defaultValue="") String address,
                                                                     @RequestParam(value = "now_page", defaultValue = "1") int now_page) {
        System.out.println("--> now_page: " + now_page);

        ModelAndView mav = new ModelAndView(); 
        
        HashMap<String, Object> map = new HashMap<String, Object>(); 
        map.put("address", address); // #{address}
        map.put("now_page", now_page);  // 페이지에 출력할 레코드의 범위를 산출하기위해 사용
        
        // 검색 목록
        List<ParkVO> list = parkProc.park_list_search_paging(map);
        mav.addObject("list", list);
        
        // 검색 레코드 개수
        int search_count = parkProc.search_count(map);
        mav.addObject("search_count", search_count);
        
        // 페이지 목록 문자열 생성
        String paging = parkProc.pagingBox(search_count, now_page, address);
        mav.addObject("paging", paging);
        mav.addObject("now_page", now_page);
        
        mav.setViewName("/park/park_list_search_paging");

        return mav;
        
    }
    
    
    /**
     * 등록 폼
     * @return
     */
    @RequestMapping(value = "/park/park_create.do", method = RequestMethod.GET)
    public ModelAndView park_create() {
        ModelAndView mav = new ModelAndView();
        
        mav.setViewName("/park/park_create");
        
        return mav;
    }
    
    
    /**
     * 등록 처리
     * @param request
     * @param parkVO
     * @return
     */
    @RequestMapping(value = "/park/park_create.do", method = RequestMethod.POST)
    public ModelAndView park_create(HttpServletRequest request, ParkVO parkVO) {
        ModelAndView mav = new ModelAndView();
        
        // ------------------------------------------------------------------------------
        // 파일 전송 코드 시작
        // ------------------------------------------------------------------------------
        String file1 = "";    // 원본 파일명 image
        String uploadDir = this.uploadDir;   // 파일 업로드 경로
        
        MultipartFile mf = parkVO.getFile1MF();
        
        file1 = Tool.getFname(mf.getOriginalFilename()); // 원본 순수 파일명 산출
        System.out.println("file1"+ file1);
        
        long size1 = mf.getSize(); // 파일 크기
        System.out.println("size1"+ size1);
        
        if (size1 > 0) { // 파일 크기 체크
            // 파일 저장 후 업로드된 파일명이 리턴됨
            file1 = Upload.saveFileSpring(mf, uploadDir);
            System.out.println("file1"+ file1);
        }
        

        parkVO.setFile1(file1);

        // ------------------------------------------------------------------------------
        // 파일 전송 코드 종료
        // ------------------------------------------------------------------------------
        mav.addObject("parkno", parkVO.getParkno());
        
        int cnt = this.parkProc.park_create(parkVO);
        //cnt = 0;    // else 테스트
        
        mav.addObject("cnt", cnt);
        
        if (cnt == 1) {
            mav.setViewName("redirect:/park/park_list_search_paging.do");
        } else {
            mav.setViewName("/park/msg");
        }
        
        return mav;
    }
    
    
    /**
     * 글 한 개 조회
     * @param parkno
     * @return
     */
    @RequestMapping(value="/park/read.do", method=RequestMethod.GET )
    public ModelAndView read(int parkno) {
        ModelAndView mav = new ModelAndView();
        
        ParkVO parkVO = this.parkProc.read(parkno);
        mav.addObject("parkVO", parkVO);
        
        mav.setViewName("/park/read");
        
        
        return mav;
        
    }

    
   /**
    *  회원별 등록한 주차장 목록
    * @param parkno
    * @return
    */
    @RequestMapping(value="/mypage/my_park.do", method=RequestMethod.GET )
    public ModelAndView my_park(int memberno) {
        ModelAndView mav = new  ModelAndView(); 
        
        List<ParkVO> list = this.parkProc.my_park(memberno);
        mav.addObject("list", list);
        
        return mav;
    }
    
    
    
    /**
     * 회원별 등록한 주차장 목록 조회
     * @param parkno
     * @return
     */
    @RequestMapping(value="/mypage/my_park_read.do", method=RequestMethod.GET )
    public ModelAndView my_park_read(int parkno) {
        ModelAndView mav = new ModelAndView();
        
        ParkVO parkVO = this.parkProc.my_park_read(parkno);
        mav.addObject("parkVO", parkVO);
        
        mav.setViewName("/mypage/my_park_read");
        
        
        return mav;
    }
    
    
}

