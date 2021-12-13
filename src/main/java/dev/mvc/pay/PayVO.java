package dev.mvc.pay;


/*
 payno     NUMBER NOT NULL,
 reserveno NUMBER NOT NULL,
 price     NUMBER NOT NULL,
 payway    VARCHAR2(50 BYTE) NOT NULL
 */

public class PayVO {
    /** 결제 번호 */
    private int payno;
    /** 예약 번호 */
    private int reserveno;
    /** 주차 가격 */
    private int price;
    /** 결제 수단 */
    private String payway;
    
    
    
    public int getPayno() {
        return payno;
    }
    public void setPayno(int payno) {
        this.payno = payno;
    }
    public int getReserveno() {
        return reserveno;
    }
    public void setReserveno(int reserveno) {
        this.reserveno = reserveno;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getPayway() {
        return payway;
    }
    public void setPayway(String payway) {
        this.payway = payway;
    }
    
    
    @Override
    public String toString() {
        return "PayVO [payno=" + payno + ", reserveno=" + reserveno + ", price=" + price + ", payway=" + payway + "]";
    }
    
    
    
    
}
