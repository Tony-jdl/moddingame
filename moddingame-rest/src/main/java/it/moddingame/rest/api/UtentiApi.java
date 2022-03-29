package it.moddingame.rest.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.moddingame.rest.exception.ApplicationException;
import it.moddingame.rest.exception.ServiceException;
import it.moddingame.rest.utenti.UtenteService;
import it.moddingame.rest.utenti.dto.UtenteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.reflections.Reflections.log;

@Api(value = "Servizi per la gestione di un utente", tags = "Utenti")
@RestController
@RequestMapping({"${app.context-path}/utenti"})
public class UtentiApi extends BaseApi {

    @Autowired
    UtenteService utenteService;


    /* POST - Creazione Utente */
    @CrossOrigin
    @ApiOperation(value = "Crea un nuovo utente", consumes = MediaType.APPLICATION_JSON_VALUE, response = UtenteDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Creazione utente avvenuta con successo")})
    @PostMapping(value = {""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity creaUtente(@RequestBody UtenteDTO utenteDTO) throws ApplicationException {
        try {
            String idUtente = utenteService.crea(utenteDTO);
            log.info("Nuovo utente creata con id:" + idUtente);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{isUtente}").buildAndExpand(idUtente).toUri();
            return ResponseEntity.created(location).build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    /* DELETE - Eliminazione Utente */
    @CrossOrigin
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cancellazione avvenuto con successo")})
    @ApiOperation(value = "Cancella un utente per id", notes = "Cancella uno specifico utente dato un id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity cancellaUtente(@PathVariable("id") String idUtente) throws ApplicationException {
        try {
            log.info("Cancella utente con id:" + idUtente);
            utenteService.cancella(idUtente);
            return ResponseEntity.noContent().build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }
}
