package org.unicauca.docenteservice.fachadaServices.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageResponseDTO {
    @JsonProperty("message")
    private String message;

    public MessageResponseDTO() {}

    public MessageResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
