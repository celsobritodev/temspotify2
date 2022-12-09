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

/**
 *
 * @author Celso
 */
public class EfetivaCadastroServlet extends HttpServlet {

  

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pagina = "/myAccount.jsp";
        String nome = request.getParameter("txtNome");
        String email = request.getParameter("txtEmail");
        String senha = request.getParameter("txtSenha");
        
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        
        DataSource dataSource = new DataSource();
        UsuarioDAO usuarioDAO = new UsuarioDAO(dataSource);
        usuarioDAO.create(usuario);
        System.out.println(usuario);
        try {
            dataSource.getConnection().close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar conexao "+ex.getMessage());
            request.setAttribute("erroSTR","Erro ao criar nova conta de usuario");
            pagina="/error.jsp";
        }
         
        if (usuario.getId()!=0) {
            request.getSession().setAttribute("Usuario",usuario);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
        dispatcher.forward(request, response);
        
    }

  
  
}
