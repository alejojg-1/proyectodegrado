package co.proyectoGrado.proyectoGrado.domain.repository.persistence;

import co.proyectoGrado.proyectoGrado.domain.model.Estudiante;
import co.proyectoGrado.proyectoGrado.domain.repository.EstudianteRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.EstudianteCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.EstudianteEntity;
import co.proyectoGrado.proyectoGrado.excepciones.excepcion.ExcepcionValorInvalido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EstudianteRepositoryImpl implements EstudianteRepository {
    private final EstudianteCrud estudianteCrud;
    private final String ACTIVO = "t";
    private final String INACTIVO = "f";
    private static final String EL_ESTUDIANTE_NO_EXISTE_EN_EL_SISTEMA = "El estudiante con ese id no existe en el sistema";


    @Autowired
    public EstudianteRepositoryImpl(EstudianteCrud estudianteCrud) {
        this.estudianteCrud = estudianteCrud;
    }

    @Override
    public List<Estudiante> getAll() {
        List<Estudiante> estudiantes = new ArrayList<>();
        estudianteCrud.findAll().forEach(estudianteEntity -> {
            Estudiante estudiante = new Estudiante(estudianteEntity.getIdEstudiantes(),
                    estudianteEntity.getNombre(),
                    estudianteEntity.getApellido(), estudianteEntity.getIdentificacion(),
                    estudianteEntity.getCorreo(), estudianteEntity.getContrasena(),
                    ACTIVO.equals(estudianteEntity.getEstado()));

            if(estudiante.isEstado()==true){
               estudiantes.add(estudiante);
            }
        });


        return estudiantes;
    }

    @Override
    public List<Estudiante> getByIds(List<Integer> listaIds) {
        List<Estudiante> estudiantes = new ArrayList<>();
        estudianteCrud.findByIdEstudiantesIn(listaIds).forEach(estudianteEntity -> {
            Estudiante estudiante = new Estudiante(estudianteEntity.getIdEstudiantes(),
                    estudianteEntity.getNombre(),
                    estudianteEntity.getApellido(), estudianteEntity.getIdentificacion(),
                    estudianteEntity.getCorreo(), estudianteEntity.getContrasena(),
                    ACTIVO.equals(estudianteEntity.getEstado()));
            if(estudiante.isEstado()==true){
                estudiantes.add(estudiante);
            }
        });
        return estudiantes;
    }

    @Override
    public Estudiante get(int identificacion) {
        EstudianteEntity estudianteEntity = estudianteCrud.findFirstByIdentificacion(identificacion);


        if (!(estudianteEntity != null && estudianteEntity.getEstado().equals(ACTIVO))) {
            throw new ExcepcionValorInvalido(EL_ESTUDIANTE_NO_EXISTE_EN_EL_SISTEMA);
        }
        return new Estudiante(estudianteEntity.getIdEstudiantes(), estudianteEntity.getNombre(),
                estudianteEntity.getApellido(), estudianteEntity.getIdentificacion(),
                estudianteEntity.getCorreo(), estudianteEntity.getContrasena(),
                "S".equals(estudianteEntity.getEstado()));
    }

    @Override
    public Estudiante getById(int idEstudiante) {
        EstudianteEntity estudianteEntity = estudianteCrud.findFirstByIdEstudiantes(idEstudiante);

        if (estudianteEntity != null && ACTIVO.equals(estudianteEntity.getEstado())) {
            return new Estudiante(estudianteEntity.getIdEstudiantes(), estudianteEntity.getNombre(),
                    estudianteEntity.getApellido(), estudianteEntity.getIdentificacion(),
                    estudianteEntity.getCorreo(), estudianteEntity.getContrasena(),
                    ACTIVO.equals(estudianteEntity.getEstado()));
        } else {
            return null;
        }
    }

    @Override
    public Estudiante get(String email) {
        EstudianteEntity estudianteEntity = estudianteCrud.findFirstByCorreo(email);

        if (estudianteEntity != null && ACTIVO.equals(estudianteEntity.getEstado())) {
            return new Estudiante(estudianteEntity.getIdEstudiantes(), estudianteEntity.getNombre(),
                    estudianteEntity.getApellido(), estudianteEntity.getIdentificacion(),
                    estudianteEntity.getCorreo(), estudianteEntity.getContrasena(),
                    ACTIVO.equals(estudianteEntity.getEstado()));
        } else {
            return null;
        }
    }


    @Override
    public boolean save(Estudiante estudiante) {
        try {
            EstudianteEntity estudianteEntity = new EstudianteEntity();

            estudianteEntity.setIdEstudiantes(estudiante.getIdEstudiante());
            estudianteEntity.setNombre(estudiante.getNombre());
            estudianteEntity.setApellido(estudiante.getApellido());
            estudianteEntity.setIdentificacion(estudiante.getIdentificacion());
            estudianteEntity.setCorreo(estudiante.getCorreo());
            estudianteEntity.setContrasena(estudiante.getContrasena());
            estudianteEntity.setEstado(estudiante.isEstado() ? ACTIVO :INACTIVO);

            estudianteCrud.save(estudianteEntity);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean actualizar(Estudiante estudiante) {
        if(estudianteCrud.findById(estudiante.getIdEstudiante())!=null){

            try {

               EstudianteEntity estudianteEntity = new EstudianteEntity();

                estudianteEntity.setIdEstudiantes(estudiante.getIdEstudiante());
                estudianteEntity.setNombre(estudiante.getNombre());
                estudianteEntity.setApellido(estudiante.getApellido());
                estudianteEntity.setIdentificacion(estudiante.getIdentificacion());
                estudianteEntity.setCorreo(estudiante.getCorreo());
                estudianteEntity.setContrasena(estudiante.getContrasena());
                estudianteEntity.setEstado(estudiante.isEstado() ? ACTIVO : INACTIVO);

                estudianteCrud.save(estudianteEntity);

                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public boolean delete(int idEstudiante) {
        if(estudianteCrud.findByIdEstudiantes(idEstudiante)!=null){
           EstudianteEntity estudianteEntity = estudianteCrud.findFirstByIdEstudiantes(idEstudiante);
            estudianteEntity.setEstado(INACTIVO);
            estudianteCrud.save(estudianteEntity);
            return true;
        }else{
            return false;
        }
    }
}



