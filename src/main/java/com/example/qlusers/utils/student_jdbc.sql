use jdbc_user;

create table Students
(
    id       int auto_increment primary key,
    fullName varchar(100) not null,
    email    varchar(100) not null unique,
    address varchar(255) not null,
    phone    varchar(11)  not null unique,
    status   bit

);