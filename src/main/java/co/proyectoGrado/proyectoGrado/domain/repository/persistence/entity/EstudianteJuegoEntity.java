package co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table( name = "ESTUDIANTE_JUEGO")
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteJuegoEntity {

    @EmbeddedId
    private EstudianteJuegoPK id;

    @Column(name = "calificacion")
    private double calificacion;

   /* @OneToMany(mappedBy = "estudianteJuego",cascade = {CascadeType.ALL})
    private List<EstudianteJuegoRespuestasEntity> estudianteJuegoRespuestas;*/

    @ManyToOne
    @MapsId("idReto")
    @JoinColumn(name="idreto", insertable = false, updatable = false)
    private RetoEntity reto;

    @ManyToOne
    @MapsId("idEstudiantes")
    @JoinColumn(name="idestudiantes",insertable = false, updatable = false)
    private EstudianteEntity estudiante;

}
