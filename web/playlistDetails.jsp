<!DOCTYPE html>
<jsp:useBean id="Usuario" type="br.com.professorisidro.temspotify.model.Usuario" scope="session"/>
<jsp:useBean id="PlayList" type="br.com.professorisidro.temspotify.model.PlayList" scope="session"/>
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
                        Detalhes da Playlist
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
                    <span class="text-center"><a href="playlists">Playlists </a></span>
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
            <div class="row">
                <div class="col-md-2">&nbsp;</div>
                <div class="col-md-8">
                    <h4> ${PlayList.titulo} <a href="player"> <img  id="imgplay"  src="images//play4.png" alt="Tocal Playlist" title="Tocar Playlist" > </a> </h4> 
                </div> 
                <div class="col-md-2">&nbsp;</div>
            </div>
             <br>   
            <div class="row">
                <div class="col-md-2">&nbsp;</div>
                <div class="col-md-8">
                    <h5> <a href="recuperamusicas?idplaylist=${PlayList.id}"> + Adicionar m?sicas </a> </h5> 
                </div>  
                <div class="col-md-2">&nbsp;</div>
            </div>
           
            <c:forEach items="${PlayList.musicas}" var = "Musica">
                <div class="row">
                    <div class="col-md-2">&nbsp;</div>
                    <div class="col-md-8">
                        <span class="tituloMusica">
                            ${Musica.titulo}
                        </span> 
                        <span class="artista">
                            ${Musica.artista} (Album: ${Musica.album})}
                        </span> 
                    </div>
                    <div class="col-md-2">&nbsp;</div>
                </div>
            </c:forEach>

        </div>    
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>