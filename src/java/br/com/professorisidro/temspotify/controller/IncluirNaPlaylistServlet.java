/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.professorisidro.temspotify.controller;

import br.com.professorisidro.temspotify.dao.DataSource;
import br.com.professorisidro.temspotify.dao.PlaylistDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class IncluirNaPlaylistServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paginaResultado = "/result.jsp";
        DataSource dataSource = null;
        try {
            int idPlaylist = Integer.parseInt(request.getParameter("idplaylist"));
            int idMusica = Integer.parseInt(request.getParameter("idmusica"));
            dataSource = new DataSource();
            PlaylistDAO plDAO = new PlaylistDAO(dataSource);
            if (plDAO.createMusicaPlaylist(idPlaylist, idMusica)) {
                request.setAttribute("strRESULT", "OK");
            }
            dataSource.getConnection().close();
        } catch (NumberFormatException | SQLException ex) {
            if (dataSource != null) {
                try {
                    dataSource.getConnection().close();
                } catch (SQLException ex1) {
                    System.out.println("Não fechei a conexão");
                }
            }
            System.out.println("Erro ao inserir " + ex.getMessage());
            request.setAttribute("strRESULT", "ERRO  ");
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaResultado);
        dispatcher.forward(request, response);

    }

}
