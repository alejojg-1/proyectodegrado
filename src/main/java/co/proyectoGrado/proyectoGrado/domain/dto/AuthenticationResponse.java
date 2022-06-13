package co.proyectoGrado.proyectoGrado.domain.dto;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt;
    private String sub;
    private String scope;

    public AuthenticationResponse(String jwt,String sub, String scope) {
        this.jwt = jwt;
        this.sub = sub;
        this.scope = scope;
    }
}
