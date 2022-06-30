package savvycom.productservice.service.product;

import savvycom.productservice.domain.entity.product.ProductLine;

import java.util.List;

public interface IProductLineService {
    ProductLine save(ProductLine productLine);

    void delete(Long id);

    List<ProductLine> findAll();

    ProductLine findById(Long id);

    List<ProductLine> findByCategoryId(Long categoryId);

    List<ProductLine> findByNameLike(String name);

}
