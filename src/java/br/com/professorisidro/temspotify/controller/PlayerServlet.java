/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.professorisidro.temspotify.controller;

import br.com.professorisidro.temspotify.model.PlayList;
import br.com.professorisidro.temspotify.model.Usuario;
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
public class PlayerServlet extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String paginaDestino="/error.jsp";
       Usuario usuario = (Usuario) request.getSession().getAttribute("Usuario");
       if (usuario!=null) {
           PlayList playList = (PlayList) request.getSession().getAttribute("PlayList");
           if (playList!=null) {
               paginaDestino="/player.jsp";
               
           } else {
               request.setAttribute("erroSTR", "Playlist não encontrada");
           }
       } else {
           request.setAttribute("erroSTR", "Usuário não conectado!");
       }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);    
    }

}
