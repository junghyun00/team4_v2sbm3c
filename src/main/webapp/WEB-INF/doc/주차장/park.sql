-- 테이블 삭제(제약조건도)
DROP TABLE park CASCADE CONSTRAINTS;

-- 테이블 생성
CREATE TABLE park(
    parkno  NUMBER  NOT NULL PRIMARY KEY,
    memberno    NUMBER  NOT NULL,
    name    VARCHAR2(50 BYTE)   NOT NULL,
    phone   VARCHAR2(20 BYTE)   NOT NULL,
    address    VARCHAR2(150 BYTE)  NOT NULL,
    area    VARCHAR2(20 BYTE)   NOT NULL,
    price   NUMBER  NOT NULL,
    cmt     CLOB,
    file1     VARCHAR2(100)  NOT NULL,
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE park is '주차장 정보';
COMMENT ON COLUMN park.parkno is '주차장 번호';
COMMENT ON COLUMN park.memberno is '회원 번호';
COMMENT ON COLUMN park.name is '주차장 이름';
COMMENT ON COLUMN park.phone is '주차장 전화번호';
COMMENT ON COLUMN park.address is '주차장 주소';
COMMENT ON COLUMN park.area is '주차 구역 번호';
COMMENT ON COLUMN park.price is '주차장 가격';
COMMENT ON COLUMN park.cmt is '주차장 설명';
COMMENT ON COLUMN park.file1 is '주차장 사진';



DROP SEQUENCE park_seq;

CREATE SEQUENCE park_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 999999999 --> NUMBER(10) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지


-- 등록
insert into park(parkno, memberno, name, phone, address, area, price, cmt, file1)
values(park_seq.nextval, 1, '강남역 주변 주차장', '01011112222', '서울 강남구', 'a03', 1000, '', 'image01.jpg');

insert into park(parkno, memberno, name, phone, address, area, price, cmt, file1)
values(park_seq.nextval, 1, '강남역 주변 주차장', '01011112222', '서울 강남구', 'a04', 1000, '주차 자리가 협소합니다', 'image02.jpg');

insert into park(parkno, memberno, name, phone, address, area, price, cmt, file1)
values(park_seq.nextval, 2, '건대입구 근처 주차장', '01033334444', '서울 광진구', '103호', 500, '', 'image03.jpg');

insert into park(parkno, memberno, name, phone, address, area, price, cmt, file1)
values(park_seq.nextval, 3, '성동빌딩 민영주차장', '0237141552', '서울 성동구', 'A1', 1000, '문의사항 있으면 연락 바랍니다.', 'image04.jpg');



-- 조회
select parkno, memberno, name, phone, address, area, price, cmt, file1
from park
order by parkno desc;



-- 삭제
DELETE FROM park
WHERE parkno=5;


-- 수정
UPDATE park
SET area='A06'
WHERE parkno=4;

commit;



















