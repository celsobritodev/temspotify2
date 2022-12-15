/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.professorisidro.temspotify.dao;

import br.com.professorisidro.temspotify.model.Musica;
import br.com.professorisidro.temspotify.model.PlayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class PlaylistDAO implements GenericDAO {

    private final DataSource dataSource;

    public PlaylistDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(Object o) {
        try {
            PlayList pl = (PlayList) o;
            String SQL = "INSERT INTO tblPlaylist VALUES (null,?,?)";
            PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, pl.getTitulo());
            stm.setInt(2, pl.getUsuario().getId());
            int res = stm.executeUpdate();
            if (res == 0) {
                throw new RuntimeException("Não foi possivel incluir playlist!");
            }
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                pl.setId(rs.getInt(1));
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar Playlist" + ex.getMessage());
        }

    }

    @Override
    public List<Object> read(Object o) {
        try {
            String SQL = "SELECT * FROM tblPlaylist WHERE idUsuario=?";
            Integer idUser = (Integer) o;
            PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL);
            stm.setInt(1, idUser);
            ResultSet rs = stm.executeQuery();
            ArrayList<Object> list = new ArrayList<>();
            while (rs.next()) {
                PlayList pl = new PlayList();
                pl.setId(rs.getInt("IdPlaylist"));
                pl.setTitulo(rs.getString("titulo"));
                list.add(pl);
            }
            rs.close();
            stm.close();
            return list;

        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar Playlists " + ex.getMessage());
        }
        return null;
    }

    @Override
    public void update(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public PlayList readPlaylistDetailsById(int id) {
        PlayList playlist = null;
        try {
            String SQL = "SELECT tblPlaylist.idPlaylist as idPlaylist, "
                    + "       tblPlaylist.idUsuario as idUsuario,"
                    + "       tblMusica.idMusica as idMusica,"
                    + "       tblPlaylist.titulo as pl_titulo, "
                    + "       tblMusica.titulo as mu_titulo,"
                    + "       tblMusica.artista as artista,"
                    + "       tblMusica.album as album,"
                    + "       tblMusica.estilo as estilo,"
                    + "       tblMusica.linkMP3 AS linkMP3"
                    + "  "
                    + "  FROM tblplaylist"
                    + "  left outer join tblmusicaplaylist using (idPlaylist) "
                    + "  left outer join tblMusica using (idMusica) "
                    + "  where idPlaylist = ?;";
            
            PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            rs.next();

            do {
                if (playlist == null) {
                    playlist = new PlayList();
                    playlist.setMusicas(new ArrayList<Musica>());
                    playlist.setId(rs.getInt("idPlaylist"));
                    playlist.setTitulo(rs.getString("pl_titulo"));
                }
                if (rs.getString("mu_titulo") != null) {
                    Musica musica = new Musica();
                    musica.setId(rs.getInt("idMusica"));
                    musica.setTitulo(rs.getString("mu_titulo"));
                    musica.setArtista(rs.getString("artista"));
                    musica.setEstilo(rs.getInt("estilo"));
                    musica.setAlbum(rs.getString("album"));
                    musica.setLinkMP3(rs.getString("linkMP3"));
                    playlist.getMusicas().add(musica);
                }
            } while (rs.next());
            
            return playlist;
            
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar detalhes da Playlist " + ex.getMessage());
        }
        return null;
    }
}
