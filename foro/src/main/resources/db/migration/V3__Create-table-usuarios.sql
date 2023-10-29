create table usuarios(
id int(10) not null auto_increment primary key,
nombre varchar(50) not null,
email varchar(200) not null unique,
contrasena varchar(200)not null

)