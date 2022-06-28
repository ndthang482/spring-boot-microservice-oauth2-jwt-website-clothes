package savvycom.productservice.service;

import savvycom.productservice.domain.entity.Address;

import java.util.List;

public interface IAddressService {
    Address save(Address address);

    void delete(Long id);

    List<Address> findAll();

    Address findById(Long id);
}
