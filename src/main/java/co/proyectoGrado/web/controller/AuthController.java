package co.proyectoGrado.web.controller;

import co.proyectoGrado.domain.dto.AuthenticationRequest;
import co.proyectoGrado.domain.dto.AuthenticationResponse;
import co.proyectoGrado.domain.dto.DtoCambioPassowordUsuario;
import co.proyectoGrado.domain.dto.DtoRestablecerPassword;
import co.proyectoGrado.domain.service.password.CambioPasswordUsuarioService;
import co.proyectoGrado.domain.service.password.RecuperarPasswordUsuarioService;
import co.proyectoGrado.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    private final RecuperarPasswordUsuarioService recuperarPasswordService;
    private final CambioPasswordUsuarioService cambioPasswordUsuarioService;

    public AuthController(RecuperarPasswordUsuarioService recuperarPasswordService, CambioPasswordUsuarioService cambioPasswordUsuarioService) {
        this.recuperarPasswordService = recuperarPasswordService;
        this.cambioPasswordUsuarioService = cambioPasswordUsuarioService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);
            String rol = jwtUtil.extractRol(jwt);
            return new ResponseEntity<>(new AuthenticationResponse(jwt,request.getUsername(),rol),HttpStatus.OK);
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/recuperar-password")
    public ResponseEntity<Boolean> restablecerPassword(@RequestBody DtoRestablecerPassword restablecerPassword) {
        if(recuperarPasswordService.ejecutar(restablecerPassword.getCorreo(),restablecerPassword.getIdentificacion())){
            return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Boolean.FALSE,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cambio-password")
    public ResponseEntity<Boolean> cambioPasswordUsuario(@RequestBody DtoCambioPassowordUsuario dtoCambioPassowordUsuario) {
        if(cambioPasswordUsuarioService.ejecutar(dtoCambioPassowordUsuario)){
            return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Boolean.FALSE,HttpStatus.BAD_REQUEST);
        }
    }
}
