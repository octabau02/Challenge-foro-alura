package com.alura.foro.domain.respuesta;

import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "Respuestas")
@Entity(name = "respuesta")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String mensaje;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;
    @Column(name = "fechacreacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    private Boolean solucion = false;


    public Respuesta(String mensaje, Usuario usuario, Topico topico) {
        this.mensaje = mensaje;
        this.autor = usuario;
        this.topico = topico;
    }

    public void actualizarDatos(DatosActualizarRespuesta datos){
        if(datos.mensaje() != null){
            this.mensaje = datos.mensaje();
        }

    }
}
