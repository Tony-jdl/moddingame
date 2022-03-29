package it.moddingame.rest.risorse;

import it.moddingame.rest.risorse.entity.Risorsa;
import it.moddingame.rest.risorse.dto.RisorsaDTO;
import it.moddingame.rest.risorse.mapper.RisorsaMapperImpl;
import it.moddingame.rest.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.apachecommons.CommonsLog;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@CommonsLog
public class RisorseServiceImpl implements RisorseService {

    @Autowired
    RisorsaRepository risorsaRepository;

    @Autowired
    RisorsaMapperImpl risorsaMapper;

    /* Abilitare per utilizzo kafka
    @Inject
    @Channel("risorsa-channel-out")
    Emitter<RisorsaDTO> risorsaEmitter;
    */

    @Override
    public RisorsaDTO findRisorsaByUsername(String username) throws ServiceException {
        try {
            Optional<Risorsa> risorsa = risorsaRepository.findByUsername(username);
            if (risorsa.isPresent()) {
                return risorsaMapper.convertEntityToDto(risorsa.get());
            }
            return null;
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public RisorsaDTO findById(String id) throws ServiceException {
        try {
            Optional<Risorsa> risorsa = risorsaRepository.findById(id);
            if (risorsa.isPresent()) {
                return risorsaMapper.convertEntityToDto(risorsa.get());
            }
            throw new ServiceException("Risorsa con id:" + id + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void aggiornaRisorsa(String idRisorsa, RisorsaDTO risorsaDTO) throws ServiceException {
        try {
            Optional<Risorsa> risorsa = risorsaRepository.findById(idRisorsa);
            if (risorsa.isPresent()) {
                log.info("Risorsa con id:" + idRisorsa + "trovata sul DB.");
                Risorsa newRisorsa = risorsaMapper.convertDtoToEntity(risorsaDTO);
                newRisorsa.setId(risorsa.get().getId());
                risorsaRepository.save(newRisorsa);
                return;
            }
            throw new ServiceException("Risorsa con id:" + idRisorsa + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<RisorsaDTO> elenco() throws ServiceException {
        try {
            List<Risorsa> risorsa = risorsaRepository.findAll();
            return risorsaMapper.convertEntityToDto(risorsa);
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public String crea(RisorsaDTO risorsaDTO) throws ServiceException {
        try {
            Risorsa risorsa = risorsaMapper.convertDtoToEntity(risorsaDTO);
            risorsaRepository.save(risorsa);
            //String id = risorsa.getId().toString();
            log.info("ID risorsa creato:" + risorsa.getId().toHexString());
            return risorsa.getId().toHexString();
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void cancella(String id) throws ServiceException {
        try {
            Optional<Risorsa> risorsa = risorsaRepository.findById(id);
            if (risorsa.isPresent()) {
                risorsaRepository.delete(risorsa.get());
            } else {
                throw new ServiceException("Risorsa con id:" + id + " non trovata!");
            }

        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }
    
    @Override
    public void creaRisorsaAsync(RisorsaDTO risorsaDTO) throws ServiceException {
        try {
            //Abilitare per utilizzo kafka
            //risorsaEmitter.send(risorsaDTO);
            log.info("Risorsa:"+risorsaDTO+" inviata correttamente su coda KAFKA");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }
}


