package dev.mvc.recommend;

public interface RecommendDAOInter {
	//��õ ���� ����	
    public int recommend_create(RecommendVO recommendVO);
    
    //��õ ���� ��ȸ    
    public RecommendVO read(int memberno);

}
