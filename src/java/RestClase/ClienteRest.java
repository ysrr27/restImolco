/*
 * Si quieren usar mi código, depositenme plata
 * Para cambiar este código, por favor respandenlo antes de editarlo
 * Hecho por Jalfonzo con mucha paciencia y mucho código, pero sobre todo mucha paciencia
 */
package RestClase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import static java.lang.System.out;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.stream.Stream;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author jalfonzo (Jesús Alfonzo)
 */
public class ClienteRest {

    private String URL = "";
    private String ID;
    private String total = "";
    private int returnCode;
    private String idioma;
    private String usuario;
    private String password;
    private String RPC;
    private String methodURL;
    private String Authorization;
    private int idBlog;
    private String tematica;
    private String mail;
    private String HTML;
    private String nombreEmail;
    private String response;
    private String OutOrden;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setRPC(String RPC) {
        this.RPC = RPC;
    }

    public String getRPC() {
        return RPC;
    }

    public void setMethodURL(String methodURL) {
        this.methodURL = methodURL;
    }

    public String getMethodURL() {
        return methodURL;
    }

    public void setAuthorization(String Authorization) {
        this.Authorization = Authorization;
    }

    public String getAuthorization() {
        return Authorization;
    }

    public void setIdBlog(int idBlog) {
        this.idBlog = idBlog;
    }

    public int getIdBlog() {
        return idBlog;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getTematica() {
        return tematica;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setHTML(String HTML) {
        this.HTML = HTML;
    }

    public String getHTML() {
        return HTML;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setNombreEmail(String nombreEmail) {
        this.nombreEmail = nombreEmail;
    }

    public String getNombreEmail() {
        return nombreEmail;
    }

    /**
     *
     * @param idioma
     */
    public void setCuenta(String idioma) {
        if ("esp".equals(idioma)) {
            setIdioma(idioma);
            setUsuario("telesurwebimk");
            setPassword("*t3l3SURcl4v32013/.*");
            setRPC("http://www.imolko.com/wordpress/sites/telesurwebimk/xmlrpc.php");
            setMethodURL("http://www.imolko.com/aurora-ws/usuarios/telesurwebimk/ordenes");
            setAuthorization("Basic dGVsZXN1cndlYmltazoqdDNsM1NVUmNsNHYzMjAxMy8uKg==");
            setIdBlog(7595);
            setTematica("resumen_telesur");
            setMail("contactenos@telesurtv.net");
            setHTML("http://www.telesurtv.net/NewsletterESP.html");
            setNombreEmail("Contactenos teleSUR");
        } else {
            setIdioma(idioma);
            setUsuario("telesurenglish");
            setPassword("%9uY979!");
            setRPC("http://www.imolko.com/wordpress/sites/telesurenglish/xmlrpc.php");
            setMethodURL("http://www.imolko.com/aurora-ws/usuarios/telesurenglish/ordenes");
            setAuthorization("Basic dGVsZXN1cmVuZ2xpc2g6JTl1WTk3OSE=");
            setIdBlog(8556);
            setTematica("resumen_telesur_ingles");
            setMail("contactus@telesurtv.net");
            setHTML("http://www.telesurtv.net/english/NewsletterENG.html");
            setNombreEmail("Contact Us teleSUR");
        }

    }

    public String FechaMethod() {
        Calendar calendario = new GregorianCalendar();

        String momentDate;
        String encabezado;

        if (Calendar.HOUR_OF_DAY < 12) {
            if ("esp".equals(idioma)) {
                momentDate = "Mañana";

            } else {

                momentDate = "Morning";
            }
        } else if (Calendar.HOUR_OF_DAY < 18 && Calendar.HOUR_OF_DAY > 12) {
            if ("esp".equals(idioma)) {
                momentDate = "Tarde";

            } else {

                momentDate = "Afternoon";
            }
        } else {
            if ("esp".equals(idioma)) {
                momentDate = "Noche";
            } else {

                momentDate = "Night";
            }
        }

        if ("esp".equals(idioma)) {
            encabezado = "Boletín teleSUR";
        } else {

            encabezado = "Newsletter teleSUR";
        }

        Date dNow = new Date();
        SimpleDateFormat ft
                = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = ft.format(dNow);

        //String Subject = (encabezado + " " + currentDate + " " + momentDate);
        String Subject = (encabezado + " " + currentDate);
        return Subject;

    }

    public String getURLtoSend() {
        HttpClient client = new HttpClient();

        BufferedReader br = null;
        PostMethod method = new PostMethod(getRPC());
        method.addParameter("username", getUsuario());
        method.addParameter("password", getPassword());
        method.addParameter("Authorization", getAuthorization());
        method.getDoAuthentication();
        method.setRequestBody("<methodCall><methodName>wp.getPost</methodName><params><param><value><string>" + getIdBlog() + "</string></value></param><param><value><string>" + getUsuario() + "</string></value></param><param><value><string>" + getPassword() + "</string></value></param><param><value><string>" + getID() + "</string></value></param><param><value><struct><member><name>1</name><value><string>link</string></value></member></struct></value></param></params></methodCall>");

        try {
            returnCode = client.executeMethod(method);
            br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
            String readLine;
            String xmlresponse = "";
            while (((readLine = br.readLine()) != null)) {

                xmlresponse = xmlresponse + readLine;
            }
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xmlresponse)));
            NodeList errNodes = doc.getElementsByTagName("member");
            if (errNodes.getLength() > 0) {
                Element err = (Element) errNodes.item(1);
                URL = err.getElementsByTagName("string").item(0).getTextContent();
            }
        } catch (Exception e) {
            out.println(e);
        }
        return URL;
    }

    public String CreateContentImolko() {

        try {

            URL pagina;
            pagina = new URL(getHTML());

            BufferedReader in = new BufferedReader(new InputStreamReader(pagina.openStream()));
            String entrada;

            while ((entrada = in.readLine()) != null) {
                this.total = this.total + entrada;
            }

            HttpClient client = new HttpClient();
            System.out.println(total);
            BufferedReader br = null;
            PostMethod method = new PostMethod(getRPC());
            method.addParameter("username", getUsuario());
            method.addParameter("password", getPassword());
            method.addParameter("Authorization", getAuthorization());
            method.setRequestHeader("Content-Type", "application/json;charset=utf-8");
            method.getDoAuthentication();
            String titulo = FechaMethod();
            method.setRequestBody("<methodCall><methodName>wp.newPost</methodName><params><!--id del blog--><param><value><string>" + getIdBlog() + "</string></value></param><!--Username de la cuenta de zenkiu--><param><value><string>" + getUsuario() + "</string></value></param><!--Password de la cuenta de zenkiu--><param><value> <string>" + getPassword() + "</string></value></param><param><value><struct><member><name>post_type</name><value><string>post</string></value></member><member><name>post_content</name><value><string><![CDATA[" + total + "]]> </string></value></member><member> <name>post_title</name><value><string>" + titulo + "</string></value></member><member><name>post_status</name><value><string>publish</string></value></member></struct></value></param></params></methodCall> ");

            try {
                returnCode = client.executeMethod(method);
                br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
                String readLine;
                String xmlresponse = "";
                while (((readLine = br.readLine()) != null)) {

                    xmlresponse = xmlresponse + readLine;
                }
                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xmlresponse)));
                NodeList errNodes = doc.getElementsByTagName("param");
                if (errNodes.getLength() > 0) {
                    Element err = (Element) errNodes.item(0);
                    ID = err.getElementsByTagName("string").item(0).getTextContent();
                }
            } catch (Exception e) {
                out.println(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ID;
    }

    public String SendMailToImolko() throws IOException {
        HttpClient client = new HttpClient();
        client.getParams().setParameter("http.useragent", "Test Client");
        BufferedReader br = null;
        PostMethod method = new PostMethod(getMethodURL());
        method.setRequestHeader("Authorization", getAuthorization());
        method.setRequestHeader("Content-Type", "application/json;charset=utf-8");
        String titulo = FechaMethod();

        method.setRequestBody("{'propietario':'" + getUsuario() + "','destinatarios':{'etiqueta':'" + getTematica() + "'},'asunto':'teleSUR ','contenido':'" + URL + "/?imktemplate=mime&subject=" + titulo + "','tipoOrden':'EMAIL','from':{'buzon':'" + getMail() + "','nombre':'" + getNombreEmail() + "','token':null},'replyTo':{'buzon':'" + getMail() + "','nombre':'teleSUR','token':null}}");

        String retval = new String();

        int returnCodes = client.executeMethod(method);
        try {

            //int statusCode = client.executeMethod(method);

            if (returnCodes != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }

            byte[] responseBody = method.getResponseBody();

            System.out.println("here : " + new String(responseBody) + "\n ");

            OutOrden = returnCodes + "here : " + new String(responseBody) + "\n  ";

        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
        } finally {

         //   method.releaseConnection();
        }

        return OutOrden;

    }

    public String AddFriendToSpanis() {
        return null;

    }

    
}
