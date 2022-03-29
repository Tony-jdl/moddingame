package it.moddingame.rest.risorse.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = RisorsaDTO.RisorsaDTOBuilder.class)
public class RisorsaDTO  {

    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String state;

    @JsonPOJOBuilder(withPrefix = "")
    public static class RisorsaDTOBuilder {
    }

}
