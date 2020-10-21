package fi.tuni.tamk.tiko.eemil.util;

import fi.tuni.tamk.tiko.eemil.Main;

import java.util.ResourceBundle;

/**
 * Different strings used by the application
 */
public class localestrings {

   ResourceBundle messages = ResourceBundle.getBundle("fi/tuni/tamk/tiko/eemil/resources/strings", Main.currentLocale);
   String numberMsg = messages.getString("numberMsg");
   String errorMsg = messages.getString("errorMsg");
   String errorMessageNonMinAndMax = messages.getString("errorMessageNonMinAndMax");
   String errorMessageSameNumber = messages.getString("errorMessageSameNumber");
   String calc = messages.getString("calc");
   String welocme = messages.getString("welcome");
   String firstQuestion = messages.getString("firstquestion");
   String secondQuestion = messages.getString("secondquestion");
   String invalidInput = messages.getString("invalidinput");
   String thanks = messages.getString("thanks");
   String jackpotpt1 = messages.getString("jackpotpt1");
   String jackpotpt2 = messages.getString("jackpotpt2");
   String jackpotpt3 = messages.getString("jackpotpt3");
   String allpt1 = messages.getString("allpt1");
   String allpt2 = messages.getString("allpt2");
   String allpt3 = messages.getString("allpt3");
   String allpt4 = messages.getString("allpt4");
}
