CREATE TABLE team4.answer (
    answerno NUMBER NOT NULL,
    qnano    NUMBER,
    content  CLOB NOT NULL,
    PRIMARY KEY (answerno),
    FOREIGN key(qnano) REFERENCES QNA(qnano)
);

COMMENT ON COLUMN team4.answer.answerno IS
    '답변 번호';

COMMENT ON COLUMN team4.answer.qnano IS
    '글 번호';

COMMENT ON COLUMN team4.answer.content IS
    '답변 내용';

CREATE SEQUENCE answer_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;                   -- 다시 1부터 생성되는 것을 방지

INSERT INTO answer(answerno, qnano, content)
VALUES(answer_seq.nextval, 1, '1시간 범위 내에서 주차요금을 면제하고 1시간 초과시 부터는 부과되는 주차요금의 100분의 50을 할인합니다.'); -- ANSWER 리스트 생성

INSERT INTO answer(answerno, qnano, content)
VALUES(answer_seq.nextval, 2, '주차장 인터넷 정기권 신청시 결제오류가 발생할 경우, 아래 링크를 클릭하셔서 문제 해결을 시도해보시기 바랍니다. 
http://www.inicis.com/blog/archives/496'); -- ANSWER 리스트 생성

INSERT INTO answer(answerno, qnano, content)
VALUES(answer_seq.nextval, 3, '카카오뱅크 체크카드로도 정기권 결제가 가능합니다.'); -- ANSWER 리스트 생성

SELECT answerno, qnano, content from answer order by answerno asc; -- 답변 번호를 오름차순으로 조회
SELECT answerno, qnano, content from answer where content like '%체크카드%'; -- 답변 내용에 체크카드가 들어간 리스트 검색

update answer set content = '전기차 충전 후 출차 시 무인정산기에서 호출 버튼을 눌러 직원과의 연결을 통해 전기차 주차요금 감면을 받으실 수 있습니다.' 
where content = '1시간 범위 내에서 주차요금을 면제하고 1시간 초과시 부터는 부과되는 주차요금의 100분의 50을 할인합니다.'; -- 답변 내용 수정

delete from answer where content like '%체크카드%'; -- 답변 내용에서 답변에 체크카드가 포함된 리스트 삭제

