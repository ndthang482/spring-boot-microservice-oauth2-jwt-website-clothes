package savvycom.productservice.service;

import savvycom.productservice.domain.entity.Review;

import java.util.List;

public interface IReviewService {
    Review save(Review review);

    void delete(Long id);

    List<Review> findAll();

    Review findById(Long id);

    List<Review> findReviewByProductId(Long product_id);

    List<Review> findReviewUserId(Long user_id);
}
