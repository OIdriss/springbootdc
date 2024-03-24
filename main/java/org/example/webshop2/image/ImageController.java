package org.example.webshop2.image;

import org.example.webshop2.models.Image;
import org.example.webshop2.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public List<Image> getAllImages(){
        return imageService.getAllImages();
    }

    @GetMapping("/{imageID}")
    public Optional<Image> getImage(@PathVariable("imageID") Long imageId){
        return imageService.getImage(imageId);
    }

    @PostMapping
    public void addImage(@RequestBody ImageDTO imageDTO){
        imageService.addImage(imageDTO);
    }

    @PutMapping("/update/{imageID}")
    public ResponseEntity<String> updateUser(@PathVariable Long imageID, @RequestBody ImageDTO updatedImage) {
        imageService.updateImage(imageID, updatedImage);
        return ResponseEntity.ok("Updated image: " + imageID);
    }

    @DeleteMapping(path = "/delete/{imageID}")
    public void deleteUser(@PathVariable("imageID") Long imageId) {
        imageService.deleteImage(imageId);
    }
}
