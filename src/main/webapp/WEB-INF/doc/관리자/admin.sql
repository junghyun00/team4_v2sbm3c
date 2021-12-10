-- 테이블 삭제(제약조건도)
DROP TABLE admin CASCADE CONSTRAINTS;


-- 테이블 생성
CREATE TABLE admin (
    adminno NUMBER NOT NULL PRIMARY KEY,
    id      VARCHAR2(50 BYTE) NOT NULL,
    passwd  VARCHAR2(50 BYTE) NOT NULL,
    name    VARCHAR2(30 BYTE) NOT NULL
);

COMMENT ON TABLE admin is '관리자 테이블';
COMMENT ON COLUMN admin.adminno is '관리자 번호';
COMMENT ON COLUMN admin.id is '관리자 ID';
COMMENT ON COLUMN admin.passwd is '관리자 패스워드';
COMMENT ON COLUMN admin.name is '관리자 이름';



DROP SEQUENCE admin_seq;

CREATE SEQUENCE admin_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 999999999 --> NUMBER(10) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
  
  
-- 등록
insert into admin(adminno, id, passwd, name)
values(admin_seq.nextval, 'qwe000', '1234', '하하하');

insert into admin(adminno, id, passwd, name)
values(admin_seq.nextval, 'asd111', '5678', '파파파');

insert into admin(adminno, id, passwd, name)
values(admin_seq.nextval, 'zxc222', '9123', '타타타');
  
commit;



-- 조회
select adminno, id, passwd, name
from admin
order by adminno asc;



-- 삭제
DELETE FROM admin
WHERE adminno=4;


-- 수정
UPDATE admin
SET passwd=123
WHERE adminno=4;











