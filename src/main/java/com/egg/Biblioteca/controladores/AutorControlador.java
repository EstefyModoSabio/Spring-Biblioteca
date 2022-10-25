/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.Biblioteca.controladores;

import com.egg.Biblioteca.excepciones.miException;
import com.egg.Biblioteca.servicios.autorServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/autor")

public class AutorControlador {
    
    @Autowired
     autorServicio autorservicio;
    
 @GetMapping("/registrar")
 public String registrar (){
 return "autor_registro.html";
 }
    
 @PostMapping("/registro")  
public String registro (@RequestParam String nombre) {

        try {
            autorservicio.crearAutor(nombre);
        } catch (miException ex) {
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "autor_registro.html";
        }
 
        return "index.html";
}   

}
