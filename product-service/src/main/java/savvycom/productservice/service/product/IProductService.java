package savvycom.productservice.service.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import savvycom.productservice.domain.dto.ProductResponse;
import savvycom.productservice.domain.entity.product.Product;
import savvycom.productservice.domain.dto.ProductOutput;

import java.util.List;

public interface IProductService {
    Product createProduct(ProductOutput productOutput);

    Product save(Product product);

    void delete(Long id);
    Product findById(Long id);

//    List<ProductDTO> findProductDTOById(Long id);

   List<ProductOutput> findProductByProductLine(Long productLineId);

    ProductOutput findProductOutputById(Long id);


    ProductResponse findAllResponse(int pageNo, int pageSize, String sortBy, String sortDir);

    ProductResponse findAllByProductLineIds(List<Long> productLineIds, int pageNo, int pageSize, String sortBy, String sortDir );

    ProductResponse findByColorAndSizeAndPriceBetween(String color, String size, Long priceFrom, Long priceTo
            , int pageNo, int pageSize, String sortBy, String sortDir );

}


