/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.professorisidro.temspotify.dao;

import java.util.List;

/**
 *
 * @author Usuario
 */
public interface GenericDAO {
      public void create (Object o) ;
      public List<Object> read(Object o) ;
      public void update (Object o) ;
      public void delete (Object o);
}
