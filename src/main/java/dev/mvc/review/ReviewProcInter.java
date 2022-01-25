package dev.mvc.review;

import java.util.List;

public interface ReviewProcInter {
	public int review_create(ReviewVO reviewvo);
	public List<ReviewlistVO> listcmt_by_parkno(int parkno);
	public int review_delete(int reviewno);
}
