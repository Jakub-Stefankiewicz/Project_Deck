package pl.coderslab.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    @Autowired
    private final JavaMailSender javaMailSender;

    public void sendMessage(String to, String subject, String text){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("noreply@realwelt.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
