package co.proyectoGrado.repository.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class JuegoPreguntasPK implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="native")
    @Column(name="idjuego_preguntas")
    private Integer idJuegoPreguntas;
    @Column(name = "idpreguntas")
    private int idPreguntas;
    @Column(name = "idreto")
    private int idReto;

}
