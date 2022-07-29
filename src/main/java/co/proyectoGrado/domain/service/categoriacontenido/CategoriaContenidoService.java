package co.proyectoGrado.domain.service.categoriacontenido;

import co.proyectoGrado.domain.model.CategoriaContenido;
import co.proyectoGrado.domain.model.CursoContenido;
import co.proyectoGrado.repository.CategoriaContenidoRepository;
import co.proyectoGrado.repository.CursoContenidoRepository;
import co.proyectoGrado.repository.persistence.crud.CategoriaContenidoCrud;
import co.proyectoGrado.repository.persistence.entity.CategoriaContenidoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaContenidoService {
    private final CategoriaContenidoRepository categoriaContenidoRepository;
    private final CursoContenidoRepository cursoContenidoRepository;

    @Autowired
    private CategoriaContenidoCrud categoriaContenidoCrud;

    @Autowired
    public CategoriaContenidoService(CategoriaContenidoRepository categoriaContenidoRepository, CursoContenidoRepository cursoContenidoRepository) {
        this.categoriaContenidoRepository = categoriaContenidoRepository;
        this.cursoContenidoRepository = cursoContenidoRepository;
    }

    private final ModelMapper mapper = new ModelMapper();

    public List<CategoriaContenido> getAll() {
        return categoriaContenidoRepository.getAll();
    }


    public CategoriaContenido get(int idCategoriaContenido) {
        return categoriaContenidoRepository.get(idCategoriaContenido);
    }

    public List<CategoriaContenido> obtenerCategoriaPorCursoId(int cursoId) {
        List<Integer> IdsCursoContenido = cursoContenidoRepository.getByIdCurso(cursoId).stream()
                .map(CursoContenido::getIdCategoriaContenido).collect(Collectors.toList());
        return categoriaContenidoRepository.getByIds(IdsCursoContenido);
    }

    public CategoriaContenido save(CategoriaContenido categoriaContenido) {
        return categoriaContenidoRepository.save(categoriaContenido);
    }

    public Boolean actualizar(int id, CategoriaContenido categoriaContenido) {
        CategoriaContenidoEntity categoria = categoriaContenidoCrud.findByIdCategoriaContenido(id);
        if (categoria != null) {
            return categoriaContenidoRepository.actualizar(id, categoriaContenido);
        }
        return Boolean.FALSE;
    }

    public Boolean eliminar(int id) {
        return categoriaContenidoRepository.delete(id);
    }


}
