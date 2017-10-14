package A00980851.util;

import A00980851.io.ApplicationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    A00980851Lab
 * Class  Name     Validadtor
 * Date            2017-05-03
 */
public class Validadtor {
    Logging logging = new Logging();
    public Validadtor() {
    }

    /**
     * @param email - the email address
     * @return - either N/A or the email address
     */
    public String emailCheck(String email) throws ApplicationException {
        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);

        if (matcher.matches() == true) {
            return email;
        } else {
            logging.error(String.format("Invalid email \"" + email + "\" is not a valid email"));
            throw new ApplicationException();
        }
    }
}
