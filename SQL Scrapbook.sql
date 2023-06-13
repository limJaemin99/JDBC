
-- CRUD(Create, Read, Update, Delete) : 데이터의 생성, 읽기, 갱신, 삭제를 가리키는 약자
-- 프로그램이 가져야 할 사용자 인터페이스(메뉴) 기본 기능

-- 단순 조회 SELECT (Read)
select * from TBL_STUDENT;
select * from TBL_SCORE;

-- INSERT 테스트 (Create)
insert into TBL_STUDENT values ('2023001','김땡땡',16,'경기도');

select a.*, b.subject, b.jumsu, b.teacher, b.term from TBL_STUDENT a left join tbl_score b on a.stuno=b.stuno


-- UPDATE 테스트 ★★★★★
UPDATE TBL_STUDENT
SET AGE = 17, ADDRESS = '종로구'
WHERE STUNO = '2021017';

-- DELETE 테스트
delete from TBL_STUDENT where stuno = '201901';



select * from TBL_SCORE where subject = '국어';

select a.name, b.subject, b.jumsu
from tbl_student a left join TBL_score b on a.stuno = b.stuno
where subject = '국어';

select * from tbl_score

select count(*)
from (select a.name, b.subject, b.jumsu
from tbl_student a left join TBL_score b on a.stuno = b.stuno
where subject = '국어');

