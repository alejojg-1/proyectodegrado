package co.proyectoGrado.domain.excepciones.excepcion;

public class ExcepcionDuplicidad extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionDuplicidad(String mensaje) {
        super(mensaje);
    }
}
