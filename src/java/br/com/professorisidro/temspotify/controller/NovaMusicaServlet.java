/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.professorisidro.temspotify.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 *
 * @author Celso
 */
public class NovaMusicaServlet extends HttpServlet {

  
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paginaDestino = "/erro.jsp";
        if (request.getSession().getAttribute("Usuario")!=null) {
            paginaDestino="/novaMusica.jsp";
        } else {
            request.setAttribute("erroSTR", " Usuario não conectado!");
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
        
   
    }

   

}
