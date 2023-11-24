/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tienda.domain;

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
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data //Para la creacion automatica de los sets y gets de los atributos
@Entity //esta clase se va a manejar como entidad
@Table(name = "usuario") //mapeo con la tabla categoria
public class Usuario implements Serializable{
    
    private static final long serialVersionUID = 1L;
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ES EL ID y es auto incrmental
    @Column(name = "id_usuario") //cuando se le cambia el nombre al atributo
    private Long idUsuario;
    
    //no puede estar vacio
    @NotEmpty
     private String username;
    
    @NotEmpty
     private String password;
    
    @NotEmpty
     private String nombre;
    
    @NotEmpty
     private String apellido;
    
     private String correo;
     private String telefono;
     private String rutaImagen;
     private boolean activo;
     
      @OneToMany
    @JoinColumn(name="id_usuario")
      private List<Rol> roles;
}
