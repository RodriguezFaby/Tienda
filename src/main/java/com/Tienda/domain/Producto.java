package com.Tienda.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

@Data 
@Entity 
@Table(name = "producto") 
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id_producto") 
    private Long idProducto; 
    private Long idCategoria;
    private String descripcion;
    private String detalle;
    private double precio;
    private int existencias;
    private String rutaImagen;
    private boolean activo;
    

    public Producto() {
    }

    public Producto(Long idCategoria, String descripcion, String detalle, double precio, int existencias, String rutaImagen, boolean activo) {
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
        this.detalle = detalle;
        this.precio = precio;
        this.existencias = existencias;
        this.rutaImagen = rutaImagen;
        this.activo = activo;
    }

    
    
    
    
}
