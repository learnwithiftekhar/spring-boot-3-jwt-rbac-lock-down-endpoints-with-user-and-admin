package com.learnwithiftekhar.spring_jwt_demo.ropository;

import com.learnwithiftekhar.spring_jwt_demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
