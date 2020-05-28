package com.cristianvera.login.models.service;

import com.cristianvera.login.models.dao.IUsuarioDao;
import com.cristianvera.login.models.entity.Rol;
import com.cristianvera.login.models.entity.SpringUser;
import com.cristianvera.login.models.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

    private final IUsuarioDao usuarioDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public JpaUserDetailsService(IUsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);
        if(usuario == null) {
            logger.error("Error login el usuario " + username + " no existe");
            throw new UsernameNotFoundException("Usuario no existe");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Rol rol: usuario.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(rol.getRol()));
        }

        SpringUser user = new SpringUser(username, usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities, usuario.getNombre(), usuario.getApellidos(), usuario.getFechaCreacion());

        return user;
    }
}
