package com.cristianvera.login.models.dao;

import com.cristianvera.login.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}
