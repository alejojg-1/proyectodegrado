package co.proyectoGrado.domain.service.emailsender;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderService {

    @Autowired

    public JavaMailSender javaMailSender;

    public void enviarCorreo(String toEmail,
                             String body) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("proyectogrado36@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText("Tu contraseña de ingreso es:"+" "+ body );
        mimeMessageHelper.setSubject("Confidencial envio de contraseña");


        javaMailSender.send(mimeMessage);
        System.out.printf("Mail with attachment sent successfully..");

    }
}
