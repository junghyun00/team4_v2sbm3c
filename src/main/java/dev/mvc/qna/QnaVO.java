package dev.mvc.qna;

import org.springframework.web.multipart.MultipartFile;

public class QnaVO {
    private int qnano;
    private int memberno;
    private String title="";
    private String content="";
    private String img="";
    
    private MultipartFile file1MF;
    
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
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    
    public MultipartFile getFile1MF() {
        return file1MF;
    }

    public void setFile1MF(MultipartFile file1mf) {
        file1MF = file1mf;
    }
    
    
}
