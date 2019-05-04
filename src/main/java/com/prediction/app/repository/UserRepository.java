package com.prediction.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prediction.app.model.User;

/**
 * @author Shaju K
 *
 */

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUsername(String username);
	
}
