package dev.mvc.answer;


public interface AnswerProcInter {
    
    /**
     * 답변 등록
     * @param answerVO
     * @return
     */
    public int answer_create(AnswerVO answerVO);
    
    public AnswerVO answer_read(int answerno);

}
