package com.dev2win.iniciativas.data.users;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String userName);

    @Query("SELECT * FROM sys.user WHERE profile=:profile")
    List<User> findByProfile(String profile);

}
