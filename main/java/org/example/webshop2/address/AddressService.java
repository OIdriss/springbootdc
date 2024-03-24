package org.example.webshop2.address;

import org.example.webshop2.models.Address;
import org.example.webshop2.models.CustomUser;
import org.example.webshop2.models.Order;
import org.example.webshop2.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;

    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Optional<Address> getAddress(Long addressID) {
        boolean addressExists = addressRepository.existsById(addressID);
        if (!addressExists) {
            throw new IllegalStateException("Address with id doesn't exist, id: "+ addressID);
        }
        return addressRepository.findById(addressID);
    }

    public void addAddress(AddressDTO addressDTO) {
        Optional<CustomUser> userOptional = userRepository.findById(addressDTO.userID());
        if (userOptional.isPresent()) {
            CustomUser customUser = userOptional.get();
            Address address = new Address(addressDTO.street(), addressDTO.housenumber(), addressDTO.zipCode(), addressDTO.city(), addressDTO.country(), customUser);
            addressRepository.save(address);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public void deleteAddress(Long addressID) {
        boolean userExists = addressRepository.existsById(addressID);
        if (!userExists) {
            throw new IllegalStateException("address with id doesn't exist, id: "+ addressID);
        }
        addressRepository.deleteById(addressID);
    }

    public void updateAddress(Long addressID, AddressDTO updatedAddress) {
        boolean addressExists = addressRepository.existsById(addressID);
        if (!addressExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        } else {
            Address address = addressRepository.getById(addressID);
            address.setStreet(updatedAddress.street());
            address.setCity(updatedAddress.city());
            address.setHousenumber(updatedAddress.housenumber());
            address.setCountry(updatedAddress.country());
            address.setZipCode(updatedAddress.zipCode());

            addressRepository.save(address);
        }
    }
}
