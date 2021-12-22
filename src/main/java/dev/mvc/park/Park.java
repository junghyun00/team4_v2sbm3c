package dev.mvc.park;



public class Park {
    /** 페이지당 출력할 레코드 갯수 */
    public static int RECORD_PER_PAGE = 3;
    
    /** 블럭당 페이지 수, 하나의 블럭은 1개 이상의 페이지로 구성됨 */
    public static int PAGE_PER_BLOCK = 10;
    
    
    /** 목록 파일명 */
    public static String LIST_FILE = "park_list_search_paging.do";
    
    
    /* 혹시 몰라서 주석 처리함
    // Windows, VMWare, AWS cloud 절대 경로
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
    */
}
