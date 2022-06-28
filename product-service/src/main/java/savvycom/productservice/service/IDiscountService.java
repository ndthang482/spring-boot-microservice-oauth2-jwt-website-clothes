package savvycom.productservice.service;

import savvycom.productservice.domain.entity.Discount;

import java.util.List;

public interface IDiscountService {
    Discount save(Discount discount);

    void delete(Long id);

    List<Discount> findAll();

    Discount findById(Long id);

}
