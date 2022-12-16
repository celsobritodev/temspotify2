/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.professorisidro.temspotify.controller;

import br.com.professorisidro.temspotify.dao.DataSource;
import br.com.professorisidro.temspotify.dao.MusicaDAO;
import br.com.professorisidro.temspotify.model.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 *
 * @author Usuario
 */
public class RecuperaMusicasServlet extends HttpServlet {

 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paginaDestino = "/error.jsp";
        try {
           Usuario usuario = (Usuario) request.getSession().getAttribute("Usuario");
           if (usuario == null) {
               request.setAttribute("erroSTR", "Usuário não conectado");
           } else {
               DataSource dataSource = new DataSource();
               MusicaDAO mDao = new MusicaDAO(dataSource);
               List<Object> lista = mDao.read(null);
               if (lista == null ) {
                   request.setAttribute("erroSTR", "Erro ao recuperar músicas do Banco de Dados");
                           
               } else {
                   String idPlaylist = request.getParameter("idplaylist");
                   request.setAttribute("idPlaylist", idPlaylist);
                   request.setAttribute("ListaMusicas", lista);
                   paginaDestino = "/minhasMusicas.jsp";
               }
              dataSource.getConnection().close();
           }
           
        }
        catch (Exception ex) {
            System.out.println("Erro ao montar pagina de músicas "+ex.getMessage());
            request.setAttribute("erroSTR", "Erro ao montar páginas de músicas");
        } 
         RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
         dispatcher.forward(request, response);
    }
    

   
}
