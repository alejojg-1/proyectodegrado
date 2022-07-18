package co.proyectoGrado.proyectoGrado.domain.service;

import co.proyectoGrado.proyectoGrado.domain.model.Estudiante;
import co.proyectoGrado.proyectoGrado.domain.repository.EstudianteRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.EstudianteCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.EstudianteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    private EstudianteCrud estudianteCrud;

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public EstudianteService(EstudianteRepository estudianteRepository,
                             BCryptPasswordEncoder passwordEncoder) {
        this.estudianteRepository = estudianteRepository;
        this.passwordEncoder =passwordEncoder;
    }

    public List<Estudiante> getAll(){
        return estudianteRepository.getAll();
    }
    public Estudiante get(String email) {
        return estudianteRepository.get(email);
    }

    public boolean save(Estudiante estudiante) {
        estudiante.setContrasena(encodeContrasena(estudiante.getContrasena()));
        EstudianteEntity contenido = mapper.map(estudiante, EstudianteEntity.class);
        try {
            estudianteRepository.save(estudiante);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public Boolean actualizar(int id, Estudiante estudiante) {
        return  estudianteRepository.actualizar(id, estudiante);
    }

    public boolean eliminar(int id){

        return estudianteRepository.delete(id);
    }

    private String encodeContrasena(String contrasena){
        return passwordEncoder.encode(contrasena);
    }

    private String decoderContrasena(String contrasena){
        //¿Cómo decodificar?
        return passwordEncoder.encode(contrasena);
    }


}