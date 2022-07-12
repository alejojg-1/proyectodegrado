package co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table( name = "ESTUDIANTE_JUEGO")
@NoArgsConstructor
public class EstudianteJuegoEntity {


   // @Id validar si se agrega a clave compuesta
    @Column(name = "idestudiantes_juego")
    private int idEstudianteJuego;

    @EmbeddedId
    private EstudianteJuegoPK id;

    @Column(name = "calificacion")
    private double calificacion;


    @ManyToOne
    @MapsId("idReto")
    @JoinColumn(name="idreto", insertable = false, updatable = false)
    private RetoEntity reto;

    @ManyToOne
    @MapsId("idEstudianteJuegoRespuesta")
    @JoinColumn(name="idestudiante_juego_respuestas", insertable = false, updatable = false)
    private EstudianteJuegoRespuestasEntity estudianteJuegoRespuesta;

    @ManyToOne
    @MapsId("idEstudiantes")
    @JoinColumn(name="idEstudiantes",insertable = false, updatable = false)
    private EstudianteEntity estudiante;
}
