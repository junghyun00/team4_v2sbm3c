CREATE TABLE team4.qna (
    qnano    NUMBER NOT NULL,
    memberno NUMBER NOT NULL,
    title    VARCHAR2(80 BYTE) NOT NULL,
    content  CLOB NOT NULL,
    PRIMARY KEY (qnano),
    FOREIGN key(memberno) REFERENCES MEMBER(memberno)
);

COMMENT ON COLUMN team4.qna.qnano IS
    '글 번호';

COMMENT ON COLUMN team4.qna.memberno IS
    '회원 번호';

COMMENT ON COLUMN team4.qna.title IS
    '글 제목';

COMMENT ON COLUMN team4.qna.content IS
    '글 내용';

CREATE SEQUENCE qna_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;                   -- 다시 1부터 생성되는 것을 방지

INSERT INTO qna(qnano, memberno, title, content)
VALUES(qna_seq.nextval, 2, '전기차 충전 관련하여', '전기차 충전시 주차요금감면방법이 궁금합니다.'); -- QNA 리스트 생성

INSERT INTO qna(qnano, memberno, title, content)
VALUES(qna_seq.nextval, 3, '결제 관련하여', '결제 모듈 오류시 조치방법이 어떤건가요?'); -- QNA 리스트 생성

INSERT INTO qna(qnano, memberno, title, content)
VALUES(qna_seq.nextval, 4, '문의 드립니다.', '카카오뱅크 체크카드로 결제가 가능한가요?'); -- QNA 리스트 생성

SELECT qnano, memberno, title, content from qna order by qnano asc; -- 글 번호를 오름차순으로 조회
SELECT qnano, memberno, title, content from qna where title like '%결제%'; -- 글 제목에 결재가 들어간 리스트 검색

update qna set title = '전기차 충전시 주차요금감면 관련하여' where title = '전기차 충전 관련하여'; -- QNA 글 제목 수정

delete from qna where memberno = 3; -- QNA에서 회원 번호가 3인 리스트 삭제