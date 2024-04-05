-- board 테이블을 만들어 보자. 

create table board(
	board_no number(7) primary key,         -- 게시판 글 번호.
	board_writer vachar2(50) not null, 		-- 게시판 글 작성자.
	board_title varchar2(200) not null,     -- 게시판 글 제목.
	board_cont varchar2(2000), 				-- 게시판 글 내용.
	board_pwd varchar2(50) not null,		-- 게시판 글 비밀번호.
	board_hit number(5) default 0,           -- 게시판 글 조회수.
	board_date date,				        -- 게시판 글 작성일자.
	board_update date					    -- 게시판 글 수정일자.
);


-- board 테이블에 게시글을 추가해 보자.
insert into board
	values(1, '홍길동', '제목1', '길동이 글입니다.', '1111', default, sysdate, '');
	
	insert into board
	values(2, '세종대왕', '제목2', '한글입니다.', '1111', default, sysdate, '');
	
	insert into board
	values(3, '유관순', '제목3', '독립만세.', '1111', default, sysdate, '');
	
	insert into board
	values(4, '이순신', '제목4', '만세 글', '대한독립만세 글입니다.', default, sysdate, '');
	
	insert into board
	values(5, '신시임당', '제목5', '신사임당 글입니다.', '1111', default, sysdate, '');