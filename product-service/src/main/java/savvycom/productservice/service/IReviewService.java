package savvycom.productservice.service;

import savvycom.productservice.domain.entity.Review;

import java.util.List;

public interface IReviewService {
    Review save(Review review);

    void delete(Long id);

    List<Review> findAll();
    
    List<Review> findReviewUserId(Long userId);

    Review findReviewByProductId(Long productId);

    Review findById(Long id);
}
