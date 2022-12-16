/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.professorisidro.temspotify.dao;

import br.com.professorisidro.temspotify.model.Musica;
import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class MusicaDAO implements GenericDAO {

    private DataSource dataSource;

    public MusicaDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(Object o) {
        try {
            if (o instanceof Musica) {
                Musica musica = (Musica) o;
                String SQL = "INSERT INTO  tblMusica VALUES  (null,?,?,?,?,?)";
                try ( PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL)) {
                    stm.setString(1, musica.getTitulo());
                    stm.setString(2, musica.getArtista());
                    stm.setString(3, musica.getAlbum());
                    stm.setInt(4, musica.getEstilo());
                    stm.setString(5, musica.getLinkMP3());
                    stm.executeUpdate();
                }

            }
        } catch (SQLException ex) {
            System.out.println("Erro:  " + ex.getMessage());
        }
    }

    @Override
    public List<Object> read(Object o) {
        try {
            String SQL = "Select * from tblMusica order by titulo";
            PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL);
            ResultSet rs = stm.executeQuery();
            List<Object> lista = new ArrayList<>();
            while (rs.next()) {
                Musica musica = new Musica();
                musica.setId(rs.getInt("IdMusica"));
                musica.setTitulo(rs.getString("titulo"));
                musica.setArtista(rs.getString("artista"));
                musica.setAlbum(rs.getString("album"));
                musica.setEstilo(rs.getInt("estilo"));
                musica.setLinkMP3(rs.getString("linkMP3"));
                lista.add(musica);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar acervo de musicas " + ex.getMessage());
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

}
