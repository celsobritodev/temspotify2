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

        <style>

            .musica {
                font-family: Verdana;
                display: block;
                font-style: italic;
                height: 45px;
                width: 100%;
                margin: 10px;
                color: black;
                padding-top: 15px;
                padding-left: 50px;
                background-color: #66ff66;
                border-radius: 5px;
                background-image: url('./images/play4.png');
                background-repeat: no-repeat;
                background-size: 40px 40px;
                background-position: left top;
            }
            
            #musicplayer {
                width: 100%;
                position: fixed;
                bottom: 0;
            }
            
            #nowPlaying {
                width: 100%;
                border-radius: 10px;
                height: 50px;
                background-color: #80ff80;
                color: black;
            }
            
            #playerContent {
                margin-bottom: 80px;
            }

        </style>


        <script type ="text/javascript">

            var musics = new Array(); // lista de musicas
            var repeat = false; // se a playlist vai ficar em modo repeat
            var currentSong = 0;
            var totalMusicas = 0;
            var URL = "http://localhost:8080/TemSpotify2/";
            var player;

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

                player = document.getElementById("musicplayer");
                // colocando ao musica inicial
                player.src = URL + musics[0];
                document.getElementById("nowPlaying").innerHTML="Now Playing: "+document.getElementById(musics[currentSong]).innerHTML;

                // funcao para quando terminar a musica
                player.onended = function () {
                    if (currentSong < musics.length - 1) {
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
                    console.log("Musica atual = " + currentSong);
                    document.getElementById("nowPlaying").innerHTML="Now Playing: "+document.getElementById(musics[currentSong]).innerHTML;
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
                console.log("tocando agora..." + objetoMusica.title);
                for (i = 0; i < musics.length; i++) {

                    if (musics[i] === objetoMusica.title) {
                        console.log("Valor de musics[i]= '" + musics[i] + "''");
                        console.log("Valor de objetoMusica= '" + objetoMusica.title + "'");
                        console.log("--------------------------------------------------");
                        currentSong = i;
                        aTocar = URL + musics[currentSong];
                        player.src = aTocar;
                        console.log("musica a tocar " + aTocar);
                        player.play();
                        document.getElementById("nowPlaying").innerHTML="Now Playing: "+document.getElementById(musics[currentSong]).innerHTML;
                    }
                }
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
                <div  id="nowPlaying" title="${music.linkMP3}" class="musica" onClick="play(this);">
                    ${music.titulo}  (${music.artista})
                </div>
            </c:forEach>
        </div>

        <div id="playerdiv">
            <div id="nowPlaying">Now Playing: </div>
            <audio id="musicplayer" controls="" controlsList="nodownload"> 
        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/scripts.js"></script>

    </body>
</html>