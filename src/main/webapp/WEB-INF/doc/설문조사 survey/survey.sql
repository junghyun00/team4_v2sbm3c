CREATE TABLE SURVEY 
(   SURVEYNO NUMBER, 
   MEMBERNO NUMBER, 
   PURPOSEPARK NUMBER, 
   PREFERDAY NUMBER, 
   RESERVEPERIOD NUMBER,
   RDATE DATE,
    PRIMARY KEY (SURVEYNO),
    FOREIGN key(MEMBERNO) REFERENCES MEMBER(MEMBERNO)
) 

COMMENT ON TABLE SURVEY is '설문조사 테이블';
COMMENT ON COLUMN SURVEY.SURVEYNO is '설문조사 번호';
COMMENT ON COLUMN SURVEY.MEMBERNO is '회원 번호';
COMMENT ON COLUMN SURVEY.PURPOSEPARK is '주차 목적';
COMMENT ON COLUMN SURVEY.PREFERDAY is '선호 요일';
COMMENT ON COLUMN SURVEY.RESERVEPERIOD is '예약 기간';

CREATE SEQUENCE SURVEY_SEQ INCREMENT BY 1 START WITH 1 MAXVALUE 999999999 MINVALUE 1 CACHE 2;

INSERT INTO survey (surveyno,memberno,purposepark,preferday,reserveperiod) 
VALUES (1,1,1,2,2);
INSERT INTO survey (surveyno,memberno,purposepark,preferday,reserveperiod) 
VALUES (2,2,3,1,1);
INSERT INTO survey (surveyno,memberno,purposepark,preferday,reserveperiod) 
VALUES (3,3,2,1,2);

DELETE FROM survey
WHERE surveyno = 1;

UPDATE survey
SET purposepark = '주변 방문'
WHERE memberno = 1;

SELECT *
FROM survey
WHERE surveyno = 1;

SELECT *
FROM survey
order by surveyno asc;