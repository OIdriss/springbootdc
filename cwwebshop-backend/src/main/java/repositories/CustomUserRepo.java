package repositories;

import models.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepo extends JpaRepository<CustomUser, Long> {
}
