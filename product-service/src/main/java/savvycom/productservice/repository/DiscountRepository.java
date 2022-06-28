package savvycom.productservice.repository;
// @Repo access database, class click database.

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savvycom.productservice.domain.entity.Discount;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    Optional<Discount> findById(Long id);

//    @Query(value = "SELECT * FROM discount WHERE discount_percent BETWEEN 0 AND 30", nativeQuery = true)
//    List<Discount> findByDiscountUnder30(String discountPercent);
//    @Query(value = "SELECT * FROM discount WHERE discount_percent BETWEEN 30 AND 50", nativeQuery = true)
//    List<Discount> findByDiscount30to50(String discountPercent);
//    @Query(value = "SELECT * FROM discount WHERE discount_percent BETWEEN 60 AND 99", nativeQuery = true)
//    List<Discount> findByDiscountAbove50(String discountPercent);
}
