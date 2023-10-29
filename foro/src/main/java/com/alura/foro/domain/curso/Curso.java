package com.alura.foro.domain.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name ="Cursos")
@Entity(name ="curso")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String categoria;
    private Boolean activo;

    public Curso(DatosNuevoCurso datosNuevoCurso){
        this.nombre = datosNuevoCurso.nombre();
        this.categoria = datosNuevoCurso.categoria();
    }

    public void actualizarDatos(DatosActualizarCurso datos){
        if(datos.nombre() != null){
            this.nombre = datos.nombre();
        }
        if (datos.categoria() != null){
            this.categoria = datos.categoria();
        }
    }

    public void desactivar(){
        this.activo = false;
    }
}
