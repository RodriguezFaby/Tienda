/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tienda.controller;

import com.Tienda.domain.Categoria;
/*import com.Tienda.domain.Pruebas;*/
import com.Tienda.service.CategoriaService;
import com.Tienda.service.ProductoService;
/*import com.Tienda.service.PruebasService;*/
import com.Tienda.service.impl.FirebaseStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Fabiola
 */
@Controller
@RequestMapping("/pruebas") //mapping a nuestro controller
public class PruebasController {
    
    @Autowired
    private ProductoService productoService; //ya puedo utilizar los metodos de producto service
    
    
    @Autowired
    private CategoriaService categoriaService;
    
    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    
    @GetMapping("/listado") //vistas 
    public String listado(Model model) {
        var productos = productoService.getProductos(false);
        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("productos", productos);
        model.addAttribute("totalPruebass", productos.size());
        model.addAttribute("categorias", categorias);
        
        return "/pruebas/listado";
    }
    
    
    @GetMapping("/listado/{idCategoria}") //vistas 
    public String listado(Model model, Categoria categoria) {
        var productos = categoriaService.getCategoria(categoria).getProductos();
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("productos", productos);
        model.addAttribute("totalPruebass", productos.size());
        model.addAttribute("categorias", categorias);
        
        return "/pruebas/listado";
    }
}
    