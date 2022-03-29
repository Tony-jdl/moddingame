package it.moddingame.rest.utenti;

import it.moddingame.rest.exception.ServiceException;
import it.moddingame.rest.utenti.dto.UtenteDTO;
import it.moddingame.rest.utenti.entity.Utente;
import it.moddingame.rest.utenti.mapper.UtenteMapperImpl;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@CommonsLog
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    UtenteMapperImpl utenteMapper;

    /* Creazione di un Utente */
    @Override
    public String crea(UtenteDTO utenteDTO) throws ServiceException {
        try {
            Utente utente = utenteMapper.convertDtoToEntity(utenteDTO);
            utenteRepository.save(utente);
            //String id = risorsa.getId().toString();
            log.info("ID utente creato:" + utente.getId().toHexString());
            return utente.getId().toHexString();
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    /* Eliminazione di un Utente */
    @Override
    public void cancella(String id) throws ServiceException {
        try {
            Optional<Utente> utente = utenteRepository.findById(id);
            if (utente.isPresent()) {
                utenteRepository.delete(utente.get());
            } else {
                throw new ServiceException("Utente con id:" + id + " non trovato!");
            }

        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public UtenteDTO findById(String id) throws ServiceException {
        try {
            Optional<Utente> utente = utenteRepository.findById(id);
            if (utente.isPresent()) {
                return utenteMapper.convertEntityToDto(utente.get());
            }
            throw new ServiceException("Utente con id:" + id + " non trovato!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }
}
