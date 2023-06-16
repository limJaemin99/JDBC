SELECT * FROM MEMBER_TBL_02;
SELECT * FROM MONEY_TBL_02;

-- 회원가입 번호 시퀀스 
create sequence joinmem_seq start with 100007;
select joinmem_seq.nextval from dual;

drop sequence joinmem_seq;


-- custno에 해당하는 price 를 sum as sales(매출) 로 만들기
select custno , sum(price) as sales
from MONEY_TBL_02
group by custno
order by custno;
-- 결과를 view 테이블로 만들기
create or replace view custSales
as
select custno , sum(price) as sales
from MONEY_TBL_02
group by custno
order by custno;

-- 문제에서 제시한 조건 출력문
select m.custno , m.custname , decode(m.grade,'A','VIP','B','일반','C','직원') , c.sales
from MEMBER_TBL_02 m join custSales c on m.custno = c.custno;
-- 결과를 view 테이블로 만들기
create or replace view Check_sales
as
select m.custno , m.custname , decode(m.grade,'A','VIP','B','일반','C','직원') as grade , c.sales
from MEMBER_TBL_02 m join custSales c on m.custno = c.custno;

-- 확인용 출력문
select * from check_sales;


