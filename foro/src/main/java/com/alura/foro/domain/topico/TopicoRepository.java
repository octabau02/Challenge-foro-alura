package com.alura.foro.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository  extends JpaRepository<Topico, Long> {
    List<Topico> findByActivoTrue();
}
