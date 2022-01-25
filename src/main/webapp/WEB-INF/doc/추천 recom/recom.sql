DROP TABLE recom;

CREATE TABLE recom (
  recommendno	NUMBER NOT NULL  PRIMARY KEY,
  memberno		NUMBER NOT NULL, 
  purposepark  NUMBER NOT NULL, 
  preferday      NUMBER NOT NULL, 
  reserveperiod  NUMBER NOT NULL, 
  redate			DATE,
  FOREIGN KEY(memberno) REFERENCES member(memberno)
);

ALTER TABLE RECOM ADD CONSTRAINT memberno FOREIGN KEY(MEMBERNO) REFERENCES MEMBER(MEMBERNO);

COMMENT ON TABLE recom is '추천 테이블';
COMMENT ON COLUMN recom.recommendno	is '추천 내역 번호';
COMMENT ON COLUMN recom.memberno is '회원 번호';
COMMENT ON COLUMN recom.purposepark is '회원 번호';
COMMENT ON COLUMN recom.preferday is '회원 번호';
COMMENT ON COLUMN recom.reserveperiod is '회원 번호';
COMMENT ON COLUMN recom.redate is '추천 날짜';

CREATE SEQUENCE RECOM_SEQ INCREMENT BY 1 START WITH 1 MAXVALUE 9999999999 MINVALUE 1 CACHE 2;


INSERT INTO recom (recommendno, memberno, purposepark, preferday, reserveperiod, redate)
VALUES (RECOM_SEQ.nextval, 1, 1, 2, 1, SYSDATE);

INSERT INTO recom (recommendno, parkno, memberno, redate)
VALUES (RECOM_SEQ.nextval, 2, 2, 1, 1, SYSDATE);

INSERT INTO recom (recommendno, parkno, memberno, redate)
VALUES (RECOM_SEQ.nextval, 3, 3, 1, 2, SYSDATE);


SELECT recommendno, memberno, purposepark, preferday, reserveperiod, redate
FROM recom
where memberno=1;

SELECT recommendno, memberno, purposepark, preferday, reserveperiod, redate
FROM recom
ORDER BY recommendno asc;

UPDATE recom 
SET preferday = 1
WHERE memberno = 1;

DELETE FROM recom
WHERE recommendno = 2 or recommendno = 3;