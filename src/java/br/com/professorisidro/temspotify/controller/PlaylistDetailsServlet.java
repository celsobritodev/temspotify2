/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.professorisidro.temspotify.controller;

import br.com.professorisidro.temspotify.dao.DataSource;
import br.com.professorisidro.temspotify.dao.PlaylistDAO;
import br.com.professorisidro.temspotify.model.PlayList;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Usuario
 */
public class PlaylistDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paginaDestino = "/error.jsp";
        if (request.getSession().getAttribute("Usuario") != null) {
            try {
                DataSource dataSource = new DataSource();
                PlaylistDAO plDAO = new PlaylistDAO(dataSource);
                int id = Integer.parseInt(request.getParameter("id"));
                PlayList playlist = plDAO.readPlaylistDetailsById(id);
                if (playlist != null) {
                     request.getSession().setAttribute("PlayList",playlist);
                     paginaDestino = "/playlistDetails.jsp";
                } else {
                    request.setAttribute("erroSTR", "Erro ao recuperar Playlist!");
                }
            } catch (Exception ex) {
                request.setAttribute("erroSTR", "Erro inesperado!");
            }
        } else {
            request.setAttribute("erroSTR", "Você não está conectado!");
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);

    }
}
