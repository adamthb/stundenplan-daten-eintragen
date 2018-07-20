package org.camunda.bpm.getstarted.loanapproval;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class reminder_wikarski implements JavaDelegate {

  // TODO: Set Mail Server Properties
  private static final String HOST = "smtp.gmail.com";
  private static final String USER = "demodemo1234512345@gmail.com";
  private static final String PWD = "google12345";

  private final static Logger LOGGER = Logger.getLogger(reminder_wikarski.class.getName());

  public void execute(DelegateExecution execution) throws Exception {

      
      String recipient = "ddemosius@gmail.com" ;//emailadressvariable
      String etext = "Sehr geehrter Herr Wikarski, \n\n Leider haben Sie ihre Verfügbarkeit und Raumwunsch für das kommende Semester noch nicht eingetragen, ich bitte Sie dies in den nächsten 7 Tagen zu erledigen .\n\n Mit freundlichen Gruessen, \n\n Demosius Demonasis";
  
      
      if (recipient != null){
      Email email = new SimpleEmail();
      email.setCharset("utf-8");
      email.setHostName(HOST);
      email.setSmtpPort(465);
      email.setAuthentication(USER, PWD);
      email.setSSL(true);
      
      try {
          email.setFrom("stundenplan-abteilung@th-brandenburg.de");
          email.setSubject("Stundenplandaten eintragen! Reminder!!!");
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