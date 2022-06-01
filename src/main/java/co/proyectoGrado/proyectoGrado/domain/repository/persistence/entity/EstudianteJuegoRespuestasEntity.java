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
    @Column(name= "idestudiante_juego_respuestas")
    private int idEstudianteJuegoRespuestas;
    @Column(name= "idpreguntas")
    private int idpreguntas;
    @Column(name= "idreto")
    private int idReto;
    @Column(name="estado")
    private String estado;
    @Column(name= "idjuego_preguntas")
    private int idJuegoPregunta;


   // @OneToOne(mappedBy = "idestudianteJuego_Respuesta")
  //  private EstudianteJuegoEntity estudianteJuego;
    @OneToOne
    @JoinColumn(name="idjuego_preguntas", insertable = false, updatable = false)
    private JuegoPreguntasEntity juegoPregunta;



}
