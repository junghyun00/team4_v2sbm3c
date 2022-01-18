package dev.mvc.member;

/*
memberno NUMBER NOT NULL PRIMARY KEY,
id       VARCHAR2(50 BYTE) NOT NULL,
passwd   VARCHAR2(50 BYTE) NOT NULL,
name     VARCHAR2(30 BYTE) NOT NULL,
address  VARCHAR2(150 BYTE),
phone    VARCHAR2(30 BYTE) NOT NULL,
email    VARCHAR2(100 BYTE)
*/

public class MemberVO {
    /** 회원 번호 */
    private int memberno;
    /** 회원 ID */
    private String id;
    /** 회원 패스워드 */
    private String passwd;
    /** 회원 이름 */
    private String name;
    /** 회원 주소 */
    private String address;
    /** 회원 핸드폰 번호 */
    private String phone;
    /** 회원 email */
    private String email;
    /** 등급 */
    private int grade = 0;
    
    
    
    public int getMemberno() {
        return memberno;
    }
    public void setMemberno(int memberno) {
        this.memberno = memberno;
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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    @Override
    public String toString() {
        return "MemberVO [memberno=" + memberno + ", id=" + id + ", passwd=" + passwd + ", name=" + name + ", address="
                + address + ", phone=" + phone + ", email=" + email + ", grade=" + grade + "]";
    }
    
    
    
    
    
    
    
}
