package savvycom.productservice.repository.product;
// @Repo access database, class click database.

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import savvycom.productservice.domain.entity.product.Product;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);

    Page<Product> findByProductLineIdIn(List<Long> productLineIds, Pageable pageable);

    Page<Product> findByColorAndSizeAndPriceBetweenAndDiscountId(String color, String size, Long priceFrom, Long priceTo,Long discountId, Pageable pageable);

}
