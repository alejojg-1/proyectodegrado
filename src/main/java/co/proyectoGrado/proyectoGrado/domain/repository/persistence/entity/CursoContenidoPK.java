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
public class CursoContenidoPK implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="native")
    @Column(name = "idcurso_contenido")
    private Long idCursoContenido;
    @Column(name = "idcategoria_contenido")
    private int idCategoriaContenido;
    @Column(name = "idcursos")
    private int idCursos;
}
