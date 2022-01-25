package dev.mvc.reservation;

public class Park_ReservationVO {
    /** 주차장 번호 */
    private int p_parkno;
    /** 주차장 이름 */
    private String p_name;
    /** 주차장 주소 */
    private String p_address;
    /** 주차장 전화번호 */
    private String p_phone;
    /** 주차 구역 번호 */
    private String p_area;
    /** 주차장 가격 */
    private int p_price;
    /** 주차장 설명 */
    private String p_cmt;
    /** 주차장 사진 */
    private String p_file1;
    
    
    
    /** 예약 번호 */
    private int reserveno;
    /** 회원 번호 */
    private int memberno;
    /** 주차장 번호 */
    private int parkno;
    /** 예약 일시 */
    private String reservedate;
    /** 예약 시간 */
    private String reservetime;
    /** 차 번호 */
    private String carno;
    /** 전달 사항 */
    private String notice;
    
    
    
    public int getP_parkno() {
        return p_parkno;
    }
    public void setP_parkno(int p_parkno) {
        this.p_parkno = p_parkno;
    }
    public String getP_name() {
        return p_name;
    }
    public void setP_name(String p_name) {
        this.p_name = p_name;
    }
    public String getP_address() {
        return p_address;
    }
    public void setP_address(String p_address) {
        this.p_address = p_address;
    }
    public String getP_phone() {
        return p_phone;
    }
    public void setP_phone(String p_phone) {
        this.p_phone = p_phone;
    }
    public String getP_area() {
        return p_area;
    }
    public void setP_area(String p_area) {
        this.p_area = p_area;
    }
    public int getP_price() {
        return p_price;
    }
    public void setP_price(int p_price) {
        this.p_price = p_price;
    }
    public String getP_cmt() {
        return p_cmt;
    }
    public void setP_cmt(String p_cmt) {
        this.p_cmt = p_cmt;
    }
    public String getP_file1() {
        return p_file1;
    }
    public void setP_file1(String p_file1) {
        this.p_file1 = p_file1;
    }
    public int getReserveno() {
        return reserveno;
    }
    public void setReserveno(int reserveno) {
        this.reserveno = reserveno;
    }
    public int getMemberno() {
        return memberno;
    }
    public void setMemberno(int memberno) {
        this.memberno = memberno;
    }
    public int getParkno() {
        return parkno;
    }
    public void setParkno(int parkno) {
        this.parkno = parkno;
    }
    public String getReservedate() {
        return reservedate;
    }
    public void setReservedate(String reservedate) {
        this.reservedate = reservedate;
    }
    public String getReservetime() {
        return reservetime;
    }
    public void setReservetime(String reservetime) {
        this.reservetime = reservetime;
    }
    public String getCarno() {
        return carno;
    }
    public void setCarno(String carno) {
        this.carno = carno;
    }
    public String getNotice() {
        return notice;
    }
    public void setNotice(String notice) {
        this.notice = notice;
    }
    @Override
    public String toString() {
        return "Park_ReservationVO [p_parkno=" + p_parkno + ", p_name=" + p_name + ", p_address=" + p_address
                + ", p_phone=" + p_phone + ", p_area=" + p_area + ", p_price=" + p_price + ", p_cmt=" + p_cmt
                + ", p_file1=" + p_file1 + ", reserveno=" + reserveno + ", memberno=" + memberno + ", parkno=" + parkno
                + ", reservedate=" + reservedate + ", reservetime=" + reservetime + ", carno=" + carno + ", notice="
                + notice + "]";
    }
    
    
    
    
    
    
    
    
    
    
    
}
