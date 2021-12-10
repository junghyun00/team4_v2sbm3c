-- 테이블 삭제(제약조건도)
DROP TABLE member CASCADE CONSTRAINTS;


-- 테이블 생성
CREATE TABLE member (
    memberno NUMBER NOT NULL PRIMARY KEY,
    id       VARCHAR2(50 BYTE) NOT NULL,
    passwd   VARCHAR2(50 BYTE) NOT NULL,
    name     VARCHAR2(30 BYTE) NOT NULL,
    address  VARCHAR2(150 BYTE),
    phone    VARCHAR2(30 BYTE) NOT NULL,
    email    VARCHAR2(100 BYTE)
);

COMMENT ON TABLE member is '회원 테이블';
COMMENT ON COLUMN member.memberno is '회원번호';
COMMENT ON COLUMN member.id is '회원 ID';
COMMENT ON COLUMN member.passwd is '회원 패스워드';
COMMENT ON COLUMN member.name is '회원 이름';
COMMENT ON COLUMN member.address is '회원 주소';
COMMENT ON COLUMN member.phone is '회원 핸드폰 번호';
COMMENT ON COLUMN member.email is '회원 EMAIL';


DROP SEQUENCE member_seq;

CREATE SEQUENCE member_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 999999999 --> NUMBER(10) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
  
  
-- 등록
insert into member(memberno, id, passwd, name, address, phone, email)
values(member_seq.nextval, 'abc123', '123456', '가나다', '서울특별시 강남구', '01011112222', 'abc123@mail.com');

insert into member(memberno, id, passwd, name, address, phone, email)
values(member_seq.nextval, 'def456', '789123', '라마바', '서울특별시 광진구', '01033334444', 'def456@mail.com');

insert into member(memberno, id, passwd, name, address, phone, email)
values(member_seq.nextval, 'ghi789', '456789', '사아자', '서울특별시 성동구', '01055556666', 'ghi789@mail.com');
  

-- 조회
select memberno, id, passwd, name, address, phone, email
from member
order by memberno asc;

-- 삭제
DELETE FROM member
WHERE memberno=4;

-- 수정
UPDATE member
SET passwd=123, name='바바바'
WHERE memberno=4;

commit;

