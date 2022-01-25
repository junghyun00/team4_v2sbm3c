package dev.mvc.park;

import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
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
import dev.mvc.review.ReviewProcInter;
import dev.mvc.review.ReviewlistVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class ParkCont {
    @Autowired
    @Qualifier("dev.mvc.park.ParkProc")
    private ParkProcInter parkProc;
    
    @Autowired
    @Qualifier("dev.mvc.member.MemberProc")
    private MemberProcInter memberProc;
    
	@Autowired
	@Qualifier("dev.mvc.review.Reviewproc")
	private ReviewProcInter reviewproc;
    
    
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
                                                                     @RequestParam(value = "now_page", defaultValue = "1") int now_page, 
                                                                     @RequestParam(value = "memberno", defaultValue = "1") int memberno,
                                                                     @RequestParam(value = "parkno", defaultValue = "1") int parkno,
                                                                     HttpSession session) {
    	ModelAndView mav = new ModelAndView(); 
        
        if (memberProc.isMember(session)) {
            HashMap<String, Object> map = new HashMap<String, Object>(); 
            map.put("address", address); // #{address}
            map.put("now_page", now_page);  // 페이지에 출력할 레코드의 범위를 산출하기위해 사용
            map.put("memberno", memberno);

            MemberVO memberVO = this.memberProc.read(memberno);
            mav.addObject("memberVO", memberVO);
            //System.out.println(memberVO.getMemberno());
            
            // 검색 목록
            List<ParkVO> list = parkProc.park_list_search_paging(map);
            mav.addObject("list", list);
            System.out.println(list);

            List<ParkVO> list2 = parkProc.only_address(address);
            mav.addObject("list2", list2);

            int park_su = Park.RECORD_PER_PAGE;
            mav.addObject("park_su", park_su);

            
            // 검색 레코드 개수
            int search_count = parkProc.search_count(map);
            mav.addObject("search_count", search_count);
            
            // 페이지 목록 문자열 생성
            String paging = parkProc.pagingBox(search_count, now_page, address);
            mav.addObject("paging", paging);
            mav.addObject("now_page", now_page);
            
            mav.setViewName("/park/park_list_search_paging");
        } else {
            mav.setViewName("/member/login_need"); 
        }


        return mav;
        
    }
    
    /**
     * 등록 폼
     * @return
     */
    @RequestMapping(value = "/park/park_create.do", method = RequestMethod.GET)
    public ModelAndView park_create(HttpSession session, 
                                                    @RequestParam(value = "memberno", defaultValue = "1") int memberno) {
        ModelAndView mav = new ModelAndView();
        
        MemberVO memberVO = this.memberProc.read(memberno);
        mav.addObject("memberVO", memberVO);
        
        if (memberProc.isMember(session)) {

            mav.setViewName("/park/park_create");
        } else {
            mav.setViewName("/member/login_need"); 
        }
         
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
        
        mav.addObject("memberno", parkVO.getMemberno());
        
        // ------------------------------------------------------------------------------
        // 파일 전송 코드 시작
        // ------------------------------------------------------------------------------
        String file1 = "";    // 원본 파일명 image
        String uploadDir = this.uploadDir;   // 파일 업로드 경로
        
        MultipartFile mf = parkVO.getFile1MF();
        
        file1 = Tool.getFname(mf.getOriginalFilename()); // 원본 순수 파일명 산출
        System.out.println("file1"+ file1);
        
        long size1 = mf.getSize();  // 파일 크기
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
            mav.setViewName("redirect:/mypage/my_park.do?memberno=" + parkVO.getMemberno());
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
    public ModelAndView read(int parkno,
                                           @RequestParam(value="memberno", defaultValue="") int memberno) {
        ModelAndView mav = new ModelAndView();
        
        MemberVO memberVO = this.memberProc.read(memberno);
        mav.addObject("memberVO", memberVO);
        
        List<ReviewlistVO> cmtlist = this.reviewproc.listcmt_by_parkno(parkno);
        mav.addObject("cmtlist", cmtlist);
        
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
        
        MemberVO memberVO = this.memberProc.read(memberno);
        mav.addObject("memberVO", memberVO);
        
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
    
    
    
    /**
     * 주차장 정보 수정폼
     * @param parkno
     * @return
     */
    @RequestMapping(value="/mypage/my_park_update.do", method=RequestMethod.GET )
    public ModelAndView my_park_update(int parkno) {
        ModelAndView mav = new ModelAndView();
        
        ParkVO parkVO = this.parkProc.read_my_park_update(parkno);
        int memberno = parkVO.getMemberno();
        
        MemberVO memberVO = this.memberProc.read(memberno);
        mav.addObject("memberVO", memberVO);
        
        mav.addObject("parkVO", parkVO);
        mav.setViewName("/mypage/my_park_update");
        
        return mav;
    }
    
    /**
     * 주차장 정보 수정 처리
     * @param parkVO
     * @return
     */
    @RequestMapping(value="/mypage/my_park_update.do", method=RequestMethod.POST )
    public ModelAndView my_park_update(ParkVO parkVO) {
        ModelAndView mav = new ModelAndView();
        
        int cnt = this.parkProc.my_park_update(parkVO);
        
        mav.addObject("cnt", cnt);
        mav.addObject("parkno", parkVO.getParkno());
        mav.addObject("memberno", parkVO.getMemberno());
        
        mav.setViewName("redirect:/mypage/my_park.do");
        
        return mav;
        
        
    }
    
    
    /**
     * 주차장 이미지 파일 수정 폼
     * @param parkno
     * @return
     */
    @RequestMapping(value="/mypage/my_park_update_file.do", method=RequestMethod.GET )
    public ModelAndView my_park_update_file(int parkno) {
        ModelAndView mav = new ModelAndView();
        
        ParkVO parkVO = this.parkProc.read_my_park_update(parkno);
        
        mav.addObject("parkVO", parkVO);
        mav.setViewName("/mypage/my_park_update_file");
        
        return mav;
    }
    
    /**
     * 주차장 이미지 파일 수정 처리
     * @param parkVO
     * @return
     */
    @RequestMapping(value="/mypage/my_park_update_file.do", method=RequestMethod.POST )
    public ModelAndView my_park_update_file(HttpServletRequest request, ParkVO parkVO) {
        ModelAndView mav = new ModelAndView();
        String uploadDir = this.uploadDir;
        
        
        ParkVO vo = parkProc.my_park_read(parkVO.getParkno());  // 삭제할 파일 정보 읽어옴
        
        mav.addObject("memberno", parkVO.getMemberno());
        System.out.println("memberno" + parkVO.getMemberno());
        
        
        int cnt = 0;
        // -------------------------------------------------------------------
        // 파일 삭제 코드 시작
        // -------------------------------------------------------------------
        String file1 = vo.getFile1();
        long size1 = 0;
        boolean sw = false;
        
        sw = Tool.deleteFile(uploadDir, file1);  // Folder에서 1건의 파일 삭제
        // -------------------------------------------------------------------
        // 파일 삭제 종료 
        // -------------------------------------------------------------------
        // -------------------------------------------------------------------
        // 파일 전송 코드 시작
        // -------------------------------------------------------------------
        file1 = "";
        MultipartFile mf = parkVO.getFile1MF();
        
        file1 = mf.getOriginalFilename();
        size1 = mf.getSize(); // 파일 크기
        
        if (size1 > 0) { // 파일 크기 체크
            // 파일 저장 후 업로드된 파일명이 리턴됨
            file1 = Upload.saveFileSpring(mf, uploadDir);
            System.out.println("file1"+ file1);
        }
        

        parkVO.setFile1(file1);
        // -------------------------------------------------------------------
        // 파일 전송 코드 종료
        // -------------------------------------------------------------------
        cnt = this.parkProc.my_park_update_file(parkVO);
        
        mav.addObject("cnt", cnt);
        mav.addObject("parkno", parkVO.getParkno());
        mav.addObject("memberno", parkVO.getMemberno());
        
        mav.setViewName("redirect:/mypage/my_park.do");
        
        return mav;
        
        
    }
    
    
                                                            
    
    /**
     * 주차장 삭제 폼
     * @param parkno
     * @return
     */
//    @RequestMapping(value="/mypage/my_park_delete.do", method=RequestMethod.GET )
//    public ModelAndView my_park_delete(int parkno) {
//        ModelAndView mav = new ModelAndView();
//        
//        ParkVO parkVO = this.parkProc.my_park_read(parkno);
//        
//        mav.addObject("parkVO", parkVO);
//        mav.setViewName("/mypage/my_park_delete");
//        
//        return mav;
//    }
    
    
    /**
     * 마이페이지 주차장 삭제 폼 ajax
     * @param parkno
     * @return
     */
    @RequestMapping(value="/mypage/my_park_delete_ajax.do", method=RequestMethod.GET )
    @ResponseBody
    public String my_park_delete_ajax(int parkno) {
        ParkVO parkVO = this.parkProc.read(parkno);
        System.out.println(parkVO);
        
        JSONObject json = new JSONObject();
        json.put("parkno", parkVO.getParkno());
        json.put("memberno", parkVO.getMemberno());
        json.put("name", parkVO.getName());
        json.put("phone", parkVO.getPhone());
        json.put("address", parkVO.getAddress());
        json.put("area", parkVO.getArea());
        json.put("price", parkVO.getPrice());
        json.put("cmt", parkVO.getCmt());
        json.put("file1", parkVO.getFile1());
            
        return json.toString();
    }
    
    
    /**
     * 마이페이지 주차장 삭제 처리
     * @param parkno
     * @return
     */
    @RequestMapping(value="/mypage/my_park_delete.do", method=RequestMethod.POST)
    public ModelAndView my_park_delete(HttpServletRequest request, ParkVO parkVO) {
        ModelAndView mav = new ModelAndView();
        String uploadDir = this.uploadDir;
        
        int parkno = parkVO.getParkno();
        
        mav.addObject("memberno", parkVO.getMemberno());
        System.out.println("memberno" + parkVO.getMemberno());
        
        int cnt = 0;
        // -------------------------------------------------------------------
        // 파일 삭제 코드 시작
        // -------------------------------------------------------------------
        // 삭제할 파일 정보를 읽어옴.
        ParkVO vo = parkProc.my_park_read(parkno);
        System.out.println("여기는1");
        
        
        String file1 = vo.getFile1();
        long size1 = 0;
        boolean sw = false;
        
        sw = Tool.deleteFile(uploadDir, file1);  // Folder에서 1건의 파일 삭제
        System.out.println("여기는2");
        // -------------------------------------------------------------------
        // 파일 삭제 종료 시작
        // -------------------------------------------------------------------
        
        cnt = this.parkProc.my_park_delete(parkno);
        System.out.println("cnt : "+cnt);
        
        //mav.addObject("cnt", cnt);
        //mav.addObject("parkno", parkVO.getParkno());
        
        
        mav.setViewName("redirect:/mypage/my_park.do");
        
        
        return mav;
        
    }
    
    
    
    
    
    
    ///////////////// admin 용///////////////////////
    /**
     * 목록 + 검색 + 페이징
     * @param address
     * @param now_page
     * @return
     */
    @RequestMapping(value = "/admin/park_list.do", method = RequestMethod.GET)
    public ModelAndView admin_park_list(@RequestParam(value="address", defaultValue="") String address,
                                                            @RequestParam(value = "now_page", defaultValue = "1") int now_page) {

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
        String paging = parkProc.admin_pagingBox(search_count, now_page, address);
        mav.addObject("paging", paging);
        mav.addObject("now_page", now_page);
        
        mav.setViewName("/admin/park_list");

        return mav;
        
    }
    
    
    /**
     * 주차장 정보 수정폼
     * @param parkno
     * @return
     */
    @RequestMapping(value="/admin/park_update.do", method=RequestMethod.GET )
    public ModelAndView admin_park_update(int parkno) {
        ModelAndView mav = new ModelAndView();
        
        ParkVO parkVO = this.parkProc.read_my_park_update(parkno);
        
        mav.addObject("parkVO", parkVO);
        mav.setViewName("/admin/park_update");
        
        return mav;
    }
    
    /**
     * 주차장 정보 수정 처리
     * @param parkVO
     * @return
     */
    @RequestMapping(value="/admin/park_update.do", method=RequestMethod.POST )
    public ModelAndView admin_park_update(ParkVO parkVO) {
        ModelAndView mav = new ModelAndView();
        
        int cnt = this.parkProc.my_park_update(parkVO);
        
        mav.addObject("cnt", cnt);
        mav.addObject("parkno", parkVO.getParkno());
        mav.addObject("memberno", parkVO.getMemberno());
        
        mav.setViewName("redirect:/admin/park_list.do");
        
        return mav;
        
        
    }
    
    
    /**
     * 주차장 이미지 파일 수정 폼
     * @param parkno
     * @return
     */
    @RequestMapping(value="/admin/park_update_file.do", method=RequestMethod.GET )
    public ModelAndView admin_park_update_file(int parkno) {
        ModelAndView mav = new ModelAndView();
        
        ParkVO parkVO = this.parkProc.read_my_park_update(parkno);
        
        mav.addObject("parkVO", parkVO);
        mav.setViewName("/admin/park_update_file");
        
        return mav;
    }
    
    /**
     * 주차장 이미지 파일 수정 처리
     * @param parkVO
     * @return
     */
    @RequestMapping(value="/admin/park_update_file.do", method=RequestMethod.POST )
    public ModelAndView admin_park_update_file(HttpServletRequest request, ParkVO parkVO) {
        ModelAndView mav = new ModelAndView();
        String uploadDir = this.uploadDir;
        
        
        ParkVO vo = parkProc.my_park_read(parkVO.getParkno()); // 삭제할 파일 정보 읽어옴
        
        mav.addObject("memberno", parkVO.getMemberno());
        System.out.println("memberno" + parkVO.getMemberno());
        
        
        int cnt = 0;
        // -------------------------------------------------------------------
        // 파일 삭제 코드 시작
        // -------------------------------------------------------------------
        String file1 = vo.getFile1();
        long size1 = 0;
        boolean sw = false;
        
        sw = Tool.deleteFile(uploadDir, file1);  // Folder에서 1건의 파일 삭제
        // -------------------------------------------------------------------
        // 파일 삭제 종료 
        // -------------------------------------------------------------------
        // -------------------------------------------------------------------
        // 파일 전송 코드 시작
        // -------------------------------------------------------------------
        file1 = "";
        MultipartFile mf = parkVO.getFile1MF();
        
        file1 = mf.getOriginalFilename();
        size1 = mf.getSize(); // 파일 크기
        
        if (size1 > 0) { // 파일 크기 체크
            // 파일 저장 후 업로드된 파일명이 리턴됨
            file1 = Upload.saveFileSpring(mf, uploadDir);
            System.out.println("file1"+ file1);
        }
        

        parkVO.setFile1(file1);
        // -------------------------------------------------------------------
        // 파일 전송 코드 종료
        // -------------------------------------------------------------------
        cnt = this.parkProc.my_park_update_file(parkVO);
        
        mav.addObject("cnt", cnt);
        mav.addObject("parkno", parkVO.getParkno());
        mav.addObject("memberno", parkVO.getMemberno());
        
        mav.setViewName("redirect:/admin/park_list.do");
        
        return mav;
        
        
    }
    
    
                                                            
    
    /**
     * 주차장 삭제 폼
     * @param parkno
     * @return
     */
    /*
    @RequestMapping(value="/admin/park_delete.do", method=RequestMethod.GET )
    public ModelAndView admin_park_delete(int parkno) {
        ModelAndView mav = new ModelAndView();
        
        ParkVO parkVO = this.parkProc.my_park_read(parkno);
        
        mav.addObject("parkVO", parkVO);
        mav.setViewName("/admin/park_delete");
        
        return mav;
    }
    */
    
    
    /**
     * 관리자용 주차장 삭제 폼 ajax
     * @param parkno
     * @return
     */
    @RequestMapping(value="/admin/park_delete_ajax.do", method=RequestMethod.GET )
    @ResponseBody
    public String admin_park_delete_ajax(int parkno) {
        ParkVO parkVO = this.parkProc.my_park_read(parkno);
        //System.out.println(parkVO);
        
        
        JSONObject json = new JSONObject();
        json.put("parkno", parkVO.getParkno());
        json.put("memberno", parkVO.getMemberno());
        json.put("name", parkVO.getName());
        json.put("phone", parkVO.getPhone());
        json.put("address", parkVO.getAddress());
        json.put("area", parkVO.getArea());
        json.put("price", parkVO.getPrice());
        json.put("cmt", parkVO.getCmt());
        json.put("file1", parkVO.getFile1());
        
        //System.out.println(json);
        
        return json.toString();
    }
    
    /**
     * 관리자용 주차장 삭제 처리
     * @param parkno
     * @return
     */
    @RequestMapping(value="/admin/park_delete.do", method=RequestMethod.POST)
    public ModelAndView admin_park_delete(HttpServletRequest request, ParkVO parkVO) {
        ModelAndView mav = new ModelAndView();
        String uploadDir = this.uploadDir;
        
        int parkno = parkVO.getParkno();
        
        mav.addObject("memberno", parkVO.getMemberno());
        //System.out.println("memberno = " + parkVO.getMemberno());
        
        int cnt = 0;
        // -------------------------------------------------------------------
        // 파일 삭제 코드 시작
        // -------------------------------------------------------------------
        // 삭제할 파일 정보를 읽어옴.
        ParkVO vo = this.parkProc.my_park_read(parkno);
        //System.out.println("vo = "+vo);
        
        String file1 = vo.getFile1();
        long size1 = 0;
        boolean sw = false;
        
        sw = Tool.deleteFile(uploadDir, file1);  // Folder에서 1건의 파일 삭제
        // -------------------------------------------------------------------
        // 파일 삭제 종료 시작
        // -------------------------------------------------------------------
        
        cnt = this.parkProc.my_park_delete(parkno);
        
        //mav.addObject("cnt", cnt);
        //mav.addObject("parkno", parkVO.getParkno());
        
        mav.setViewName("redirect:/admin/park_list.do");
        
        
        return mav;
        
    }
    
    
    
    
    
}

