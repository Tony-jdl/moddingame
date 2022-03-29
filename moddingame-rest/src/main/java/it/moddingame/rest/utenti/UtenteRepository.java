package it.moddingame.rest.utenti;

import it.moddingame.rest.utenti.entity.Utente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "utenteRepository")
public interface UtenteRepository extends MongoRepository<Utente, String> {
}
