package services;

import org.springframework.stereotype.Service;
import repositories.CustomUserRepo;

@Service
public class CustomUserService {
    CustomUserRepo customUserRepo;

    public CustomUserService(CustomUserRepo customUserRepo) {
        this.customUserRepo = customUserRepo;
    }
}
