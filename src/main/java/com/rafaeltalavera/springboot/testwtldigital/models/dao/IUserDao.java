package com.rafaeltalavera.springboot.testwtldigital.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.rafaeltalavera.springboot.testwtldigital.models.entity.Usuario;


public interface IUserDao extends CrudRepository<Usuario, Long> {

	public Usuario findByUsername(String username);
}
