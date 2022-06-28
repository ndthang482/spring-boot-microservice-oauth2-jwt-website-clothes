package savvycom.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import savvycom.productservice.domain.entity.Review;
import savvycom.productservice.repository.ReviewRepository;
import savvycom.productservice.service.IReviewService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController extends BaseController{
    private IReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewController(IReviewService ReviewService) {
        this.reviewService = ReviewService;
    }

    //find all review

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return successResponse(reviewService.findAll());
    }

    //pos: create review

    @PostMapping("")
    public ResponseEntity<?> newReview(@RequestBody Review review){
        return successResponse(reviewService.save(review));
    }

    //find id by review

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return successResponse(reviewService.findById(id));
    }

    //put: update review
    @PutMapping("{id}")
    public ResponseEntity<?> updateReview(@RequestBody Review review){
        return successResponse(reviewService.save(review));
    }

    //find all product by review

    @GetMapping("/product/{id}")
    public ResponseEntity<?> findReviewByProductId(@PathVariable("id") Long id){
        return successResponse(reviewService.findReviewByProductId(id));
    }

    //find all user by review

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findReviewByUserId(@PathVariable("id") Long id){
        return successResponse(reviewService.findReviewUserId(id));
    }

}
