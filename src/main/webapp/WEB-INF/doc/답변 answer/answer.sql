-- 테이블 삭제(제약조건)
DROP TABLE answer CASCADE CONSTRAINTS;

-- 테이블 생성
CREATE TABLE answer(
    answerno  NUMBER  NOT NULL PRIMARY KEY,
    qnano    NUMBER  NOT NULL,
    adminno    NUMBER   NOT NULL,
    content   CLOB
);

COMMENT ON TABLE answer is '답변';
COMMENT ON COLUMN answer.answerno is '답변 번호';
COMMENT ON COLUMN answer.qnano is '글 번호';
COMMENT ON COLUMN answer.adminno is '관리자 번호';
COMMENT ON COLUMN answer.content is '답변 내용';


-- 외래키 설정
alter table ANSWER add foreign key (qnano) references QNA(qnano);
alter table ANSWER add foreign key (adminno) references admin(adminno);
