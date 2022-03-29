package it.moddingame.rest.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created by s.g. on 05/12/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String OK = "OK";
    public static final String KO = "KO";
    private String stato;

    private String descrizione;

    private PaginazioneDTO paginazione;

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public PaginazioneDTO getPaginazione() {
        return paginazione;
    }

    public void setPaginazione(PaginazioneDTO paginazione) {
        this.paginazione = paginazione;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "stato='" + stato + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", paginazione=" + paginazione +
                '}';
    }
}
