package co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table( name = "ESTUDIANTE_JUEGO")
@NoArgsConstructor
public class EstudianteJuegoEntity {


    public EstudianteJuegoEntity(int idEstudianteJuego, double calificacion, int idReto, int estudiante_juego_respuesta,
                                 int idEstudiantes, RetoEntity reto, EstudianteJuegoRespuestasEntity estudianteJuegoRespuesta,
                                 EstudianteEntity estudiante) {
        this.idEstudianteJuego = idEstudianteJuego;
        this.calificacion = calificacion;
        this.idReto = reto.getIdReto();
        this.estudiante_juego_respuesta = estudianteJuegoRespuesta.getIdEstudianteJuegoRespuestas();
        this.idEstudiantes = estudiante.getIdEstudiantes();
        this.reto = reto;
        this.estudianteJuegoRespuesta = estudianteJuegoRespuesta;
        this.estudiante = estudiante;
    }

    @Id
    @Column(name = "idestudiantes_juego")
    private int idEstudianteJuego;
    @Column(name = "calificacion")
    private double calificacion;
    @Column(name = "idReto")
    private int idReto;
    @Column(name = "estudiante_juego_respuesta")
    private int estudiante_juego_respuesta;
    @Column(name = "idEstudiantes")
    private int idEstudiantes;




    @ManyToOne
    @JoinColumn(name="idReto", insertable = false, updatable = false)
    private RetoEntity reto;

    @OneToOne
    @JoinColumn(name="estudiante_juego_respuesta", insertable = false, updatable = false)
    private EstudianteJuegoRespuestasEntity estudianteJuegoRespuesta;


    @OneToOne
    @JoinColumn(name="idEstudiantes",insertable = false, updatable = false)
    private EstudianteEntity estudiante;
}
