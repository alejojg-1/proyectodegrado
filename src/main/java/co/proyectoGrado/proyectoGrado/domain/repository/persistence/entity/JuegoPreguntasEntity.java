package co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table( name = "JUEGO_PREGUNTAS")
@NoArgsConstructor
public class JuegoPreguntasEntity {


    public JuegoPreguntasEntity(int idJuegoPreguntas, PreguntaEntity pregunta, RetoEntity reto) {
        this.idJuegoPreguntas = idJuegoPreguntas;
        this.pregunta = pregunta;
        this.reto = reto;
        this.idpreguntas= pregunta.getIdPregunta();
        this.idreto= reto.getIdReto();
    }

    @Id
    @Column(name="idjuego_preguntas")
    private int idJuegoPreguntas;
    @Column(name="idpreguntas")
    private int idpreguntas;
    @Column(name="idreto")
    private int idreto;
    @Column(name="estado")
    private char estado;


    @ManyToOne
    @JoinColumn(name="idpreguntas", insertable = false, updatable = false)
    private PreguntaEntity pregunta;
    @OneToOne
    @JoinColumn(name="idreto", insertable = false, updatable = false)
    private RetoEntity reto;
    @OneToOne(mappedBy = "juegoPregunta")
    private EstudianteJuegoRespuestasEntity estudianteJuegoRespuesta;



}
