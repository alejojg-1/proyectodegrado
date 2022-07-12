package co.proyectoGrado.proyectoGrado.domain.repository.persistence;

import co.proyectoGrado.proyectoGrado.domain.model.JuegoPregunta;
import co.proyectoGrado.proyectoGrado.domain.repository.JuegoPreguntasRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.JuegoPreguntasCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.PreguntaCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.RetoCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.JuegoPreguntasEntity;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.PreguntaEntity;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.RetoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class JuegoPreguntaRepositoryImpl implements JuegoPreguntasRepository {

    private static final String ACTIVO = "t";
    private JuegoPreguntasCrud juegoPreguntasCrud;
    private final PreguntaCrud preguntaCrud;
    private final RetoCrud retoCrud;


    @Autowired
    public JuegoPreguntaRepositoryImpl(JuegoPreguntasCrud juegoPreguntasCrud, PreguntaCrud preguntaCrud, RetoCrud retoCrud){
        this.juegoPreguntasCrud = juegoPreguntasCrud;
        this.preguntaCrud = preguntaCrud;
        this.retoCrud = retoCrud;
    }
    /*
    private int idJuegoPreguntas;
    private int idPreguntas;
    private int idReto;*/

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
    public boolean save(JuegoPregunta juegoPregunta) {

        try{

            PreguntaEntity preguntaEntity= preguntaCrud.findFirstByIdPregunta(juegoPregunta.getIdJuegoPreguntas());
            RetoEntity retoEntity= retoCrud.findByIdReto(juegoPregunta.getIdReto());

            //JuegoPreguntasEntity juegoPreguntasEntity = new JuegoPreguntasEntity(juegoPregunta.getIdJuegoPreguntas(),preguntaEntity,retoEntity);
            JuegoPreguntasEntity juegoPreguntasEntity = new JuegoPreguntasEntity();


            juegoPreguntasEntity.setEstado(juegoPregunta.isEstado() ? 'S' : 'N');
            juegoPreguntasCrud.save(juegoPreguntasEntity);
            return  true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(int id, JuegoPregunta juegoPregunta) {

        try{
            JuegoPreguntasEntity juegoPreguntasEntity = new JuegoPreguntasEntity();

            //juegoPreguntasEntity.setIdJuegoPreguntas(juegoPregunta.getIdJuegoPreguntas());
            juegoPreguntasEntity.getPregunta().setIdPregunta(juegoPregunta.getIdPreguntas());
            juegoPreguntasEntity.getReto().setIdReto(juegoPregunta.getIdReto());
            juegoPreguntasEntity.setEstado(juegoPregunta.isEstado() ? 'S' : 'N');
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

            JuegoPreguntasEntity juegoPreguntasEntity = (JuegoPreguntasEntity) juegoPreguntasCrud.findFirstById_IdJuegoPreguntas(idJuegoPreguntas);
            juegoPreguntasEntity.setEstado('N');
            juegoPreguntasCrud.save( juegoPreguntasEntity);
            return true;
        } else {
            return false;
        }
    }
}
