package dev.mvc.qna;

public class QnaVO {
    private int qnano;
    private int memberno;
    private String title="";
    private String content="";

    public int getQnano() {
        return qnano;
    }
    public void setQnano(int qnano) {
        this.qnano = qnano;
    }
    public int getMemberno() {
        return memberno;
    }
    public void setMemberno(int memberno) {
        this.memberno = memberno;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
    
    
}
