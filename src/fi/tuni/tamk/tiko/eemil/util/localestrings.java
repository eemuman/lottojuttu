package fi.tuni.tamk.tiko.eemil.util;

import fi.tuni.tamk.tiko.eemil.Main;

import java.util.ResourceBundle;

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
   String printresultAll = messages.getString("printresultAll");
   String printresultJackpoit = messages.getString("printresultJackpot");
}
