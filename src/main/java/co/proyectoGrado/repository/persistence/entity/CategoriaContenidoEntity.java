package co.proyectoGrado.repository.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name ="CATEGORIA_CONTENIDO")
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaContenidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcategoria_contenido")
    private Integer idCategoriaContenido;
    @Column(name="idpreguntas")
    private Integer idpreguntas;
    @Column(name="nombre")
    private String nombre;

    @ManyToOne
    //@MapsId("idPregunta")
    @JoinColumn(name = "idpreguntas", insertable = false, updatable = false)
    private PreguntaEntity pregunta;

    @OneToMany( mappedBy = "categoriaContenido",cascade = {CascadeType.ALL})
    private List<CursoContenidoEntity> cursoContenidos;

}
