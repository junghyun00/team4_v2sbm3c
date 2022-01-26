package dev.mvc.review;

import java.util.List;


public interface ReviewDAOInter {
	//리뷰 등록	
	public int review_create(ReviewVO reviewvo);
	
	//주차장 별 리뷰 목록	
	public List<ReviewlistVO> listcmt_by_parkno(int parkno);
	
	//리뷰 삭제	
	public int review_delete(int reviewno);
}
