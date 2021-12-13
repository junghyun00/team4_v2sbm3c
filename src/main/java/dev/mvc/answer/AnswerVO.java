package dev.mvc.answer;

/*
answerno NUMBER NOT NULL,
qnano    NUMBER,
adminno  NUMBER,
content  CLOB NOT NULL
*/

public class AnswerVO {
    /** 답변 번호 */
    private int answerno;
    /** 글 번호 */
    private int qnano;
    /** 관리자 번호 */
    private int adminno;
    /** 답변 내용 */
    private String content;
    
    
    public int getAnswerno() {
        return answerno;
    }
    public void setAnswerno(int answerno) {
        this.answerno = answerno;
    }
    public int getQnano() {
        return qnano;
    }
    public void setQnano(int qnano) {
        this.qnano = qnano;
    }
    public int getAdminno() {
        return adminno;
    }
    public void setAdminno(int adminno) {
        this.adminno = adminno;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
    
    @Override
    public String toString() {
        return "AnswerVO [answerno=" + answerno + ", qnano=" + qnano + ", adminno=" + adminno + ", content=" + content
                + "]";
    }
    
    
    
    
}
