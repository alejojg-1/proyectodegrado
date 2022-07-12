package co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table( name = "ESTUDIANTE_JUEGO_RESPUESTAS")
@NoArgsConstructor
public class EstudianteJuegoRespuestasEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idestudiante_juego_respuestas")
    private int idEstudianteJuegoRespuestas;

    @Column(name= "idpreguntas")
    private int idpreguntas;
    @Column(name= "idreto")
    private int idReto;
    @Column(name= "idjuego_preguntas")
    private int idJuegoPregunta;
    @Column(name="respuesta")
    private String respuesta;
    @Column(name="estado")
    private String estado;


    @OneToMany(mappedBy = "estudianteJuegoRespuesta",cascade = {CascadeType.ALL})
    private List<EstudianteJuegoEntity> estudianteJuego;

    @ManyToOne
    @MapsId("idJuegoPreguntas")
    @JoinColumn(name="idjuego_preguntas", insertable = false, updatable = false)
    @JoinColumn(name="idpreguntas", insertable = false, updatable = false)
    @JoinColumn(name="idreto", insertable = false, updatable = false)
    private JuegoPreguntasEntity juegoPregunta;

}
