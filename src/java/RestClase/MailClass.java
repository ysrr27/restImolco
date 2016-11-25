
/*
 *La clase para el envío de emails está aparte porque por alguna razón entraba en conflicto con las demás librerías
 */
package RestClase;

import java.util.Properties;
import java.util.stream.Stream;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailClass {

    private String idioma;
    private String id;
    private String respuesta;
    private String asunto;
    private String cuerpo;

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }


    String host = "correo.telesurtv.net";
    String to = "serviciosweb@telesurtv.net";
    String from = "alertas@telesurtv.net";
    final String username = "webtelesurt@telesurtv.net";
    final String password = "/W3b-Tele$ur..";
    String result;
    String de;

    public void SendAlertToMail() {

        if ("esp".equals(idioma)) {
            idioma = "Español";
        } else {
            idioma = "Inglés";
        }

        // La dirección de envío (to)
        String para = "serviciosweb@telesurtv.net";
        if ("201".equals(respuesta)) {
            // La dirección de la cuenta de envío (from)
            de = "servicioswebtelesur@gmail.com";
        } else {

            de = "alertas@telesurtv.net";
        }
        // El servidor (host). En este caso usamos localhost
        String host = "correo.telesurtv.net";

        // Obtenemos las propiedades del sistema
        Properties propiedades = System.getProperties();

        // Configuramos el servidor de correo
        propiedades.setProperty("mail.smtp.host", host);

        // Obtenemos la sesión por defecto
        Session sesion = Session.getDefaultInstance(propiedades);

        try {
            // Creamos un objeto mensaje tipo MimeMessage por defecto.
            MimeMessage mensaje = new MimeMessage(sesion);

            // Asignamos el “de o from” al header del correo.
            mensaje.setFrom(new InternetAddress(de));

            // Asignamos el “para o to” al header del correo.
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));

            // Asignamos el asunto
            if ("201".equals(respuesta)) {

                mensaje.setSubject("El newsletter " + idioma + " fue enviado con exito respuesta http : " + respuesta);

                // Asignamos el mensaje como tal
                mensaje.setText("El newsletter " + idioma + " fue enviado con exito \n\n "
                        + "Respuesta http = " + respuesta + "   \n\n "
                        + "DATOS DE LA ORDEN: \n" + id);

            } else {

                mensaje.setSubject("El newsletter " + idioma + "No fue enviado por favor verifique \n\n "
                        + "Respuesta http : " + respuesta + "\n\n"
                        + "  DATOS DE LA ORDEN N°= \n  " + id);

                // Asignamos el mensaje como tal
                mensaje.setText("El newsletter " + idioma + " No fue enviado por favor verifique  \n\n "
                        + "Respuesta http:  " + respuesta + "\n\n"
                        + "DATOS DE LA ORDEN: \n" + id);

            }

            // Enviamos el correo
            Transport.send(mensaje);
            System.out.println("Mensaje enviado");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
	