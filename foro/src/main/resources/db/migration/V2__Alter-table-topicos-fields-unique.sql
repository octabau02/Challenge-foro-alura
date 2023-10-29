alter table topicos modify column titulo varchar(50) not null unique;
alter table topicos modify column mensaje varchar(300) not null unique;
