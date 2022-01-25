CREATE TABLE review ( 
    reviewno 	NUMBER NOT NULL,
    parkno   	NUMBER NOT NULL,
    memberno 	NUMBER NOT NULL,
    cmt      		VARCHAR2(100 BYTE),
    grade    	NUMBER NOT NULL,
  FOREIGN KEY(parkno) REFERENCES park(parkno),
  FOREIGN KEY(memberno) REFERENCES member(memberno)
); 

COMMENT ON TABLE review is '리뷰 테이블';
COMMENT ON COLUMN reservation.reviewno is '리뷰 번호';
COMMENT ON COLUMN reservation.parkno is '주차장 번호';
COMMENT ON COLUMN reservation.memberno is '회원 번호';
COMMENT ON COLUMN reservation.cmt is '댓글';
COMMENT ON COLUMN reservation.grade is '평점';


CREATE SEQUENCE REVIEW_SEQ INCREMENT BY 1 START WITH 1 MAXVALUE 999999999 MINVALUE 1 CACHE 2;


INSERT INTO review (reviewno, parkno, memberno, cmt, grade) 
VALUES (REVIEW_SEQ.nextval, 1, 1, '깨끗하고 가격도 저렴해서 좋습니다!', 4.5);

INSERT INTO review (reviewno, parkno, memberno, cmt, grade) 
VALUES (REVIEW_SEQ.nextval, 2, 2, '주차장은 넓고 좋은데 가격이 비싸네요', 3);

INSERT INTO review (reviewno, parkno, memberno, cmt, grade) 
VALUES (REVIEW_SEQ.nextval, 3, 3, '주차 자리도 너무 좁고 가격도 비싸요 다시는 안갈거 같네요', 1);




SELECT reviewno,parkno, memberno, cmt, grade
FROM review
where parkno=1;

SELECT reviewno,  parkno,  memberno, cmt, grade
FROM  review
ORDER BY reviewno asc;

DELETE FROM review
WHERE reviewno = 1;

UPDATE review
SET grade=5.0
WHERE memberno=1;