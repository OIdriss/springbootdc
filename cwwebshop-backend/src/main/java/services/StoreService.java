package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.StoreRepo;

@Service
public class StoreService {
    private StoreRepo storeRepo;

    @Autowired
    public StoreService(StoreRepo storeRepo) {
        this.storeRepo = storeRepo;
    }
}
