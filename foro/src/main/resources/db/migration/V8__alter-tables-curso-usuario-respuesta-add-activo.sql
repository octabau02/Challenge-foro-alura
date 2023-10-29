alter table cursos add activo bit;
alter table usuarios add activo bit;
alter table respuestas add activo bit;

update cursos set activo=1;
update usuarios set activo=1;
update respuestas set activo=1;
