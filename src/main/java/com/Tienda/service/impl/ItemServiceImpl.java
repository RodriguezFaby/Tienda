
package com.Tienda.service.impl;

import com.Tienda.dao.FacturaDao;
import com.Tienda.dao.ProductoDao;
import com.Tienda.dao.VentaDao;
import com.Tienda.domain.Factura;
import com.Tienda.domain.Item;
import com.Tienda.domain.Producto;
import com.Tienda.domain.Usuario;
import com.Tienda.domain.Venta;
import com.Tienda.service.ItemService;
import static com.Tienda.service.ItemService.listaItems;
import com.Tienda.service.UsuarioService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public List<Item> gets() {
        return listaItems;
    }

    //Se usa en el addCarrito... agrega un elemento
    @Override
    public void save(Item item) {
        boolean existe = false;
        for (Item i : listaItems) {
            //Busca si ya existe el producto en el carrito
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                //Valida si aún puede colocar un item adicional -segun existencias-
                if (i.getCantidad() < item.getExistencias()) { 
                    //Incrementa en 1 la cantidad de elementos
                    i.setCantidad(i.getCantidad() + 1);
                }
                existe = true;
                break;
            }
        }
        if (!existe) {//Si no está el producto en el carrito se agrega cantidad =1.            
            item.setCantidad(1);
            listaItems.add(item);
        }
    }

    //Se usa para eliminar un producto del carrito
    @Override
    public void delete(Item item) {
        var posicion = -1;
        var existe = false;
        for (Item i : listaItems) { //recorro la lista
            ++posicion; //posicion le suma 1
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) { //el item es igual al item encontrado en la lista
                existe = true;
                break;
            }
        }
        if (existe) {
            listaItems.remove(posicion); //remueve el item de la lista
        }
    }

    //Se obtiene la información de un producto del carrito... para modificarlo
    @Override
    public Item get(Item item) {
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                return i;
            }
        }
        return null;
    }

    //Se usa en la página para actualizar la cantidad de productos
    @Override
    public void actualiza(Item item) {
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                i.setCantidad(item.getCantidad());
                break;
            }
        }
    }

    @Autowired
    private UsuarioService uuarioService;

    @Autowired
    private FacturaDao facturaDao;
    @Autowired
    private VentaDao ventaDao;
    @Autowired
    private ProductoDao productoDao;

    @Override
    public void facturar() {
        System.out.println("Facturando");

        //Se obtiene el usuario autenticado
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }

        if (username.isBlank()) {
            return;
        }

        Usuario uuario = uuarioService.getUsuarioPorUsername(username);

        if (uuario == null) {
            return;
        }
        
        Factura factura = new Factura(uuario.getIdUsuario());
        factura = facturaDao.save(factura);

        double total = 0;
        for (Item i : listaItems) {//por cada item
            System.out.println("Producto: " + i.getDescripcion()  //lo mostramos en consola
                    + " Cantidad: " + i.getCantidad()
                    + " Total: " + i.getPrecio() * i.getCantidad());
            Venta venta = new Venta(factura.getIdFactura(), i.getIdProducto(), i.getPrecio(), i.getCantidad()); //objeto tipo venta, donde le pasamos idfactura,idproducto,precio y cantidad
            ventaDao.save(venta); //y lo guardamos
            Producto producto = productoDao.getReferenceById(i.getIdProducto());//creo objeto de tipo producto, y consulto en productoDao con el ID
            producto.setExistencias(producto.getExistencias()-i.getCantidad()); //ajusto la cantidad de existencias
            productoDao.save(producto);
            total += i.getPrecio() * i.getCantidad(); 
        }
        factura.setTotal(total);
        facturaDao.save(factura);
        listaItems.clear();
    }
}

