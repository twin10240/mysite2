-- create sequence
drop sequence seq_member;

create sequence seq_member
start with 1 
increment by 1
maxvalue 9999999999;

-- create table
create table member(
	no number(10),
	name nvarchar2(20) NOT NULL,
	password nvarchar2(32) NOT NULL,
	email nvarchar2(200) NOT NULL,
	gender nvarchar2(10) NOT NULL,
	join_date date NOT NULL,
	
	PRIMARY KEY(no)
);

drop table member;

-- insert
insert into member values(seq_member.nextval, '둘리', '1234', 'dooly@gmail.com', 'male', sysdate);
insert into member values(seq_member.nextval, '조형근', '1234', 'twin10240@gmail.com', 'male', sysdate);

-- login
select no, name from member where email='twin10240@gmail.com' and password='1234';

select name, email, gender from member where no = 2;

select * from member;

update member set name = '조형근2', password = '12345', gender='female' where no = 3;

commit;