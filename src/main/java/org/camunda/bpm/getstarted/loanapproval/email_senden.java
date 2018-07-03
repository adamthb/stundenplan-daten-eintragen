package org.camunda.bpm.getstarted.loanapproval;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class email_senden implements JavaDelegate {

  // TODO: Set Mail Server Properties
  private static final String HOST = "smtp.gmail.com";
  private static final String USER = "demodemo1234512345@gmail.com";
  private static final String PWD = "google12345";

  private final static Logger LOGGER = Logger.getLogger(email_senden.class.getName());

  public void execute(DelegateExecution execution) throws Exception {
//      String var = (String) execution.getVariable("bezeichnung");  
      
      String recipient = "ddemosius@gmail.com" ;//emailadressvariable
      String etext = "Sehr geehrte Professorinnen und Professoren, \n\n Bitte tragen Sie f�r das kommende Semester Ihre Verf�gbarkeit und Ihren Raumwunsch ein. \n\n Ich w�re Ihnen sehr verbunden, wenn Sie dies innerhalb von 2 Wochen erledigen k�nnten.\n\n Mit freundlichen Gruessen, \n\n Demosius Demonasis";
    //  String etext = "Sehr geehrte Damen und Herren, \n\n Ich wuerde gerne folgenden Artikel bestellen: " + var + ".\n\n Mit freundlichen Gruessen, \n\n Demo Demo";
      
      if (recipient != null){
      Email email = new SimpleEmail();
      email.setCharset("utf-8");
      email.setHostName(HOST);
      email.setSmtpPort(465);
      email.setAuthentication(USER, PWD);
      email.setSSL(true);
      
      try {
          email.setFrom("stundenplan-abteilung@th-brandenburg.de");
          email.setSubject("Stundenplandaten eintragen!");
          email.setMsg(etext);

          email.addTo(recipient);

          email.send();
          LOGGER.info("Task Assignment Email successfully sent to address: " + recipient); 

        } catch (Exception e) {
          LOGGER.log(Level.WARNING, "Could not send email to assignee", e);
        }
      
      
      } 
    }

}