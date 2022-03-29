package it.moddingame.rest.util;

public class Costanti {

    /**
     * il pattern per le e-mail.
     */
    public static final String PATTERN_EMAIL
            = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"
            + "[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    /**
     * un date format generale.
     */
    public static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    
    /**
     * Nome del file di configurazione per la web app.
     */
    public static final String APPLICATION_PROPERTIES_FILE = "application.properties";


    /**
     * Nome del file di log.
     */
    public static final String LOG_FILE = "log4j.xml";

    public static String TRUE = "true";

    public static String FALSE = "false";
    
    public static final String ROLE_UTENTE_FO = "ROLE_UTENTE_FO";
    public static final String ROLE_REDATTORE = "ROLE_REDATTORE";
    public static final String ROLE_AMMINISTRATORE = "ROLE_ADMIN";
    
    public static final String PROFILO_SSO_UTENTE_FO = "UTENTE_FO";
    public static final String PROFILO_SSO_REDATTORE = "REDATTORE";
    public static final String PROFILO_SSO_AMMINISTRATORE = "ADMIN";
    
    public static final String BUSINESS_OBJECT_NAME = "BusinessObjectName";

    public static final String REDIRECT_URL_LOGIN = "redirectUrlLogin";
}
