
package com.Tienda.domain;
//Creacion de la entidad Categoria

//creacion de anotacion Data y Entity que empieza con @
//las anotaciones siempre van a la par de un metodo o clase, sin dejar lineas innecasrios

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data //Para la creacion automatica de los sets y gets de los atributos
@Entity //esta clase se va a manejar como entidad
@Table(name = "categoria") //mapeo con la tabla categoria
public class Categoria implements Serializable{  //serializacion es convertir un objeto en algo mas que se puede almacenar en la BD
    
    private static final long serialVersionUID = 1L;//version de serializacion
    //en esta entidad la asociamos con la tabla de categoria en la BD, debe tener los mismo atributos
    //Atributos
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ES EL ID y es auto incrmental
    @Column(name = "id_categoria") //cuando se le cambia el nombre al atributo
    private Long idCategoria; //en camelcase porque hibernate lo transforma en id_categoria luego
    private String descripcion;
    private String rutaImagen;
    private boolean activo;
    
    @OneToMany
    @JoinColumn(name="id_categoria", insertable = false, updatable = false)
    List<Producto>productos;
    
    //constructor vacio
    public Categoria (){
        
    }

    //No se agrega el ID categoria 
    public Categoria(String descripcion, String rutaImagen, boolean activo) {
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
        this.activo = activo;
    }
    
    
    
}
