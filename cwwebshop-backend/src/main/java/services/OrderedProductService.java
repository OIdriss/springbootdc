package services;

import org.springframework.stereotype.Service;
import repositories.OrderedProductRepo;

@Service
public class OrderedProductService {
    OrderedProductRepo orderedProductRepo;

    public OrderedProductService(OrderedProductRepo orderedProductRepo) {
        this.orderedProductRepo = orderedProductRepo;
    }
}
