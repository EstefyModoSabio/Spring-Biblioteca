/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.Biblioteca.repositorios;

import com.egg.Biblioteca.entidades.libros;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author usuario
 */
@Repository
public interface libroRepositorio extends JpaRepository<libros,Long>{
    
    @Query ("SELECT l FROM libros l WHERE l.titulo= :titulo ")
    public libros buscarPorTitulo(@Param("titulo")String titulo);
    
    @Query ("SELECT l FROM libros l WHERE l.autor.nombre =:nombre")
public List <libros> buscarPorAutor(@Param("nombre") String nombre);
    
    }
