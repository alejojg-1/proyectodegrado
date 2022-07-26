package co.proyectoGrado.repository.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table( name = "RETO")
@NoArgsConstructor
public class RetoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="native")
    @Column(name = "idreto")
    private Integer idReto;
    @Column(name = "idcursos")
    private Integer idCursos;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "reto")
    private List<EstudianteJuegoEntity> juegoEstudiantes;

    @ManyToOne
    @MapsId("idCursos") //Revisar si es necesario para consulta
    @JoinColumn(name = "idcursos", insertable = false, updatable = false)
    private CursoEntity curso;

    @OneToMany(mappedBy = "reto",cascade = {CascadeType.ALL})
    private List<JuegoPreguntasEntity> juegoPreguntas;

}
