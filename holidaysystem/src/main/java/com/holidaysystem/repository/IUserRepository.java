package com.holidaysystem.repository;

import java.util.UUID;
import java.util.List;
import com.holidaysystem.entity.User;

public interface IUserRepository {
	
	User findById(UUID userId);
    
    User findByEmail(String email);
    
    boolean save(User user);
    
    List<User> getUsers();
}
