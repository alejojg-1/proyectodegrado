package co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class CursoDocentePK implements Serializable {

    @Column(name="iddocentes")
    private int idDocentes;
    @Column(name="idcursos")
    private int idCursos;

}
