package co.proyectoGrado.repository.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table( name = "CURSOS_ESTUDIANTES")
@NoArgsConstructor
@AllArgsConstructor
public class CursosEstudiantesEntity {

    @EmbeddedId
    private CursoEstudiantePK id;

    @ManyToOne
    @MapsId("idEstudiantes")
    @JoinColumn(name="idestudiantes", insertable = false, updatable = false)
    private EstudianteEntity estudiante;

    @ManyToOne
    @MapsId("idCursos")
    @JoinColumn(name="idcursos", insertable = false, updatable = false)
    private CursoEntity curso;

}
