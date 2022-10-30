/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.Biblioteca.controladores;

import com.egg.Biblioteca.entidades.autor;
import com.egg.Biblioteca.entidades.editorial;
import com.egg.Biblioteca.excepciones.miException;
import com.egg.Biblioteca.servicios.autorServicio;
import com.egg.Biblioteca.servicios.editorialServicio;
import com.egg.Biblioteca.servicios.libroServicio;
import java.util.List;
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
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    libroServicio libroservicio;
    @Autowired
    autorServicio autorservicio;
    @Autowired
    editorialServicio editorialservicio;

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {

        List<autor> autores = autorservicio.listarAutores();
        List<editorial> editoriales = editorialservicio.listarEditoriales();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);

        return "libro_registro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbn,
            @RequestParam String titulo,
            @RequestParam(required = false) Integer ejemplares,
            @RequestParam String idAutor,
            @RequestParam String idEditorial, ModelMap modelo) {

        try {
            libroservicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            modelo.put("exito", "El libro fue cargado correctamente");

        } catch (miException ex) {

            modelo.put("error", ex.getMessage());
            return ("libro_registro.html");
        }

        return "index.html";

    }

}
