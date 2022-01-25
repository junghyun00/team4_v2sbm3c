package dev.mvc.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.review.Reviewproc")
public class ReviewProc implements ReviewProcInter{
	@Autowired
	private ReviewDAOInter reviewdao;

	@Override
	public int review_create(ReviewVO reviewvo) {
		int cnt = this.reviewdao.review_create(reviewvo);
		return cnt;
	}

	@Override
	public List<ReviewlistVO> listcmt_by_parkno(int parkno) {
		List<ReviewlistVO> cmtlist = this.reviewdao.listcmt_by_parkno(parkno);
		return cmtlist;
	}

	@Override
	public int review_delete(int reviewno) {
		int cnt = this.reviewdao.review_delete(reviewno);
		return cnt;
	}
	
}
