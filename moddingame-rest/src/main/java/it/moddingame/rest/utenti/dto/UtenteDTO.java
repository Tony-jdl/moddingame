package it.moddingame.rest.utenti.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = UtenteDTO.UtenteDTOBuilder.class)
public class UtenteDTO {

    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String state;

    @JsonPOJOBuilder(withPrefix = "")
    public static class UtenteDTOBuilder {
    }
}
