package savvycom.productservice.service.product;

import savvycom.productservice.domain.dto.ProductLineDTO;
import savvycom.productservice.domain.entity.product.ProductLine;

import java.util.List;
import java.util.Optional;

public interface IProductLineService {
    ProductLine save(ProductLine productLine);

    void delete(Long id);

    List<ProductLine> findAll();

   ProductLine findById(Long id);
   ProductLineDTO findDetailById(Long id);

    List<ProductLine> findByCategoryId(Long categoryId);

    List<ProductLine> findByNameLike(String name);

}
