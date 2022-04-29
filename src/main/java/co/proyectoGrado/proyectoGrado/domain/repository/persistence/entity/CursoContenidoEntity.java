package co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table( name = "CURSO_CONTENIDO")
@NoArgsConstructor
public class CursoContenidoEntity {
    public CursoContenidoEntity(int idCursoContenido, String comentario, String descripcion, String imagen, String video, CategoriaContenidoEntity categoriaContenido, CursoEntity curso) {
        this.idCursoContenido = idCursoContenido;
        this.comentario = comentario;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.video = video;
        this.idCategoriaContenido= categoriaContenido.getIdCategoriaContenido();
        this.idCursos= curso.getIdCursos();


    }

    @Id
    @Column(name = "idcurso_contenido")
    private int idCursoContenido;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "imagen")
    private String imagen;
    @Column(name = "urlvideo")
    private String video;
    @Column(name = "idcategoria_contenido")
    private int idCategoriaContenido;
    @Column(name = "idcursos")
    private int idCursos;


    @OneToOne
    @JoinColumn(name="idcategoria_contenido", insertable = false, updatable = false)
    private CategoriaContenidoEntity categoriaContenido;
    @ManyToOne
    @JoinColumn(name="idcursos", insertable = false, updatable = false)
    private CursoEntity curso;
}
