package org.example.webshop2.address;

import org.example.webshop2.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

        @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<Address> getAllAddresses(){
        return addressService.getAllAddresses();
    }

    @GetMapping(path = "/{addressID}")
    public Optional<Address> getAddress(@PathVariable("addressID") Long addressID) {
        return addressService.getAddress(addressID);
    }

    @PostMapping
    public void addAddress(@RequestBody Address address) {
        addressService.addAddress(address);
    }

    @DeleteMapping(path = "/delete/{addressID}")
    public void deleteAddress(@PathVariable("addressID") Long addressID) {
        addressService.deleteAddress(addressID);
    }

    @PutMapping("/update/{addressID}")
    public ResponseEntity<String> updateAddress(@PathVariable Long addressID,
                                                @RequestBody AddressDTO updatedAddress) {
        addressService.updateAddress(addressID, updatedAddress);
        return ResponseEntity.ok("Updated address:" + addressID);
    }
}
