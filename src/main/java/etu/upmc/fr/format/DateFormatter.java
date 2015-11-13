package etu.upmc.fr.format;


import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * Created by daniele on 13/11/15.
 */
public class DateFormatter extends org.springframework.format.datetime.DateFormatter {
    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        if (text != null && text.isEmpty()) {
            return null;
        }
        return super.parse(text, locale);
    }
}
