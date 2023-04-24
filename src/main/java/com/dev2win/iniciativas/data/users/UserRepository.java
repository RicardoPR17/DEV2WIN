package com.dev2win.iniciativas.data.users;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String userName);
    Optional<User> findByMail(String userMail);
    @Query("SELECT u FROM User u WHERE u.profile=:profile")
    List<User> findByProfile(@Param("profile") String profile);

    @Query("SELECT u FROM User u WHERE u.role=:role")
    List<User> findByRole(@Param("role") String role);

}
