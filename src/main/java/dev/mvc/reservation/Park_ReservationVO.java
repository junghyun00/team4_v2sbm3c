package dev.mvc.reservation;

public class Park_ReservationVO {
    /** 주차장 번호 */
    private int p_parkno;
    /** 주차장 이름 */
    private String p_name;
    
    
    
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
    
    
//    // substring을 이용한 날짜 시간 합치기
//    public String getSubReservedate() {
//        String date = getReservedate();
//        String rdate = date.substring(0, 10);
//        System.out.println(rdate);
//        return rdate;
//        
//    }
//    public String getSubReservetime() {
//        String time = getReservedate();
//        String rtime = time.substring(10);
//        System.out.println(rtime);
//        return rtime;
//    }
    
    
    @Override
    public String toString() {
        return "Park_ReservationVO [p_parkno=" + p_parkno + ", p_name=" + p_name + ", reserveno=" + reserveno
                + ", memberno=" + memberno + ", parkno=" + parkno + ", reservedate=" + reservedate + ", reservetime="
                + reservetime + ", carno=" + carno + ", notice=" + notice + "]";
    }
    
    
    
    
}
