package co.proyectoGrado.web.error;

import co.proyectoGrado.domain.excepciones.excepcion.ExcepcionDeProceso;
import co.proyectoGrado.domain.excepciones.excepcion.ExcepcionDuplicidad;
import co.proyectoGrado.domain.excepciones.excepcion.ExcepcionValorInvalido;
import co.proyectoGrado.domain.excepciones.excepcion.ExcepcionValorObligatorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.concurrent.ConcurrentHashMap;

@ControllerAdvice
public class ManejadorError extends ResponseEntityExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(ManejadorError.class);

	private static final String OCURRIO_UN_ERROR_FAVOR_CONTACTAR_AL_ADMINISTRADOR = "Ocurrió un error favor contactar al administrador.";
	private static final String ERROR_GRAMATICAL_EN_BASE_DATOS = "Ocurrió un error gramatical en la base de datos";


	private static final ConcurrentHashMap<String, Integer> CODIGOS_ESTADO = new ConcurrentHashMap<>();

	public ManejadorError() {
		CODIGOS_ESTADO.put(ExcepcionValorInvalido.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
		CODIGOS_ESTADO.put(ExcepcionValorObligatorio.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
		CODIGOS_ESTADO.put(ExcepcionDeProceso.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
		CODIGOS_ESTADO.put(CannotGetJdbcConnectionException.class.getSimpleName(),
				HttpStatus.INTERNAL_SERVER_ERROR.value());
		CODIGOS_ESTADO.put(BadSqlGrammarException.class.getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		CODIGOS_ESTADO.put(ExcepcionDuplicidad.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Error> handleAllExceptions(Exception exception) {
		ResponseEntity<Error> resultado;

		String excepcionNombre = exception.getClass().getSimpleName();
		Integer codigo = CODIGOS_ESTADO.get(excepcionNombre);
		String mensaje = "";

		if (codigo != null) {
			mensaje = obtenerMensaje(exception);
			Error error = new Error(excepcionNombre, mensaje);
			resultado = new ResponseEntity<>(error, HttpStatus.valueOf(codigo));
		} else {
			LOGGER.error(excepcionNombre, exception);
			mensaje = OCURRIO_UN_ERROR_FAVOR_CONTACTAR_AL_ADMINISTRADOR;
			Error error = new Error(excepcionNombre, mensaje);
			resultado = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return resultado;
	}

	private String obtenerMensaje(Exception exception) {
		String excepcionNombre = exception.getClass().getSimpleName();

		if (excepcionNombre.equals(BadSqlGrammarException.class.getSimpleName())) {
			return ERROR_GRAMATICAL_EN_BASE_DATOS;
		}

		return exception.getMessage();
	}

}