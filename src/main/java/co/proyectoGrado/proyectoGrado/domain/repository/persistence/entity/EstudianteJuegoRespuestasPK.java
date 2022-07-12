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
public class EstudianteJuegoRespuestasPK implements Serializable {
    @Column(name = "idcategoria_contenido")
    private int idCategoriaContenido;
    @Column(name = "idcursos")
    private int idCursos;
}
