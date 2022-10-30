/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.Biblioteca.servicios;

import com.egg.Biblioteca.entidades.autor;
import com.egg.Biblioteca.entidades.editorial;
import com.egg.Biblioteca.entidades.libros;
import com.egg.Biblioteca.excepciones.miException;
import com.egg.Biblioteca.repositorios.autorRepositorio;
import com.egg.Biblioteca.repositorios.editorialRepositorio;
import com.egg.Biblioteca.repositorios.libroRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author usuario
 */
@Service
public class libroServicio {
    
    @Autowired
    private editorialRepositorio eRepositorio;
    
    @Autowired
    private autorRepositorio aRepositorio;
    
    @Autowired
    private libroRepositorio lRepositorio;
    
    @Transactional
    public void crearLibro(Long isbn, String titulo,Integer ejemplares,String idAutor, String idEditorial ) throws miException{
        
        validar(isbn,titulo,idAutor,idEditorial,ejemplares);
        
        autor autor=aRepositorio.findById(idAutor).get();
        editorial editorial=eRepositorio.findById(idEditorial).get();
        libros libro=new libros();
        libro.setIsbn(isbn);
        libro.setEjemplares(ejemplares);
        libro.setTitulo(titulo);
        libro.setAlta(new Date());
        
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        lRepositorio.save(libro);
    }
    
    public List <libros> listarLibros() {
    
    List <libros> libros=new ArrayList();
    libros= lRepositorio.findAll();
    return libros;
}
    
    @Transactional
    public void modificarLibro (Long isbn,String titulo,String idAutor,String idEditorial, Integer ejemplares)throws miException{
    
        validar(isbn,titulo,idAutor,idEditorial,ejemplares);
        
        Optional<libros> respuesta = lRepositorio.findById(isbn);
        Optional <autor> respuestaAutor=aRepositorio.findById(idAutor);
        Optional <editorial> respuestaEditorial=eRepositorio.findById(idEditorial);
        
        autor autor=new autor();
        editorial editorial=new editorial();
        
        if (respuestaAutor.isPresent()){
       
            autor=respuestaAutor.get();
        }
        
        if (respuestaEditorial.isPresent()){
            
        editorial=respuestaEditorial.get();
        
        }
        
        if (respuesta.isPresent()){
        
            libros libro= respuesta.get();
            
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);
            
            lRepositorio.save(libro);
           
        }
        
    }
    
    private void validar (Long isbn,String titulo,String idAutor,String idEditorial, Integer ejemplares)throws miException{
    
          if (isbn==null){
            
        throw new miException ("El isbn no puede ser nulo");
                
        }
        
        if (ejemplares==null){
            
        throw new miException ("Los ejemplares no pueden ser nulo");
                
        }
        
        if (idAutor==null||idAutor.isEmpty()){
            
        throw new miException ("Los ejemplares no pueden ser nulo");
                
        }
        
         if (idEditorial==null||idEditorial.isEmpty()){
            
        throw new miException ("Los ejemplares no pueden ser nulo");
                
        }
        
        
        if (titulo.isEmpty()||titulo==null){
        throw new miException ("El titulo no puede estar vacio");
        }
            
    }
}
