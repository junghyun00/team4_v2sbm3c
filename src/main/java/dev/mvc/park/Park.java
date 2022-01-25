package dev.mvc.park;

import java.io.File;

public class Park {
    /** 페이지당 출력할 레코드 갯수 */
    public static int RECORD_PER_PAGE  = 6;
//    public static int RECORD_PER_PAGE  = 10;
    
    public static int PAGE_PER_BLOCK = 5;
    
    public static String LIST_FILE = "park_list_search_paging.do";
    
    public static String ADMIN_LIST_FILE = "park_list.do";
    
    
    // Windows, VMWare, AWS cloud 
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
