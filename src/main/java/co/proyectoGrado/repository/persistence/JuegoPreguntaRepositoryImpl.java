package co.proyectoGrado.repository.persistence;

import co.proyectoGrado.domain.model.JuegoPregunta;
import co.proyectoGrado.repository.JuegoPreguntasRepository;
import co.proyectoGrado.repository.persistence.crud.JuegoPreguntasCrud;
import co.proyectoGrado.repository.persistence.crud.PreguntaCrud;
import co.proyectoGrado.repository.persistence.crud.RetoCrud;
import co.proyectoGrado.repository.persistence.entity.JuegoPreguntasEntity;
import co.proyectoGrado.repository.persistence.entity.JuegoPreguntasPK;
import co.proyectoGrado.repository.persistence.entity.PreguntaEntity;
import co.proyectoGrado.repository.persistence.entity.RetoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class JuegoPreguntaRepositoryImpl implements JuegoPreguntasRepository {

    private static final String ACTIVO = "t";
    private static final String INACTIVO = "f";
    private JuegoPreguntasCrud juegoPreguntasCrud;
    private final PreguntaCrud preguntaCrud;
    private final RetoCrud retoCrud;


    @Autowired
    public JuegoPreguntaRepositoryImpl(JuegoPreguntasCrud juegoPreguntasCrud,
                                       PreguntaCrud preguntaCrud,
                                       RetoCrud retoCrud){
        this.juegoPreguntasCrud = juegoPreguntasCrud;
        this.preguntaCrud = preguntaCrud;
        this.retoCrud = retoCrud;
    }

    @Override
    public List<JuegoPregunta> getAll() {
        List<JuegoPregunta> juegoPreguntas = new ArrayList<>();
        juegoPreguntasCrud.findAll().forEach(juegoPreguntasEntity -> {
            JuegoPregunta juegoPregunta = new JuegoPregunta(juegoPreguntasEntity.getId().getIdJuegoPreguntas(),
                    juegoPreguntasEntity.getPregunta().getIdPregunta(),juegoPreguntasEntity.getReto().getIdReto(),
                    ACTIVO.equals(juegoPreguntasEntity.getEstado()));

            juegoPreguntas.add(juegoPregunta);

        });
        return juegoPreguntas;
    }

    @Override
    public List<JuegoPregunta> getByIdReto(int idReto) {
        List<JuegoPregunta> juegoPreguntas = new ArrayList<>();
        juegoPreguntasCrud.findById_IdReto(idReto).forEach(juegoPreguntasEntity -> {
            JuegoPregunta juegoPregunta = new JuegoPregunta(juegoPreguntasEntity.getId().getIdJuegoPreguntas(),
                    juegoPreguntasEntity.getId().getIdPreguntas(),
                    juegoPreguntasEntity.getId().getIdReto(),
                    ACTIVO.equals(juegoPreguntasEntity.getEstado()));

            juegoPreguntas.add(juegoPregunta);

        });
        return juegoPreguntas;
    }

    @Override
    public JuegoPregunta get(int idPreguntas) {

        JuegoPreguntasEntity juegoPreguntasEntity = juegoPreguntasCrud.findByPregunta_IdPregunta(idPreguntas);

        if(juegoPreguntasEntity != null){

            return new JuegoPregunta(juegoPreguntasEntity.getId().getIdJuegoPreguntas(),
                    juegoPreguntasEntity.getPregunta().getIdPregunta(),juegoPreguntasEntity.getReto().getIdReto(),
                    ACTIVO.equals(juegoPreguntasEntity.getEstado()));
        }else{
            return null;
        }
    }

    @Override
    public JuegoPregunta obtenerPorIdRetoYIdPregunta(int idReto, int idPregunta) {

        JuegoPreguntasEntity juegoPreguntasEntity = juegoPreguntasCrud.
                findFirstById_IdRetoAndId_IdPreguntas(idReto,idPregunta);

        if(juegoPreguntasEntity != null){

            return new JuegoPregunta(juegoPreguntasEntity.getId().getIdJuegoPreguntas(),
                    juegoPreguntasEntity.getPregunta().getIdPregunta(),juegoPreguntasEntity.getReto().getIdReto(),
                    ACTIVO.equals(juegoPreguntasEntity.getEstado()));
        }else{
           throw new RuntimeException();
        }
    }

    @Override
    public boolean save(List<JuegoPregunta> listaJuegoPregunta) {

        try{
            listaJuegoPregunta.forEach(juegoPregunta ->{
                PreguntaEntity preguntaEntity= preguntaCrud.findFirstByIdPregunta(juegoPregunta.getIdPreguntas());
                RetoEntity retoEntity= retoCrud.findFirstByIdReto(juegoPregunta.getIdReto());
                JuegoPreguntasEntity juegoPreguntasEntity = new JuegoPreguntasEntity();
                juegoPreguntasEntity.setId(new JuegoPreguntasPK());
                juegoPreguntasEntity.getId().setIdPreguntas(juegoPregunta.getIdPreguntas());
                juegoPreguntasEntity.getId().setIdReto(juegoPregunta.getIdReto());
                juegoPreguntasEntity.setEstado(juegoPregunta.isEstado() ? ACTIVO : INACTIVO);
                juegoPreguntasEntity.setPregunta(preguntaEntity);
                juegoPreguntasEntity.setReto(retoEntity);
                juegoPreguntasCrud.save(juegoPreguntasEntity);
            });
            return  true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(int id, JuegoPregunta juegoPregunta) {

        try{
            PreguntaEntity preguntaEntity= preguntaCrud.findFirstByIdPregunta(juegoPregunta.getIdPreguntas());
            RetoEntity retoEntity= retoCrud.findFirstByIdReto(juegoPregunta.getIdReto());
            JuegoPreguntasEntity juegoPreguntasEntity = new JuegoPreguntasEntity();
            juegoPreguntasEntity.setId(new JuegoPreguntasPK());
            juegoPreguntasEntity.getId().setIdPreguntas(juegoPregunta.getIdPreguntas());
            juegoPreguntasEntity.getId().setIdReto(juegoPregunta.getIdReto());
            juegoPreguntasEntity.setEstado(juegoPregunta.isEstado() ? ACTIVO : INACTIVO);
            juegoPreguntasEntity.setPregunta(preguntaEntity);
            juegoPreguntasEntity.setReto(retoEntity);
            juegoPreguntasCrud.save(juegoPreguntasEntity);
            return  true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int idJuegoPreguntas) {
       if (juegoPreguntasCrud.findFirstById_IdJuegoPreguntas(idJuegoPreguntas) != null) {

            JuegoPreguntasEntity juegoPreguntasEntity =
                    juegoPreguntasCrud.findFirstById_IdJuegoPreguntas(idJuegoPreguntas);
            juegoPreguntasEntity.setEstado(INACTIVO);
            juegoPreguntasCrud.save( juegoPreguntasEntity);
            return true;
        } else {
            return false;
        }
    }
}
