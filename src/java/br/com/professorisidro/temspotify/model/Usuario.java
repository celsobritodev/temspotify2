/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.professorisidro.temspotify.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Usuario implements  Serializable{
    private int id;
    private String nome;
    private String email;
    private String senha;
    private List<PlayList> playlists;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the playlists
     */
    public List<PlayList> getPlaylists() {
        return playlists;
    }

    /**
     * @param playlists the playlists to set
     */
    public void setPlaylists(List<PlayList> playlists) {
        this.playlists = playlists;
    }
    
   @Override 
   public String toString() {
       return id + " . "+ nome +" . "+email;
   } 
    
}
