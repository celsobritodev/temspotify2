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

/**
 *
 * @author Usuario
 */
public class EfetivaPlaylistServlet extends HttpServlet {

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String paginaDestino = "/home.html";
        try {
         Usuario usuario = (Usuario) request.getSession().getAttribute("Usuario"); 
         if (usuario!=null) {
             String titulo = request.getParameter("txtNomePlaylist");
             PlayList p = new PlayList();
             p.setTitulo(titulo);
             p.setUsuario(usuario);
             DataSource dataSource = new DataSource();
             PlaylistDAO plDao = new PlaylistDAO(dataSource);
             plDao.create(p);
             dataSource.getConnection().close();
             if (usuario.getPlaylists()==null) {
                 usuario.setPlaylists(new ArrayList<PlayList>());
             }
             usuario.getPlaylists().add(p);
             request.getSession().setAttribute("Usuario", usuario);
             paginaDestino="/myPlaylists.jsp";
         }
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar Playlist"+ex.getMessage());
            request.setAttribute("erroSTR", "Erro grave ao criar Playlist");
            paginaDestino="/error.jsp";
        }
       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
       dispatcher.forward(request, response);
     
       
    }

 

}
