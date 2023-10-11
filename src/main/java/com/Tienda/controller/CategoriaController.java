
package com.Tienda.controller;

import com.Tienda.domain.Categoria;
import com.Tienda.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/categoria") //mapping a nuestro controller
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService; //ya puedo utilizar los metodos de categoria service
    
    @GetMapping("/listado") //vistas 
    public String inicio(Model model) {
        
        List<Categoria>listadoCategorias = categoriaService.getCategorias(false); //me muestre todas las categorias
        model.addAttribute("categorias", listadoCategorias);
                         //nombre del atributo(ID), EL DATO
        model.addAttribute("totalCategorias", listadoCategorias.size());
        
        return "/categoria/listado";
    }
    
}
