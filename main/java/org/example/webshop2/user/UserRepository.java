package org.example.webshop2.user;

import org.example.webshop2.models.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Long> {
    @Query("SELECT u FROM CustomUser u where u.emailAddress = ?1")
    Optional<CustomUser> findUserByEmail(String emailAddress);

    public CustomUser findByEmailAddress(String email);
}
