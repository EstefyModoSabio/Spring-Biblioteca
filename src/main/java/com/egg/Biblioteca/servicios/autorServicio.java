/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.Biblioteca.servicios;

import com.egg.Biblioteca.entidades.autor;
import com.egg.Biblioteca.repositorios.autorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egg.Biblioteca.excepciones.miException;

/**
 *
 * @author usuario
 */
@Service
public class autorServicio {

    @Autowired
    autorRepositorio aRepositorio;

    @Transactional
    public void crearAutor(String nombre)throws miException {
        
         validar(nombre);
        
        autor autor = new autor();
        autor.setNombre(nombre);

        aRepositorio.save(autor);

    }

    public List<autor> listarAutores() {

        List<autor> autores = new ArrayList();

        autores = aRepositorio.findAll();

        return autores;
    }

    public void modificarAutor(String id,String nombre)throws miException {

        validar(nombre);
        
        Optional<autor> respuesta = aRepositorio.findById(id);

        if (respuesta.isPresent()) {

            autor autor = respuesta.get();
            autor.setNombre(nombre);

            aRepositorio.save(autor);
        }

    }

    private void validar(String nombre) throws miException{
        
        if(nombre.isEmpty()||nombre==null){
        throw new miException ("El nombre del autor no puede estar vacio");
        }
      
    }
    
}
