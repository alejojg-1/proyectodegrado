package co.proyectoGrado.repository.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@Entity
@Table(name = "CURSO_CONTENIDO")
@NoArgsConstructor
@AllArgsConstructor
public class CursoContenidoEntity {

    @EmbeddedId
    private CursoContenidoPK id;

    @Column(name = "comentario")
    private String comentario;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "imagen")
    private String imagen;
    @Column(name = "urlvideo")
    private String video;

    @ManyToOne
    @MapsId("idCategoriaContenido")
    @JoinColumn(name = "idcategoria_contenido", insertable = false, updatable = false)
    private CategoriaContenidoEntity categoriaContenido;

    @ManyToOne
    @MapsId("idCursos")
    @JoinColumn(name = "idcursos", insertable = false, updatable = false)
    private CursoEntity curso;
}
