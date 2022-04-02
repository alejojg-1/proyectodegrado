package co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name ="CATEGORIA_CONTENIDO")
@NoArgsConstructor
public class CategoriaContenidoEntity {

    @Id
    @Column(name="idcategoria_contenido")
    private int idCategoriaContenido;
    @OneToOne
    @JoinColumn(name="idPreguntas", insertable = false, updatable = false)
    private PreguntaEntity pregunta;

    @OneToOne(mappedBy = "categoriaContenido")
    private CursoContenidoEntity cursoContenido;

}
