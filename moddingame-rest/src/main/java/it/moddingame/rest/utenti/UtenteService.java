package it.moddingame.rest.utenti;

import it.moddingame.rest.exception.ServiceException;
import it.moddingame.rest.utenti.dto.UtenteDTO;

public interface UtenteService {

    /* Creazione di un Utente */
    String crea(UtenteDTO utente) throws ServiceException;

    /* Eliminazione di un Utente */
    void cancella(String id) throws ServiceException;

    UtenteDTO findById(String id) throws ServiceException;
}
