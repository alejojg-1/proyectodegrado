package co.proyectoGrado.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCambioPassowordUsuario {
    private String correo;
    private String contrasena;
}
