package co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table( name = "PREGUNTAS")
@NoArgsConstructor
public class PreguntaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpreguntas")
    private int idPregunta;
    @Column(name = "texto")
    private String texto;
    @Column(name = "imagen")
    private String imagen;
    @Column(name = "respuesta")
    private String respuesta;
    @Column(name = "opcion1")
    private String opcion1;
    @Column(name = "opcion2")
    private String opcion2;
    @Column(name = "opcion3")
    private String opcion3;
    @Column(name = "opcion4")
    private String opcion4;
    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "pregunta",cascade = {CascadeType.ALL})
    private List<JuegoPreguntasEntity> juegoPreguntas;

    //@OneToOne(mappedBy = "pregunta")
   // private CategoriaContenidoEntity categoriaContenido;


}
