package utils;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import repositories.*;

@Component
public class Seeder {
    private OrderRepo orderRepo;
    private CustomUserRepo customUserRepo;
    private OrderedProductRepo orderedProductRepo;
    private ProductRepo productRepo;
    private StoreRepo storeRepo;

    public Seeder(OrderRepo orderRepo,
                  CustomUserRepo customUserRepo,
                  OrderedProductRepo orderedProductRepo,
                  ProductRepo productRepo,
                  StoreRepo storeRepo) {
        this.orderRepo = orderRepo;
        this.customUserRepo = customUserRepo;
        this.orderedProductRepo = orderedProductRepo;
        this.productRepo = productRepo;
        this.storeRepo = storeRepo;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {

    }
}



