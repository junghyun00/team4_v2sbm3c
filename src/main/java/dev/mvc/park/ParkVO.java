package dev.mvc.park;

import org.springframework.web.multipart.MultipartFile;

/*
parkno  NUMBER  NOT NULL PRIMARY KEY,
memberno    NUMBER  NOT NULL,
name    VARCHAR2(50 BYTE)   NOT NULL,
phone   VARCHAR2(20 BYTE)   NOT NULL,
address    VARCHAR2(150 BYTE)  NOT NULL,
area    VARCHAR2(20 BYTE)   NOT NULL,
price   NUMBER  NOT NULL,
cmt     CLOB,
file1     VARCHAR2(100)  NOT NULL,
*/

public class ParkVO {
    /** 주차장 번호 */
    private int parkno;
    /** 회원 번호 */
    private int memberno;
    /** 주차장 이름 */
    private String name;
    /** 주차장 전화번호 */
    private String phone;
    /** 주차장 주소 */
    private String address;
    /** 주차 구역 번호 */
    private String area;
    /** 주차장 가격 */
    private int price;
    /** 주차장 설명 */
    private String cmt;
    /** 주차장 사진 */
    private String file1;
    
    
    
    /** 
    이미지 업로드 MultipartFile 
    <input type='file' class="form-control" name='file1MF' id='file1MF' 
                     value='' placeholder="파일 선택">
    */
    private MultipartFile file1MF;
    
    
    /**
     * 파일 크기 단위 출력
     */
    private String size1_label;



    public int getParkno() {
        return parkno;
    }

    public void setParkno(int parkno) {
        this.parkno = parkno;
    }

    public int getMemberno() {
        return memberno;
    }

    public void setMemberno(int memberno) {
        this.memberno = memberno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public String getFile1() {
        return file1;
    }

    public void setFile1(String file1) {
        this.file1 = file1;
    }

    public MultipartFile getFile1MF() {
        return file1MF;
    }

    public void setFile1MF(MultipartFile file1mf) {
        file1MF = file1mf;
    }

    public String getSize1_label() {
        return size1_label;
    }

    public void setSize1_label(String size1_label) {
        this.size1_label = size1_label;
    }

    @Override
    public String toString() {
        return "ParkVO [parkno=" + parkno + ", memberno=" + memberno + ", name=" + name + ", phone=" + phone
                + ", address=" + address + ", area=" + area + ", price=" + price + ", cmt=" + cmt + ", file1=" + file1
                + ", file1MF=" + file1MF + ", size1_label=" + size1_label + "]";
    }
    
    


}
