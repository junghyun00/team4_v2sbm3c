-- 테이블 삭제(제약조건도)
DROP TABLE park CASCADE CONSTRAINTS;

-- 테이블 생성
CREATE TABLE park(
    parkno  NUMBER  NOT NULL PRIMARY KEY,
    memberno    NUMBER  NOT NULL,
    name    VARCHAR2(20 BYTE)   NOT NULL,
    phone   VARCHAR2(20 BYTE)   NOT NULL,
    address    VARCHAR2(150 BYTE)  NOT NULL,
    area    VARCHAR2(20 BYTE)   NOT NULL,
    price   NUMBER  NOT NULL,
    cmt     CLOB    
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


-- 외래키 설정
alter table PARK add foreign key (memberno) references member(memberno);


alter table REVIEW add foreign key (parkno) references park(parkno);
alter table REVIEW add foreign key (memberno) references member(memberno);

alter table QNA add foreign key (memberno) references member(memberno);

