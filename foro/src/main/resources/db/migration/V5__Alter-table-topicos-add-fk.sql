ALTER TABLE topicos ADD FOREIGN KEY (id_autor) REFERENCES usuarios(id),
ADD FOREIGN KEY (id_curso) REFERENCES cursos(id);