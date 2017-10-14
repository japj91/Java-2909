package A00980851.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    AssignmentOne
 * Class  Name     Validator
 * Date            2017-05-28
 *
 * class is used to validate emails
 */


public class Validator {
    /**
     * validates the email
     * @param email
     * @return
     */
    public String emailCheck(String email) {
        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);

        if (matcher.matches() == true) {
            return email;
        } else {
            //logging.error(String.format("Invalid email \"" + email + "\" is not a valid email"));
            // fix the might throw new exception part
            System.out.println("Add exception handling- Validator");
            return "japjohal@hotmail.com";
        }
    }
}
