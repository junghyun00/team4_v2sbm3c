package dev.mvc.recommend;

public interface RecommendProcInter {
    public int recommend_create(RecommendVO recommendVO);
    
    public RecommendVO read(int memberno);

}
