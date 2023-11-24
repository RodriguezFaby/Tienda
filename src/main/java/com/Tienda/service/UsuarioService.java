
package com.Tienda.service;

import com.Tienda.domain.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UsuarioService {
    
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException; //si este metodo falla, lanza un excepcion de tipo unsername not found
    
    //retorne un user
    public Usuario getUsuarioPorUsername(String username);

}
