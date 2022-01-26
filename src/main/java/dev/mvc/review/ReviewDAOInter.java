package dev.mvc.review;

import java.util.List;


public interface ReviewDAOInter {
<<<<<<< HEAD
	//¸®ºä µî·Ï	
	public int review_create(ReviewVO reviewvo);
	
	//ÁÖÂ÷Àå º° ¸®ºä ¸ñ·Ï	
	public List<ReviewlistVO> listcmt_by_parkno(int parkno);
	
	//¸®ºä »èÁ¦	
=======
    /**
     * ë¦¬ë·° ë“±ë¡    
     * @param reviewvo
     * @return
     */
	public int review_create(ReviewVO reviewvo);
	
	/**
	 * ì£¼ì°¨ì¥ ë³„ ë¦¬ë·° ëª©ë¡
	 * @param parkno
	 * @return
	 */
	public List<ReviewlistVO> listcmt_by_parkno(int parkno);
	
	/**
	 * ë¦¬ë·° ì‚­ì œ   
	 * @param reviewno
	 * @return
	 */
>>>>>>> 512b99187afd91405b2946adf789b22817dbe6fe
	public int review_delete(int reviewno);
}
