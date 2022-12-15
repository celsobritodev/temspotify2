<!DOCTYPE html>
<jsp:useBean id="Usuario" type="br.com.professorisidro.temspotify.model.Usuario" scope="session"/>
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
                <div class="col-md-12">
                    <p id="creditos" align ="right">Developed by Professor Isidro Students </p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12" >
                    <img src="images/isidro-logo.png" class="rounded mx-auto d-block" width="150" align="center"/>
                </div>

            </div>
            <div class="col-md-12">

                <h3 class="text-center">
                    Tem Spotify - Sua playlist na Web!
                </h3>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <h4 class="text-center">
                    Crie sua playlist ${Usuario.nome}
                </h4>    
            </div>
            
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-2">
                    </div>
                    <div class="col-md-8">
                        <form  action="efetivaplaylist" method="post">
                            
                            <div class="form-group">
                               <label for="playlistName">
                                    Nome da Playlist
                                </label>
                                 <input type="text" class="form-control" id="playlistName" name="txtNomePlaylist">
                            </div> 
                            
                          
                            <button type="submit" class="btn btn-primary">
                                Criar Playlist
                            </button>
                            
                        </form>
                    </div>
                    <div class="col-md-2">
                    </div>
                </div>
            </div>
        </div>
    

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
</body>
</html>
