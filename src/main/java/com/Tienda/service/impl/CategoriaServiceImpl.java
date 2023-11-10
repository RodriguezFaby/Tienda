
package com.Tienda.service.impl;
//Sirve para llevar a cabo las implementaciones de la interfaces de Servicio

import com.Tienda.dao.CategoriaDao;
import com.Tienda.domain.Categoria;
import com.Tienda.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service //Siempre se llama en impl, porque esta es la implementacion de CategoriaService

//Esto implementa los metods que tiene CategoriaService (tiene que implementar todos los metodos en este Service
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired //para usar la interfaz Dao
    private CategoriaDao categoriaDao;
    
    
    @Override
    @Transactional(readOnly = true) //ponerlo en todos los metodos de lectura-asegura que todas las trasacciones se haga de una vez
    public List<Categoria> getCategorias(boolean activos) {
        List<Categoria> categorias = categoriaDao.findAll();//trae todos los registros de base de datos
        //evaluar si el user quiere solo las activas
        if(activos) {
            categorias.removeIf(c -> !c.isActivo());//remover elementos de la lista donde activo sea falso
        }
        return categorias;
    }

    @Override
    public Categoria getCategoria(Categoria categoria) {
        return categoriaDao.findById(categoria.getIdCategoria()).orElse(null); //si no trae respuesta, si no encuentra nada, retorne un null

    }
    
    @Override
    @Transactional
    public void save(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    @Override
    @Transactional
    public void delete(Categoria categoria) {
        categoriaDao.delete(categoria);
    }
    
}
