package com.dream.thoughts.springmultipledatasourcedemo.Repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dream.thoughts.springmultipledatasourcedemo.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
