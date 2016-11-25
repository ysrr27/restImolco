<%-- 
    Document   : index
    Created on : 19/01/2015, 01:08:28 PM
    Author     : jalfonzo
--%>

<%@page import="RestClase.*" %>
<%--hjhg--%>
<%

    String idioma = request.getParameter("idioma");
    ClienteRest rest = new ClienteRest();
    MailClass correo = new MailClass();
    rest.setCuenta(idioma);
    correo.setIdioma(idioma);

    //PARA CREAR EL CONTENIDO EN EL AURORA
    String id = rest.CreateContentImolko();

    rest.setID(id);
    //OBTENGO LA URL DEL NUEVO CONTENIDO QUE ACABO DE CREAR
    String url = rest.getURLtoSend();
    rest.setURL(url);
    
    //REALIZO EL ENVÍO Y OBTENGO RESPUESTA EN ESTATUS HTTP
    String ordenID = rest.SendMailToImolko();

    //SETEO EL RESPONSE DE IMOLKO PARA ENVIARLA POR CORREO  
    correo.setId(ordenID);
    String respuesta = ordenID;
    String Rspta = respuesta.substring(0, 3);
    String RspOK = "201";
    
    
    //SETEO LA RESPUESTA EN ESTATUS HTTP PARA ENVIARLA POR CORREO
    correo.setRespuesta(Rspta);

    //SI LA RESPUESTA ES 201, SE CREÓ BIEN, SINÓ HUBO UN ERROR
    if (Rspta.equals(RspOK)) {
        out.println("Contenido enviado correctamente");
        out.println(Rspta);
        out.println(ordenID);
        correo.SendAlertToMail();
    } else {
        correo.SendAlertToMail();
        out.print("Error!");

    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1></h1>
    </body>
</html>
