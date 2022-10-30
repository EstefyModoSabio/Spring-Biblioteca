/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.Biblioteca.controladores;

import com.egg.Biblioteca.excepciones.miException;
import com.egg.Biblioteca.servicios.editorialServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author usuario
 */
@Controller
@RequestMapping("/editorial")

public class EditorialControlador {

    @Autowired
    editorialServicio editorialservicio;

    @GetMapping("/registrar")
    public String registrar() {
        return "editorial_registro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombreEditorial, ModelMap modelo) {

        try {
            editorialservicio.crearEditorial(nombreEditorial);
            modelo.put("exito", "La editorial fue cargada con exito");
        } catch (miException ex) {
            Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            return "editorial_registro.html";
        }

        return "index.html";
    }

}
