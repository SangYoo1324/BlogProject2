insert into users(create_date,email,password,username,roles)
values
    ('2017-07-20','aaaa','aaaa','aaaa','USER'),
('2017-07-21','bbbb','bbbb','bbbb','USER');


insert into board(content,create_date,title,user_id,count)
-- default값 정해져있으면 null값 지정해주면 안되고 그냥 insert into()에서부터 빼줘야함
values
('as;dljkfajdsfjl;ad','2017-07-20','asdfadsf',1,2),
('as;dljkfadfadsjdsfjl;ad','2017-07-23','asdfadsf',1,3),
('as;dljkfadfadsjdsfjl;ad','2017-07-23','asdfadsf',1,4),
('as;dljkfadfadsjdsfjl;ad','2017-07-23','asdfadsf',1,5);

insert into reply(content,create_date,board_id,user_id)
values
    ('asdfasdfasdfasd','2017-07-29',1,2),
    ('asdfasdfasdfasd','2017-07-28',1,2),
    ('asdfasdfasdfasd','2017-07-27',1,2),
    ('asdfasdfasdfasd','2017-07-25',1,1),
    ('asdfasdfasdfasd','2017-07-23',1,1),

    ('asdfasdfasdfasd','2017-07-21',2,2),
    ('asdfasdfasdfasd','2017-07-20',2,2),
    ('asdfasdfasdfasd','2017-07-20',2,2),
    ('asdfasdfasdfasd','2017-07-20',2,1),
    ('asdfasdfasdfasd','2017-07-20',2,1);

-- create user 'cos'@'%' identified by 'cos1234';
--     grant all privileges on *.* to 'cos'@'%';
--         create database security;
--             use security;