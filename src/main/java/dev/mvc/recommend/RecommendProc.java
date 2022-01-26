package dev.mvc.recommend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.recommend.RecommendProc")
public class RecommendProc implements RecommendProcInter {
    
    @Autowired
    private RecommendDAOInter recommendDAO;

    //추천 내역 등록
    @Override
    public int recommend_create(RecommendVO recommendVO) {
        int cnt = this.recommendDAO.recommend_create(recommendVO);
        return cnt;
    }
    
    //추천 내역 조회    
    @Override
    public RecommendVO read(int memberno) {
        RecommendVO recommendVO = this.recommendDAO.read(memberno);
        return recommendVO;
    }

}
