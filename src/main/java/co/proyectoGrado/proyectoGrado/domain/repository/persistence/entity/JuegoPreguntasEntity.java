package co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table( name = "JUEGO_PREGUNTAS")
@NoArgsConstructor
@AllArgsConstructor
public class JuegoPreguntasEntity {

    //@Id revisar esta parte de la tabla
    //@Column(name="idjuego_preguntas")
    //private int idJuegoPreguntas;

    @EmbeddedId
    private JuegoPreguntasPK id;

    @Column(name="estado")
    private char estado;


    @ManyToOne
    @MapsId("idPreguntas")
    @JoinColumn(name="idpreguntas", insertable = false, updatable = false)
    private PreguntaEntity pregunta;

    @ManyToOne
    @MapsId("idReto")
    @JoinColumn(name="idreto", insertable = false, updatable = false)
    private RetoEntity reto;

    @OneToMany(mappedBy = "juegoPregunta")
    private List<EstudianteJuegoRespuestasEntity> estudianteJuegoRespuesta;



}
