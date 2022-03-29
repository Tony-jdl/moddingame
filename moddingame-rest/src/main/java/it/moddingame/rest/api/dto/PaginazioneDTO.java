package it.moddingame.rest.api.dto;

/**
 *
 * @author s.g.
 */
public class PaginazioneDTO {

    private Integer numeroPagina;

    private Integer risultatiPagina;

    private Integer numeroPagTotali;

    private Long numeroRisTotali;

    public Integer getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(Integer numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    public Integer getRisultatiPagina() {
        return risultatiPagina;
    }

    public void setRisultatiPagina(Integer risultatiPagina) {
        this.risultatiPagina = risultatiPagina;
    }

    public Integer getNumeroPagTotali() {
        return numeroPagTotali;
    }

    public void setNumeroPagTotali(Integer numeroPagTotali) {
        this.numeroPagTotali = numeroPagTotali;
    }

    public Long getNumeroRisTotali() {
        return numeroRisTotali;
    }

    public void setNumeroRisTotali(Long numeroRisTotali) {
        this.numeroRisTotali = numeroRisTotali;
    }

    @Override
    public String toString() {
        return "PaginazioneDTO{" + "numeroPagina=" + numeroPagina + ", risultatiPagina=" + risultatiPagina + ", numeroPagTotali=" + numeroPagTotali + ", numeroRisTotali=" + numeroRisTotali + '}';
    }

}

