package it.moddingame.rest.risorse;

import it.moddingame.rest.risorse.entity.Risorsa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "risorsaRepository")
public interface RisorsaRepository extends MongoRepository<Risorsa, String> {

    Optional<Risorsa> findByUsername(String username);
}