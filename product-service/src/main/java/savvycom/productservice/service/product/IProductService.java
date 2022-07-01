package savvycom.productservice.service.product;

import savvycom.productservice.domain.dto.ProductDTO;
import savvycom.productservice.domain.dto.ProductResponse;
import savvycom.productservice.domain.dto.ProductReviewResponse;
import savvycom.productservice.domain.entity.product.Product;
import savvycom.productservice.domain.dto.ProductOutput;

import java.util.List;

public interface IProductService {
    Product save(Product product);

    void deleteById(Long id);
   List<ProductOutput> findProductByProductLine(Long productLineId);

    ProductOutput findProductOutputById(Long id);

    ProductResponse findAllProductResponse(int pageNo, int pageSize, String sortBy, String sortDir);

    ProductResponse findByProductLineId(List<Long> productLineId, int pageNo, int pageSize, String sortBy, String sortDir);

    ProductResponse findByColorAndSizeAndPriceBetweenAndDiscountId(String color, String size, Long priceFrom, Long priceTo
           ,Long discountId , int pageNo, int pageSize, String sortBy, String sortDir);

//    ProductReviewResponse findProductDTOByProductLineIds(List<Long> productLineIds, int pageNo, int pageSize, String sortBy, String sortDir);
    List<ProductDTO> findListProductDTOByProductLineId(Long productLineId);


}


