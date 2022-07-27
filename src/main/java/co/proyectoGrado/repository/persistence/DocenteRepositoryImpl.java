package co.proyectoGrado.repository.persistence;

import co.proyectoGrado.domain.model.Docente;
import co.proyectoGrado.repository.DocenteRepository;
import co.proyectoGrado.domain.excepciones.excepcion.ExcepcionValorInvalido;
import co.proyectoGrado.repository.persistence.crud.DocenteCrud;
import co.proyectoGrado.repository.persistence.entity.DocenteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DocenteRepositoryImpl implements DocenteRepository {
    private final DocenteCrud docenteCrud;
    private final String ACTIVO = "t";
    private final String INACTIVO = "f";
    private static final String EL_DOCENTE_NO_EXISTE_EN_EL_SISTEMA = "El docente con ese id no existe en el sistema";

    @Autowired
    public DocenteRepositoryImpl(DocenteCrud docenteCrud) {
        this.docenteCrud = docenteCrud;
    }

    @Override
    public List<Docente> getAll() {
        List<Docente> docentes = new ArrayList<>();

        docenteCrud.findAll().forEach(docenteEntity -> {
            Docente docente = new Docente(docenteEntity.getIdDocentes(), docenteEntity.getNombre(),
                                            docenteEntity.getApellido(), docenteEntity.getIdentificacion(),
                                            docenteEntity.getCorreo(), docenteEntity.getContrasena(),
                    ACTIVO.equals(docenteEntity.getEstado()));
            if(docente.isEstado()==true){
                docentes.add(docente);
            }
        });

        return docentes;
    }

    @Override
    public Docente get(int identificacion) {
        DocenteEntity docenteEntity = docenteCrud.findFirstByIdentificacion(identificacion);

        if (!(docenteEntity != null && docenteEntity.getEstado().equals(ACTIVO))) {
            throw new ExcepcionValorInvalido(EL_DOCENTE_NO_EXISTE_EN_EL_SISTEMA);
        }
        return new Docente(docenteEntity.getIdDocentes(), docenteEntity.getNombre(),
                docenteEntity.getApellido(), docenteEntity.getIdentificacion(),
                docenteEntity.getCorreo(), docenteEntity.getContrasena(),
                "S".equals(docenteEntity.getEstado()));
    }

    @Override
    public Docente get(String email) {
        DocenteEntity docenteEntity = docenteCrud.findFirstByCorreo(email);
        if (docenteEntity != null && ACTIVO.equals(docenteEntity.getEstado())) {
            return new Docente(docenteEntity.getIdDocentes(), docenteEntity.getNombre(),
                    docenteEntity.getApellido(), docenteEntity.getIdentificacion(),
                    docenteEntity.getCorreo(), docenteEntity.getContrasena(),
                    ACTIVO.equals(docenteEntity.getEstado()));
        } else {
            return null;
        }
    }

    @Override
    public boolean save(Docente docente) {
        try{
            DocenteEntity docenteEntity = new DocenteEntity();

            docenteEntity.setIdDocentes(docente.getIdDocente());
            docenteEntity.setNombre(docente.getNombre());
            docenteEntity.setApellido(docente.getApellido());
            docenteEntity.setIdentificacion(docente.getIdentificacion());
            docenteEntity.setCorreo(docente.getCorreo());
            docenteEntity.setContrasena(docente.getContrasena());
            docenteEntity.setEstado(docente.isEstado() ? ACTIVO : INACTIVO);

            docenteCrud.save(docenteEntity);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean actualizar(Docente docente) {
        if(docenteCrud.findById(docente.getIdDocente())!=null){

            try {

                DocenteEntity docenteEntity = new DocenteEntity();

                docenteEntity.setIdDocentes(docente.getIdDocente());
                docenteEntity.setNombre(docente.getNombre());
                docenteEntity.setApellido(docente.getApellido());
                docenteEntity.setIdentificacion(docente.getIdentificacion());
                docenteEntity.setCorreo(docente.getCorreo());
                docenteEntity.setContrasena(docente.getContrasena());
                docenteEntity.setEstado(docente.isEstado() ? ACTIVO: INACTIVO);

                docenteCrud.save(docenteEntity);

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
    public boolean delete(int idDocente) {
        if(docenteCrud.findByIdDocentes(idDocente)!=null){
            DocenteEntity docenteEntity =  docenteCrud.findFirstByIdDocentes(idDocente);
            docenteEntity.setEstado(INACTIVO);
            docenteCrud.save(docenteEntity);
            return true;
        }else{
            return false;
        }
    }
}
