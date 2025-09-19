drop database if exists 평가5;
create database 평가5;
use 평가5;

-- ---------------- 영화 -----------
create table movie(
	mno int auto_increment ,
    title varchar(100) not null ,
    director varchar(100) not null ,
    genre varchar(100) not null ,
    content varchar(200) not null ,
    pwd varchar(40) not null ,
    constraint primary key(mno) 

);

-- ---------------- 리뷰 -----------
create table review(
	rno int auto_increment ,
    mno int ,
    r_title varchar(100) not null ,
    r_content varchar(200) not null ,
    r_pwd varchar(40) not null ,
    constraint primary key(rno),
    foreign key(mno) references movie(mno) on delete cascade 
    
);

insert into movie( title , director , genre , content , pwd ) values
("기생충", "봉준호", "블랙코미디", "미치도록 잘만든 영화", "1234"),
("올드보이", "박찬욱", "스릴러", "충격적인 반전으로 유명한 작품", "1111"),
("암살", "최동훈", "액션", "1930년대 독립군 암살 작전을 그린 영화", "2222"),
("명량", "김한민", "사극", "이순신 장군의 해전을 다룬 대작", "3333"),
("극한직업", "이병헌", "코미디", "치킨집 잠입 수사극, 폭소를 자아낸다", "4444"),
("택시운전사", "장훈", "드라마", "5·18 민주화운동을 다룬 실화 바탕 영화", "5555"),
("신세계", "박훈정", "범죄", "언더커버 형사와 조직 간의 숨막히는 갈등", "6666"),
("광해", "추창민", "사극", "왕과 똑같이 생긴 광대의 운명적 이야기", "7777"),
("국제시장", "윤제균", "드라마", "한 아버지의 가족사와 대한민국 현대사", "8888"),
("도둑들", "최동훈", "범죄", "10명의 도둑들이 한탕을 노리는 케이퍼 무비", "9999");

insert into review(mno, r_title, r_content, r_pwd) values
(1, "대작 냄새!!", "봉준호의 역작, 다시는 나올 수 없는 영화", "1234"),
(2, "반전의 끝판왕", "마지막 장면의 충격은 아직도 잊을 수 없다", "1111"),
(3, "몰입감 최고", "역사적 배경과 액션이 훌륭하게 어우러졌다", "2222"),
(4, "웅장한 전투씬", "명량해전의 긴장감이 스크린에 그대로 전달된다", "3333"),
(5, "웃다가 눈물난다", "코미디지만 따뜻한 가족애가 느껴졌다", "4444"),
(6, "가슴 먹먹한 실화", "역사 속 숨겨진 이야기들을 알게 해준 영화", "5555"),
(7, "한국 느와르의 정점", "배우들의 연기와 스토리가 압도적이다", "6666"),
(8, "연기의 향연", "이병헌의 연기는 정말 미쳤다", "7777"),
(9, "부모님 생각난다", "가족을 위해 희생한 아버지의 모습이 감동적", "8888"),
(10, "한국형 케이퍼 무비", "긴장감과 유머가 동시에 살아있다", "9999");

select * from movie;
select * from review;