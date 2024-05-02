package com.userManagement.springcrud.repository;

import com.userManagement.springcrud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByAppName(String appName);

    boolean existsByappKey(String appKey);

    boolean existsByappName(String appName);

    boolean existsByname(String name);

    User findById(int id);

}
