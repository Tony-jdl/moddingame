package it.moddingame.rest.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SecurityTrace {

    boolean skipCheck() default false;

    String businessObj();

    String codArea();

    TipoOperazione[] tipoOperazione() default TipoOperazione.find;

    public enum TipoOperazione {

        insert("INS"),
        update("UPD"),
        delete("DEL"),
        find("QRY");

        private final String descrizione;

        TipoOperazione(String descrizione) {
            this.descrizione = descrizione;
        }

        public String getDescrizione() {
            return this.descrizione;
        }
    };
}
