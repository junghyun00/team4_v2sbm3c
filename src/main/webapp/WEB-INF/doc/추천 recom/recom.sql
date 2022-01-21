CREATE TABLE recom (
  recommendno	NUMBER NOT NULL  PRIMARY KEY,
  parkno			NUMBER NOT NULL,
  memberno		NUMBER NOT NULL, 
  redate			DATE NOT NULL,
  reshow			VARCHAR2(5) DEFAULT 'Y', 
  FOREIGN KEY(parkno) REFERENCES park(parkno),
  FOREIGN KEY(memberno) REFERENCES member(memberno)
);

COMMENT ON TABLE recom is '추천 테이블';
COMMENT ON COLUMN recom.recommendno	is '추천 번호';
COMMENT ON COLUMN recom.parkno is '주차장 번호';
COMMENT ON COLUMN recom.memberno is '회원 번호';
COMMENT ON COLUMN recom.redate is '추천 날짜';
COMMENT ON COLUMN recom.reshow is '추천 여부';



CREATE SEQUENCE RECOM_SEQ INCREMENT BY 1 START WITH 1 MAXVALUE 9999999999 MINVALUE 1 CACHE 2;

INSERT INTO recom (recommendno, parkno, memberno, redate)
VALUES (RECOM_SEQ.nextval, 1, 1, SYSDATE);

INSERT INTO recom (recommendno, parkno, memberno, redate)
VALUES (RECOM_SEQ.nextval, 2, 2, SYSDATE);

INSERT INTO recom (recommendno, parkno, memberno, redate)
VALUES (RECOM_SEQ.nextval, 3, 3, SYSDATE);


SELECT recommendno, parkno, memberno, redate, reshow
FROM recom
where parkno=1;

SELECT recommendno, parkno, memberno, redate, reshow
FROM recom
ORDER BY recommendno asc;

UPDATE recom 
SET reshow='N'
WHERE memberno = 1;

DELETE FROM recom
WHERE recommendno = 1;