/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.professorisidro.temspotify.controller;

import br.com.professorisidro.temspotify.dao.DataSource;
import br.com.professorisidro.temspotify.dao.PlaylistDAO;
import br.com.professorisidro.temspotify.model.PlayList;
import br.com.professorisidro.temspotify.model.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */

public class PlaylistServlet extends HttpServlet {

 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String paginaDestino = "/myPlaylists.jsp";
        try {
            Usuario usuario = (Usuario) request.getSession().getAttribute("Usuario");
            System.out.println("Logado "+usuario.getNome());
            if (usuario != null) {
                if (usuario.getPlaylists() == null) {
                    DataSource dataSource = new DataSource();
                    PlaylistDAO plDAO = new PlaylistDAO(dataSource);
                    List<Object> lista = plDAO.read(usuario.getId());
                    dataSource.getConnection().close();
                    
                    System.out.println("Recuperei valores!");
                    
                    if (lista != null) {
                        ArrayList<PlayList> myPlaylists = new ArrayList<>();
                        for (Object o : lista) {
                            PlayList novaPl = (PlayList) o;
                            novaPl.setUsuario(usuario);
                            myPlaylists.add(novaPl);
                        }
                        usuario.setPlaylists(myPlaylists);

                    }
                }
                request.getSession().setAttribute("Usuario", usuario);
                paginaDestino = "/myPlaylists.jsp";
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar Playlists " + ex.getMessage());
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }

}
