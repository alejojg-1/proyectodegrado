package co.proyectoGrado.proyectoGrado.domain.service.docente;

import co.proyectoGrado.proyectoGrado.domain.model.Docente;
import co.proyectoGrado.proyectoGrado.domain.repository.DocenteRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.DocenteCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.DocenteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteService {
    private final DocenteRepository docenteRepository;
    @Autowired
    private DocenteCrud docenteCrud;

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public DocenteService(DocenteRepository docenteRepository, BCryptPasswordEncoder passwordEncoder) {
        this.docenteRepository = docenteRepository;
        this.passwordEncoder =passwordEncoder;
    }
    private final ModelMapper mapper = new ModelMapper();

    public List<Docente> getAll(){
        return docenteRepository.getAll();
    }
    public Docente get(String email) {
        return docenteRepository.get(email);
    }

    public boolean save(Docente docente) {
        docente.setContrasena(encodeContrasena(docente.getContrasena()));
        DocenteEntity contenido = mapper.map(docente, DocenteEntity.class);
        try {
            docenteRepository.save(docente);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public Boolean actualizar(int id, Docente docente) {
    return  docenteRepository.actualizar(id, docente);
    }

    public boolean eliminar(int id){

        return docenteRepository.delete(id);
    }

    private String encodeContrasena(String contrasena){
        return passwordEncoder.encode(contrasena);
    }

}
