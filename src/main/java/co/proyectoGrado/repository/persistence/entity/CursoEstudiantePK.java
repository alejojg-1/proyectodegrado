package co.proyectoGrado.repository.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Data
@Embeddable
public class CursoEstudiantePK implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="native")
    @Column(name="idcurso_estudiantes")
    private Integer idCursoEstudiante;
    @Column(name="idestudiantes")
    private int idEstudiantes;
    @Column(name="idcursos")
    private int idCursos;
}
