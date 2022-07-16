package co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Data
@Embeddable
public class CursoDocentePK implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcurso_docente")
    private Integer idCursoDocente;
    @Column(name="iddocentes")
    private int idDocentes;
    @Column(name="idcursos")
    private int idCursos;

}
