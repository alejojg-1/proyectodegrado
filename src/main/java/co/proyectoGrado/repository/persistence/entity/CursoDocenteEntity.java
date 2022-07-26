package co.proyectoGrado.repository.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table( name = "curso_docente")
@NoArgsConstructor
public class CursoDocenteEntity {

    @EmbeddedId
    private CursoDocentePK id;

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @MapsId("idDocentes")
    @JoinColumn(name="iddocentes", insertable = false, updatable = false)
    private DocenteEntity docente;

    @ManyToOne
    @MapsId("idCursos")
    @JoinColumn(name="idcursos", insertable = false, updatable = false)
    private CursoEntity curso;

}
