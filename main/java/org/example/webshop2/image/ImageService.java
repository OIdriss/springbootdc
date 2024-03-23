package org.example.webshop2.image;

import org.example.webshop2.models.Image;
import org.example.webshop2.models.Product;
import org.example.webshop2.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository, ProductRepository productRepository) {
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public Optional<Image> getImage(Long imageId) {
        boolean imageExists = imageRepository.existsById(imageId);
        if (!imageExists){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,"image niet gevonden"
            );
        }
        return imageRepository.findById(imageId);
    }

    public void addImage(ImageDTO imageDTO) {
        Optional<Product> productOptional = productRepository.findById(imageDTO.productID());
        if (productOptional.isPresent()){
            Product product = productOptional.get();
            Image image = new Image(imageDTO.imageURL(), product);
            Optional<Image> optionalImage = imageRepository.findImageByUrl(image.getImageURL());
            if (optionalImage.isPresent()) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT, "Image is already present"
                );
            }
            imageRepository.save(image);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product bestaat niet");
        }
    }

    public void updateImage(Long imageID, ImageDTO imageDTO) {
        boolean imageExists = imageRepository.existsById(imageID);
        if (!imageExists) {
            throw new IllegalStateException("image with id doesn't exist, id: "+ imageID);
        }
        else {
            Image image = imageRepository.getById(imageID);
            if (isNotValidUpdate(imageDTO)) {
                throw new IllegalStateException("Url already present");
            }
            image.setImageURL(imageDTO.imageURL());
            imageRepository.save(image);
        }
    }

    public boolean isNotValidUpdate(ImageDTO imageDTO) {
        Optional<Image> imageOptional = imageRepository.findImageByUrl(imageDTO.imageURL());
        return imageOptional.isPresent();
    }

    public void deleteImage(Long imageId) {
        boolean imageExists = imageRepository.existsById(imageId);
        if (!imageExists) {
            throw new IllegalStateException("image with id doesn't exist, id: "+ imageId);
        }
        imageRepository.deleteById(imageId);
    }
}
