CREATE TABLE respuestas(
id int(10) auto_increment primary key,
mensaje varchar(100) not null,
fechaCreacion datetime not null,
solucion boolean not null,

topico_id int(10) not null,
autor_id int(10) not null,

FOREIGN KEY (topico_id) REFERENCES topicos(id),
FOREIGN KEY (autor_id) REFERENCES usuarios(id)
);