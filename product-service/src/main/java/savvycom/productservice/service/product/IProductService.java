package savvycom.productservice.service.product;

import org.springframework.data.domain.PageImpl;
import savvycom.productservice.domain.dto.ProductDTO;
import savvycom.productservice.domain.entity.product.Product;
import savvycom.productservice.domain.dto.ProductOutput;

import java.util.List;

public interface IProductService {
    Product save(Product product);

    void deleteById(Long id);
    ProductOutput findProductOutputById(Long id);

    PageImpl<?> findAllProductResponse(int pageNo, int pageSize, String sortBy, String sortDir);

    PageImpl<?> findByProductLineId(List<Long> productLineId, int pageNo, int pageSize, String sortBy, String sortDir);

    List<ProductDTO> findListProductDTOByProductLineId(Long productLineId);
    PageImpl<?> findByColorAndSizeAndPriceBetweenAndDiscountId(String color, String size, Long priceFrom, Long priceTo
            , Long discountId , int pageNo, int pageSize, String sortBy, String sortDir);
    PageImpl<?> findProductByColorAndPriceBetween(String color,Long priceFrom, Long priceTo
             , int pageNo, int pageSize, String sortBy, String sortDir);
    PageImpl<?> findProductBySizeAndPriceBetween(String size,Long priceFrom, Long priceTo
            , int pageNo, int pageSize, String sortBy, String sortDir);
    PageImpl<?> findProductByDiscountIdAndPriceBetween(Long discountId
            , Long priceFrom, Long priceTo, int pageNo, int pageSize, String sortBy, String sortDir);
    PageImpl<?> findByColorAndSizeAndPriceBetween(String color, String size, Long priceFrom, Long priceTo
            , int pageNo, int pageSize, String sortBy, String sortDir);
    PageImpl<?> findByColorAndDiscountIdAndPriceBetween(String color
            , Long priceFrom, Long priceTo, Long discountId , int pageNo, int pageSize, String sortBy, String sortDir);
    PageImpl<?> findBySizeAndDiscountIdAndPriceBetween(String size,
           Long priceFrom, Long priceTo , Long discountId , int pageNo, int pageSize, String sortBy, String sortDir);
    List<ProductOutput> findProductOutput(Long id);

}


