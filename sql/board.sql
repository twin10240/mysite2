-- create sequence
drop sequence seq_board;

create sequence seq_board
start with 1
increment by 1
maxvalue 9999999999;

select max(g_no) from board;
-- 새글
insert into board values(seq_board.nextval, '페이지 테스트', '페이지 테스트', sysdate, 0, nvl((select max(g_no) from board), 0) + 1, 1, 1, 2);
insert into board values(seq_board.nextval, ?, ?, sysdate, 0, nvl((select max(g_no) from board), 0) + 1, 1, 1, ?);

-- 답글 
-- 부모글이 g_no = 1, o_no = 1, depth = 1 인 경우
--1)
update board set o_no = o_no + 1 where g_no = 1 and o_no > 1;
update board set o_no = o_no + 1 where g_no = ? and o_no > ?;

--2)
insert into board values(seq_board.nextval, 
						 '111', 
						 '111', 
						 sysdate, 
						 0, 
						 7, -- g_no
						 2, -- o_no + 1
						 2, -- depth +
						 2);

commit;

update board set g_no = 2 where no = 4;  
update board set title = ?, content = ? where no = ?; 

select * from board;
 
select * from member;

select title, content from board where no=10;

-- list
-- 페이지당 5개씩 3 페이지를 가져오는 경우
select * from (select no, title, hit, reg_date, depth, member_no, name, rownum as rn
					from (select a.no, a.title, a.hit, to_char(reg_date, 'yyyy-mm-dd hh:mi:ss') as reg_date, a.depth, a.member_no, b.name 
							from board a, member b 
							where a.member_no = b.no
							order by g_no desc, o_no asc))
	where (1-1)*5+1 <= rn and rn <= 1*5;

select no, title, hit, reg_date, depth, member_no, name, rownum
	from (select a.no, a.title, a.hit, to_char(reg_date, 'yyyy-mm-dd hh:mi:ss') as reg_date, a.depth, a.member_no, b.name 
			from board a, member b 
			where a.member_no = b.no
			order by g_no desc, o_no asc);

select a.no, a.title, a.hit, to_char(reg_date, 'yyyy-mm-dd hh:mi:ss'), a.user_no, b.name 
	from board a, member b where a.member_no = b.no
	order by g_no desc, o_no asc;

select count(*) from board;

delete from board where no = 38;

