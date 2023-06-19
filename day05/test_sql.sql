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


--------------------------------------- 선생님 답변 ---------------------------------------
							-- 아래 외부조인 부분 확인하기 ★★★★★
-- step 1 회원별 매출합계
select custno, sum(price) from money_tbl_02 group by custno;

-- step 2 정렬 기준 확인하기
select custno, sum(price) from money_tbl_02 group by custno order by sum(price) desc;

-- step 3 custno 컬럼으로 조인하여 고객 정보 전체 가져오기
select * from member_tbl_02 met,
   (select custno, sum(price) asum from money_tbl_02 mot 
   group by custno
   order by asum desc) sale
where met.custno = sale.custno;
-- 또는
select * from member_tbl_02 met join
   (select custno, sum(price) asum from money_tbl_02 mot 
   group by custno
   order by asum desc) sale
on met.custno = sale.custno;

-- step 4 필요한 컬럼만 가져오기
select met.custno, custname,
   decode(met.grade, 'A', 'VIP', 'B', '일반', 'C', '직원') as grade,
   asum
   from member_tbl_02 met join
   (select custno, sum(price) asum from money_tbl_02 mot 
   group by custno
   order by asum desc) sale
   on met.custno = sale.custno ORDER BY asum desc;
-- 또는
select met.custno, custname,
   decode(met.grade, 'A', 'VIP', 'B', '일반', 'C', '직원') as grade,
   sale.asum
   from member_tbl_02 met,
   (select custno, sum(price) asum from money_tbl_02 mot 
   group by custno
   order by asum desc) sale
   where met.custno = sale.custno 
   ORDER BY total desc;

++ decode(grade, 'A', 'VIP', 'B', '일반', 'C', '직원');

-- 외부조인 : 매출이 없는 회원도 포함한다.----------------------------- ★★★★★ 
select met.custno, custname,
   decode(met.grade, 'A', 'VIP', 'B', '일반', 'C', '직원') as grade,
   nvl(sale.asum,0) total
   from member_tbl_02 met LEFT OUTER join
   (select custno, sum(price) asum from money_tbl_02 mot 
   group by custno
   order by asum desc) sale
   on met.custno = sale.custno ORDER BY total DESC ,custno;
   