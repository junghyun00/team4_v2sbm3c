package dev.mvc.recommend;

public interface RecommendDAOInter {
	//추천 내역 생성	
    public int recommend_create(RecommendVO recommendVO);
    
    //추천 내역 조회    
    public RecommendVO read(int memberno);

}
