package it.moddingame.rest.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * A date helper
 */
public class DateHelper {
    public static final String TIME_FORMAT = "HHmm";

    public static final String TIME_HOUR_MINUTE = "HH:mm";

    public static final String TIME_HOUR_MINUTE_XLS = "HH.mm";

    public static final String TIME_FORMAT_IT = "HH:mm:ss";

    private static DateFormat timeFormatter = new SimpleDateFormat(TIME_FORMAT);

    private static DateFormat timeFormatterIT = new SimpleDateFormat(TIME_FORMAT_IT);

    private static final String FIRST_DATE = "18000101";

    private static final String LAST_DATE = "20501231";

    public static final int FIRST_YEAR = 1800;

    public static final int LAST_YEAR = 2050;

    public static final long MILLI_SEC_X_SECOND = 1000;

    public static final long MILLI_SEC_X_MINUTE = 60 * MILLI_SEC_X_SECOND;

    public static final long MILLI_SEC_X_HOUR = 60 * MILLI_SEC_X_MINUTE;

    public static final long MILLI_SEC_X_DAY = 24 * MILLI_SEC_X_HOUR;

    /**
     * The italian date format with slash
     */
    public static final String DATE_FORMAT_IT_SLASH = "dd/MM/yyyy";

    /**
     * The italian date format
     */
    public static final String DATE_FORMAT_IT = "dd-MM-yyyy";

    public static final String DATE_FORMAT_IT_MMM = "dd-MMM-yyyy";

    public static final String DATE_FORMAT_IT_MONTH = "dd MMMMM yyyy";

    public static final String DATE_FORMAT_CENTURY_SHORT = "yyMMdd";

    public static final String DATE_FORMAT_CENTURY_LONG = "yyyyMMdd";

    public static final String MONTH_FORMAT_IT_LONG = "MMMMM";

    public static final String DATE_MONTH_YEAR_FORMAT_IT_LONG = "MMMMM yyyy";

    public static final String DATE_HOUR_FORMAT_IT = "dd/MM/yyyy HH:mm";

    public static final String DATE_HOUR_FORMAT_IT_SHORT = "dd/MM/yy HH:mm";

    public static final String DATE_HMS_FORMAT_IT = "dd/MM/yyyy HH:mm:ss";

    public static final int NUMERO_GG_FESTIVI = 11;

    public static Date ActualDate() {
        return new Date();
    }


    public static Integer actualYear() {
        return new Integer(formatDateAsInt(ActualDate(), "yyyy"));
    }


    /**
     * Format a date with the italian format
     *
     * @param data the date fo format
     * @return the formatted date
     */
    public static String formatDateItalian(Date data) {
        return formatDate(data, DATE_FORMAT_IT);
    }


    /**
     * Formats a date with the given format
     *
     * @param data       the date to format
     * @param dateFormat the dateFormat to use
     * @return the formatted date
     */
    public static String formatDate(Date data, String dateFormat) {
        return formatDate(data, dateFormat, null);
    }

    public static String formatDate(Date data, String dateFormat, Locale locale) {
        String result = null;
        DateFormat dateFormatter = null;

        if (locale == null)
            dateFormatter = new SimpleDateFormat(dateFormat);
        else
            dateFormatter = new SimpleDateFormat(dateFormat, locale);

        result = dateFormatter.format(data);

        return result;
    }

    /**
     * Format the date as an integer
     *
     * @param date the input date
     * @return the related integer
     */
    public static int formatDateAsInt(Date date) {
        return Integer.parseInt(formatDate(date, DATE_FORMAT_CENTURY_LONG));
    }

    /**
     * Format the time as an integer
     *
     * @param date the input date
     * @return the related integer
     */
    public static int formatDateAsInt(Date date, String format) {
        return Integer.parseInt(formatDate(date, format));
    }

    /**
     * Parses a date with the default format
     *
     * @param data the date to parse
     * @return the parsed date
     */
    public static Date parseDate(String data) throws ParseException {
        return parseDate(data, "yyyyMMdd");
    }

    /**
     * Parses a date with the default format
     *
     * @return the parsed date
     */
    public static Date parseDate(int dataNum) throws ParseException {

        return parseDate(new Integer(dataNum).toString(), "yyyyMMdd");
    }

    /**
     * Parses a date with the italian format
     *
     * @param data the date to parse
     * @return the parsed date
     */
    public static Date parseDateItalian(String data) throws ParseException {
        return parseDate(data, DATE_FORMAT_IT);
    }

    public static Date parseDateItalian(String data, String format) throws ParseException {
        return parseDate(data, format);
    }

    /**
     * Parses a date with the given format
     *
     * @param data       the date to parse
     * @param dateFormat the date format to use
     * @return the parsed date
     */
    public static Date parseDate(String data, String dateFormat) throws ParseException {
        Date result = null;
        DateFormat dateFormatter = new SimpleDateFormat(dateFormat);

        result = dateFormatter.parse(data);
        return result;
    }


    /**
     * answer a date that is equal to the one passed as parameter but with time (hours, minutes and seconds) set to zero
     *
     * @return
     * @throws Exception
     */
    public static Date getCurrentDateWithoutTime() throws ParseException {

        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");

        return sf.parse(sf.format(new Date()));
    }


    /**
     * retorna true se la data a e' uguale in giorni alla data b
     *
     * @param a
     * @param b
     * @return boolean
     */
    public static boolean equalsInDays(Calendar a, Calendar b) {
        return equalsInDays(a.getTime(), b.getTime());
    }

    /**
     * retorna true se la data a e' uguale in giorni alla data b
     *
     * @param a
     * @param b
     * @return boolean
     */
    public static boolean equalsInDays(Date a, Date b) {
        return (formatDateAsInt(a) == formatDateAsInt(b));
    }

    /**
     * retorna true se la data a e' precedente in giorni alla data b
     *
     * @param a
     * @param b
     * @return boolean
     */
    public static boolean beforeInDays(Calendar a, Calendar b) {
        return beforeInDays(a.getTime(), b.getTime());
    }

    /**
     * retorna true se la data a e' precedente in giorni alla data b
     *
     * @param a
     * @param b
     * @return boolean
     */
    public static boolean beforeInDays(Date a, Date b) {
        return (formatDateAsInt(a) < formatDateAsInt(b));
    }

    /**
     * retorna true se la data a e' successiva in giorni alla data b
     *
     * @param a
     * @param b
     * @return boolean
     */
    public static boolean afterInDays(Calendar a, Calendar b) {
        return afterInDays(a.getTime(), b.getTime());
    }

    /**
     * retorna true se la data a e' successiva in giorni alla data b
     *
     * @param a
     * @param b
     * @return boolean
     */
    public static boolean afterInDays(Date a, Date b) {
        return (formatDateAsInt(a) > formatDateAsInt(b));
    }

    public static Calendar calendarInstance(Calendar cal) {
        Calendar c = Calendar.getInstance();
        c.setTime(cal.getTime());
        return c;
    }

    /**
     * retorna true se la data a e' minore o uguale in giorni alla data b
     *
     * @param a
     * @param b
     * @return boolean
     */
    public static boolean leInDays(Calendar a, Calendar b) {
        return leInDays(a.getTime(), b.getTime());
    }

    /**
     * retorna true se la data a e' minore o uguale in giorni alla data b
     *
     * @param a
     * @param b
     * @return boolean
     */
    public static boolean leInDays(Date a, Date b) {
        return (formatDateAsInt(a) <= formatDateAsInt(b));
    }

    /**
     * retorna true se la data a e' maggiore o uguale in giorni alla data b
     *
     * @param a
     * @param b
     * @return boolean
     */
    public static boolean geInDays(Calendar a, Calendar b) {
        return geInDays(a.getTime(), b.getTime());
    }

    /**
     * retorna true se la data a e' maggiore o uguale in giorni alla data b
     *
     * @param a
     * @param b
     * @return boolean
     */
    public static boolean geInDays(Date a, Date b) {
        return (formatDateAsInt(a) >= formatDateAsInt(b));
    }


    /**
     * True if currentDate between prevDate and nextDate. False otherwise.
     *
     * @param prevDate
     * @param nextDate
     * @param currentDate
     * @return
     */
    public static boolean isInRangeDate(Date prevDate, Date nextDate, Date currentDate) {
        if (prevDate != null && DateHelper.afterInDays(prevDate, currentDate)) {
            return false;
        } else if (nextDate != null && DateHelper.afterInDays(currentDate, nextDate)) {
            return false;
        } else {
            return true;
        }
    }


    public static Calendar toCalendar(Date date) {
        if (date == null)

        {
            return null;

        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date(date.getTime()));
        return c1;
    }


    public static Calendar toCalendar(Calendar date) {
        if (date == null)

        {
            return null;

        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date(date.getTime().getTime()));
        return c1;
    }

    /**
     * @param d1
     * @param d2
     * @return Date
     */
    public static Date maxDate(Date d1, Date d2) {
        if (d1.before(d2))
            return d2;
        else {
            return d1;
        }
    }

    /**
     * @param d1
     * @param d2
     * @return Date
     */
    public static Date minDate(Date d1, Date d2) {
        if (d1.before(d2))
            return d1;
        else {
            return d2;
        }
    }


    /**
     * Differenza in gg tra le 2 date
     *
     * @param iniDate
     * @param finDate
     * @return long in gg
     */
    public static long diffInGG(Date iniDate, Date finDate) {
        return (finDate.getTime() / MILLI_SEC_X_DAY) - (iniDate.getTime() / MILLI_SEC_X_DAY) + 1;
    }


}
