package dev.mvc.review;

/*
reviewno NUMBER NOT NULL,
parkno   NUMBER NOT NULL,
memberno NUMBER NOT NULL,
cmt      VARCHAR2(100 BYTE),
grade    NUMBER NOT NULL
*/

public class ReviewVO {
    /** 후기 번호 */
    private int reviewno;
    /** 주차장 번호 */
    private int parkno;
    /** 회원 번호 */
    private int memberno;
    /** 댓글 */
    private String cmt;
    /** 평점 */
    private int grade;
    
    
    
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
    
    
    @Override
    public String toString() {
        return "ReviewVO [reviewno=" + reviewno + ", parkno=" + parkno + ", memberno=" + memberno + ", cmt=" + cmt
                + ", grade=" + grade + "]";
    }
    
    
    
}
