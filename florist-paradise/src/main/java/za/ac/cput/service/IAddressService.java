package za.ac.cput.service;

import za.ac.cput.domain.Address;
import za.ac.cput.domain.AddressId;

import java.util.List;

public interface IAddressService extends IService<Address, AddressId> {
    List<Address> getall();
}
