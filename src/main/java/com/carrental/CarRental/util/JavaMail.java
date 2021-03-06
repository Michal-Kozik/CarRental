package com.carrental.CarRental.util;

import com.carrental.CarRental.model.Car;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class JavaMail {

    @Asynchronous
    public void sendMail(String recipient, Car car) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String appEmail = "siemanokolano2137@gmail.com";
        String password = "Admin123!";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(appEmail, password);
            }
        });

        Message message = prepareMessage(session, appEmail, recipient, car);

        Transport.send(message);
    }

    private static Message prepareMessage(Session session, String appEmail, String recipient, Car car) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(appEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Złożono rezerwację.");
            message.setText("Złożono rezerwację pomyślnie. Oto szczegóły:\n" +
                    "Marka: " + car.getBrand() + ",\n" +
                    "Kwota do zapłaty: " + car.getPrice() + "zł.");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
