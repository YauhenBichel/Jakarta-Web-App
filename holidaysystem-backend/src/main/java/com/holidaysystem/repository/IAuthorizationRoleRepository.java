package com.holidaysystem.repository;

import java.util.List;
import java.util.UUID;

import com.holidaysystem.entity.AuthorizationRoleEntity;

/**
 * Interface for AuthorizationRole repository, which can be supported by
 * all implementations of the interface
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public interface IAuthorizationRoleRepository {
	
	List<AuthorizationRoleEntity> getAll();
	AuthorizationRoleEntity findById(UUID id);
	AuthorizationRoleEntity findByName(String name);
}
