package com.Tienda.service.impl;

import com.Tienda.dao.ProductoDao;
import com.Tienda.domain.Producto;
import com.Tienda.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements ProductoService{
    @Autowired //para usar la interfaz Dao
    private ProductoDao productoDao;
    
    
    @Override
    @Transactional(readOnly = true) //ponerlo en todos los metodos de lectura-asegura que todas las trasacciones se haga de una vez
    public List<Producto> getProductos(boolean activos) {
        var lista=productoDao.findAll();
        
        if(activos) {lista.removeIf(p -> !p.isActivo());//remover elementos de la lista donde activo sea falso
        }
        return lista;
    }

    @Override
    @Transactional(readOnly=true)
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null); //si no trae respuesta, si no encuentra nada, retorne un null

    }
    
    @Override
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void delete(Producto producto) {
        productoDao.delete(producto);
    }
}
