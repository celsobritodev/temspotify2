/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.professorisidro.temspotify.dao;

import br.com.professorisidro.temspotify.model.Usuario;
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
public class UsuarioDAO implements GenericDAO {

    private final DataSource dataSource;

    public UsuarioDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(Object o) {
        try {
            if (o instanceof Usuario) {
                Usuario usuario = (Usuario) o;
                String SQL = "INSERT INTO tblUsuario VALUES (null,?,?,?)";
                PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                stm.setString(1, usuario.getNome());
                stm.setString(2, usuario.getEmail());
                stm.setString(3, usuario.getSenha());
                int res = stm.executeUpdate();
                if (res != 0) {
                    ResultSet rs = stm.getGeneratedKeys();
                    if (rs.next()) {
                        usuario.setId(rs.getInt(1));
                    }
                    rs.close();
                }
                stm.close();
            } else {
                throw new RuntimeException("Invalid User Model Object");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir usuario " + ex.getMessage());
        }

    }

    @Override
    public List<Object> read(Object o) {
        try {
            if (o instanceof Usuario) {
                Usuario incompleto = (Usuario) o;
                String SQL = "SELECT * FROM tblUsuario WHERE email =? AND senha = ?";
                ResultSet rs;
                ArrayList<Object> result;
                try ( PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL)) {
                    stm.setString(1, incompleto.getEmail());
                    stm.setString(2, incompleto.getSenha());
                    rs = stm.executeQuery();
                    result = new ArrayList<>();
                    if (rs.next()) {
                        Usuario usuario = new Usuario();
                        usuario.setId(rs.getInt("idUsuario"));
                        usuario.setNome(rs.getString("nome"));
                        usuario.setEmail(rs.getString("email"));
                        usuario.setSenha(rs.getString("senha"));
                        result.add(usuario);
                    }
                }
                rs.close();
                return result;

            } else {
                throw new RuntimeException("Invalid Object");

            }
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar usuario " + ex.getMessage());
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
