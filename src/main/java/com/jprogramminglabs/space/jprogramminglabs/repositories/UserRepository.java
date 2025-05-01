package com.jprogramminglabs.space.jprogramminglabs.repositories;

import com.jprogramminglabs.space.jprogramminglabs.schema.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CommonRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.id = :id AND u.deletedAt IS NULL")
    Optional<User> findById(Long id);

    @Query("SELECT u FROM User u WHERE u.deletedAt IS NULL")
    List<User> findAll();

    @Modifying
    @Query("UPDATE User u SET u.deletedAt = CURRENT_TIMESTAMP WHERE u.id = :id")
    void softDeleteById(Long id);}
