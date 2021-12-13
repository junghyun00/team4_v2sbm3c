package dev.mvc.admin;


/*
adminno NUMBER NOT NULL PRIMARY KEY,
id      VARCHAR2(50 BYTE) NOT NULL,
passwd  VARCHAR2(50 BYTE) NOT NULL,
name    VARCHAR2(30 BYTE) NOT NULL 
 */

public class AdminVO {
    /** 관리자 번호 */
    private int adminno;
    /** 관리자 ID */
    private String id;
    /** 관리자 패스워드 */
    private String passwd;
    /** 관리자 이름 */
    private String name;
    
    
    
    public int getAdminno() {
        return adminno;
    }
    public void setAdminno(int adminno) {
        this.adminno = adminno;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPasswd() {
        return passwd;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    
    @Override
    public String toString() {
        return "AdminVO [adminno=" + adminno + ", id=" + id + ", passwd=" + passwd + ", name=" + name + "]";
    }
    
    
    
    
}
