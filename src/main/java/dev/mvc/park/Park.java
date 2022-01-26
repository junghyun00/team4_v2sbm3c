package dev.mvc.park;

import java.io.File;

public class Park {

    /** 페이지당 출력할 레코드 갯수 */

    /** �럹�씠吏��떦 異쒕젰�븷 �젅肄붾뱶 媛��닔 */
    public static int RECORD_PER_PAGE  = 10;
    
    /** 釉붾윮�떦 �럹�씠吏� �닔, �븯�굹�쓽 釉붾윮�� 1媛� �씠�긽�쓽 �럹�씠吏�濡� 援ъ꽦�맖 */
    public static int PAGE_PER_BLOCK = 5;
    
    
    /** 紐⑸줉 �뙆�씪紐� */
    public static String LIST_FILE = "park_list_search_paging.do";
    
    /** admin 紐⑸줉 �뙆�씪紐� */
    public static String ADMIN_LIST_FILE = "park_list.do";
    
    
    // Windows, VMWare, AWS cloud �젅�� 寃쎈줈
    public static synchronized String getUploadDir() {
        String path = "";
        if (File.separator.equals("\\")) {
            //System.out.println("Windows 10");
            path = "C:/kd1/deploy/team4_v2sbm3c/park/storage/";
        } else {
            //System.out.println("Linux");
            path = "/home/ubuntu/deploy/team4_v2sbm3c/park/storage/";
        }
        
        
        return path;
    }
}
