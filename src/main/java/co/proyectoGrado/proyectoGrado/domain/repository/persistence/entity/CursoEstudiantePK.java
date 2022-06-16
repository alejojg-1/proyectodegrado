package co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class CursoEstudiantePK implements Serializable {

    @Column(name="idestudiantes")
    private int idEstudiantes;
    @Column(name="idcursos")
    private int idCursos;
}
