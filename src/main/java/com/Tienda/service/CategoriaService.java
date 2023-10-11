
package com.Tienda.service;
//Todas las interfaces de servicios
//sirve para EXPONER los metods que pueden utilizar para interactuar con las diferentes categorias

import com.Tienda.domain.Categoria;
import java.util.List;

//es un tipo menu

public interface CategoriaService {
    
    //firma de un metodo que devuelve una lista de categoria, y recibe un parametro llamado activo de tipo boolean
    public List<Categoria> getCategorias(boolean activos);
    
    public Categoria getCategoria(Categoria categoria); //me devuleve una sola categoria
}
