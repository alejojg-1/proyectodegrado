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
public class JuegoPreguntasPK implements Serializable {

    @Column(name="idjuego_preguntas")
    private int idJuegoPreguntas;
    @Column(name = "idpreguntas")
    private int idPreguntas;
    @Column(name = "idreto")
    private int idReto;

}
