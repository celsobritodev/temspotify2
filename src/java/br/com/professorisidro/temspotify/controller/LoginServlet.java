/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.professorisidro.temspotify.controller;

import br.com.professorisidro.temspotify.dao.DataSource;
import br.com.professorisidro.temspotify.dao.UsuarioDAO;
import br.com.professorisidro.temspotify.model.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;



public class LoginServlet extends HttpServlet {

   
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          String email=request.getParameter("txtEmail");
          String senha=request.getParameter("txtSenha");
          Usuario incompleto = new Usuario();
          incompleto.setEmail(email);
          incompleto.setSenha(senha);
          String pagina="/error.jsp";
         
          try {
              DataSource ds = new DataSource();
              UsuarioDAO userDAO = new UsuarioDAO(ds);
              List<Object> res = userDAO.read(incompleto);
              if (res!=null && !res.isEmpty()) {
                  pagina="/myAccount.jsp";
                  request.getSession().setAttribute("Usuario",res.get(0));
              } else {
                   request.setAttribute("erroSTR","Usuario / Senha invalidos");
              }
             ds.getConnection().close();
          } catch (SQLException ex) {
              request.setAttribute("erroSTR","Erro ao recuperar");
              
          }
     
          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
          dispatcher.forward(request, response);
          
          
    }

  

}
