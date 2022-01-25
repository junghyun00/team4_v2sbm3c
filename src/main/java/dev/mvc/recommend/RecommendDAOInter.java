package dev.mvc.recommend;

public interface RecommendDAOInter {
    public int recommend_create(RecommendVO recommendVO);
    
    public RecommendVO read(int memberno);

}
