package org.unicauca.usuarioservice.configuracionSeguridad.fachada.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.entidades.*;
import org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.repositorios.IRoleRepository;
import org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.repositorios.ITipoIdentificacionRepository;
import org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.repositorios.IUserRepository;
import org.unicauca.usuarioservice.configuracionSeguridad.fachada.DTO.request.LoginRequestDTO;
import org.unicauca.usuarioservice.configuracionSeguridad.fachada.DTO.request.SignupRequestDTO;
import org.unicauca.usuarioservice.configuracionSeguridad.fachada.DTO.response.JwtResponseDTO;
import org.unicauca.usuarioservice.configuracionSeguridad.fachada.DTO.response.MessageResponseDTO;
import org.unicauca.usuarioservice.configuracionSeguridad.security.jwt.JwtUtils;
import org.unicauca.usuarioservice.configuracionSeguridad.security.services.UserDetailsImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthImpl implements AuthInt{
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IRoleRepository roleRepository;

    @Autowired
    ITipoIdentificacionRepository tipoIdentificacionRepository;

    @Autowired
    PasswordEncoder encoder;


    @Override
    public JwtResponseDTO authenticateUser(LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponseDTO(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getPassword(),
                roles);
    }

    @Override
    public MessageResponseDTO registerUser(SignupRequestDTO signUpRequest) {

        User user = new User(signUpRequest.getIdentificacion(),signUpRequest.getNombres(),signUpRequest.getApellidos(),signUpRequest.getEmail(),signUpRequest.getTitulo(),signUpRequest.getEstado(),encoder.encode(signUpRequest.getContrasenia()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();


        String strTipoIdentificacion=signUpRequest.getTipoIdentificacion();
        System.out.println(strTipoIdentificacion);

        switch (strTipoIdentificacion){
            case "TARJETA_IDENTIDAD":
                Optional<TipoIdentificacion> tarjetaIdentidad = tipoIdentificacionRepository.findByName(ETipoIdentificacion.TARJETA_IDENTIDAD);
                user.setTipoIdentificacion(tarjetaIdentidad.get().getTipoIdentificacion().toString());
                break;

            case "CEDULA":
                Optional<TipoIdentificacion> cedula = tipoIdentificacionRepository.findByName(ETipoIdentificacion.CEDULA);
                user.setTipoIdentificacion(cedula.get().getTipoIdentificacion().toString());
                break;
            case  "EXTRANJERIA":
            Optional<TipoIdentificacion> extranjeria = tipoIdentificacionRepository.findByName(ETipoIdentificacion.EXTRANJERIA);
            user.setTipoIdentificacion(extranjeria.get().getTipoIdentificacion().toString());
            break;
            default:
                throw new RuntimeException("Tipo de identificacion no encontrado");

        }

        strRoles.forEach(role -> {
                switch (role) {
                    case "docente":
                        Role docenteRole = roleRepository.findByName(ERole.ROLE_DOCENTE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(docenteRole);
                        break;
                    case "coordinador":
                        Role coordinadorRole= roleRepository.findByName(ERole.ROLE_COORDINADOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(coordinadorRole);
                        break;
                }
            });


        user.setRoles(roles);
        userRepository.save(user);
        return new MessageResponseDTO("Usuario "+user.getNombres()+" creado exitosamente");
    }

}
