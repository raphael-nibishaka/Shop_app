package URaphael.raphy.utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailUtil {

    // Use your Gmail credentials
    private static final String from = "hatumacharles1@gmail.com";
    private static final String password = "pwvojnhrrorvzmhj"; // App Password, NOT your Gmail password
    private static final String host = "smtp.gmail.com";

    public static void sendEmail(String toEmail, String subject, String body) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setContent(body, "text/html"); // Set content type to HTML for formatting

            Transport.send(message);

            System.out.println("Email sent successfully to " + toEmail);

        } catch (MessagingException e) {
            e.printStackTrace(); // Log the exception for debugging
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
