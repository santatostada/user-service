package com.santatostada.database.repository;

import com.santatostada.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends JpaRepository <User, Long> {
    User findById(int id);

    @Modifying
    @Transactional
    @Query(value = "update users_db set status = ?2 where id = ?1", nativeQuery = true)
    void updateUser(int id, String status);



}
