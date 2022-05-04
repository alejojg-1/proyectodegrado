package co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name ="CATEGORIA_CONTENIDO")
@NoArgsConstructor
public class CategoriaContenidoEntity {

    public CategoriaContenidoEntity(int idpreguntas, PreguntaEntity pregunta, int idCategoriaContenido) {
        this.idpreguntas = idpreguntas;
        this.idpreguntas= pregunta.getIdPregunta();
        this.idCategoriaContenido= idCategoriaContenido;

    }

    @Id
    @Column(name="idcategoria_contenido")
    private int idCategoriaContenido;
    @Column(name="idpreguntas")
    private int idpreguntas;

    @OneToOne
    @JoinColumn(name="idpreguntas", insertable = false, updatable = false)
    private PreguntaEntity pregunta;


   // @OneToOne
  //  @JoinColumn(name="idcursoContenido", insertable = false, updatable = false)
    //private CursoContenidoEntity cursoContenido;

}
