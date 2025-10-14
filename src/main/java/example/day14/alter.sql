use springweb2; -- 사용할 데이터베이스 선택

# [1] 생성된 테이블 수정하기
drop table if exists employee;
create table employee(		-- 테이블 생성
	id int ,
    name varchar(50) ,
    dept varchar(30)
);

# [2] 기존 테이블의 필드 추가
# alter table 테이블명 add column 새로운필드명 속석/제약조건;
alter table employee add column age int default 10; 
alter table employee add column date date; 

# [3] 기존 테이블의 필드 정보 수정
# alter table 테이블명 modify column 수정할필드명 새로운타입/제약조건
alter table employee modify column dept longtext;

# [4] 기존 테이블의 필드명 수정
# alter table 테이블명 change column 기존필드명 새로운필드명 타입
alter table employee change column name nickname varchar(100);

# [5] 기존 테이블의 필드 삭제 , 필드명 == 속정명 == 컬럼명
# alter table 테이블명 drop column 삭제할필드명
alter table employee drop column date;

# [6] 특정한 테이블의 레코드 정보 확인(속성값 확인)
show columns from employee;

# [7] 제약조건 추가
# alter table 테이블명 add constraint 제약조건명(중복없이 아무거나) primary key (pk필드명) 					-- pk제약 조건 추가
# alter table 테이블명 add constraint 제약조건명 foreign key (fk필드명) references 참조테이블명(pk필드명) 	-- fk제약조건 추가
alter table employee add constraint employee_id primary key (id);
alter table employee add constraint employee_nickname unique( nickname );

# [8] 제약조건 삭제
alter table employee drop primary key;
# alter table 테이블명 drop constraint 삭제할제약조건명
alter table employee drop constraint employee_id; 

# [9] 수정없이 삭제후 다시 제약조건 추가
# [10] 제약조건 확인
-- 전체 확인
select * from INFORMATION_SCHEMA.TABLE_CONSTRAINTS;
-- 특정 시그마 확인
select * from INFORMATION_SCHEMA.TABLE_CONSTRAINTS where TABLE_SCHEMA = "springweb2";
-- 특정 시그마의 특정 테이블 확인 
select * from INFORMATION_SCHEMA.TABLE_CONSTRAINTS 
	where TABLE_SCHEMA = "springweb2" and TABLE_NAME = "employee";

select * from employee;
desc employee;







