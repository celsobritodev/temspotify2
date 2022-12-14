/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.professorisidro.temspotify.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;


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
        if (request.getSession().getAttribute("Usuario")!=null) {
            try {
                
                Collection<Part> partes =request.getParts();
                for(Part p: partes) {
                    String partName=p.getName();
                    System.out.println("Formulario contem "+partName);
                }
                
                
                String artista = request.getParameter("txtArtista");
                String album = request.getParameter("txtAlbum");
                String titulo = request.getParameter("txtNomeMusica");
                String estiloStr = request.getParameter("txtEstilo");
                int estilo = Integer.parseInt(estiloStr);
                
                FileOutputStream arquivoMP3;
                try (InputStream arqOriginal = request.getPart("fileMP3").getInputStream()) {
                    String nomeArquivo= getServletContext().getRealPath("/")
                            +"/"+"musicas"+"/"+request.getPart("fileMP3").getSubmittedFileName();
                    System.out.println("Nome do arquivo "+nomeArquivo);
                    arquivoMP3 = new FileOutputStream(nomeArquivo);
                    byte b[] = new byte[1024];
                    while (arqOriginal.available()>0) {
                        arqOriginal.read(b);
                        arquivoMP3.write(b);
                    }
                }
                arquivoMP3.close();
             
            } catch (ServletException | IOException ex) {
               request.setAttribute("erroSTR", "ERRO: Upload falhou!");
            }
            
            
        } else {
            request.setAttribute("erroSTR", "Erro: Usuario não conectado");
        }
   }

   
}
