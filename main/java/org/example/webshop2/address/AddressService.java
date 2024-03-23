package org.example.webshop2.address;

import org.example.webshop2.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Optional<Address> getAddress(Long addressID) {
        boolean addressExists = addressRepository.existsById(addressID);
        if (!addressExists) {
            throw new IllegalStateException("student with id doesn't exist, id: "+ addressID);
        }
        return addressRepository.findById(addressID);
    }

    public void addAddress(Address address) {

    }

    public void deleteAddress(Long addressID) {
        boolean userExists = addressRepository.existsById(addressID);
        if (!userExists) {
            throw new IllegalStateException("student with id doesn't exist, id: "+ addressID);
        }
        addressRepository.deleteById(addressID);
    }

    public void updateAddress(Long addressID, AddressDTO updatedAddress) {

    }
}
