package repositories;

import models.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedProductRepo extends JpaRepository<OrderedProduct, Long> {
}
