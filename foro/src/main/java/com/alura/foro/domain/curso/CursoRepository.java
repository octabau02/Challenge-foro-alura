package com.alura.foro.domain.curso;

import com.alura.foro.domain.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByActivoTrue();
}
