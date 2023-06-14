/*
	1. 회원 로그인 - 간단히 회원아이디를 입력해서 존재하면 로그인 성공
	2. 상품 목록 보기
	3. 상품명으로 검색하기
	4. 상품 장바구니 담기 - 장바구니 테이블이 없으므로 구매를 원하는 상품을 List 에 담기
	5. 상품 구매(결제)하기 - 장바구니의 데이터를 '구매' 테이블에 입력하기 (여러개 데이터를 INSERT)
	6. 나의 구매 내역 보기. 총 구매 금액을 출력해주기
*/

select * from TBL_CUSTOM;
select * from TBL_PRODUCT;
select * from TBL_BUY;


select * from TBL_PRODUCT where pname like '%'||'동원'||'%';

SELECT * FROM TBL_BUY; -- 구매 정보 테이블
SELECT * FROM TBL_BUY WHERE CUSTOMID = 'mina012';

-- j_custom
create table j_custom
as								-- 이런식으로 만들면 pk , fk 는 복사하지 않음
select * from tbl_custom;			-- 필요시 따로 제약조건을 추가해야함
--------------------------
select * from j_custom;
--------------------------
-- j_product
create table j_product
as
select * from tbl_product;
--------------------------
select * from j_product;
--------------------------
-- j_buy
create table j_buy
as
select * from tbl_buy;
--------------------------
select * from j_buy;
--------------------------

-- pk , fk 는 복사되지 않았으므로 따로 제약조건을 추가해주겠습니다.
	-- custom_id , pcode , buy_seq 컬럼으로 pk 설정하기
	-- tbl_buy 테이블에는 외래키도 2개가 있습니다. (j_buy는 외래키 설정을 제외하고 하겠습니다.)
alter table j_custom add constraint custom_pk primary key(customer_id);
alter table j_product add constraint product_pk primary key(pcode);
alter table j_buy add constraint buy_pk primary key(buy_seq);





