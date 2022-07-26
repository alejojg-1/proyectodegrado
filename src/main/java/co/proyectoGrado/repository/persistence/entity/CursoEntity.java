package co.proyectoGrado.repository.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table( name = "cursos")
@NoArgsConstructor
@AllArgsConstructor
public class CursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="native")
    @Column(name = "idcursos")
    private Integer idCursos;
    @Column(name = "grado")
    private String grado;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "codigo")
    private String codigo;

    @OneToMany(mappedBy = "curso", cascade = {CascadeType.ALL})
    private List<CursosEstudiantesEntity> cursoEstudiantes;

    @OneToMany(mappedBy = "curso" , cascade = {CascadeType.ALL})
    private List<CursoDocenteEntity> cursoDocentes;

    @OneToMany(mappedBy = "curso" , cascade = {CascadeType.ALL})
    private List<CursoContenidoEntity> cursoContenidos;

    @OneToMany(mappedBy = "curso" , cascade = {CascadeType.ALL})
    private List<RetoEntity> reto;

}
