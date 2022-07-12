package co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteJuegoPK implements Serializable {

    @Column(name = "idreto")
    private int idReto;
    @Column(name = "idestudiante_juego_respuesta")
    private int idEstudianteJuegoRespuesta;
    @Column(name = "idEstudiantes")
    private int idEstudiantes;
}
