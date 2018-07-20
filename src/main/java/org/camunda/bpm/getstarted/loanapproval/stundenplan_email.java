package org.camunda.bpm.getstarted.loanapproval;


import java.util.ArrayList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class stundenplan_email implements JavaDelegate {

  // TODO: Set Mail Server Properties
  private static final String HOST = "smtp.gmail.com";
  private static final String USER = "demodemo1234512345@gmail.com";
  private static final String PWD = "google12345";

  private final static Logger LOGGER = Logger.getLogger(stundenplan_email.class.getName());

  public void execute(DelegateExecution execution) throws Exception {
      String var1 = (String) execution.getVariable("zeitkonflikt");  
      //String var2 = (String) execution.getVariable("raumkonflikt");
      ArrayList<String> var2 = new ArrayList<>();
      var2 = (ArrayList<String>) execution.getVariable("raumkonflikt");
      String var3 = (String) execution.getVariable("wikarski_wunschfrei");
      String var4 = (String) execution.getVariable("franz_wunschfrei");
      String var5 = (String) execution.getVariable("meister_wunschfrei");
      String var6 = (String) execution.getVariable("pfister_wunschfrei");
      String var7 = (String) execution.getVariable("scheeg_wunschfrei");
      
      String recipient = "ddemosius@gmail.com" ;//emailadressvariable
      String etext = "Sehr geehrter Herr Stundenplan-Bearbeiter, \n\n  Ergebnisse aus Cockpit: \n\n Zeitkonflikt: "+ var1 +" \n\n Wunschfrei Herr Wikarski "+ var3 +" \n Wunschfrei Herr Franz "+ var4 +" \n Wunschfrei Frau Meister "+ var5 +" \n Wunschfrei Herr Pfister "+ var6 +" \n Wunschfrei Herr Scheeg "+ var7 +"  \n\n Raumkonflikt und zugewiesene Räume: "+ var2 +" \n\n Mit freundlichen Gruessen, \n\n Cockpit Interface";
    
     
      if (recipient != null){
      Email email = new SimpleEmail();
      email.setCharset("utf-8");
      email.setHostName(HOST);
      email.setSmtpPort(465);
      email.setAuthentication(USER, PWD);
      email.setSSL(true);
      
      try {
          email.setFrom("Stundenplan-Cockpit@th-brandenburg.de");
          email.setSubject("Cockpit Ergebnisse");
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