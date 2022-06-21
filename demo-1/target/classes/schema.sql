create table if not exists user(
id int auto_increment primary key,
username nvarchar(20) not null,
password nvarchar(20) not null
);