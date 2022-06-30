package savvycom.productservice.service.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import savvycom.productservice.domain.dto.ProductResponse;
import savvycom.productservice.domain.entity.product.Product;
import savvycom.productservice.domain.dto.ProductOutput;

import java.util.List;

public interface IProductService {
    Product save(Product product);

    void delete(Long id);
    Product findById(Long id);

   List<ProductOutput> findProductByProductLine(Long productLineId);

    ProductOutput findProductOutputById(Long id);

    ProductResponse findAllProductResponse(int pageNo, int pageSize, String sortBy, String sortDir);

    ProductResponse findByProductLineId(List<Long> productLineIds, int pageNo, int pageSize, String sortBy, String sortDir);

    ProductResponse findByColorAndSizeAndPriceBetweenAndDiscountId(String color, String size, Long priceFrom, Long priceTo
           ,Long discountId , int pageNo, int pageSize, String sortBy, String sortDir);
}


