package it.moddingame.rest.utenti.mapper;

import it.moddingame.rest.exception.MapperException;
import it.moddingame.rest.utenti.dto.UtenteDTO;
import it.moddingame.rest.utenti.entity.Utente;
import it.moddingame.rest.util.mapper.AbstractMapperComponent;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class UtenteMapperImpl extends AbstractMapperComponent<UtenteDTO, Utente> {
    @Override
    public UtenteDTO convertEntityToDto(Utente entity) throws MapperException {
        if (entity != null) {
            UtenteDTO dto = UtenteDTO.builder()
                    .username(entity.getUsername())
                    .email(entity.getEmail())
                    .firstName(entity.getFirstName())
                    .lastName(entity.getLastName())
                    .password(entity.getPassword())
                    .state(entity.getState())
                    .id(entity.getId().toHexString())
                    .build();
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public Utente convertDtoToEntity(UtenteDTO dto) throws MapperException {
        try {

            if (dto != null) {
                Utente entity = new Utente();
                entity.setUsername(dto.getUsername());
                entity.setEmail(dto.getEmail());
                entity.setFirstName(dto.getFirstName());
                entity.setLastName(dto.getLastName());
                entity.setPassword(dto.getPassword());
                entity.setState(dto.getState());
                if (dto.getId() != null) {
                    entity.setId(new ObjectId(dto.getId()));
                }
                return entity;
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new MapperException("Errore in mapper Utente " + ex.getMessage());
        }
    }
}
