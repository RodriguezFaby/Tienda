
package com.Tienda.dao;

//Creacion de Categoria DAO
//es un interface, que permiten exponen metodos que pueden ser utilizados por alguien 

import com.Tienda.domain.Categoria;//se ocupa llamar a Categoria porque esta en diferentes paquetes
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriaDao extends JpaRepository<Categoria, Long> { //para que utilize metodos de hibernate
    
    
}
