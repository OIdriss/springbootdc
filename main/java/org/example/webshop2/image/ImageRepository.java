package org.example.webshop2.image;

import org.example.webshop2.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query("SELECT i FROM Image i WHERE i.imageURL = ?1")
    Optional<Image> findImageByUrl(String imageUrl);
}
