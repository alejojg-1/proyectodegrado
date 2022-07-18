package co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table( name = "ESTUDIANTE_JUEGO_RESPUESTAS")
@NoArgsConstructor
public class EstudianteJuegoRespuestasEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="native")
    @Column(name= "idestudiante_juego_respuestas")
    private Integer idEstudianteJuegoRespuestas;
 //¿ @Embeddable PK?
    @Column(name= "idpreguntas")
    private int idpreguntas;
    @Column(name= "idreto")
    private Integer idReto;
    @Column(name = "idestudiante_juego")
    private Integer idEstudianteJuego;
    @Column(name= "idjuego_preguntas")
    private int idJuegoPregunta;

    @Column(name="respuesta")
    private String respuesta;
    @Column(name="estado")
    private String estado;


    @ManyToOne
    @MapsId("idEstudianteJuego")
    @JoinColumn(name="idreto", insertable = false, updatable = false)
    @JoinColumn(name="idestudiantes", insertable = false, updatable = false)
    @JoinColumn(name="idestudiante_juego", insertable = false, updatable = false)
    private EstudianteJuegoEntity estudianteJuego;

    @ManyToOne
    @MapsId("idJuegoPreguntas")
    @JoinColumn(name="idjuego_preguntas", insertable = false, updatable = false)
    @JoinColumn(name="idpreguntas", insertable = false, updatable = false)
    @JoinColumn(name="idreto", insertable = false, updatable = false)
    private JuegoPreguntasEntity juegoPregunta;

}
