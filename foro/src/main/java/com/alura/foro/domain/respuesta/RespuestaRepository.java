package com.alura.foro.domain.respuesta;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    List<Respuesta> findByActivoTrue();
}
