package savvycom.productservice.repository.product;
// @Repo access database, class click database.

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import savvycom.productservice.domain.dto.ProductDTO;
import savvycom.productservice.domain.entity.product.Product;
import savvycom.productservice.domain.entity.product.ProductLine;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);

    Page<Product> findByProductLineIdIn(List<Long> productLineId, Pageable pageable);

    Page<Product> findByColorAndSizeAndPriceBetweenAndDiscountId(String color, String size,
    Long priceFrom, Long priceTo,Long discountId, Pageable pageable);

    List<Product> findByProductLineId(Long productLineId);

    Page<Product> findProductByColor(String color, Pageable pageable);

    Page<Product> findProductBySize(String size, Pageable pageable);

    Page<Product> findProductByDiscountId(Long discountId, Pageable pageable);

    Page<Product> findByColorAndSize(String color, String size, Pageable pageable);

    @Query(value = "select * from product where price between 1000000 and 3000000 order by price asc", nativeQuery = true)
    Page<Product> findByPriceBetween(Long priceFrom, Long priceTo, Pageable pageable);
}
