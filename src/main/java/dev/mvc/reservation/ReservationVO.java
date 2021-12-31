package dev.mvc.reservation;

/*
reserveno   NUMBER NOT NULL,
memberno    NUMBER NOT NULL,
parkno      NUMBER NOT NULL,
reservedate DATE NOT NULL,
reservetime DATE NOT NULL,
carno        VARCHAR2(30 BYTE) NOT NULL,
notice      VARCHAR2(150 BYTE)
*/

public class ReservationVO {
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
        return "ReservationVO [reserveno=" + reserveno + ", memberno=" + memberno + ", parkno=" + parkno
                + ", reservedate=" + reservedate + ", reservetime=" + reservetime + ", carno=" + carno + ", notice="
                + notice + "]";
    }
    
    
    
    
}
