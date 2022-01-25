CREATE TABLE reservation ( 
    reserveno   NUMBER NOT NULL,
    memberno    NUMBER NOT NULL,
    parkno      NUMBER NOT NULL,
    reservedate DATE NOT NULL,
    reservetime DATE NOT NULL,
    carno       NUMBER NOT NULL,
    notice      VARCHAR2(150 BYTE),
  FOREIGN KEY(memberno) REFERENCES member(memberno)
); 

COMMENT ON TABLE reservation is '예약 테이블';
COMMENT ON COLUMN reservation.reserveno is '예약 번호';
COMMENT ON COLUMN reservation.memberno is '회원 번호';
COMMENT ON COLUMN reservation.parkno is '주차 번호';
COMMENT ON COLUMN reservation.reservedate is '예약 일시';
COMMENT ON COLUMN reservation.reservetime is '예약 시간';
COMMENT ON COLUMN reservation.carno is '차 번호';
COMMENT ON COLUMN reservation.notice is '전달 사항';

CREATE SEQUENCE reservation_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 999999999 --> NUMBER(10) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지


  
insert into reservation(reserveno,memberno,parkno,reservedate,reservetime,carno,notice)
values(reservation_seq.nextval,1,1,TO_DATE('2021-12-13','yyyy-mm-dd'),TO_DATE('18:12','hh24:mi'),3323,'전달사항입니다');


insert into reservation(reserveno,memberno,parkno,reservedate,reservetime,carno,notice)
values(reservation_seq.nextval,2,2,TO_DATE('2021-12-12','yyyy-mm-dd'),TO_DATE('18:10','hh24:mi'),3313,'전달사항입니다');

insert into reservation(reserveno,memberno,parkno,reservedate,reservetime,carno,notice)
values(reservation_seq.nextval,2,11,TO_DATE('2021-01-18 07:16','yyyy-mm-dd hh24:mi'), '2021-01-18 07:16', 1234, '전달사항입니다');


select reserveno,memberno,parkno,reservedate,reservetime,carno,notice
from reservation
where memberno =1;

select reserveno,memberno,parkno,reservedate,reservetime,carno,notice
from reservation
order by reservetime asc;


SELECT reserveno,memberno,parkno,reservedate,reservetime,carno,notice
FROM reservation
WHERE parkno = 1;

update reservation 
set carno =5523
where carno = 3323;

delete from reservation
where reserveno =70;



SELECT reserveno, memberno, parkno, reservedate, reservetime, carno,notice
FROM reservation
WHERE memberno = 1
ORDER BY memberno DESC;




-- reservation + park join
-- 회원 번호가 1번인 회원이 한 예약 목록
SELECT p.parkno as p_parkno, p.name as p_name, 
           r.reservedate, r.carno, r.notice
FROM park p, reservation r
WHERE p.parkno = r.parkno AND r.memberno= 1
ORDER BY r.reserveno DESC;


select * from reservation;


-- reservation + park join
-- 전체 예약 목록
SELECT p.parkno as p_parkno, p.name as p_name, 
           r.reserveno, r.reservedate, r.carno, r.notice
FROM park p, reservation r
WHERE p.parkno = r.parkno
ORDER BY r.reserveno DESC;



-- reservation + park join
-- 회원 번호가 1번인 회원이 한 39번 예약
SELECT p.parkno as p_parkno, p.name as p_name, 
           r.reserveno, r.reservedate, r.reservetime, r.carno, r.notice
FROM park p, reservation r
WHERE p.parkno = r.parkno AND r.memberno = 1 AND r.reserveno=39;


-- 수정
UPDATE reservation
SET reservedate = TO_DATE('2021-05-15','YYYY-MM-DD'), reservetime = TO_DATE('05:05','HH24:MI'), carno = 1555, notice = '없다.'
WHERE reserveno = 45;

