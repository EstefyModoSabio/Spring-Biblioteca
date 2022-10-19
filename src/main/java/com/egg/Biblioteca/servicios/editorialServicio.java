/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.Biblioteca.servicios;

import com.egg.Biblioteca.entidades.editorial;
import com.egg.Biblioteca.excepciones.miException;
import com.egg.Biblioteca.repositorios.editorialRepositorio;
import java.util.ArrayList;
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
public class editorialServicio {
    
    @Autowired
    editorialRepositorio eRepositorio;
    
    @Transactional
    public void crearEditorial(String nombre)throws miException{
        
        validar(nombre);
    
    editorial editorial=new editorial();
    
    editorial.setNombre(nombre);
    
    eRepositorio.save(editorial);
            
    }
    
    public List <editorial> listarEditoriales() {
    
    List <editorial> editoriales=new ArrayList();
    editoriales= eRepositorio.findAll();
    return editoriales;
}
    
    public void modificarEditorial(String id,String nombre)throws miException {
        
        validar( nombre);

        Optional<editorial> respuesta = eRepositorio.findById(id);

        if (respuesta.isPresent()) {

            editorial editorial = respuesta.get();
            editorial.setNombre(nombre);

            eRepositorio.save(editorial);
        }

    }
    
    private void validar(String nombre) throws miException{
    
        if (nombre==null||nombre.isEmpty()){
        
            throw new miException ("El nombre de la editorial no puede estar vacio");
        }
                
    }
    
}
