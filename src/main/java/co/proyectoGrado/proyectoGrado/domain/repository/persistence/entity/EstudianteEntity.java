package co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table( name = "ESTUDIANTES")
public class EstudianteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="native")
    @Column(name = "idestudiantes")
    private Integer idEstudiantes;
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

    @OneToMany(mappedBy = "estudiante")
    private List<CursosEstudiantesEntity> cursoEstudiantes;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<EstudianteJuegoEntity> estudianteJuego;
}
