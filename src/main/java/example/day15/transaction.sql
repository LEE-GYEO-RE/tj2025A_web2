USE springweb2; -- 데이터베이스 선택
set SQL_SAFE_UPDATES = 0;	-- mysql 워크벤치에서 update 사용 설정

# 트랜잭션 : 여러 작업들을 하나의 묶음으로 간주하여 모두 성공하면 commit  , 하나라도 실패이면 rollback
set autocommit = 0; -- mysql 워크벤치에서 자동 commit 비활성화 설정 ( 학습용 )

# 1. 트랜잭션 시작
start transaction;

# 2. 여러 작업 ( DML : INSERT , UPDATE , DELETE ) , DDL 불가능( ALTER , CREATE , DROP )
update trans set money = money - 30000 where name = '신동엽'; -- 출금
update trans set money = money + 30000 where name = '서장훈'; -- 입금

# 3. 되돌리기(취소)
rollback;

# 4. 완료
commit;

# 5. 확인
select * from trans;

# [2] 
# 1. 트랜잭션 시작
start transaction; -- 트랜잭션 시작

# 2. 여러 작업1
update trans set money = money - 30000 where name = '신동엽'; -- 출금

# 3. 저장 지점 만들기
savepoint pointA; -- 저장지점 

# 4. 여러작업2
update trans set money = money - 30000 where name = '서장훈'; -- 출금

# 5. 완료
commit;

# 6. 롤백
rollback to pointA; -- 저장지점 까지 롤백(이동)

# 7. 확인
select * from trans;

# TCL( transaction , commit , rollback , savepoint )
# 1. JAVA SPRING ( @Transactional 사용하되 RuntimeException으로 롤백한다. savepoint 지원하지 않음. )
# 2. JAVA JDBC( DAO ) : String sql = "savepoint" 등 모두 지원

# [3] 여러개 savepoint 존재
start transaction;

update trans set money = money - 10000 where name = '유재석';
savepoint step1;

update trans set money = money - 10000 where name = '서장훈';
savepoint step2;

update trans set money = money - 10000 where name = '강호동';
savepoint step3;

rollback to step1;
rollback to step2;
rollback to step3;

commit;

select * from trans;





