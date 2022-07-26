package co.proyectoGrado.repository.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table( name = "DOCENTES")
public class DocenteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="native")
    @Column(name = "iddocentes")
    private Integer idDocentes;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "identificacion")
    private int identificacion;
    @Column(name = "correo")
    private String correo;
    @Column(name = "contrasena")
    private String contrasena;
    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "docente")
    private List<CursoDocenteEntity> cursoDocentes;
}
