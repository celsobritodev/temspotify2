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

        <script type ="text/javascript">

            var musics = new Array(); // lista de musicas
            var repeat = false; // se a playlist vai ficar em modo repeat
            var currentSong = 0;
            var totalMusicas = 0;
            var URL = "http://localhost:8080/TemSpotify2/";

            function setupPlayer() {
                var divMusicas = document.getElementById("playerContent");
                var filhos = divMusicas.childNodes;
                for (i = 0; i < filhos.length; i++) {
                    if (filhos[i].nodeName === "DIV") {
                        musics.push(filhos[i].title);
                        totalMusicas++;
                        //console.log(filhos[i].title);
                    }
                }
                console.log(musics);
                console.log(repeat);
                console.log(totalMusicas);
                
                var player = document.getElementById("musicplayer");
                // colocando ao musica inicial
                player.src = URL + musics[0];
                
                // funcao para quando terminar a musica
                player.onended = function () {
                    if (currentSong <musics.length ) {
                        currentSong = currentSong + 1;
                        player.src = URL + musics[currentSong];
                        player.play();
                    } else {
                        if (repeat) {
                            console.log("estou em modo repeat");
                            currentSong = 0;
                            player.src = URL + musics[currentSong];
                            player.play();
                        } else {
                            alert("Fim das músicas");
                        }
                    }
                  console.log("Musica atual = "+currentSong);
                };

            }

            function changeRepeat() {
                repeat = !repeat;
                if (repeat) {
                    document.getElementById("imgRepeat").src = "images/repeat_green.png";
                } else
                {
                    document.getElementById("imgRepeat").src = "images/repeat_gray.png";
                }

            }




            function play(objetoMusica) {
                console.log("Vai tocar a musica");
            }

        </script>    

    </head>
    <body onload="setupPlayer();">
        ${Usuario.nome}

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12" >
                    <a href="playlists"><img src="images/isidro-logo.png" class="rounded mx-auto d-block" width="150" align="center"/></a>
                    <img id="imgRepeat" src="images/repeat_gray.png" class="roundex mx-auto d-block" width="55" onclick="changeRepeat()" align="center">
                </div>
            </div>
        </div>
        <div id="playerContent">
            <c:forEach var="music"  items="${PlayList.musicas}">
                <div title="${music.linkMP3}" class="musica" onClick="play(this);">
                    ${music.titulo}  (${music.artista})
                </div>
            </c:forEach>
        </div>

        <div>
            <audio id="musicplayer" controls controlsList="nodownload"> </audio>
        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/scripts.js"></script>

    </body>
</html>