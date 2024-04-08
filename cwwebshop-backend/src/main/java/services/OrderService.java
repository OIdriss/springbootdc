package services;

import org.springframework.stereotype.Service;
import repositories.OrderRepo;

@Service
public class OrderService {
    private OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }
}
