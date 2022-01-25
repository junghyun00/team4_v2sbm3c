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
    
    
    /** �뾽濡쒕뱶 �뙆�씪 �젅�� 寃쎈줈 */
    private String uploadDir = Park.getUploadDir();
    
    
    public ParkCont() {
        System.out.println("-> ParkCont created.");
    }
    
    
    /**
     * 紐⑸줉 + 寃��깋 + �럹�씠吏�
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

            List<ParkVO> list2 = parkProc.only_address();
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
     * �벑濡� �뤌
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
     * �벑濡� 泥섎━
     * @param request
     * @param parkVO
     * @return
     */
    @RequestMapping(value = "/park/park_create.do", method = RequestMethod.POST)
    public ModelAndView park_create(HttpServletRequest request, ParkVO parkVO) {
        ModelAndView mav = new ModelAndView();
        
        mav.addObject("memberno", parkVO.getMemberno());
        
        // ------------------------------------------------------------------------------
        // �뙆�씪 �쟾�넚 肄붾뱶 �떆�옉
        // ------------------------------------------------------------------------------
        String file1 = "";    // �썝蹂� �뙆�씪紐� image
        String uploadDir = this.uploadDir;   // �뙆�씪 �뾽濡쒕뱶 寃쎈줈
        
        MultipartFile mf = parkVO.getFile1MF();
        
        file1 = Tool.getFname(mf.getOriginalFilename()); // �썝蹂� �닚�닔 �뙆�씪紐� �궛異�
        System.out.println("file1"+ file1);
        
        long size1 = mf.getSize(); // �뙆�씪 �겕湲�
        System.out.println("size1"+ size1);
        
        if (size1 > 0) { // �뙆�씪 �겕湲� 泥댄겕
            // �뙆�씪 ���옣 �썑 �뾽濡쒕뱶�맂 �뙆�씪紐낆씠 由ы꽩�맖
            file1 = Upload.saveFileSpring(mf, uploadDir);
            System.out.println("file1"+ file1);
        }
        

        parkVO.setFile1(file1);

        // ------------------------------------------------------------------------------
        // �뙆�씪 �쟾�넚 肄붾뱶 醫낅즺
        // ------------------------------------------------------------------------------
        mav.addObject("parkno", parkVO.getParkno());
        
        int cnt = this.parkProc.park_create(parkVO);
        //cnt = 0;    // else �뀒�뒪�듃
        
        mav.addObject("cnt", cnt);
        
        if (cnt == 1) {
            mav.setViewName("redirect:/mypage/my_park.do?memberno=" + parkVO.getMemberno());
        } else {
            mav.setViewName("/park/msg");
        }
        
        return mav;
    }
    
    
    /**
     * 湲� �븳 媛� 議고쉶
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
    *  �쉶�썝蹂� �벑濡앺븳 二쇱감�옣 紐⑸줉
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
     * �쉶�썝蹂� �벑濡앺븳 二쇱감�옣 紐⑸줉 議고쉶
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
     * 二쇱감�옣 �젙蹂� �닔�젙�뤌
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
     * 二쇱감�옣 �젙蹂� �닔�젙 泥섎━
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
        
        mav.setViewName("redirect:/mypage/my_park_read.do");
        
        return mav;
        
        
    }
    
    
    /**
     * 二쇱감�옣 �씠誘몄� �뙆�씪 �닔�젙 �뤌
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
     * 二쇱감�옣 �씠誘몄� �뙆�씪 �닔�젙 泥섎━
     * @param parkVO
     * @return
     */
    @RequestMapping(value="/mypage/my_park_update_file.do", method=RequestMethod.POST )
    public ModelAndView my_park_update_file(HttpServletRequest request, ParkVO parkVO) {
        ModelAndView mav = new ModelAndView();
        String uploadDir = this.uploadDir;
        
        
        ParkVO vo = parkProc.my_park_read(parkVO.getParkno());  // �궘�젣�븷 �뙆�씪 �젙蹂� �씫�뼱�샂
        
        mav.addObject("memberno", parkVO.getMemberno());
        System.out.println("memberno" + parkVO.getMemberno());
        
        
        int cnt = 0;
        // -------------------------------------------------------------------
        // �뙆�씪 �궘�젣 肄붾뱶 �떆�옉
        // -------------------------------------------------------------------
        String file1 = vo.getFile1();
        long size1 = 0;
        boolean sw = false;
        
        sw = Tool.deleteFile(uploadDir, file1);  // Folder�뿉�꽌 1嫄댁쓽 �뙆�씪 �궘�젣
        // -------------------------------------------------------------------
        // �뙆�씪 �궘�젣 醫낅즺 
        // -------------------------------------------------------------------
        // -------------------------------------------------------------------
        // �뙆�씪 �쟾�넚 肄붾뱶 �떆�옉
        // -------------------------------------------------------------------
        file1 = "";
        MultipartFile mf = parkVO.getFile1MF();
        
        file1 = mf.getOriginalFilename();
        size1 = mf.getSize(); // �뙆�씪 �겕湲�
        
        if (size1 > 0) { // �뙆�씪 �겕湲� 泥댄겕
            // �뙆�씪 ���옣 �썑 �뾽濡쒕뱶�맂 �뙆�씪紐낆씠 由ы꽩�맖
            file1 = Upload.saveFileSpring(mf, uploadDir);
            System.out.println("file1"+ file1);
        }
        

        parkVO.setFile1(file1);
        // -------------------------------------------------------------------
        // �뙆�씪 �쟾�넚 肄붾뱶 醫낅즺
        // -------------------------------------------------------------------
        cnt = this.parkProc.my_park_update_file(parkVO);
        
        mav.addObject("cnt", cnt);
        mav.addObject("parkno", parkVO.getParkno());
        mav.addObject("memberno", parkVO.getMemberno());
        
        mav.setViewName("redirect:/mypage/my_park_read.do");
        
        return mav;
        
        
    }
    
    
                                                            
    
    /**
     * 二쇱감�옣 �궘�젣 �뤌
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
     * 留덉씠�럹�씠吏� 二쇱감�옣 �궘�젣 �뤌 ajax
     * @param parkno
     * @return
     */
    @RequestMapping(value="/mypage/my_park_delete_ajax.do", method=RequestMethod.GET )
    @ResponseBody
    public String my_park_delete_ajax(int parkno) {
        ParkVO parkVO = this.parkProc.my_park_read(parkno);
        
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
     * 留덉씠�럹�씠吏� 二쇱감�옣 �궘�젣 泥섎━
     * @param parkno
     * @return
     */
    @RequestMapping(value="/mypage/my_park_delete.do", method=RequestMethod.POST)
    public ModelAndView my_park_delete(HttpServletRequest request, ParkVO parkVO) {
        ModelAndView mav = new ModelAndView();
        System.out.println("�뿬湲곗뿉�슂 �뿬湲�~");
        
        String uploadDir = this.uploadDir;
        
        int parkno = parkVO.getParkno();
        
        mav.addObject("memberno", parkVO.getMemberno());
        System.out.println("memberno" + parkVO.getMemberno());
        
        int cnt = 0;
        // -------------------------------------------------------------------
        // �뙆�씪 �궘�젣 肄붾뱶 �떆�옉
        // -------------------------------------------------------------------
        // �궘�젣�븷 �뙆�씪 �젙蹂대�� �씫�뼱�샂.
        ParkVO vo = parkProc.my_park_read(parkno);
        
        
        String file1 = vo.getFile1();
        long size1 = 0;
        boolean sw = false;
        
        sw = Tool.deleteFile(uploadDir, file1);  // Folder�뿉�꽌 1嫄댁쓽 �뙆�씪 �궘�젣
        // -------------------------------------------------------------------
        // �뙆�씪 �궘�젣 醫낅즺 �떆�옉
        // -------------------------------------------------------------------
        
        cnt = this.parkProc.my_park_delete(parkno);
        System.out.println(cnt);
        
        //mav.addObject("cnt", cnt);
        //mav.addObject("parkno", parkVO.getParkno());
        
        
        mav.setViewName("redirect:/mypage/my_park.do");
        
        
        return mav;
        
    }
    
    
    
    
    
    
    ///////////////// admin �슜///////////////////////
    /**
     * 紐⑸줉 + 寃��깋 + �럹�씠吏�
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
        map.put("now_page", now_page);  // �럹�씠吏��뿉 異쒕젰�븷 �젅肄붾뱶�쓽 踰붿쐞瑜� �궛異쒗븯湲곗쐞�빐 �궗�슜
        
        // 寃��깋 紐⑸줉
        List<ParkVO> list = parkProc.park_list_search_paging(map);
        mav.addObject("list", list);
        
        // 寃��깋 �젅肄붾뱶 媛쒖닔
        int search_count = parkProc.search_count(map);
        mav.addObject("search_count", search_count);
        
        // �럹�씠吏� 紐⑸줉 臾몄옄�뿴 �깮�꽦
        String paging = parkProc.admin_pagingBox(search_count, now_page, address);
        mav.addObject("paging", paging);
        mav.addObject("now_page", now_page);
        
        mav.setViewName("/admin/park_list");

        return mav;
        
    }
    
    
    /**
     * 二쇱감�옣 �젙蹂� �닔�젙�뤌
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
     * 二쇱감�옣 �젙蹂� �닔�젙 泥섎━
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
     * 二쇱감�옣 �씠誘몄� �뙆�씪 �닔�젙 �뤌
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
     * 二쇱감�옣 �씠誘몄� �뙆�씪 �닔�젙 泥섎━
     * @param parkVO
     * @return
     */
    @RequestMapping(value="/admin/park_update_file.do", method=RequestMethod.POST )
    public ModelAndView admin_park_update_file(HttpServletRequest request, ParkVO parkVO) {
        ModelAndView mav = new ModelAndView();
        String uploadDir = this.uploadDir;
        
        
        ParkVO vo = parkProc.my_park_read(parkVO.getParkno());  // �궘�젣�븷 �뙆�씪 �젙蹂� �씫�뼱�샂
        
        mav.addObject("memberno", parkVO.getMemberno());
        System.out.println("memberno" + parkVO.getMemberno());
        
        
        int cnt = 0;
        // -------------------------------------------------------------------
        // �뙆�씪 �궘�젣 肄붾뱶 �떆�옉
        // -------------------------------------------------------------------
        String file1 = vo.getFile1();
        long size1 = 0;
        boolean sw = false;
        
        sw = Tool.deleteFile(uploadDir, file1);  // Folder�뿉�꽌 1嫄댁쓽 �뙆�씪 �궘�젣
        // -------------------------------------------------------------------
        // �뙆�씪 �궘�젣 醫낅즺 
        // -------------------------------------------------------------------
        // -------------------------------------------------------------------
        // �뙆�씪 �쟾�넚 肄붾뱶 �떆�옉
        // -------------------------------------------------------------------
        file1 = "";
        MultipartFile mf = parkVO.getFile1MF();
        
        file1 = mf.getOriginalFilename();
        size1 = mf.getSize(); // �뙆�씪 �겕湲�
        
        if (size1 > 0) { // �뙆�씪 �겕湲� 泥댄겕
            // �뙆�씪 ���옣 �썑 �뾽濡쒕뱶�맂 �뙆�씪紐낆씠 由ы꽩�맖
            file1 = Upload.saveFileSpring(mf, uploadDir);
            System.out.println("file1"+ file1);
        }
        

        parkVO.setFile1(file1);
        // -------------------------------------------------------------------
        // �뙆�씪 �쟾�넚 肄붾뱶 醫낅즺
        // -------------------------------------------------------------------
        cnt = this.parkProc.my_park_update_file(parkVO);
        
        mav.addObject("cnt", cnt);
        mav.addObject("parkno", parkVO.getParkno());
        mav.addObject("memberno", parkVO.getMemberno());
        
        mav.setViewName("redirect:/admin/park_list.do");
        
        return mav;
        
        
    }
    
    
                                                            
    
    /**
     * 二쇱감�옣 �궘�젣 �뤌
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
     * 愿�由ъ옄�슜 二쇱감�옣 �궘�젣 �뤌 ajax
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
     * 愿�由ъ옄�슜 二쇱감�옣 �궘�젣 泥섎━
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
        // �뙆�씪 �궘�젣 肄붾뱶 �떆�옉
        // -------------------------------------------------------------------
        // �궘�젣�븷 �뙆�씪 �젙蹂대�� �씫�뼱�샂.
        ParkVO vo = this.parkProc.my_park_read(parkno);
        //System.out.println("vo = "+vo);
        
        String file1 = vo.getFile1();
        long size1 = 0;
        boolean sw = false;
        
        sw = Tool.deleteFile(uploadDir, file1);  // Folder�뿉�꽌 1嫄댁쓽 �뙆�씪 �궘�젣
        // -------------------------------------------------------------------
        // �뙆�씪 �궘�젣 醫낅즺 �떆�옉
        // -------------------------------------------------------------------
        
        cnt = this.parkProc.my_park_delete(parkno);
        
        //mav.addObject("cnt", cnt);
        //mav.addObject("parkno", parkVO.getParkno());
        
        mav.setViewName("redirect:/admin/park_list.do");
        
        
        return mav;
        
    }
    
    
    
    
    
}

