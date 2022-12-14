/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.professorisidro.temspotify.controller;

import br.com.professorisidro.temspotify.dao.DataSource;
import br.com.professorisidro.temspotify.dao.MusicaDAO;
import br.com.professorisidro.temspotify.model.Musica;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Celso
 */
public class UploadMusicaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Passei por aqui!");

        String paginaDestino = "/error.jsp";
        if (request.getSession().getAttribute("Usuario") != null) {
            try {

                Collection<Part> partes = request.getParts();
                for (Part p : partes) {
                    String partName = p.getName();
                    System.out.println("Formulario contem " + partName);
                }

                String artista = request.getParameter("txtArtista");
                String album = request.getParameter("txtAlbum");
                String titulo = request.getParameter("txtNomeMusica");
                String estiloStr = request.getParameter("txtEstilo");
                int estilo = Integer.parseInt(estiloStr);

                FileOutputStream arquivoMP3;
                try ( InputStream arqOriginal = request.getPart("fileMP3").getInputStream()) {
                    String nomeArquivoOriginal = request.getPart("fileMP3").getSubmittedFileName();
                    String nomeArquivo = getServletContext().getRealPath("/")
                            + "musicas" + "\\" + nomeArquivoOriginal;
                    System.out.println("Nome do arquivo " + nomeArquivo);
                    arquivoMP3 = new FileOutputStream(nomeArquivo);
                    byte b[] = new byte[1024];
                    while (arqOriginal.available() > 0) {
                        arqOriginal.read(b);
                        arquivoMP3.write(b);
                    }
                    arqOriginal.close();
                    arquivoMP3.close();

                    Musica musica = new Musica();

                    musica.setArtista(artista);
                    musica.setTitulo(titulo);
                    musica.setAlbum(album);
                    musica.setEstilo(estilo);
                    musica.setLinkMP3("musicas/" + nomeArquivoOriginal);

                    DataSource dataSource = new DataSource();
                    MusicaDAO musicaDao = new MusicaDAO(dataSource);
                    musicaDao.create(musica);
                    dataSource.getConnection().close();
                    paginaDestino = "/myAccount.jsp";
                } catch (SQLException ex) {
                    Logger.getLogger(UploadMusicaServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (ServletException | IOException ex) {
                request.setAttribute("erroSTR", " Upload falhou!");
                paginaDestino = "/error.jsp";
            }

        } else {
            request.setAttribute("erroSTR", "Usuario n??o conectado");
            paginaDestino = "/error.jsp";
        }
         RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }

    
    
    
}
