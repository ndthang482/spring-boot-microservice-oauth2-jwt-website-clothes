package savvycom.productservice.service.impl;
//@Service hold the business handling code in it

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import savvycom.productservice.domain.dto.ProductOutput;
import savvycom.productservice.domain.dto.ProductResponse;
import savvycom.productservice.domain.entity.Review;
import savvycom.productservice.domain.entity.product.Product;
import savvycom.productservice.repository.ReviewRepository;
import savvycom.productservice.service.IReviewService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService implements IReviewService {
    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> findReviewByProductId(Long productId) {
        return reviewRepository.findReviewByProductId(productId);
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }


    @Override
    public List<Review> findReviewUserId(Long userId) {
        return reviewRepository.findAll().stream()
                .filter(review -> review.getUserId() == userId)
                .collect(Collectors.toList());
    }

}
