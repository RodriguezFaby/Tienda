
package com.Tienda.service;
//Todas las interfaces de servicios
//sirve para EXPONER los metods que pueden utilizar para interactuar con las diferentes categorias

import com.Tienda.domain.Categoria;
import java.util.List;

//es un tipo menu

public interface CategoriaService {
    
    // Se obtiene un listado de categorias en un List
    //firma de un metodo que devuelve una lista de categoria, y recibe un parametro llamado activo de tipo boolean
    public List<Categoria> getCategorias(boolean activos);
    
    // Se obtiene un Categoria, a partir del id de un categoria
    public Categoria getCategoria(Categoria categoria); //me devuleve una sola categoria
    
    // Se inserta un nuevo categoria si el id del categoria esta vacío
    // Se actualiza un categoria si el id del categoria NO esta vacío
    public void save(Categoria categoria);
    
    // Se elimina el categoria que tiene el id pasado por parámetro
    public void delete(Categoria categoria);
}
