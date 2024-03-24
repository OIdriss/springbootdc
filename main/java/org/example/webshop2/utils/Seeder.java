package org.example.webshop2.utils;

import org.example.webshop2.address.AddressRepository;
import org.example.webshop2.image.ImageRepository;
import org.example.webshop2.models.*;
import org.example.webshop2.order.OrderRepository;
import org.example.webshop2.orderedproduct.OrderedProductRepository;
import org.example.webshop2.product.ProductRepository;
import org.example.webshop2.user.UserRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;


@Component
public class Seeder {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderedProductRepository orderedProductRepository;
    private final ImageRepository imageRepository;

    public Seeder(AddressRepository addressRepository, UserRepository userRepository, OrderRepository orderRepository, ProductRepository productRepository, OrderedProductRepository orderedProductRepository, ImageRepository imageRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderedProductRepository = orderedProductRepository;
        this.imageRepository = imageRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {

        CustomUser customUser1 = new CustomUser("omar", "id", "@mail.com", "xxxxx");
        userRepository.save(customUser1);
        CustomUser customUser2 = new CustomUser("Doaa", "id", "doaa@mail.com", "xxxxx");
        userRepository.save(customUser2);
        Address address1 = new Address("adres","adres","adres","adres","adres", customUser1);
        addressRepository.save(address1);
        Address address = new Address("adres1","adres1","adres1","adres1","adres1", customUser2);
        addressRepository.save(address);
        Order order = new Order(customUser1, "verzonden", LocalDate.of(1, Month.JANUARY, 20));
        orderRepository.save(order);
        Order order1 = new Order(customUser2, "pending", LocalDate.of(1999, Month.JANUARY, 25));
        orderRepository.save(order1);

        Product product = new Product("ss","n","d","ca", 10,LocalDate.of(1, Month.JANUARY, 20) , customUser1,10, true);
        productRepository.save(product);
        Product product1 = new Product("SKJU#)))","broek","d","ca", 10,LocalDate.of(1999, Month.JANUARY, 20) , customUser1,10, true);
        productRepository.save(product1);

        Image image = new Image("url", product);
        imageRepository.save(image);
        Image image1 = new Image("url2", product);
        imageRepository.save(image1);
        OrderedProduct orderedProduct = new OrderedProduct(product, order, 10);
        orderedProductRepository.save(orderedProduct);
        OrderedProduct orderedProduct1 = new OrderedProduct(product, order, 7);
        orderedProductRepository.save(orderedProduct1);
    }
}
