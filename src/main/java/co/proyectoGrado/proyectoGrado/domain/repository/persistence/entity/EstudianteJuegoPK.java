package co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteJuegoPK implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="native")
    @Column(name = "idestudiante_juego")
    private Integer idEstudianteJuego;
    @Column(name = "idreto")
    private int idReto;
    @Column(name = "idestudiantes")
    private int idEstudiantes;
}
