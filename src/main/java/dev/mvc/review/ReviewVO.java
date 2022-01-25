package dev.mvc.review;

/*
reviewno NUMBER NOT NULL,
parkno   NUMBER NOT NULL,
memberno NUMBER NOT NULL,
cmt      VARCHAR2(100 BYTE),
grade    NUMBER NOT NULL
*/

public class ReviewVO {
    /** �썑湲� 踰덊샇 */
    private int reviewno;
    /** 二쇱감�옣 踰덊샇 */
    private int parkno;
    /** �쉶�썝 踰덊샇 */
    private int memberno;
    /** �뙎湲� */
    private String cmt;
    /** �룊�젏 */
    private int grade;
    private String cmt_date;
    
    
    public int getReviewno() {
        return reviewno;
    }
    public void setReviewno(int reviewno) {
        this.reviewno = reviewno;
    }
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
    public String getCmt() {
        return cmt;
    }
    public void setCmt(String cmt) {
        this.cmt = cmt;
    }
    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public String cmt_date() {
        return cmt_date;
    }
    public void cmt_date(String cmt_date) {
        this.cmt_date = cmt_date;
    }
    
    
    @Override
    public String toString() {
        return "ReviewVO [reviewno=" + reviewno + ", parkno=" + parkno + ", memberno=" + memberno + ", cmt=" + cmt
                + ", grade=" + grade + "]";
    }
    
    
    
}
