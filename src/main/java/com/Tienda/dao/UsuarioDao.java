/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Tienda.dao;

import com.Tienda.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Fabiola
 */
public interface UsuarioDao extends JpaRepository<Usuario, Long>{
    /*me devuelve el primer usuario que encuentra*/
    Usuario findByUsername(String username);
    
}
