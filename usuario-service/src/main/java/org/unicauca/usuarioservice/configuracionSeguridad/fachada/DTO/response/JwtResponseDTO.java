package org.unicauca.usuarioservice.configuracionSeguridad.fachada.DTO.response;

import java.util.List;

public class JwtResponseDTO {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private String contrasenia;
    private List<String> roles;

    public JwtResponseDTO(String accessToken, Long id, String email, String contrasenia, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.email = email;
        this.contrasenia = contrasenia;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public List<String> getRoles() {
        return roles;
    }
}
