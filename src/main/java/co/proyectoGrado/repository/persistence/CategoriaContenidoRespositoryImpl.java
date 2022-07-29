package co.proyectoGrado.repository.persistence;

import co.proyectoGrado.domain.excepciones.excepcion.ExcepcionDeProceso;
import co.proyectoGrado.domain.excepciones.excepcion.ExcepcionDuplicidad;
import co.proyectoGrado.domain.model.CategoriaContenido;
import co.proyectoGrado.repository.CategoriaContenidoRepository;
import co.proyectoGrado.repository.persistence.crud.CategoriaContenidoCrud;
import co.proyectoGrado.repository.persistence.crud.CursoContenidoCrud;
import co.proyectoGrado.repository.persistence.crud.PreguntaCrud;
import co.proyectoGrado.repository.persistence.entity.CategoriaContenidoEntity;
import co.proyectoGrado.repository.persistence.entity.PreguntaEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoriaContenidoRespositoryImpl implements CategoriaContenidoRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(EstudianteRepositoryImpl.class);
    private static final String ERROR_YA_EXISTE_UNA_CATEGORIA_CON_ESE_NOMBRE = "Ya existe una categoría con el nombre: %s";
    private static final String ERROR_ACTUALIZAR_EL_CATEGORIA = "Error actualizando categoria";
    private static final String ERROR_CREANDO_EL_CATEGORIA = "Error creando categoria";

    private final CategoriaContenidoCrud categoriaContenidoCrud;
    private final PreguntaCrud preguntaCrud;
    private final CursoContenidoCrud cursoContenidoCrud;

    @Autowired
    public CategoriaContenidoRespositoryImpl(CategoriaContenidoCrud categoriaContenidoCrud,
                                             PreguntaCrud preguntaCrud,
                                             CursoContenidoCrud cursoContenidoCrud) {

        this.categoriaContenidoCrud = categoriaContenidoCrud;
        this.preguntaCrud = preguntaCrud;
        this.cursoContenidoCrud = cursoContenidoCrud;
    }


    @Override
    public List<CategoriaContenido> getAll() {
        List<CategoriaContenido> categoriaContenidos = new ArrayList<>();

        categoriaContenidoCrud.findAll().forEach(categoriaContenidoEntity -> {
            CategoriaContenido categoriaContenido = new CategoriaContenido(categoriaContenidoEntity.getIdCategoriaContenido(),
                    categoriaContenidoEntity.getIdPreguntas(), categoriaContenidoEntity.getNombre());

            categoriaContenidos.add(categoriaContenido);
        });

        return categoriaContenidos;
    }

    @Override
    public List<CategoriaContenido> getByIds(List<Integer> listaIdsCategoriaContenido) {
        List<CategoriaContenido> listaCategoriaContenido = new ArrayList<>();
        categoriaContenidoCrud.findByIdCategoriaContenidoIn(listaIdsCategoriaContenido).forEach(categoriaContenidoEntity -> {
            CategoriaContenido categoriaContenido = new CategoriaContenido(categoriaContenidoEntity.getIdCategoriaContenido(),
                    categoriaContenidoEntity.getIdPreguntas(), categoriaContenidoEntity.getNombre());
            listaCategoriaContenido.add(categoriaContenido);
        });
        return listaCategoriaContenido;
    }

    @Override
    public CategoriaContenido get(int idCategoriaContenido) {
        CategoriaContenidoEntity categoriaContenidoEntity = categoriaContenidoCrud.findByIdCategoriaContenido(idCategoriaContenido);
        if (categoriaContenidoEntity != null) {
            return new CategoriaContenido(categoriaContenidoEntity.getIdCategoriaContenido(),
                    categoriaContenidoEntity.getIdPreguntas(), categoriaContenidoEntity.getNombre());
        } else {
            return null;
        }
    }

    @Override
    public CategoriaContenido getPregunta(int idPregunta) {

        CategoriaContenidoEntity categoriaContenidoEntity = categoriaContenidoCrud.findFirstByIdPreguntas(idPregunta);

        if (categoriaContenidoEntity != null) {
            return new CategoriaContenido(categoriaContenidoEntity.getIdCategoriaContenido(),
                    categoriaContenidoEntity.getIdPreguntas(), categoriaContenidoEntity.getNombre());
        } else {
            return null;
        }
    }

    @Override
    public CategoriaContenido save(CategoriaContenido categoriaContenido) {
        try {
            PreguntaEntity preguntaEntity = new PreguntaEntity();
            if(categoriaContenidoCrud.findFirstByNombre(categoriaContenido.getNombre()) != null){
                throw new ExcepcionDuplicidad("");
            }
            if (categoriaContenido.getIdPregunta() != null) {
                preguntaEntity = preguntaCrud.findFirstByIdPregunta(categoriaContenido.getIdPregunta());
            } else {
                preguntaEntity = null;
            }
            CategoriaContenidoEntity categoriaContenidoEntity = new CategoriaContenidoEntity(categoriaContenido.getIdCategoriaContenido(),
                    categoriaContenido.getIdPregunta(), categoriaContenido.getNombre(), preguntaEntity, null);

            return entityToDomain(categoriaContenidoCrud.save(categoriaContenidoEntity));
        } catch (ExcepcionDuplicidad excepcionDuplicidad) {
            LOGGER.error(String.format(ERROR_YA_EXISTE_UNA_CATEGORIA_CON_ESE_NOMBRE, categoriaContenido.getNombre()), excepcionDuplicidad);
            throw new ExcepcionDuplicidad(String.format(ERROR_YA_EXISTE_UNA_CATEGORIA_CON_ESE_NOMBRE, categoriaContenido.getNombre()));
        } catch (Exception e) {
            LOGGER.error(ERROR_CREANDO_EL_CATEGORIA, e);
            throw new ExcepcionDeProceso(ERROR_CREANDO_EL_CATEGORIA);
        }
    }

    @Override
    public Boolean actualizar(int id, CategoriaContenido categoriaContenido) {
        try {
            PreguntaEntity preguntaEntity;
            if (categoriaContenido.getIdPregunta() != null) {
                preguntaEntity = preguntaCrud.findFirstByIdPregunta(categoriaContenido.getIdPregunta());
            } else {
                preguntaEntity = null;
            }
            CategoriaContenidoEntity categoriaContenidoEntity = new CategoriaContenidoEntity(categoriaContenido.getIdCategoriaContenido(),
                    categoriaContenido.getIdPregunta(), categoriaContenido.getNombre(), preguntaEntity, null);
            categoriaContenidoCrud.save(categoriaContenidoEntity);
            return true;
        } catch (Exception e) {
            LOGGER.error(ERROR_ACTUALIZAR_EL_CATEGORIA, e);
            throw new ExcepcionDeProceso(ERROR_ACTUALIZAR_EL_CATEGORIA);
        }
    }

    @Override
    public Boolean delete(int idCategoriaContenido) {
        if (categoriaContenidoCrud.findByIdCategoriaContenido(idCategoriaContenido) != null) {
            CategoriaContenidoEntity categoriaContenidoEntity = categoriaContenidoCrud.findFirstByIdCategoriaContenido(idCategoriaContenido);
            categoriaContenidoCrud.delete(categoriaContenidoEntity);
            return true;
        } else {
            return false;
        }
    }

    private CategoriaContenido entityToDomain(CategoriaContenidoEntity categoriaContenidoEntity) {
        return new CategoriaContenido(categoriaContenidoEntity.getIdCategoriaContenido(),
                categoriaContenidoEntity.getIdPreguntas(), categoriaContenidoEntity.getNombre());
    }
}
