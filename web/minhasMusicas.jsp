<!DOCTYPE html>
<jsp:useBean id="Usuario" type="br.com.professorisidro.temspotify.model.Usuario" scope="session"/>
<jsp:useBean id="ListaMusicas" type="java.util.List" scope="request"/>
<jsp:useBean id="idPlaylist" type="java.lang.String" scope="request"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>.:  TemSpotify by TemAula!  :.</title>

        <meta name="description" content="Source code generated using layoutit.com">
        <meta name="author" content="LayoutIt!">

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">

        <script>
            function adicionar(idPlaylist, idMusica) {
                var xmlhttp = new XMLHttpRequest();
           //  alert("Recebí os valores idPlaylist = " + idPlaylist + "  idMusica " + idMusica);
            //    var pagina="oi";
                var pagina =" http://localhost:8080/TemSpotify2/incluirnaplaylist?idplaylist="+idPlaylist+"&idmusica="+idMusica;
                 xmlhttp.open("GET",pagina);
                xmlhttp.onreadystatechange = function() {
                    if (xmlhttp.status === 200 && xmlhttp.readyState === 4) {
                        alert(xmlhttp.responseText);
                    }
               };
                xmlhttp.send();        
          
         //   alert(pagina);
            }
        </script>    


    </head>
    <body>
        ${Usuario.nome}
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12" >
                    <img src="images/isidro-logo.png" class="rounded mx-auto d-block" width="150" align="center"/>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">

                    <h3 class="text-center">
                        Tem Spotify - Sua playlist na Web!
                    </h3>

                </div>  
            </div>
            <div class="row">
                <div class="col-md-12">

                    <h4 class="text-center">
                        Acervo de músicas
                    </h4>

                </div>  
            </div>


            <div class="row" id="conteudo">
                <div class="col-md-2">
                    &nbsp; 
                </div> 
                <div class="col-md-2 botao">
                    <span class="text-center"><a  href="novamusica">Upload</a></span>
                </div>
                <div class="col-md-2 botao">
                    <span class="text-center"><a href="playlists"> Playlists</a></span>
                </div><!-- comment -->
                <div class="col-md-2 botao">
                    <span class="text-center"><a href="novaplaylist">Add Playlist</a></span>
                </div><!-- comment -->
                <div class="col-md-2 botao">
                    <span class="text-center"><a href="logout">Logout</a></span>
                </div>
                <div class="col-md-2">
                    &nbsp;
                </div>
            </div>                


            <br>

            <c:forEach items="${ListaMusicas}" var="musica">
                <div class="row">
                    <div class="col-md-2">  </div>
                    <div class="col-md-1"> 
                        <button class="btn" onclick="adicionar(${idPlaylist},${musica.id})">+ </button> 
                    </div>
                    <div class="col-md-7">
                        ${musica.titulo}  (${musica.artista}) <br/>
                        <span class="artista">Album: ${musica.album} <br/></span>
                        <span class="artista">Estilo:
                            <c:if test="${musica.estilo == 1}">ROCK </c:if>
                            <c:if test="${musica.estilo == 2}">SERTANEJO / MODA DE VIOLA </c:if>
                            <c:if test="${musica.estilo == 3}">PAGODE / SAMBA </c:if>
                            <c:if test="${musica.estilo == 4}">ELETRÔNICO </c:if>
                            <c:if test="${musica.estilo == 5}">JOVEM PAN STYLE </c:if>
                            <c:if test="${musica.estilo == 6}">OUTROS </c:if>
                            </span>    
                        </div>
                        <div class="col-md-2"> &nbsp; </div>
                    </div> 
            </c:forEach>
        </div>    
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
